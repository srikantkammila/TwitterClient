// Generated code from Butter Knife. Do not modify!
package com.codepath.apps.restclienttemplate.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ProfileActivity$$ViewBinder<T extends com.codepath.apps.restclienttemplate.activity.ProfileActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427436, "field 'profilebackground'");
    target.profilebackground = finder.castView(view, 2131427436, "field 'profilebackground'");
    view = finder.findRequiredView(source, 2131427437, "field 'profilePicture'");
    target.profilePicture = finder.castView(view, 2131427437, "field 'profilePicture'");
    view = finder.findRequiredView(source, 2131427439, "field 'userName'");
    target.userName = finder.castView(view, 2131427439, "field 'userName'");
    view = finder.findRequiredView(source, 2131427440, "field 'screenName'");
    target.screenName = finder.castView(view, 2131427440, "field 'screenName'");
    view = finder.findRequiredView(source, 2131427441, "field 'tagline'");
    target.tagline = finder.castView(view, 2131427441, "field 'tagline'");
    view = finder.findRequiredView(source, 2131427442, "field 'followingCount'");
    target.followingCount = finder.castView(view, 2131427442, "field 'followingCount'");
    view = finder.findRequiredView(source, 2131427443, "field 'followersCount'");
    target.followersCount = finder.castView(view, 2131427443, "field 'followersCount'");
    view = finder.findRequiredView(source, 2131427446, "field 'flTimeline'");
    target.flTimeline = finder.castView(view, 2131427446, "field 'flTimeline'");
  }

  @Override public void unbind(T target) {
    target.profilebackground = null;
    target.profilePicture = null;
    target.userName = null;
    target.screenName = null;
    target.tagline = null;
    target.followingCount = null;
    target.followersCount = null;
    target.flTimeline = null;
  }
}
