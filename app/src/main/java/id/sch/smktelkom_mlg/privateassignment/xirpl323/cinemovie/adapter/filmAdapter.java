package id.sch.smktelkom_mlg.privateassignment.xirpl323.cinemovie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl323.cinemovie.NowPlayingFragment;
import id.sch.smktelkom_mlg.privateassignment.xirpl323.cinemovie.R;
import id.sch.smktelkom_mlg.privateassignment.xirpl323.cinemovie.ScrollingActivity1;
import id.sch.smktelkom_mlg.privateassignment.xirpl323.cinemovie.model.Result;

/**
 * Created by Nabila Basharahil on 5/13/2017.
 */

public class filmAdapter extends RecyclerView.Adapter<filmAdapter.MyViewHolder> {

    public String url = "https://image.tmdb.org/t/p/w500";
    public String image;
    ArrayList<Result> mlist;
    NowPlayingFragment nowPlayingFragment;
    Context context;
    private int lastposition = -1;

    public filmAdapter(NowPlayingFragment NowPlayingFragment, ArrayList<Result> mlist, Context context) {
        this.mlist = mlist;
        this.nowPlayingFragment = NowPlayingFragment;
        this.context = context;
    }

    @Override
    public filmAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_list, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(filmAdapter.MyViewHolder holder, int position) {
        final Result results = mlist.get(position);
        holder.tvName.setText(results.title);
        holder.tvDesc.setText(results.overview);
        image = url + results.backdrop_path;
        Glide.with(context).load(image)
                .crossFade()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher)
                .into(holder.imageView);

        //setAnimation(holder.itemView,position);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = results.id;
                Intent intent = new Intent(context, ScrollingActivity1.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("movie_title",results.title);
                intent.putExtra("poster_path",results.backdrop_path);
                intent.putExtra("description",results.overview);
                context.startActivity(intent);
            }
        });
    }




    @Override
    public int getItemCount() {
        if (mlist != null)
            return mlist.size();
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvDesc;
        ImageView imageView;
        CardView cardView;

        public MyViewHolder(View v) {
            super(v);

            tvName = (TextView) v.findViewById(R.id.tv_text);
            tvDesc = (TextView) v.findViewById(R.id.tv_desc);
            imageView = (ImageView) v.findViewById(R.id.iv_image);
            cardView = (CardView) v.findViewById(R.id.cardView);
        }


    }
}
