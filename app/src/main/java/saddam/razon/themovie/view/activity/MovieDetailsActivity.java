package saddam.razon.themovie.view.activity;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import saddam.razon.themovie.R;
import saddam.razon.themovie.dataParser.Parser;
import saddam.razon.themovie.model.CastCrewSync;
import saddam.razon.themovie.model.MovieDetails;
import saddam.razon.themovie.model.MovieSimilar;
import saddam.razon.themovie.model.Trailers;
import saddam.razon.themovie.utils.Apis;
import saddam.razon.themovie.utils.MyApplication;
import saddam.razon.themovie.utils.RecyclerTOuchListener;
import saddam.razon.themovie.utils.interfaces.ClickListener;
import saddam.razon.themovie.view.adapter.AdapterCastCrewRecycler;
import saddam.razon.themovie.view.adapter.AdapterSimilarRecycler;
import saddam.razon.themovie.view.adapter.AdapterTrailersRecycler;
import saddam.razon.themovie.viewModel.MoviDetailsViewModel;

public class MovieDetailsActivity extends AppCompatActivity {

    private TextView toolbarText;
    private Toolbar toolbar;
    private TextView title;
    private TextView year;
    private TextView genre;
    private TextView rating;
    private TextView awards;
    private TextView overview;
    private TextView productionCompany;
    private TextView budget;
    private TextView productionCountry;
    private TextView language;
    ImageView cover;

    ImageView trailer;

    private RecyclerView recycler;
    AdapterSimilarRecycler adapterSimilarRecycler;
    ArrayList<MovieSimilar.Result> similarmovieList;
    LinearLayoutManager similarLayoutManager;

    String titleStr;
    String id;

    MoviDetailsViewModel viewModel;

    @Inject
    Parser parser;
    @Inject
    MovieDetails movieDetails;
    ProgressDialog progressDialog;

    ArrayList<Trailers> trailerList;
    private RecyclerView recyclerTailers;
    private LinearLayout trailers;
    AdapterTrailersRecycler adapterTrailersRecycler;
    LinearLayoutManager trailerLayoutManager;

    String trailerLink;

