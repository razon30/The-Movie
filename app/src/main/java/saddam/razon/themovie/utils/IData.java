package saddam.razon.themovie.utils;

import android.arch.lifecycle.LiveData;

import saddam.razon.themovie.model.Movie;

/**
 * Created by razon.hossain on 2/25/2018.
 */

public interface IData {

    public void fetchData(LiveData<Movie> movieLiveData);

}
