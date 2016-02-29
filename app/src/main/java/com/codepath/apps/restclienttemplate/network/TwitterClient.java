package com.codepath.apps.restclienttemplate.network;

import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;

/*
 * 
 * This is the object responsible for communicating with a REST API. 
 * Specify the constants below to change the API being communicated with.
 * See a full list of supported API classes: 
 *   https://github.com/fernandezpablo85/scribe-java/tree/master/src/main/java/org/scribe/builder/api
 * Key and Secret are provided by the developer site for the given API i.e dev.twitter.com
 * Add methods for each relevant endpoint in the API.
 * 
 * NOTE: You may want to rename this object based on the service i.e TwitterClient or FlickrClient
 * 
 */
public class TwitterClient extends OAuthBaseClient {
	public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class; // Change this
	public static final String REST_URL = "https://api.twitter.com/1.1/"; // Change this, base API URL
	public static final String REST_CONSUMER_KEY = "JFjETYvKgiAXnBsjaqoOhpOAZ";       // Change this
	public static final String REST_CONSUMER_SECRET = "QfsD1tBnBhpebctluSdOBKqlNl15hMlOH8W0dPA3b3b5cklAbN"; // Change this
	public static final String REST_CALLBACK_URL = "oauth://cpsimpletwitter"; // Change this (here and in manifest)

	public TwitterClient(Context context) {
		super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
	}

	// CHANGE THIS
	// DEFINE METHODS for different API endpoints here
	public void getInterestingnessList(AsyncHttpResponseHandler handler) {
		String apiUrl = getApiUrl("?nojsoncallback=1&method=flickr.interestingness.getList");
		// Can specify query string params directly or through RequestParams.
		RequestParams params = new RequestParams();
		params.put("format", "json");
		client.get(apiUrl, params, handler);
	}

	public void getTimelineActivity(AsyncHttpResponseHandler handler, String  max_id) {
		String apiUrl = getApiUrl("statuses/home_timeline.json");
		// Can specify query string params directly or through RequestParams.
		RequestParams params = new RequestParams();
		if (max_id != null) {
			params.put("count", "9");
			//fetch next pages
			params.put("max_id", max_id);
		} else {
			//first page
			params.put("count", "8");
		}

		client.get(apiUrl, params, handler);
	}

	public void getUserTimelineActivity(AsyncHttpResponseHandler handler, String  max_id, String screenName) {
		String apiUrl = getApiUrl("statuses/user_timeline.json");
		// Can specify query string params directly or through RequestParams.
		RequestParams params = new RequestParams();
		if (max_id != null) {
			params.put("count", "9");
			//fetch next pages
			params.put("max_id", max_id);
		} else {
			//first page
			params.put("count", "8");
		}
		params.put("screen_name", screenName);

		client.get(apiUrl, params, handler);
	}

	public void postTimelineActivity(AsyncHttpResponseHandler handler, String  status) {
		String apiUrl = getApiUrl("statuses/update.json");
		// Can specify query string params directly or through RequestParams.
		RequestParams params = new RequestParams();
			params.put("status", status);
			client.post(apiUrl, params, handler);

	}

	public void getMentions(AsyncHttpResponseHandler handler, String  max_id) {
		String apiUrl = getApiUrl("statuses/mentions_timeline.json");
		// Can specify query string params directly or through RequestParams.
		RequestParams params = new RequestParams();
		if (max_id != null) {
			params.put("count", "9");
			//fetch next pages
			params.put("max_id", max_id);
		} else {
			//first page
			params.put("count", "8");
		}

		client.get(apiUrl, params, handler);
	}

	public void getLoginUser(AsyncHttpResponseHandler handler) {

		String apiUrl = getApiUrl("account/verify_credentials.json");

		client.get(apiUrl, null, handler);
	}

	public void getUserProfile(AsyncHttpResponseHandler handler,String screenName) {

		String apiUrl = getApiUrl("users/show.json?screen_name="+screenName);

		client.get(apiUrl, null, handler);
	}

	public void getUserProfileBanner(AsyncHttpResponseHandler handler,String screenName) {

		String apiUrl = getApiUrl("users/profile_banner.json?screen_name="+screenName);

		client.get(apiUrl, null, handler);
	}



	/* 1. Define the endpoint URL with getApiUrl and pass a relative path to the endpoint
	 * 	  i.e getApiUrl("statuses/home_timeline.json");
	 * 2. Define the parameters to pass to the request (query or body)
	 *    i.e RequestParams params = new RequestParams("foo", "bar");
	 * 3. Define the request method and make a call to the client
	 *    i.e client.get(apiUrl, params, handler);
	 *    i.e client.post(apiUrl, params, handler);
	 */
}