    ArrayList<CastCrewSync> castCrewSyncList;
    AdapterCastCrewRecycler adapterCastCrewRecycler;
    LinearLayoutManager castCrewLayoutManager;
    @BindView(R.id.cast_crew_recycler)
    RecyclerView castRcrewRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getMyApplication(this).getComponent().inject(this);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);
        initView();

        id = getIntent().getStringExtra("id");
        titleStr = getIntent().getStringExtra("title");
        toolbarText.setText(titleStr);

        populateData();




    }


    private void worksOnRecycler() {

        recycler.addOnItemTouchListener(new RecyclerTOuchListener(this, recycler, new ClickListener() {
            @Override
            public void onCLick(View v, int position) {

                startActivity(new Intent(MovieDetailsActivity.this, MovieDetailsActivity.class)
                        .putExtra("title", similarmovieList.get(position).getTitle())
                        .putExtra("id", String.valueOf(similarmovieList.get(position).getId())));

            }

            @Override
            public void onLongClick(View v, int position) {

            }
        }));


    }

    private void populateData() {


        progressDialog.show();
        viewModel.getMovieDetails(id).observe(this, this::setData);


    }

    private void setData(MovieDetails movieDetails) {

        String releeaseYear = movieDetails.getReleaseDate().split("-")[0];
        String genreStr = "";
        String productionCompanyStr = "";
        String productionCountryStr = "";
        String languageStr = "";

        List<MovieDetails.Genres> genres = movieDetails.getGenres();
        for (int i = 0; i < genres.size(); i++) {
            if (i == genres.size() - 1) {
                genreStr = genres.get(i).getName();
            } else {
                genreStr = genres.get(i).getName() + ",";
            }
        }

        List<MovieDetails.ProductionCompanies> productionCompanies = movieDetails.getProductionCompanies();
        for (int i = 0; i < productionCompanies.size(); i++) {
            if (i == productionCompanies.size() - 1) {
                productionCompanyStr = productionCompanies.get(i).getName();
            } else {
                productionCompanyStr = productionCompanies.get(i).getName() + ",";
            }
        }

        List<MovieDetails.ProductionCountries> productionCountries = movieDetails.getProductionCountries();
        for (int i = 0; i < productionCountries.size(); i++) {
            if (i == productionCountries.size() - 1) {
                productionCountryStr = productionCountries.get(i).getName();
            } else {
                productionCountryStr = productionCountries.get(i).getName() + ",";
            }
        }

        List<MovieDetails.SpokenLanguages> spokenLanguages = movieDetails.getSpokenLanguages();
        for (int i = 0; i < spokenLanguages.size(); i++) {
            if (i == spokenLanguages.size() - 1) {
                languageStr = spokenLanguages.get(i).getName();
            } else {
                languageStr = spokenLanguages.get(i).getName() + ",";
            }
        }


        title.setText(movieDetails.getTitle());
        year.setText("(" + releeaseYear + ")");
        genre.setText(genreStr);
        rating.setText(movieDetails.getVoteAverage() + "");
        awards.setText("0");
        overview.setText(movieDetails.getOverview());
        productionCompany.setText(productionCompanyStr);
        productionCountry.setText(productionCountryStr);
        budget.setText(movieDetails.getBudget() + "");
        language.setText(languageStr);

        String poster = movieDetails.getBackdropPath();
        String url = Apis.IMAGE_BASE_API + poster + "?api_key" + Apis.API_KEY;

        Glide.with(MovieDetailsActivity.this)
                .load(url)
                .apply(new RequestOptions()
                        .fitCenter()
                        .placeholder(R.drawable.movie_bg)
                        .fitCenter())
                .into(cover);


        populateSimilarMovies(String.valueOf(movieDetails.getId()));
        populateVideos(String.valueOf(movieDetails.getId()));
        populateCastAndCrew(String.valueOf(movieDetails.getId()));


        trailer.setOnClickListener(v -> {

            if (trailerList.size() == 0){

                viewModel.getTrailers(String.valueOf(movieDetails.getId())).observe(this, trailer -> {

                    trailerLink = "https://www.youtube.com/watch?v=" + trailer.getVideos().get(0).getKey();

                });

            }else {
                trailerLink = "https://www.youtube.com/watch?v=" + trailerList.get(0).getResult().getKey();
            }

            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(trailerLink));
            startActivity(i);

        });


    }

    private void populateCastAndCrew(String movieId) {

        viewModel.getMovieCastCrew(movieId).observe(this, castCrewSyncs -> {
            castCrewSyncList.addAll(castCrewSyncs);
            adapterCastCrewRecycler.notifyDataSetChanged();
        });

    }

    private void populateVideos(String movieId) {

        recyclerTailers.setLayoutManager(trailerLayoutManager);
        recyclerTailers.setAdapter(adapterTrailersRecycler);

        viewModel.getTrailers(movieId).observe(this, trailer -> {

            if (trailer != null) {

                trailers.setVisibility(View.VISIBLE);

                if (trailer.getVideos().size() == 0 || trailer.getVideos() == null) {
                    trailers.setVisibility(View.GONE);
                    return;
                }

                if (trailer.getVideos().size() < trailer.getBackdrops().getBackdrops().size()) {

                    for (int i = 0; i < trailer.getVideos().size(); i++) {

                        trailerList.add(new Trailers(trailer.getBackdrops().getBackdrops().get(i), trailer.getVideos().get(i)));

                    }
                } else {

                    for (int i = 0; i < trailer.getBackdrops().getBackdrops().size(); i++) {

                        trailerList.add(new Trailers(trailer.getBackdrops().getBackdrops().get(i), trailer.getVideos().get(i)));

                    }


                }

                progressDialog.dismiss();
                adapterTrailersRecycler.notifyDataSetChanged();
            } else {

                trailers.setVisibility(View.GONE);

            }

        });


    }

    private void populateSimilarMovies(String movieId) {

        recycler.setLayoutManager(similarLayoutManager);
        recycler.setAdapter(adapterSimilarRecycler);

        viewModel.getSimilarMovies(movieId).observe(this, movieSimilar -> {
            if (movieSimilar != null) {
                similarmovieList.addAll(movieSimilar.getResults());
                adapterSimilarRecycler.notifyDataSetChanged();
            }

            //  progressDialog.dismiss();
            worksOnRecycler();

        });


    }

    private void initView() {

        viewModel = ViewModelProviders.of(this).get(MoviDetailsViewModel.class);

        toolbarText = findViewById(R.id.toolbar_text);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        title = findViewById(R.id.title);
        year = findViewById(R.id.year);
        genre = findViewById(R.id.genre);
        rating = findViewById(R.id.rating);
        awards = findViewById(R.id.awards);
        overview = findViewById(R.id.overview);
        productionCompany = findViewById(R.id.production_company);
        budget = findViewById(R.id.budget);
        productionCountry = findViewById(R.id.production_country);
        language = findViewById(R.id.language);
        cover = findViewById(R.id.cover);
        trailer = findViewById(R.id.trailer);

        recycler = findViewById(R.id.recycler);
        similarmovieList = new ArrayList<>();
        adapterSimilarRecycler = new AdapterSimilarRecycler(similarmovieList, MovieDetailsActivity.this);
        similarLayoutManager = new LinearLayoutManager(MovieDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false);

        progressDialog = new ProgressDialog(MovieDetailsActivity.this);
        progressDialog.setMessage("Loading");

        recyclerTailers = findViewById(R.id.recycler_tailers);
        trailers = findViewById(R.id.trailers);
        trailerList = new ArrayList<>();
        adapterTrailersRecycler = new AdapterTrailersRecycler(trailerList, MovieDetailsActivity.this);
        trailerLayoutManager = new LinearLayoutManager(MovieDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false);


        castCrewSyncList = new ArrayList<>();
        castCrewLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        adapterCastCrewRecycler = new AdapterCastCrewRecycler(castCrewSyncList, this);
        castRcrewRecycler.setLayoutManager(castCrewLayoutManager);
        castRcrewRecycler.setAdapter(adapterCastCrewRecycler);


    }
}
