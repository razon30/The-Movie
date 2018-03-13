package saddam.razon.themovie.dataParser;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import saddam.razon.themovie.model.CastCrew;
import saddam.razon.themovie.model.Images;
import saddam.razon.themovie.model.Movie;
import saddam.razon.themovie.model.MovieDetails;
import saddam.razon.themovie.model.MovieSimilar;
import saddam.razon.themovie.model.Videos;

/**
 * Created by RAZON on 20-Jan-18.
 */

public interface Client {


    @GET("upcoming")
    Observable<Movie> getUpcomingMovieList(@Query("api_key") String api_key, @Query("page") int page);

    @GET("now_playing")
    Observable<Movie> getNewReleaseMovieListObservble(@Query("api_key") String api_key, @Query("page") int page);

    @GET("top_rated")
    Observable<Movie> getTopRatedMovieListObservble(@Query("api_key") String api_key, @Query("page") int page);

    @GET("{movie_id}")
    Observable<MovieDetails> getMovieDetails(@Path("movie_id") String movie_id, @Query("api_key") String api_key);

    @GET("{movie_id}/similar")
    Observable<MovieSimilar> getSimilarMovies(@Path("movie_id") String movie_id, @Query("api_key") String api_key);

    @GET("{movie_id}/videos")
    Observable<Videos> getVideosMovies(@Path("movie_id") String movie_id, @Query("api_key") String api_key);

    @GET("{movie_id}/images")
    Observable<Images> getImagesMovies(@Path("movie_id") String movie_id, @Query("api_key") String api_key);

    @GET("{movie_id}/credits")
    Observable<CastCrew> getCastCrews(@Path("movie_id") String movie_id, @Query("api_key") String api_key);

}
