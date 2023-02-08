package com.example.myserverrecycle;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Myadapter extends RecyclerView.Adapter<Myadapter.myviewholder>{

    private ArrayList<History>arrayList;
    Context context;

    public Myadapter(ArrayList<History> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.single_view,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        History history=arrayList.get(position);
        holder.tvUserid.setText("UserId : "+history.getUserId().toString());
        holder.tvId.setText("Id : "+history.getId().toString());
        holder.tvTitle.setText("Title : "+history.getTitle());
        holder.tvBody.setText("Body : "+history.getBody());
        Glide.with(context).load("http://goo.gl/gEgYUd").into(holder.imgDesign);
        holder.btnShowdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),MySeprate_Data.class);
                intent.putExtra("userid",String.valueOf(history.getUserId()));
                intent.putExtra("id",String.valueOf(history.getId()));
                intent.putExtra("title",String.valueOf(history.getTitle()));
                intent.putExtra("body",String.valueOf(history.getBody()));

                view.getContext().startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void filterlist(ArrayList<History> filteredList) {
        arrayList=filteredList;
        notifyDataSetChanged();
    }

    public class myviewholder extends RecyclerView.ViewHolder{

        private TextView tvUserid;
        private TextView tvId;
        private TextView tvTitle;
        private TextView tvBody;
        private AppCompatButton btnShowdetails;
        private ImageView imgDesign;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            imgDesign = (ImageView) itemView.findViewById(R.id.img_design);
            tvUserid = (TextView) itemView.findViewById(R.id.tv_userid);
            tvId = (TextView) itemView.findViewById(R.id.tv_id);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvBody = (TextView) itemView.findViewById(R.id.tv_body);
            btnShowdetails = (AppCompatButton) itemView.findViewById(R.id.btn_showdetails);


        }
    }
}