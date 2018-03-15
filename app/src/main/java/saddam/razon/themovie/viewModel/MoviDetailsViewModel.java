package saddam.razon.themovie.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import saddam.razon.themovie.dataParser.Client;
import saddam.razon.themovie.dataParser.Parser;
import saddam.razon.themovie.model.CastCrew;
import saddam.razon.themovie.model.CastCrewSync;
import saddam.razon.themovie.model.Images;
import saddam.razon.themovie.model.MovieDetails;
import saddam.razon.themovie.model.MovieSimilar;
import saddam.razon.themovie.model.Trailer;
import saddam.razon.themovie.model.Videos;
import saddam.razon.themovie.utils.MyApplication;

/**
 * Created by razon.hossain on 2/15/2018.
 */

public class MoviDetailsViewModel extends AndroidViewModel {

    @Inject
    Parser parser;
    private Client client;

    private MutableLiveData<MovieDetails> movieDetails;
    private MutableLiveData<MovieSimilar> similarMovies;
    private MutableLiveData<Trailer> trailersList;
    private MutableLiveData<ArrayList<CastCrewSync>> castCrewList;

    private Observable<MovieSimilar> observableSimilar;
    private Observable<Videos> observableVideos;
    private Observable<Images> observableImages;
    private Observable<MovieDetails> observableDetails;
    private Observable<CastCrew> observableCastCrews;

    public MoviDetailsViewModel(@NonNull Application application) {
        super(application);
        MyApplication.getMyApplication(application).getComponent().inject(this);

        if (client == null){
            client = MyApplication.getMyApplication(application).getClient();
        }

        if (movieDetails == null){
            movieDetails = new MutableLiveData<>();
        }

        similarMovies = new MutableLiveData<>();
        trailersList = new MutableLiveData<>();
        castCrewList = new MutableLiveData<>();
    }

    public MutableLiveData<MovieDetails> getMovieDetails(String id){

       // MutableLiveData<MovieDetails> movieDetails = new MutableLiveData<>();
        observableDetails = parser.getMovieDetails(id, client);

        observableDetails
                .subscribe(movieDetails::setValue, throwable -> {
                    Log.d("movieError", throwable.getLocalizedMessage());
                    Toast.makeText(getApplication(), "Something wrong, please try later", Toast.LENGTH_LONG).show();
                }, ()->{

                });

        return movieDetails;
    }

    public MutableLiveData<MovieSimilar> getSimilarMovies(String id){

      //  MutableLiveData<MovieSimilar> movieDetails = new MutableLiveData<>();
        observableSimilar = parser.getSimiliarMovies(id,client);

        observableSimilar.subscribe(similarMovies::setValue, throwable -> {
            Toast.makeText(getApplication(), "Something wrong, please try later", Toast.LENGTH_LONG).show();
        },()->{

        });

       return similarMovies;
    }

    public MutableLiveData<ArrayList<CastCrewSync>> getMovieCastCrew(String id){

        //  MutableLiveData<MovieSimilar> movieDetails = new MutableLiveData<>();

        ArrayList<CastCrewSync> castCrewSyncs = new ArrayList<>();

        observableCastCrews = parser.getCastCrews(id,client);

        observableCastCrews.subscribe(castCrew -> {

            for (CastCrew.Cast cast : castCrew.getCast()){

                CastCrewSync castCrewSync = new CastCrewSync(cast.getName(), cast.getCharacter(), String.valueOf(cast.getId()), cast.getProfilePath());
                castCrewSyncs.add(castCrewSync);

            }

            for (CastCrew.Crew cast : castCrew.getCrew()){

                CastCrewSync castCrewSync = new CastCrewSync(cast.getName(), cast.getJob(), String.valueOf(cast.getId()), cast.getProfilePath());
                castCrewSyncs.add(castCrewSync);

            }

        }, throwable -> {
            Toast.makeText(getApplication(), "Something wrong, please try later", Toast.LENGTH_LONG).show();
        },()->{

            castCrewList.setValue(castCrewSyncs);

        });

        return castCrewList;
    }

    public MutableLiveData<Trailer> getTrailers(String id){

   //     MutableLiveData<Trailer> movieDetails = new MutableLiveData<>();
       observableVideos = parser.getVideosMovies(id,client);
       observableImages = parser.getImagesMovies(id,client);


        Observable<Trailer> responseObservable = Observable.zip(observableImages, observableVideos, (images, videos) -> new Trailer(images, videos.getResults()));

        responseObservable.subscribe(new Observer<Trailer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Trailer trailer) {
                trailersList.postValue(trailer);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {



            }
        });


        return trailersList;
    }



}
