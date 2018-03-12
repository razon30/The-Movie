package saddam.razon.themovie.view.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.Module;
import saddam.razon.themovie.R;
import saddam.razon.themovie.dataParser.Parser;
import saddam.razon.themovie.model.Movie;
import saddam.razon.themovie.utils.MyApplication;
import saddam.razon.themovie.utils.RecyclerTOuchListener;
import saddam.razon.themovie.utils.interfaces.ClickListener;
import saddam.razon.themovie.view.activity.MovieDetailsActivity;
import saddam.razon.themovie.view.adapter.AdapterMovieListRecycler;
import saddam.razon.themovie.viewModel.MovieViewModel;


@Module
public class TopRatedFragment extends Fragment {

    RecyclerView recyclerView;
    @Inject
    Parser parser;
    ArrayList<Movie.Results> movies;
    ArrayList<Long> movieID;
    GridLayoutManager layoutManager;
    AdapterMovieListRecycler adapter;
    int page = 1;

    private int lastPage;

    MovieViewModel viewModel;
    private boolean loading = false;
    private ContentLoadingProgressBar contentLoadingProgressbar;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_upcoming, container, false);
        init(view);
        fetchData(page);
        worksOnRecycler();
        initView(view);
        return view;
    }

    private void worksOnRecycler() {

        recyclerView.addOnItemTouchListener(new RecyclerTOuchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onCLick(View v, int position) {

                startActivity(new Intent(getActivity(), MovieDetailsActivity.class)
                        .putExtra("title", movies.get(position).getTitle())
                        .putExtra("id", String.valueOf(movies.get(position).getId())));

            }

            @Override
            public void onLongClick(View v, int position) {

            }
        }));


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                int lastvisibleitemposition = layoutManager.findLastVisibleItemPosition();

                if (lastvisibleitemposition == adapter.getItemCount() - 1) {

                    if (!loading && (page < lastPage)) {

                        loading = true;
                        fetchData((++page));

                    }


                }
            }
        });


    }

    private void fetchData(int pageNumber) {

        if (pageNumber > 1) {
            contentLoadingProgressbar.setVisibility(View.VISIBLE);
            contentLoadingProgressbar.show();
        }


        viewModel.getTopRatedMovies(pageNumber).observe(this, results -> {

            if (results != null) {
                for (int i = 0; i < results.getResults().size(); i++) {

                    long id = results.getResults().get(i).getId();
                    if (!movieID.contains(id)) {
                        movieID.add(id);
                        movies.add(results.getResults().get(i));
                    }

                }
            }

            if (results != null) {
                lastPage = results.getTotalPages();
            }
            adapter.notifyDataSetChanged();
            loading = false;

            if (contentLoadingProgressbar.isShown()) {
                contentLoadingProgressbar.hide();
                contentLoadingProgressbar.setVisibility(View.GONE);
            }

        });
    }


    private void init(View view) {
        viewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        viewModel.initViewModel(getActivity());
        movies = new ArrayList<>();
        movieID = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recycler);
        layoutManager = new GridLayoutManager(getActivity(), 2);
        adapter = new AdapterMovieListRecycler(movies, getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getMyApplication(getActivity()).getComponent().inject(this);
    }


    @Inject
    public TopRatedFragment() {
        // Required empty public constructor
    }


    private void initView(View view) {
        contentLoadingProgressbar = (ContentLoadingProgressBar) view.findViewById(R.id.contentLoadingProgressbar);
    }
}
