package saddam.razon.themovie.view.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import saddam.razon.themovie.R;
import saddam.razon.themovie.model.CastCrewSync;
import saddam.razon.themovie.utils.Apis;
import saddam.razon.themovie.utils.MyTextView;


public class AdapterCastCrewRecycler extends RecyclerView.Adapter<AdapterCastCrewRecycler.HomeViewholder> {

    ArrayList<CastCrewSync> movieList;
    Context context;
    LayoutInflater inflater;


    public AdapterCastCrewRecycler(ArrayList<CastCrewSync> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public HomeViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HomeViewholder(inflater.inflate(R.layout.item_trailer, parent, false));
    }

    @Override
    public void onBindViewHolder(HomeViewholder holder, int position) {

        holder.play.setVisibility(View.GONE);

        String image = movieList.get(position).getImage();
        String url = Apis.IMAGE_BASE_API + image + "?api_key" + Apis.API_KEY;
      //  String trailerLink = "https://www.youtube.com/watch?v=" + movieList.get(position).getResult().getKey();
        holder.name.setText(movieList.get(position).getName()+"\n\n"+movieList.get(position).getRole());

        Glide.with(context)
                .load(url)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.movie_bg)
                )
                .into(holder.image);

//        holder.card.setOnClickListener(v -> {
//
//            Intent i = new Intent(Intent.ACTION_VIEW);
//            i.setData(Uri.parse(trailerLink));
//            context.startActivity(i);
//
//        });


    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class HomeViewholder extends RecyclerView.ViewHolder {

        public View rootView;
        public ImageView image;
        public ImageView play;
        public MyTextView name;
        private CardView card;

        public HomeViewholder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.image = rootView.findViewById(R.id.image);
            this.play = rootView.findViewById(R.id.play);
            this.name = rootView.findViewById(R.id.name);
            this.card = rootView.findViewById(R.id.card);
        }
    }

}
