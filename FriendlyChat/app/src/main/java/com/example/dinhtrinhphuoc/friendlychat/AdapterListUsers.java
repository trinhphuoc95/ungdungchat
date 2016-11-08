package com.example.dinhtrinhphuoc.friendlychat;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by TrinhPhuoc on 05-Nov-16.
 */

public class AdapterListUsers extends RecyclerView.Adapter<AdapterListUsers.AdapterViewHolder> {
    private ArrayList<UserMessage> list;
    private Context context;


    public AdapterListUsers(ArrayList<UserMessage> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public AdapterListUsers.AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_user, parent, false);
        AdapterViewHolder holder = new AdapterViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(AdapterListUsers.AdapterViewHolder holder, int position) {


        holder.id_list_item_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, MainActivity.class));
            }
        });

        holder.textView.setText(list.get(position).getName());

        if (list.get(position).getPhotoUrl()== null){
            holder.imageView.setImageDrawable(ContextCompat.getDrawable(context,
                    R.drawable.ic_account_circle_black_36dp));
        }else{
            Glide.with(context)
                    .load(list.get(position).getPhotoUrl())
                    .into(holder.imageView);

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        CircleImageView imageView;
        RelativeLayout id_list_item_user;

        public AdapterViewHolder(View itemView) {
            super(itemView);
            imageView = (CircleImageView) itemView.findViewById(R.id.imageView3);
            textView = (TextView) itemView.findViewById(R.id.textView3);
            id_list_item_user = (RelativeLayout) itemView.findViewById(R.id.id_list_item_user);
        }
    }
}
