package saddam.razon.themovie.view.fragments.movieDetails;


import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.Module;
import saddam.razon.themovie.R;
import saddam.razon.themovie.dataParser.Parser;
import saddam.razon.themovie.model.MovieDetails;
import saddam.razon.themovie.model.MovieSimilar;
import saddam.razon.themovie.utils.MyApplication;
import saddam.razon.themovie.utils.RecyclerTOuchListener;
import saddam.razon.themovie.utils.interfaces.ClickListener;
import saddam.razon.themovie.view.activity.MovieDetailsActivity;
import saddam.razon.themovie.view.adapter.AdapterSimilarRecycler;
import saddam.razon.themovie.viewModel.MoviDetailsViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
@Module
public class MovieDetailsFragment extends Fragment {

    String id;

    private TextView overview;
    private TextView productionCompany;
    private TextView budget;
    private TextView productionCountry;
    private TextView language;

    private RecyclerView recycler;
    AdapterSimilarRecycler adapterSimilarRecycler;
    ArrayList<MovieSimilar.Result> similarmovieList;
    LinearLayoutManager similarLayoutManager;

    MoviDetailsViewModel viewModel;

    @Inject
    Parser parser;
    @Inject
    MovieDetails movieDetails;
    ProgressDialog progressDialog;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getInit(view);
        id = getActivity().getIntent().getStringExtra("id");
        populateData();

    }

    private void populateData() {

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        viewModel.getMovieDetails(id).observe(this, this::setData);

    }

    private void setData(MovieDetails movieDetails) {

        String productionCompanyStr = "";
        String productionCountryStr = "";
        String languageStr = "";

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

        overview.setText(movieDetails.getOverview());
        productionCompany.setText(productionCompanyStr);
        productionCountry.setText(productionCountryStr);
        budget.setText(movieDetails.getBudget() + "");
        language.setText(languageStr);

        populateSimilarMovies(String.valueOf(movieDetails.getId()));

    }

    private void populateSimilarMovies(String movieId) {

        recycler.setLayoutManager(similarLayoutManager);
        recycler.setAdapter(adapterSimilarRecycler);

        viewModel.getSimilarMovies(movieId).observe(this, movieSimilar -> {
            if (movieSimilar != null) {
                similarmovieList.addAll(movieSimilar.getResults());
                adapterSimilarRecycler.notifyDataSetChanged();
            }

              progressDialog.dismiss();
            worksOnRecycler();

        });


    }

    private void worksOnRecycler() {

        recycler.addOnItemTouchListener(new RecyclerTOuchListener(getActivity(), recycler, new ClickListener() {
            @Override
            public void onCLick(View v, int position) {

                startActivity(new Intent(getActivity(), MovieDetailsActivity.class)
                        .putExtra("title", similarmovieList.get(position).getTitle())
                        .putExtra("id", String.valueOf(similarmovieList.get(position).getId())));

            }

            @Override
            public void onLongClick(View v, int position) {

            }
        }));


    }

    private void getInit(View view) {
        viewModel = ViewModelProviders.of(this).get(MoviDetailsViewModel.class);

        overview = view.findViewById(R.id.overview);
        productionCompany = view.findViewById(R.id.production_company);
        budget = view.findViewById(R.id.budget);
        productionCountry = view.findViewById(R.id.production_country);
        language = view.findViewById(R.id.language);

        recycler = view.findViewById(R.id.recycler);
        similarmovieList = new ArrayList<>();
        adapterSimilarRecycler = new AdapterSimilarRecycler(similarmovieList, getActivity());
        similarLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

    }

    @Inject
    public MovieDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MyApplication.getMyApplication(getActivity()).getComponent().inject(this);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_details, container, false);
    }

}
