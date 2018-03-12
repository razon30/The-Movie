package saddam.razon.themovie.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import saddam.razon.themovie.R;
import saddam.razon.themovie.model.MovieSimilar;
import saddam.razon.themovie.utils.Apis;


public class AdapterSimilarRecycler extends RecyclerView.Adapter<AdapterSimilarRecycler.HomeViewholder> {

    ArrayList<MovieSimilar.Result> movieList;
    Context context;
    LayoutInflater inflater;

    public AdapterSimilarRecycler(ArrayList<MovieSimilar.Result> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public HomeViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HomeViewholder(inflater.inflate(R.layout.item_recycler_similar, parent, false));
    }

    @Override
    public void onBindViewHolder(HomeViewholder holder, int position) {


        String image = movieList.get(position).getPosterPath();
        String url = Apis.IMAGE_BASE_API + image + "?api_key" + Apis.API_KEY;
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.movie_bg)
                )
                .into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class HomeViewholder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public HomeViewholder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image);

        }
    }

}
