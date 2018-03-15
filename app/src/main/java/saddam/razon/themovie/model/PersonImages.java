package saddam.razon.themovie.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PersonImages {
    @SerializedName("id")
    private final int id;

    @SerializedName("profiles")
    private final List<Profiles> profiles;

    public PersonImages(int id, List<Profiles> profiles) {
        this.id = id;
        this.profiles = profiles;
    }

    public int getId() {
        return id;
    }

    public List<Profiles> getProfiles() {
        return profiles;
    }

    public static class Profiles {
        @SerializedName("aspect_ratio")
        private final double aspectRatio;

        @SerializedName("file_path")
        private final String filePath;

        @SerializedName("height")
        private final int height;

        @SerializedName("iso_639_1")
        private final Object iso6391;

        @SerializedName("vote_average")
        private final double voteAverage;

        @SerializedName("vote_count")
        private final int voteCount;

        @SerializedName("width")
        private final int width;

        public Profiles(double aspectRatio, String filePath, int height, Object iso6391,
                        double voteAverage, int voteCount, int width) {
            this.aspectRatio = aspectRatio;
            this.filePath = filePath;
            this.height = height;
            this.iso6391 = iso6391;
            this.voteAverage = voteAverage;
            this.voteCount = voteCount;
            this.width = width;
        }

        public double getAspectRatio() {
            return aspectRatio;
        }

        public String getFilePath() {
            return filePath;
        }

        public int getHeight() {
            return height;
        }

        public Object getIso6391() {
            return iso6391;
        }

        public double getVoteAverage() {
            return voteAverage;
        }

        public int getVoteCount() {
            return voteCount;
        }

        public int getWidth() {
            return width;
        }
    }
}
