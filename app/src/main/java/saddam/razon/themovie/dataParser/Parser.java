package saddam.razon.themovie.dataParser;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import saddam.razon.themovie.model.CastCrew;
import saddam.razon.themovie.model.Images;
import saddam.razon.themovie.model.Movie;
import saddam.razon.themovie.model.MovieDetails;
import saddam.razon.themovie.model.MovieSimilar;
import saddam.razon.themovie.model.Videos;
import saddam.razon.themovie.utils.Apis;

/**
 * Created by RAZON on 20-Jan-18.
 */

@Module
public class Parser{


 //   Client client;

    @Inject
    public Parser(){
       // getRetroClient();
    }

    @Provides
    @Singleton
    public Client getRetroClient(){

        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Apis.BASE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okBuilder.build());

        return builder.build().create(Client.class);

    }


    public Observable<Movie> getUpcomingMovieListObservble(int page, Client retrofitClient){
        return retrofitClient.getUpcomingMovieList(Apis.API_KEY, page).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Movie> getNewReleaseMovieListObservble(int page, Client retrofitClient){
        return retrofitClient.getNewReleaseMovieListObservble(Apis.API_KEY, page).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Movie> getTopRatedMovieListObservble(int page, Client retrofitClient){
        return retrofitClient.getTopRatedMovieListObservble(Apis.API_KEY, page).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public Observable<MovieDetails> getMovieDetails(String movie_id, Client retrofitClient){
        return retrofitClient.getMovieDetails(movie_id, Apis.API_KEY).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<MovieSimilar> getSimiliarMovies(String movie_id,  Client retrofitClient) {
        return retrofitClient.getSimilarMovies(movie_id, Apis.API_KEY).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Videos> getVideosMovies(String movie_id, Client retrofitClient) {
        return retrofitClient.getVideosMovies(movie_id, Apis.API_KEY).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Images> getImagesMovies(String movie_id, Client retrofitClient) {
        return retrofitClient.getImagesMovies(movie_id, Apis.API_KEY).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<CastCrew> getCastCrews(String movie_id, Client retrofitClient) {
        return retrofitClient.getCastCrews(movie_id, Apis.API_KEY).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

//    @Override
//    public Observable<Movie> getNewReleaseMovieListObservble(int page) {
//        return client.getNewReleaseMovieListObservble(Apis.API_KEY, page).subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
//
//    @Override
//    public Observable<Movie> getTopRatedMovieListObservble(int page) {
//        return client.getTopRatedMovieListObservble(Apis.API_KEY, page).subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
//
//    @Override
//    public Observable<Movie> getUpcomingMovieListObservble(int page) {
//        return client.getUpcomingMovieList(Apis.API_KEY, page).subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
}
