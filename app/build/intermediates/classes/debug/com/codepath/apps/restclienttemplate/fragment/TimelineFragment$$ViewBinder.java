// Generated code from Butter Knife. Do not modify!
package com.codepath.apps.restclienttemplate.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class TimelineFragment$$ViewBinder<T extends com.codepath.apps.restclienttemplate.fragment.TimelineFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427463, "field 'svContainer'");
    target.svContainer = finder.castView(view, 2131427463, "field 'svContainer'");
    view = finder.findRequiredView(source, 2131427464, "field 'rvTweets'");
    target.rvTweets = finder.castView(view, 2131427464, "field 'rvTweets'");
  }

  @Override public void unbind(T target) {
    target.svContainer = null;
    target.rvTweets = null;
  }
}
