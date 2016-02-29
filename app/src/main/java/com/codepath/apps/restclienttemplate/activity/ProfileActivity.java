package com.codepath.apps.restclienttemplate.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.fragment.UserTweetsFragment;
import com.codepath.apps.restclienttemplate.models.UserProfile;
import com.codepath.apps.restclienttemplate.network.TwitterClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.apache.http.Header;
import org.json.JSONObject;

import java.lang.reflect.Type;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity {

    @Bind(R.id.ivProfileBg)
    ImageView profilebackground;
    @Bind(R.id.ivProfileDp)
    ImageView profilePicture;

    @Bind(R.id.tvUserName)
    TextView userName;

    @Bind(R.id.tvScreenName)
    TextView screenName;

    @Bind(R.id.tvTagline)
    TextView tagline;

    @Bind(R.id.followingCount)
    TextView followingCount;

    @Bind(R.id.followersCount)
    TextView followersCount;

    @Bind(R.id.timelineFMLayout)
    FrameLayout flTimeline;

    JsonHttpResponseHandler responseHandler;
    JsonHttpResponseHandler bannerHandler;
    TwitterClient twitterClient;

    private static String LOG_TAG = ProfileActivity.class.toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        //Response handler
        responseHandler = new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d(LOG_TAG, response.toString());
                Type collectionType = new TypeToken<UserProfile>() {
                }.getType();
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                UserProfile userProfile = gson.fromJson(response.toString(), collectionType);
                showProfileData(userProfile);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e(LOG_TAG, "Error while API call");
                throwable.printStackTrace();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.e(LOG_TAG, "Error while API call");
                throwable.printStackTrace();
            }
        };

//        bannerHandler = new JsonHttpResponseHandler() {
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                Log.d(LOG_TAG, response.toString());
//                Type collectionType = new TypeToken<Banner>() {
//                }.getType();
//                GsonBuilder gsonBuilder = new GsonBuilder();
//                Gson gson = gsonBuilder.create();
//                Banner banner = gson.fromJson(response.toString(), collectionType);
//                showBanner(banner);
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                Log.e(LOG_TAG, "Error while API call");
//                throwable.printStackTrace();
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//                Log.e(LOG_TAG, "Error while API call");
//                throwable.printStackTrace();
//            }
//        };

        String userScreenName = "", userName = "";

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userScreenName = extras.getString("UserScreenName");
            userName = extras.getString("UserName");
        }
        setTitle(userName);


        UserTweetsFragment fg = UserTweetsFragment.newInstance(userScreenName, "");

        twitterClient = new TwitterClient(this);
        twitterClient.getUserProfile(responseHandler, userScreenName);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.timelineFMLayout, fg).commit();
    }

    private void showProfileData (UserProfile profile) {
        userName.setText(profile.getName());
        screenName.setText("@"+profile.getScreenName());
        tagline.setText(profile.getDescription());
        followingCount.setText(profile.getFriendsCount()+ " Following");
        followersCount.setText(profile.getFollowersCount()+ " Followers");
        Picasso.with(this).load(profile.getProfileImageUrl()).into(profilePicture);
        Picasso.with(this).load(profile.getProfileBannerUrl()).into(profilebackground);
    }
}
