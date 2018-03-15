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

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.Module;
import saddam.razon.themovie.R;
import saddam.razon.themovie.dataParser.Parser;
import saddam.razon.themovie.model.CastCrewSync;
import saddam.razon.themovie.utils.MyApplication;
import saddam.razon.themovie.view.adapter.AdapterCastCrewRecycler;
import saddam.razon.themovie.viewModel.MoviDetailsViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
@Module
public class CastCrewFragment extends Fragment {


    ArrayList<CastCrewSync> castCrewSyncList;
    AdapterCastCrewRecycler adapterCastCrewRecycler;
    LinearLayoutManager castCrewLayoutManager;
    RecyclerView castRcrewRecycler;


    MoviDetailsViewModel viewModel;

    @Inject
    Parser parser;

    String id;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);

        id = getActivity().getIntent().getStringExtra("id");
        populateCastAndCrew(String.valueOf(id));
    }

    private void initView(View view) {
        viewModel = ViewModelProviders.of(this).get(MoviDetailsViewModel.class);
        castCrewSyncList = new ArrayList<>();
        castRcrewRecycler = view.findViewById(R.id.cast_crew_recycler);
        castCrewLayoutManager = new GridLayoutManager(getActivity(), 2);
        adapterCastCrewRecycler = new AdapterCastCrewRecycler(castCrewSyncList, getActivity());
        castRcrewRecycler.setLayoutManager(castCrewLayoutManager);
        castRcrewRecycler.setAdapter(adapterCastCrewRecycler);

    }

    private void populateCastAndCrew(String movieId) {

        viewModel.getMovieCastCrew(movieId).observe(this, castCrewSyncs -> {
            castCrewSyncList.addAll(castCrewSyncs);
            adapterCastCrewRecycler.notifyDataSetChanged();
        });

    }

    @Inject
    public CastCrewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MyApplication.getMyApplication(getActivity()).getComponent().inject(this);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cast_crew, container, false);
    }

}
