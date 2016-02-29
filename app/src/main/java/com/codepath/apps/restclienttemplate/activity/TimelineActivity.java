package com.codepath.apps.restclienttemplate.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.adapter.TwitterFragmentPagerAdapter;
import com.codepath.apps.restclienttemplate.fragment.PostTweetFragment;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.apps.restclienttemplate.models.User;
import com.codepath.apps.restclienttemplate.network.TwitterClient;
import com.codepath.apps.restclienttemplate.utils.Global;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by skammila on 2/18/16.
 */
public class TimelineActivity extends AppCompatActivity {

    private static String LOG_TAG = TimelineActivity.class.toString();

    List<Tweet> tweets = new ArrayList<Tweet>();
    TwitterClient twitterClient;
//    TimelineAdapter adapter;
    JsonHttpResponseHandler responseHandler;
    JsonHttpResponseHandler postTweetResponseHandler;
    TwitterFragmentPagerAdapter frgPageAdp;
    ViewPager viewPager;
    //    @Bind(R.id.svContainer)
//    SwipeRefreshLayout svContainer;
//
//    @Bind(R.id.rvTweets)
//    RecyclerView rvTweets;
    User loginUser;

    String tweetMaxId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_timeline);
        ButterKnife.bind(this);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        frgPageAdp = new TwitterFragmentPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(frgPageAdp);

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        ActiveAndroid.initialize(this);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.faTweetBtn);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
//                PostTweetFragment fr = PostTweetFragment.newInstance(null, null);
//                fr.show(fm, "post_tweet");
//            }
//        });
//
//        RecyclerView.ItemDecoration itemDecoration = new
//                DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
//        rvTweets.addItemDecoration(itemDecoration);
////
//        rvTweets.setItemAnimator(new SlideInUpAnimator());
//
//        svContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                if (isNetworkAvailable()) {
//                    //reload timeline
//                    tweets.clear();
//                    adapter.notifyDataSetChanged();
//                    tweetMaxId = null;
//                    twitterClient.getTimelineActivity(responseHandler, tweetMaxId);
//                } else {
//                    showetworkErrorToast();
//                }
//            }
//        });
//
        responseHandler = new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d(LOG_TAG, response.toString());
                Type collectionType = new TypeToken<User>() {
                }.getType();
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                loginUser = gson.fromJson(response.toString(), collectionType);

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
        twitterClient = new TwitterClient(this);
        twitterClient.getLoginUser(responseHandler);
//
        postTweetResponseHandler = new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("DEBUG", response.toString());
//                GsonBuilder gsonBuilder = new GsonBuilder();
//                Gson gson = gsonBuilder.create();
//                Tweet tweet = gson.fromJson(response.toString(), Tweet.class);
//                tweets.add(0, tweet);
//                adapter.notifyItemInserted(0);
//                tweetMaxId = tweet.getIdStr();
                viewPager.setCurrentItem(0);

                frgPageAdp.tlFm.refresh();

                //reload the timeline
//                tweets.clear();
//                adapter.notifyDataSetChanged();
//                tweetMaxId = null;
//                loadTimelineActivity();
//                twitterClient.loadTimelineActivity(responseHandler, tweetMaxId);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e("ERROR", "Error while API call" + responseString);
                throwable.printStackTrace();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.e("ERROR", "Error while API call" + errorResponse.toString());
                throwable.printStackTrace();
            }
        };
//
//        twitterClient = new TwitterClient(this);
//
//        // Create adapter
//        adapter = new TimelineAdapter(tweets);
//        // Attach the adapter to the recyclerview
//        rvTweets.setAdapter(adapter);
//        // Set layout manager to position the items
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        rvTweets.setLayoutManager(linearLayoutManager);
//        // That's all!
//        rvTweets.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
//            @Override
//            public void onLoadMore(int page, int totalItemsCount) {
//                // Triggered only when new data needs to be appended to the list
//                // Add whatever code is needed to append new items to the bottom of the list
//                loadMoreDataFromApi(page);
//            }
//        });
//
//        loadTimelineActivity();
//        twitterClient.loadTimelineActivity(responseHandler, tweetMaxId);
    }

    // Append more data into the adapter
    // This method probably sends out a network request and appends new data items to your adapter.
//    public void loadMoreDataFromApi(int page) {
//        loadTimelineActivity();
////        twitterClient.getTimelineActivity(responseHandler, tweetMaxId);
//    }

    // Inflate the menu; this adds items to the action bar if it is present.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_new_tweet) {
            //launch new tweet overlay
            android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
            PostTweetFragment fr = PostTweetFragment.newInstance(null, null);
            fr.show(fm, "post_tweet");
        } else if (item.getItemId() == R.id.action_user_profile) {
            //launch profile activity
            Intent i = new Intent(this, ProfileActivity.class);
            i.putExtra("UserScreenName", loginUser.getScreenName());
            i.putExtra("UserName", loginUser.getName());
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    public void postTweet(String status) {
        if (Global.isNetworkAvailable(this)) {
            twitterClient.postTimelineActivity(postTweetResponseHandler, status);
        } else {
            showetworkErrorToast();
        }
    }

//    private Boolean isNetworkAvailable() {
//        ConnectivityManager connectivityManager
//                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
//        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting() && isOnline();
//    }
//
//    public boolean isOnline() {
//        Runtime runtime = Runtime.getRuntime();
//        try {
//            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
//            int     exitValue = ipProcess.waitFor();
//            return (exitValue == 0);
//        } catch (IOException e)          { e.printStackTrace(); }
//        catch (InterruptedException e) { e.printStackTrace(); }
//        return false;
//    }

//    private void loadTimelineActivity() {
//        if (isNetworkAvailable()) {
//            //make api call and fetch data
//            twitterClient.getTimelineActivity(responseHandler, tweetMaxId);
//        } else {
////            tweets.clear();
//            //load from db
//            List<Tweet> tweetList = Tweet.getNextSet(tweetMaxId);
//            Log.d("DEBUG", " " + tweetList.size());
//            if (tweetList != null && tweetList.size() > 1) {
//                tweetMaxId = tweetList.get(tweetList.size()-1).getIdStr();
//                tweets.addAll(tweetList);
//            } else {
////                rvTweets.stopScroll();
//                showetworkErrorToast();
//            }
//        }
//    }

    private void showetworkErrorToast() {
        Toast.makeText(this,"Network not available. Can't perform this operation at this time.", Toast.LENGTH_SHORT).show();
//        svContainer.setRefreshing(false);
    }
}
