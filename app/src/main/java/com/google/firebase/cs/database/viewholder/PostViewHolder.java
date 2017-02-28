package com.google.firebase.cs.database.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.cs.database.R;
import com.google.firebase.cs.database.models.Equipment;

import static com.google.firebase.cs.database.R.id.post_body;

public class PostViewHolder extends RecyclerView.ViewHolder {

    public TextView titleView;
    public TextView authorView;
    public TextView bodyView;
    public TextView demoView;

    public PostViewHolder(View itemView) {
        super(itemView);

        titleView = (TextView) itemView.findViewById(R.id.post_title);
        authorView = (TextView) itemView.findViewById(R.id.post_author);
        bodyView = (TextView) itemView.findViewById(post_body);
        demoView = (TextView) itemView.findViewById(R.id.post_location);

    }

    public void bindToPost(Equipment post, View.OnClickListener starClickListener) {
        titleView.setText(post.title);
        authorView.setText(post.author);
        bodyView.setText(post.body);
        demoView.setText(post.location);

    }

}
