// Generated code from Butter Knife. Do not modify!
package com.codepath.apps.restclienttemplate.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MentionsAdapter$MentionsViewHolder$$ViewBinder<T extends com.codepath.apps.restclienttemplate.adapter.MentionsAdapter.MentionsViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427467, "field 'tvTweetText'");
    target.tvTweetText = finder.castView(view, 2131427467, "field 'tvTweetText'");
    view = finder.findRequiredView(source, 2131427465, "field 'ivProfile'");
    target.ivProfile = finder.castView(view, 2131427465, "field 'ivProfile'");
    view = finder.findRequiredView(source, 2131427439, "field 'userName'");
    target.userName = finder.castView(view, 2131427439, "field 'userName'");
    view = finder.findRequiredView(source, 2131427440, "field 'screenName'");
    target.screenName = finder.castView(view, 2131427440, "field 'screenName'");
    view = finder.findRequiredView(source, 2131427466, "field 'timeDuration'");
    target.timeDuration = finder.castView(view, 2131427466, "field 'timeDuration'");
    view = finder.findRequiredView(source, 2131427468, "field 'ivMedia'");
    target.ivMedia = finder.castView(view, 2131427468, "field 'ivMedia'");
  }

  @Override public void unbind(T target) {
    target.tvTweetText = null;
    target.ivProfile = null;
    target.userName = null;
    target.screenName = null;
    target.timeDuration = null;
    target.ivMedia = null;
  }
}
