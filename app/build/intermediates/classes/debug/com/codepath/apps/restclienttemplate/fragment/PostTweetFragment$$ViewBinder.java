// Generated code from Butter Knife. Do not modify!
package com.codepath.apps.restclienttemplate.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class PostTweetFragment$$ViewBinder<T extends com.codepath.apps.restclienttemplate.fragment.PostTweetFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427460, "field 'tweetText'");
    target.tweetText = finder.castView(view, 2131427460, "field 'tweetText'");
    view = finder.findRequiredView(source, 2131427461, "field 'tweetBtn'");
    target.tweetBtn = finder.castView(view, 2131427461, "field 'tweetBtn'");
    view = finder.findRequiredView(source, 2131427462, "field 'charCount'");
    target.charCount = finder.castView(view, 2131427462, "field 'charCount'");
  }

  @Override public void unbind(T target) {
    target.tweetText = null;
    target.tweetBtn = null;
    target.charCount = null;
  }
}
