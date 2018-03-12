package saddam.razon.themovie.viewModel;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.widget.Toast;

import javax.inject.Inject;

import io.reactivex.Observable;
import saddam.razon.themovie.dataParser.Client;
import saddam.razon.themovie.dataParser.Parser;
import saddam.razon.themovie.model.Movie;
import saddam.razon.themovie.utils.MyApplication;

/**
 * Created by razon.hossain on 2/14/2018.
 */

public class MovieViewModel extends ViewModel {

    @Inject
    Parser parser;
    private Client client;
    @SuppressLint("StaticFieldLeak")
    private
    Context application;

    private MutableLiveData<Movie> liveDataNewReleased;
    private MutableLiveData<Movie> liveDataTopRated;
    private MutableLiveData<Movie> liveDataUpcoming;


//    public MovieViewModel(@NonNull Application application) {
//        super(application);
//        MyApplication.getMyApplication(application).getComponent().inject(this);
//        this.application = application;
//        client = MyApplication.getMyApplication(application).getClient();
//        liveDataNewReleased = new MutableLiveData<>();
//        liveDataTopRated = new MutableLiveData<>();
//        liveDataUpcoming = new MutableLiveData<>();
//
//    }

    public void initViewModel(Context context){
        MyApplication.getMyApplication(context).getComponent().inject(this);
        this.application = context;
        client = MyApplication.getMyApplication(context).getClient();
        liveDataNewReleased = new MutableLiveData<>();
        liveDataTopRated = new MutableLiveData<>();
        liveDataUpcoming = new MutableLiveData<>();
    }


    public LiveData<Movie> getNewReleaseMovies(int pageNumber) {

      //  final MutableLiveData<Movie> liveData = new MutableLiveData<>();


        Observable<Movie> observable = parser.getNewReleaseMovieListObservble(pageNumber, client);
        observable
                .subscribe(liveDataNewReleased::postValue, throwable -> {
                    Toast.makeText(getApplication(), "Something wrong, please try later", Toast.LENGTH_LONG).show();

                }, () -> {

                });

        return liveDataNewReleased;

    }


    public LiveData<Movie> getTopRatedMovies(int pageNumber) {

      //  final MutableLiveData<Movie> liveData = new MutableLiveData<>();

        Observable<Movie> observable = parser.getTopRatedMovieListObservble(pageNumber, client);
        observable
                .subscribe(liveDataTopRated::postValue, throwable -> {
                    Toast.makeText(getApplication(), "Something wrong, please try later", Toast.LENGTH_LONG).show();

                }, () -> {

                });

        return liveDataTopRated;

    }

    public LiveData<Movie> getUpcomingMovies(int pageNumber) {

     //   final MutableLiveData<Movie> liveData = new MutableLiveData<>();

        Observable<Movie> observable = parser.getUpcomingMovieListObservble(pageNumber, client);
        observable
                .subscribe(liveDataUpcoming::postValue, throwable -> {
                    Toast.makeText(getApplication(), "Something wrong, please try later", Toast.LENGTH_LONG).show();

                }, () -> {

                });

        return liveDataUpcoming;

    }



    public Context getApplication() {

        return application;
    }

}
