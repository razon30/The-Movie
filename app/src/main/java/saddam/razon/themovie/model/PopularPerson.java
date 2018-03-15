package saddam.razon.themovie.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PopularPerson {
    @SerializedName("page")
    private final int page;

    @SerializedName("total_results")
    private final int totalResults;

    @SerializedName("total_pages")
    private final int totalPages;

    @SerializedName("results")
    private final List<Results> results;

    public PopularPerson(int page, int totalResults, int totalPages, List<Results> results) {
        this.page = page;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public List<Results> getResults() {
        return results;
    }

    public static class Results {
        @SerializedName("popularity")
        private final double popularity;

        @SerializedName("id")
        private final int id;

        @SerializedName("profile_path")
        private final String profilePath;

        @SerializedName("name")
        private final String name;

        @SerializedName("known_for")
        private final List<KnownFor> knownFor;

        @SerializedName("adult")
        private final boolean adult;

        public Results(double popularity, int id, String profilePath, String name,
                       List<KnownFor> knownFor, boolean adult) {
            this.popularity = popularity;
            this.id = id;
            this.profilePath = profilePath;
            this.name = name;
            this.knownFor = knownFor;
            this.adult = adult;
        }

        public double getPopularity() {
            return popularity;
        }

        public int getId() {
            return id;
        }

        public String getProfilePath() {
            return profilePath;
        }

        public String getName() {
            return name;
        }

        public List<KnownFor> getKnownFor() {
            return knownFor;
        }

        public boolean isAdult() {
            return adult;
        }

        public static class KnownFor {
            @SerializedName("vote_average")
            private final double voteAverage;

            @SerializedName("vote_count")
            private final int voteCount;

            @SerializedName("id")
            private final int id;

            @SerializedName("video")
            private final boolean video;

            @SerializedName("media_type")
            private final String mediaType;

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

            public KnownFor(double voteAverage, int voteCount, int id, boolean video,
                            String mediaType, String title, double popularity, String posterPath,
                            String originalLanguage, String originalTitle, List<Integer> genreIds,
                            String backdropPath, boolean adult, String overview, String releaseDate) {
                this.voteAverage = voteAverage;
                this.voteCount = voteCount;
                this.id = id;
                this.video = video;
                this.mediaType = mediaType;
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

            public double getVoteAverage() {
                return voteAverage;
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

            public String getMediaType() {
                return mediaType;
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
    }
}
