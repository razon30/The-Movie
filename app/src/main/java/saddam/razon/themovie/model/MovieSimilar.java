
package saddam.razon.themovie.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class MovieSimilar {

    @SerializedName("page")
    private Long Page;
    @SerializedName("results")
    private List<Result> Results;
    @SerializedName("total_pages")
    private Long TotalPages;
    @SerializedName("total_results")
    private Long TotalResults;

    public Long getPage() {
        return Page;
    }

    public void setPage(Long page) {
        Page = page;
    }

    public List<Result> getResults() {
        return Results;
    }

    public void setResults(List<Result> results) {
        Results = results;
    }

    public Long getTotalPages() {
        return TotalPages;
    }

    public void setTotalPages(Long totalPages) {
        TotalPages = totalPages;
    }

    public Long getTotalResults() {
        return TotalResults;
    }

    public void setTotalResults(Long totalResults) {
        TotalResults = totalResults;
    }

    public class Result {

        @SerializedName("adult")
        private Boolean Adult;
        @SerializedName("backdrop_path")
        private String BackdropPath;
        @SerializedName("genre_ids")
        private List<Long> GenreIds;
        @SerializedName("id")
        private Long Id;
        @SerializedName("original_language")
        private String OriginalLanguage;
        @SerializedName("original_title")
        private String OriginalTitle;
        @SerializedName("overview")
        private String Overview;
        @SerializedName("popularity")
        private Double Popularity;
        @SerializedName("poster_path")
        private String PosterPath;
        @SerializedName("release_date")
        private String ReleaseDate;
        @SerializedName("title")
        private String Title;
        @SerializedName("video")
        private Boolean Video;
        @SerializedName("vote_average")
        private Double VoteAverage;
        @SerializedName("vote_count")
        private Long VoteCount;

        public Boolean getAdult() {
            return Adult;
        }

        public void setAdult(Boolean adult) {
            Adult = adult;
        }

        public String getBackdropPath() {
            return BackdropPath;
        }

        public void setBackdropPath(String backdropPath) {
            BackdropPath = backdropPath;
        }

        public List<Long> getGenreIds() {
            return GenreIds;
        }

        public void setGenreIds(List<Long> genreIds) {
            GenreIds = genreIds;
        }

        public Long getId() {
            return Id;
        }

        public void setId(Long id) {
            Id = id;
        }

        public String getOriginalLanguage() {
            return OriginalLanguage;
        }

        public void setOriginalLanguage(String originalLanguage) {
            OriginalLanguage = originalLanguage;
        }

        public String getOriginalTitle() {
            return OriginalTitle;
        }

        public void setOriginalTitle(String originalTitle) {
            OriginalTitle = originalTitle;
        }

        public String getOverview() {
            return Overview;
        }

        public void setOverview(String overview) {
            Overview = overview;
        }

        public Double getPopularity() {
            return Popularity;
        }

        public void setPopularity(Double popularity) {
            Popularity = popularity;
        }

        public String getPosterPath() {
            return PosterPath;
        }

        public void setPosterPath(String posterPath) {
            PosterPath = posterPath;
        }

        public String getReleaseDate() {
            return ReleaseDate;
        }

        public void setReleaseDate(String releaseDate) {
            ReleaseDate = releaseDate;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String title) {
            Title = title;
        }

        public Boolean getVideo() {
            return Video;
        }

        public void setVideo(Boolean video) {
            Video = video;
        }

        public Double getVoteAverage() {
            return VoteAverage;
        }

        public void setVoteAverage(Double voteAverage) {
            VoteAverage = voteAverage;
        }

        public Long getVoteCount() {
            return VoteCount;
        }

        public void setVoteCount(Long voteCount) {
            VoteCount = voteCount;
        }

    }


}
