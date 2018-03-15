package saddam.razon.themovie.view.fragments.movieDetails;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.Module;
import saddam.razon.themovie.R;
import saddam.razon.themovie.model.Trailers;
import saddam.razon.themovie.view.adapter.AdapterTrailersRecycler;
import saddam.razon.themovie.viewModel.MoviDetailsViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
@Module
public class TrailersFragment extends Fragment {

    ArrayList<Trailers> trailerList;
    private RecyclerView recyclerTailers;
    private LinearLayout trailers;
    AdapterTrailersRecycler adapterTrailersRecycler;
    LinearLayoutManager trailerLayoutManager;

    String id;
    MoviDetailsViewModel viewModel;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        id = getActivity().getIntent().getStringExtra("id");
        populateData(id);

    }

    private void populateData(String movieId) {

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

                adapterTrailersRecycler.notifyDataSetChanged();
            } else {

                trailers.setVisibility(View.GONE);

            }

        });


    }

    private void initView(View view) {

        viewModel = ViewModelProviders.of(this).get(MoviDetailsViewModel.class);
        recyclerTailers = view.findViewById(R.id.recycler_tailers);
        trailers = view.findViewById(R.id.trailers);
        trailerList = new ArrayList<>();
        adapterTrailersRecycler = new AdapterTrailersRecycler(trailerList, getActivity());
        trailerLayoutManager = new GridLayoutManager(getActivity(), 2);

    }

    @Inject
    public TrailersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_images, container, false);
    }

}
