package saddam.razon.themovie.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.inject.Inject;

import dagger.Module;

/**
 * Created by RAZON on 20-Jan-18.
 */

@Module
public class MovieDetails {

    @Inject
    public MovieDetails(){}


    @SerializedName("adult")
    private  boolean adult;

    @SerializedName("backdrop_path")
    private  String backdropPath;

    @SerializedName("belongs_to_collection")
    private  BelongsToCollection belongsToCollection;

    @SerializedName("budget")
    private  int budget;

    @SerializedName("genres")
    private  List<Genres> genres;

    @SerializedName("homepage")
    private  String homepage;

    @SerializedName("id")
    private  int id;

    @SerializedName("imdb_id")
    private  String imdbId;

    @SerializedName("original_language")
    private  String originalLanguage;

    @SerializedName("original_title")
    private  String originalTitle;

    @SerializedName("overview")
    private  String overview;

    @SerializedName("popularity")
    private  double popularity;

    @SerializedName("poster_path")
    private  String posterPath;

    @SerializedName("production_companies")
    private  List<ProductionCompanies> productionCompanies;

    @SerializedName("production_countries")
    private  List<ProductionCountries> productionCountries;

    @SerializedName("release_date")
    private  String releaseDate;

    @SerializedName("revenue")
    private  int revenue;

    @SerializedName("runtime")
    private  int runtime;

    @SerializedName("spoken_languages")
    private  List<SpokenLanguages> spokenLanguages;

    @SerializedName("status")
    private  String status;

    @SerializedName("tagline")
    private  String tagline;

    @SerializedName("title")
    private  String title;

    @SerializedName("video")
    private  boolean video;

    @SerializedName("vote_average")
    private  double voteAverage;

    @SerializedName("vote_count")
    private  int voteCount;

    public MovieDetails(boolean adult, String backdropPath, BelongsToCollection belongsToCollection,
                        int budget, List<Genres> genres, String homepage, int id, String imdbId,
                        String originalLanguage, String originalTitle, String overview, double popularity,
                        String posterPath, List<ProductionCompanies> productionCompanies,
                        List<ProductionCountries> productionCountries, String releaseDate, int revenue,
                        int runtime, List<SpokenLanguages> spokenLanguages, String status, String tagline,
                        String title, boolean video, double voteAverage, int voteCount) {
        this.adult = adult;
        this.backdropPath = backdropPath;
        this.belongsToCollection = belongsToCollection;
        this.budget = budget;
        this.genres = genres;
        this.homepage = homepage;
        this.id = id;
        this.imdbId = imdbId;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.overview = overview;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.productionCompanies = productionCompanies;
        this.productionCountries = productionCountries;
        this.releaseDate = releaseDate;
        this.revenue = revenue;
        this.runtime = runtime;
        this.spokenLanguages = spokenLanguages;
        this.status = status;
        this.tagline = tagline;
        this.title = title;
        this.video = video;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public BelongsToCollection getBelongsToCollection() {
        return belongsToCollection;
    }

    public int getBudget() {
        return budget;
    }

    public List<Genres> getGenres() {
        return genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public int getId() {
        return id;
    }

    public String getImdbId() {
        return imdbId;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public List<ProductionCompanies> getProductionCompanies() {
        return productionCompanies;
    }

    public List<ProductionCountries> getProductionCountries() {
        return productionCountries;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getRevenue() {
        return revenue;
    }

    public int getRuntime() {
        return runtime;
    }

    public List<SpokenLanguages> getSpokenLanguages() {
        return spokenLanguages;
    }

    public String getStatus() {
        return status;
    }

    public String getTagline() {
        return tagline;
    }

    public String getTitle() {
        return title;
    }

    public boolean isVideo() {
        return video;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public static class BelongsToCollection {
        @SerializedName("id")
        private  int id;

        @SerializedName("name")
        private  String name;

        @SerializedName("poster_path")
        private  Object posterPath;

        @SerializedName("backdrop_path")
        private  Object backdropPath;

        public BelongsToCollection(int id, String name, Object posterPath, Object backdropPath) {
            this.id = id;
            this.name = name;
            this.posterPath = posterPath;
            this.backdropPath = backdropPath;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public Object getPosterPath() {
            return posterPath;
        }

        public Object getBackdropPath() {
            return backdropPath;
        }
    }

    public static class Genres {
        @SerializedName("id")
        private  int id;

        @SerializedName("name")
        private  String name;

        public Genres(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    public static class ProductionCompanies {
        @SerializedName("name")
        private  String name;

        @SerializedName("id")
        private  int id;

        public ProductionCompanies(String name, int id) {
            this.name = name;
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }
    }

    public static class ProductionCountries {
        @SerializedName("iso_3166_1")
        private  String iso31661;

        @SerializedName("name")
        private  String name;

        public ProductionCountries(String iso31661, String name) {
            this.iso31661 = iso31661;
            this.name = name;
        }

        public String getIso31661() {
            return iso31661;
        }

        public String getName() {
            return name;
        }
    }

    public static class SpokenLanguages {
        @SerializedName("iso_639_1")
        private  String iso6391;

        @SerializedName("name")
        private  String name;

        public SpokenLanguages(String iso6391, String name) {
            this.iso6391 = iso6391;
            this.name = name;
        }

        public String getIso6391() {
            return iso6391;
        }

        public String getName() {
            return name;
        }
    }
}
