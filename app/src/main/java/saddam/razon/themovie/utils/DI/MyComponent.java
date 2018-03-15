package saddam.razon.themovie.utils.DI;

import javax.inject.Singleton;

import dagger.Component;
import saddam.razon.themovie.utils.MyApplication;
import saddam.razon.themovie.view.fragments.movieDetails.CastCrewFragment;
import saddam.razon.themovie.view.fragments.movieDetails.TrailersFragment;
import saddam.razon.themovie.view.fragments.movieDetails.MovieDetailsFragment;
import saddam.razon.themovie.viewModel.MoviDetailsViewModel;
import saddam.razon.themovie.viewModel.MovieViewModel;
import saddam.razon.themovie.dataParser.Parser;
import saddam.razon.themovie.model.MovieDetails;
import saddam.razon.themovie.view.activity.MainActivity;
import saddam.razon.themovie.view.activity.MovieDetailsActivity;
import saddam.razon.themovie.view.fragments.mainActivity.NewReleaseFragment;
import saddam.razon.themovie.view.fragments.mainActivity.TopRatedFragment;
import saddam.razon.themovie.view.fragments.mainActivity.UpcomingFragment;

/**
 * Created by RAZON on 20-Jan-18.
 */

@Singleton
@Component(modules = {Parser.class,
        NewReleaseFragment.class, UpcomingFragment.class, TopRatedFragment.class, MovieDetails.class,
        CastCrewFragment.class, TrailersFragment.class, MovieDetailsFragment.class})
public interface MyComponent {

    void inject(MainActivity mainActivity);
    void inject(MovieDetailsActivity movieDetailsActivity);
    void inject(MyApplication myApplication);
    void inject(UpcomingFragment upcomingFragment);
    void inject(TopRatedFragment topRatedFragment);
    void inject(NewReleaseFragment newReleaseFragment);
    void inject(MovieViewModel movieViewModel);
    void inject(MoviDetailsViewModel movieViewModel);
    void inject(CastCrewFragment movieViewModel);
    void inject(TrailersFragment movieViewModel);
    void inject(MovieDetailsFragment movieViewModel);

}
