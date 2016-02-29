package com.codepath.apps.restclienttemplate.fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import com.codepath.apps.restclienttemplate.adapter.MentionsAdapter;
import com.codepath.apps.restclienttemplate.database.PersistanceUtil;
import com.codepath.apps.restclienttemplate.models.Mention;
import com.codepath.apps.restclienttemplate.network.TwitterClient;
import com.codepath.apps.restclienttemplate.utils.DividerItemDecoration;
import com.codepath.apps.restclienttemplate.utils.EndlessRecyclerViewScrollListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;


public class MentionsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static String LOG_TAG = MentionsFragment.class.toString();

    @Bind(R.id.svMentions)
    SwipeRefreshLayout svContainer;

    @Bind(R.id.rvMentions)
    RecyclerView rvTweets;

    List<Mention> mentions = new ArrayList<Mention>();
    TwitterClient twitterClient;
    MentionsAdapter adapter;
    JsonHttpResponseHandler responseHandler;
    JsonHttpResponseHandler postMentionResponseHandler;
    String mentionMaxId;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TimelineFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MentionsFragment newInstance(String param1, String param2) {
        MentionsFragment fragment = new MentionsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public MentionsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_mentions, container, false);



        ButterKnife.bind(this, view);



        ActiveAndroid.initialize(this.getContext());
//
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.faTweetBtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.support.v4.app.FragmentManager fm = getFragmentManager();
                PostTweetFragment fr = PostTweetFragment.newInstance(null, null);
                fr.show(fm, "post_tweet");
            }
        });

        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this.getContext(), DividerItemDecoration.VERTICAL_LIST);
        rvTweets.addItemDecoration(itemDecoration);
//
        rvTweets.setItemAnimator(new SlideInUpAnimator());

        svContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (isNetworkAvailable()) {
                    //reload timeline
                    mentions.clear();
                    adapter.notifyDataSetChanged();
                    mentionMaxId = null;
                    twitterClient.getMentions(responseHandler, mentionMaxId);
                } else {
                    showetworkErrorToast();
                }
            }
        });

        responseHandler = new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                Log.d(LOG_TAG, response.toString());
                Type collectionType = new TypeToken<List<Mention>>() {
                }.getType();
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                List<Mention> mentionsList = gson.fromJson(response.toString(), collectionType);
                if (mentionMaxId != null) {
                    //not first page. Remove the first object from response as this is duplicate of previous page
                    mentionsList.remove(0);
                }

                if (mentionsList != null && mentionsList.size() > 1) {
                    mentions.addAll(mentionsList);
                    PersistanceUtil.persistMentions(mentionsList);
                    Mention lastTweet = mentionsList.get(mentionsList.size() - 1);
                    mentionMaxId = lastTweet.getIdStr();
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

        postMentionResponseHandler = new JsonHttpResponseHandler() {

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
                mentions.clear();
                adapter.notifyDataSetChanged();
                mentionMaxId = null;
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
        adapter = new MentionsAdapter(mentions);
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

    // Append more data into the adapter
    // This method probably sends out a network request and appends new data items to your adapter.
    public void loadMoreDataFromApi(int page) {
        loadTimelineActivity();
//        twitterClient.getTimelineActivity(responseHandler, mentionMaxId);
    }



    public void postTweet(String status) {
        if (isNetworkAvailable()) {
            twitterClient.postTimelineActivity(postMentionResponseHandler, status);
        } else {
            showetworkErrorToast();
        }
    }

    private Boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting() && isOnline();
    }

    public boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }
        return false;
    }

    private void loadTimelineActivity() {
        if (isNetworkAvailable()) {
            //make api call and fetch data
            twitterClient.getMentions(responseHandler, mentionMaxId);
        } else {
//            mentions.clear();
            //load from db
            List<Mention> mentionList = Mention.getNextSet(mentionMaxId);
            Log.d("DEBUG", " " + mentionList.size());
            if (mentionList != null && mentionList.size() > 1) {
                mentionMaxId = mentionList.get(mentionList.size()-1).getIdStr();
                mentions.addAll(mentionList);
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
