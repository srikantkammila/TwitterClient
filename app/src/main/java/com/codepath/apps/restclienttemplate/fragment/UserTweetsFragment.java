package com.codepath.apps.restclienttemplate.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.activity.TimelineActivity;
import com.codepath.apps.restclienttemplate.adapter.TimelineAdapter;
import com.codepath.apps.restclienttemplate.database.PersistanceUtil;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.apps.restclienttemplate.network.TwitterClient;
import com.codepath.apps.restclienttemplate.utils.DividerItemDecoration;
import com.codepath.apps.restclienttemplate.utils.EndlessRecyclerViewScrollListener;
import com.codepath.apps.restclienttemplate.utils.Global;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

/**
 * Created by skammila on 2/28/16.
 */
public class UserTweetsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "screenName";
    private static final String ARG_PARAM2 = "param2";

    private static String LOG_TAG = TimelineActivity.class.toString();

    // TODO: Rename and change types of parameters
    private String screenName;
    private String mParam2;
    @Bind(R.id.svContainer)
    SwipeRefreshLayout svContainer;

    @Bind(R.id.rvTweets)
    RecyclerView rvTweets;

    List<Tweet> tweets = new ArrayList<Tweet>();
    TwitterClient twitterClient;
    TimelineAdapter adapter;
    JsonHttpResponseHandler responseHandler;
    JsonHttpResponseHandler postTweetResponseHandler;
    String tweetMaxId;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TimelineFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserTweetsFragment newInstance(String param1, String param2) {
        UserTweetsFragment fragment = new UserTweetsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public UserTweetsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            screenName = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_timeline, container, false);
        ButterKnife.bind(this, view);


        ActiveAndroid.initialize(this.getContext());
//
//        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.faTweetBtn);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                android.support.v4.app.FragmentManager fm = getFragmentManager();
//                PostTweetFragment fr = PostTweetFragment.newInstance(null, null);
//                fr.show(fm, "post_tweet");
//            }
//        });

        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this.getContext(), DividerItemDecoration.VERTICAL_LIST);
        rvTweets.addItemDecoration(itemDecoration);
//
        rvTweets.setItemAnimator(new SlideInUpAnimator());

        svContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (Global.isNetworkAvailable(getContext())) {
                    //reload timeline
                    tweets.clear();
                    adapter.notifyDataSetChanged();
                    tweetMaxId = null;
                    twitterClient.getUserTimelineActivity(responseHandler, tweetMaxId, screenName);
                } else {
                    showetworkErrorToast();
                }
            }
        });

        responseHandler = new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                Log.d(LOG_TAG, response.toString());
                Type collectionType = new TypeToken<List<Tweet>>() {
                }.getType();
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                List<Tweet> tweetsList = gson.fromJson(response.toString(), collectionType);
                if (tweetMaxId != null) {
                    //not first page. Remove the first object from response as this is duplicate of previous page
                    tweetsList.remove(0);
                }

                if (tweetsList != null && tweetsList.size() > 1) {
                    tweets.addAll(tweetsList);
                    PersistanceUtil.persistTweets(tweetsList);
                    Tweet lastTweet = tweetsList.get(tweetsList.size() - 1);
                    tweetMaxId = lastTweet.getIdStr();
                }

                adapter.notifyDataSetChanged();
                svContainer.setRefreshing(false);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e(LOG_TAG, "Error while API call");
                throwable.printStackTrace();
                svContainer.setRefreshing(false);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.e(LOG_TAG, "Error while API call");
                throwable.printStackTrace();
                svContainer.setRefreshing(false);
            }
        };

        postTweetResponseHandler = new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("DEBUG", response.toString());
//                GsonBuilder gsonBuilder = new GsonBuilder();
//                Gson gson = gsonBuilder.create();
//                Tweet tweet = gson.fromJson(response.toString(), Tweet.class);
//                mentions.add(0, tweet);
//                adapter.notifyItemInserted(0);
//                mentionMaxId = tweet.getIdStr();

                //reload the timeline
                tweets.clear();
                adapter.notifyDataSetChanged();
                tweetMaxId = null;
                loadTimelineActivity();
//                twitterClient.loadTimelineActivity(responseHandler, mentionMaxId);
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

        twitterClient = new TwitterClient(this.getContext());

        // Create adapter
        adapter = new TimelineAdapter(tweets);
        // Attach the adapter to the recyclerview
        rvTweets.setAdapter(adapter);
        // Set layout manager to position the items
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        rvTweets.setLayoutManager(linearLayoutManager);
        // That's all!
        rvTweets.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                loadMoreDataFromApi(page);
            }
        });

        loadTimelineActivity();
        return view;
    }

    public void loadMoreDataFromApi(int page) {
        loadTimelineActivity();
//        twitterClient.getTimelineActivity(responseHandler, mentionMaxId);
    }

    private void loadTimelineActivity() {
        if (Global.isNetworkAvailable(getContext())) {
            //make api call and fetch data
            twitterClient.getUserTimelineActivity(responseHandler, tweetMaxId, screenName);
        } else {
//            mentions.clear();
            //load from db
            List<Tweet> tweetList = Tweet.getNextSet(tweetMaxId);
            Log.d("DEBUG", " " + tweetList.size());
            if (tweetList != null && tweetList.size() > 1) {
                tweetMaxId = tweetList.get(tweetList.size() - 1).getIdStr();
                tweets.addAll(tweetList);
                adapter.notifyDataSetChanged();
            } else {
//                rvTweets.stopScroll();
                showetworkErrorToast();
            }
        }
    }

    private void showetworkErrorToast() {
        Toast.makeText(this.getContext(), "Network not available. Can't perform this operation at this time.", Toast.LENGTH_SHORT).show();
//        svContainer.setRefreshing(false);
    }

}