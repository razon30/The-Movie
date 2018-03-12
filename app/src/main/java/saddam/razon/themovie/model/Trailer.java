package saddam.razon.themovie.model;

import java.util.List;

/**
 * Created by razon.hossain on 2/22/2018.
 */

public class Trailer {

    public Images backdrops;
    public List<Videos.Result> videos;

    public Trailer(){}

    public Trailer(Images backdrops, List<Videos.Result> videos) {
        this.backdrops = backdrops;
        this.videos = videos;
    }

    public Images getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(Images backdrops) {
        this.backdrops = backdrops;
    }

    public List<Videos.Result> getVideos() {
        return videos;
    }

    public void setVideos(List<Videos.Result> videos) {
        this.videos = videos;
    }
}
