package saddam.razon.themovie.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
/**
 * Created by RAZON on 20-Jan-18.
 */
public class Movie {
    @SerializedName("results")
    private final List<Results> results;

    @SerializedName("page")
    private final int page;

    @SerializedName("total_results")
    private final int totalResults;

    @SerializedName("dates")
    private final Dates dates;

    @SerializedName("total_pages")
    private final int totalPages;

    public Movie(List<Results> results, int page, int totalResults, Dates dates, int totalPages) {
        this.results = results;
        this.page = page;
        this.totalResults = totalResults;
        this.dates = dates;
        this.totalPages = totalPages;
    }



    public List<Results> getResults() {
        return results;
    }

    public int getPage() {
        return page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public Dates getDates() {
        return dates;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public static class Results {
        @SerializedName("vote_count")
        private final int voteCount;

        @SerializedName("id")
        private final int id;

        @SerializedName("video")
        private final boolean video;

        @SerializedName("vote_average")
        private final double voteAverage;

        @SerializedName("title")
        private final String title;

        @SerializedName("popularity")
        private final double popularity;

        @SerializedName("poster_path")
        private final String posterPath;

        @SerializedName("original_language")
        private final String originalLanguage;

        @SerializedName("original_title")
        private final String originalTitle;

        @SerializedName("genre_ids")
        private final List<Integer> genreIds;

        @SerializedName("backdrop_path")
        private final String backdropPath;

        @SerializedName("adult")
        private final boolean adult;

        @SerializedName("overview")
        private final String overview;

        @SerializedName("release_date")
        private final String releaseDate;

        public Results(int voteCount, int id, boolean video, double voteAverage, String title,
                       double popularity, String posterPath, String originalLanguage, String originalTitle,
                       List<Integer> genreIds, String backdropPath, boolean adult, String overview,
                       String releaseDate) {
            this.voteCount = voteCount;
            this.id = id;
            this.video = video;
            this.voteAverage = voteAverage;
            this.title = title;
            this.popularity = popularity;
            this.posterPath = posterPath;
            this.originalLanguage = originalLanguage;
            this.originalTitle = originalTitle;
            this.genreIds = genreIds;
            this.backdropPath = backdropPath;
            this.adult = adult;
            this.overview = overview;
            this.releaseDate = releaseDate;
        }

        public int getVoteCount() {
            return voteCount;
        }

        public int getId() {
            return id;
        }

        public boolean isVideo() {
            return video;
        }

        public double getVoteAverage() {
            return voteAverage;
        }

        public String getTitle() {
            return title;
        }

        public double getPopularity() {
            return popularity;
        }

        public String getPosterPath() {
            return posterPath;
        }

        public String getOriginalLanguage() {
            return originalLanguage;
        }

        public String getOriginalTitle() {
            return originalTitle;
        }

        public List<Integer> getGenreIds() {
            return genreIds;
        }

        public String getBackdropPath() {
            return backdropPath;
        }

        public boolean isAdult() {
            return adult;
        }

        public String getOverview() {
            return overview;
        }

        public String getReleaseDate() {
            return releaseDate;
        }
    }

    public static class Dates {
        @SerializedName("maximum")
        private final String maximum;

        @SerializedName("minimum")
        private final String minimum;

        public Dates(String maximum, String minimum) {
            this.maximum = maximum;
            this.minimum = minimum;
        }

        public String getMaximum() {
            return maximum;
        }

        public String getMinimum() {
            return minimum;
        }
    }
}
