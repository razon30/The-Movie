package saddam.razon.themovie.view.activity;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import saddam.razon.themovie.R;
import saddam.razon.themovie.dataParser.Parser;
import saddam.razon.themovie.model.MovieDetails;
import saddam.razon.themovie.utils.Apis;
import saddam.razon.themovie.utils.MyApplication;
import saddam.razon.themovie.view.fragments.movieDetails.CastCrewFragment;
import saddam.razon.themovie.view.fragments.movieDetails.MovieDetailsFragment;
import saddam.razon.themovie.view.fragments.movieDetails.TrailersFragment;
import saddam.razon.themovie.viewModel.MoviDetailsViewModel;

public class MovieDetailsActivity extends AppCompatActivity {

    private TextView toolbarText;
    private Toolbar toolbar;
    private TextView title;
    private TextView year;
    private TextView genre;
    private TextView rating;
    private TextView awards;
    ImageView cover;
    ImageView trailer;

    String titleStr;
    String id;

    MoviDetailsViewModel viewModel;

    @Inject
    Parser parser;
    @Inject
    MovieDetails movieDetails;
    ProgressDialog progressDialog;



    String trailerLink;

    @Inject
    CastCrewFragment castCrewFragment;
    @Inject
    TrailersFragment trailersFragment;
    @Inject
    MovieDetailsFragment movieDetailsFragment;

    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getMyApplication(this).getComponent().inject(this);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);
        initView();
        worksOnTabViewPager();

        id = getIntent().getStringExtra("id");
        titleStr = getIntent().getStringExtra("title");
        toolbarText.setText(titleStr);

        populateData();


    }

    private void worksOnTabViewPager() {

        viewPager.setAdapter(new TabPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(3);

    }
    private class TabPagerAdapter extends FragmentPagerAdapter {
        TabPagerAdapter(FragmentManager fragmentManager) {

            super(fragmentManager);

        }

        @Override
        public Fragment getItem(int position) {


            switch (position) {
                case 0:
                    return movieDetailsFragment;
                case 1:
                    return castCrewFragment;
                case 2:
                    return trailersFragment;
                default:
                    return movieDetailsFragment;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Details";
                case 1:
                    return "Cast Crew";
                case 2:
                    return "Trailers";
                default:
                    return "Details";
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

    }




    private void populateData() {


        progressDialog.show();
        viewModel.getMovieDetails(id).observe(this, this::setData);


    }

    private void setData(MovieDetails movieDetails) {

        String releeaseYear = movieDetails.getReleaseDate().split("-")[0];
        String genreStr = "";

        List<MovieDetails.Genres> genres = movieDetails.getGenres();
        for (int i = 0; i < genres.size(); i++) {
            if (i == genres.size() - 1) {
                genreStr = genres.get(i).getName();
            } else {
                genreStr = genres.get(i).getName() + ",";
            }
        }




        title.setText(movieDetails.getTitle());
        year.setText("(" + releeaseYear + ")");
        genre.setText(genreStr);
        rating.setText(movieDetails.getVoteAverage() + "");
        awards.setText("0");


        String poster = movieDetails.getBackdropPath();
        String url = Apis.IMAGE_BASE_API + poster + "?api_key" + Apis.API_KEY;

        Glide.with(MovieDetailsActivity.this)
                .load(url)
                .apply(new RequestOptions()
                        .fitCenter()
                        .placeholder(R.drawable.movie_bg)
                        .fitCenter())
                .into(cover);

        progressDialog.dismiss();

        trailer.setOnClickListener(v -> {

          //  if (trailerList.size() == 0) {

                viewModel.getTrailers(id).observe(this, trailer -> {

                    trailerLink = "https://www.youtube.com/watch?v=" + trailer.getVideos().get(0).getKey();

                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(trailerLink));
                    startActivity(i);

                });

//            } else {
//                trailerLink = "https://www.youtube.com/watch?v=" + trailerList.get(0).getResult().getKey();
//            }



        });


    }

    private void initView() {

        viewModel = ViewModelProviders.of(this).get(MoviDetailsViewModel.class);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        toolbarText = findViewById(R.id.toolbar_text);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        title = findViewById(R.id.title);
        year = findViewById(R.id.year);
        genre = findViewById(R.id.genre);
        rating = findViewById(R.id.rating);
        awards = findViewById(R.id.awards);
        cover = findViewById(R.id.cover);
        trailer = findViewById(R.id.trailer);


        progressDialog = new ProgressDialog(MovieDetailsActivity.this);
        progressDialog.setMessage("Loading");




    }
}
