package saddam.razon.themovie.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import saddam.razon.themovie.R;
import saddam.razon.themovie.model.Movie;
import saddam.razon.themovie.utils.Apis;

/**
 * Created by RAZON on 20-Jan-18.
 */

public class AdapterMovieListRecycler extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<Movie.Results> movieArrayList;
    Context context;
    LayoutInflater inflater;


    public AdapterMovieListRecycler(ArrayList<Movie.Results> movieArrayList, Context context) {
        this.movieArrayList = movieArrayList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        return new HomeViewholder(view);

    }

//    public void addItems(ArrayList<Movie.Results> borrowModelList) {
//        this.movieArrayList = borrowModelList;
//        notifyDataSetChanged();
//    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Log.d("Movieid", movieArrayList.get(position).getId() + "");
        String image = movieArrayList.get(position).getPosterPath();
        String url = Apis.IMAGE_BASE_API + image + "?api_key" + Apis.API_KEY;
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.movie_bg)
                )
                .into(((HomeViewholder) holder).imageView);


    }


    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }

    class HomeViewholder extends RecyclerView.ViewHolder {

        ImageView imageView;

        HomeViewholder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image);

        }
    }


}
