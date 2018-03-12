
package saddam.razon.themovie.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Images {

    @SerializedName("backdrops")
    private List<Backdrop> mBackdrops;
    @SerializedName("id")
    private Long mId;
    @SerializedName("posters")
    private List<Poster> mPosters;

    public List<Backdrop> getBackdrops() {
        return mBackdrops;
    }

    public void setBackdrops(List<Backdrop> backdrops) {
        mBackdrops = backdrops;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public List<Poster> getPosters() {
        return mPosters;
    }

    public void setPosters(List<Poster> posters) {
        mPosters = posters;
    }

    public static class Backdrop {

        @SerializedName("aspect_ratio")
        private Double mAspectRatio;
        @SerializedName("file_path")
        private String mFilePath;
        @SerializedName("height")
        private Long mHeight;
        @SerializedName("iso_639_1")
        private Object mIso6391;
        @SerializedName("vote_average")
        private Double mVoteAverage;
        @SerializedName("vote_count")
        private Long mVoteCount;
        @SerializedName("width")
        private Long mWidth;

        public Double getAspectRatio() {
            return mAspectRatio;
        }

        public void setAspectRatio(Double aspectRatio) {
            mAspectRatio = aspectRatio;
        }

        public String getFilePath() {
            return mFilePath;
        }

        public void setFilePath(String filePath) {
            mFilePath = filePath;
        }

        public Long getHeight() {
            return mHeight;
        }

        public void setHeight(Long height) {
            mHeight = height;
        }

        public Object getIso6391() {
            return mIso6391;
        }

        public void setIso6391(Object iso6391) {
            mIso6391 = iso6391;
        }

        public Double getVoteAverage() {
            return mVoteAverage;
        }

        public void setVoteAverage(Double voteAverage) {
            mVoteAverage = voteAverage;
        }

        public Long getVoteCount() {
            return mVoteCount;
        }

        public void setVoteCount(Long voteCount) {
            mVoteCount = voteCount;
        }

        public Long getWidth() {
            return mWidth;
        }

        public void setWidth(Long width) {
            mWidth = width;
        }

    }

    public static class Poster {

        @SerializedName("aspect_ratio")
        private Double mAspectRatio;
        @SerializedName("file_path")
        private String mFilePath;
        @SerializedName("height")
        private Long mHeight;
        @SerializedName("iso_639_1")
        private String mIso6391;
        @SerializedName("vote_average")
        private Double mVoteAverage;
        @SerializedName("vote_count")
        private Long mVoteCount;
        @SerializedName("width")
        private Long mWidth;

        public Double getAspectRatio() {
            return mAspectRatio;
        }

        public void setAspectRatio(Double aspectRatio) {
            mAspectRatio = aspectRatio;
        }

        public String getFilePath() {
            return mFilePath;
        }

        public void setFilePath(String filePath) {
            mFilePath = filePath;
        }

        public Long getHeight() {
            return mHeight;
        }

        public void setHeight(Long height) {
            mHeight = height;
        }

        public String getIso6391() {
            return mIso6391;
        }

        public void setIso6391(String iso6391) {
            mIso6391 = iso6391;
        }

        public Double getVoteAverage() {
            return mVoteAverage;
        }

        public void setVoteAverage(Double voteAverage) {
            mVoteAverage = voteAverage;
        }

        public Long getVoteCount() {
            return mVoteCount;
        }

        public void setVoteCount(Long voteCount) {
            mVoteCount = voteCount;
        }

        public Long getWidth() {
            return mWidth;
        }

        public void setWidth(Long width) {
            mWidth = width;
        }

    }
}
