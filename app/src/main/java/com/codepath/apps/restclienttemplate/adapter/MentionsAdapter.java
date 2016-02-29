package com.codepath.apps.restclienttemplate.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.activity.ProfileActivity;
import com.codepath.apps.restclienttemplate.models.Media;
import com.codepath.apps.restclienttemplate.models.Mention;
import com.codepath.apps.restclienttemplate.utils.Global;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by skammila on 2/18/16.
 */
public class MentionsAdapter extends RecyclerView.Adapter<MentionsAdapter.MentionsViewHolder> {

    List<Mention> mentions;

    public MentionsAdapter(List<Mention> mentions) {
        this.mentions = mentions;
    }

    public static class MentionsViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.tvTweetText) TextView tvTweetText;
        @Bind(R.id.ivProfile) ImageView ivProfile;
        @Bind(R.id.tvUserName) TextView userName;
        @Bind(R.id.tvScreenName) TextView screenName;
        @Bind(R.id.tvTimeDuration) TextView timeDuration;
        @Bind(R.id.ivMedia) RoundedImageView ivMedia;


        public MentionsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public void onBindViewHolder(final MentionsViewHolder holder, int position) {
        // Get the data model based on position
        final Mention mention = mentions.get(position);

        // Set item views based on the data model
        holder.tvTweetText.setText(mention.getText());

        holder.userName.setText(mention.getUser().getName());
        holder.screenName.setText("@" + mention.getUser().getScreenName());
        holder.timeDuration.setText(Global.getTimeSpan(mention.getCreatedAt()));

        Media media = mention.getEntities() != null && mention.getEntities().getMedia() !=null && mention.getEntities().getMedia().size() > 0 ? mention.getEntities().getMedia().get(0) : null;
        String mediaUrl = media != null ? media.getMediaUrl() : null;
        if (media != null && media.getMediaUrl() != null && media.getType().equals("photo")) {
            if (media.getType().equals("photo")) {
                Picasso.with(holder.ivMedia.getContext()).load(mediaUrl).into(holder.ivMedia);
            }
        }
        Picasso.with(holder.ivProfile.getContext()).load(mention.getUser().getProfileImageUrl()).into(holder.ivProfile);
        holder.ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(holder.ivProfile.getContext(), ProfileActivity.class);
                i.putExtra("UserScreenName", mention.getUser().getScreenName());
                i.putExtra("UserName", mention.getUser().getName());
                holder.ivProfile.getContext().startActivity(i);
            }
        });
    }

    @Override
    public MentionsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View tweetView = inflater.inflate(R.layout.tweet_item, parent, false);

        // Return a new holder instance
        MentionsViewHolder viewHolder = new MentionsViewHolder(tweetView);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return mentions != null ? mentions.size() : 0;
    }
}
