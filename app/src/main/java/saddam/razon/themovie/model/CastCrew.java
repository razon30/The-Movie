
package saddam.razon.themovie.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CastCrew {

    @SerializedName("cast")
    private List<CastCrew.Cast> Cast;
    @SerializedName("crew")
    private List<CastCrew.Crew> Crew;
    @SerializedName("id")
    private Long Id;

    public List<CastCrew.Cast> getCast() {
        return Cast;
    }

    public void setCast(List<CastCrew.Cast> cast) {
        Cast = cast;
    }

    public List<CastCrew.Crew> getCrew() {
        return Crew;
    }

    public void setCrew(List<CastCrew.Crew> crew) {
        Crew = crew;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public static class Cast {

        @SerializedName("cast_id")
        private Long CastId;
        @SerializedName("character")
        private String Character;
        @SerializedName("credit_id")
        private String CreditId;
        @SerializedName("gender")
        private Long Gender;
        @SerializedName("id")
        private Long Id;
        @SerializedName("name")
        private String Name;
        @SerializedName("order")
        private Long Order;
        @SerializedName("profile_path")
        private String ProfilePath;

        public Long getCastId() {
            return CastId;
        }

        public void setCastId(Long castId) {
            CastId = castId;
        }

        public String getCharacter() {
            return Character;
        }

        public void setCharacter(String character) {
            Character = character;
        }

        public String getCreditId() {
            return CreditId;
        }

        public void setCreditId(String creditId) {
            CreditId = creditId;
        }

        public Long getGender() {
            return Gender;
        }

        public void setGender(Long gender) {
            Gender = gender;
        }

        public Long getId() {
            return Id;
        }

        public void setId(Long id) {
            Id = id;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public Long getOrder() {
            return Order;
        }

        public void setOrder(Long order) {
            Order = order;
        }

        public String getProfilePath() {
            return ProfilePath;
        }

        public void setProfilePath(String profilePath) {
            ProfilePath = profilePath;
        }

    }

    public static class Crew {

        @SerializedName("credit_id")
        private String CreditId;
        @SerializedName("department")
        private String Department;
        @SerializedName("gender")
        private Long Gender;
        @SerializedName("id")
        private Long Id;
        @SerializedName("job")
        private String Job;
        @SerializedName("name")
        private String Name;
        @SerializedName("profile_path")
        private String ProfilePath;

        public String getCreditId() {
            return CreditId;
        }

        public void setCreditId(String creditId) {
            CreditId = creditId;
        }

        public String getDepartment() {
            return Department;
        }

        public void setDepartment(String department) {
            Department = department;
        }

        public Long getGender() {
            return Gender;
        }

        public void setGender(Long gender) {
            Gender = gender;
        }

        public Long getId() {
            return Id;
        }

        public void setId(Long id) {
            Id = id;
        }

        public String getJob() {
            return Job;
        }

        public void setJob(String job) {
            Job = job;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getProfilePath() {
            return ProfilePath;
        }

        public void setProfilePath(String profilePath) {
            ProfilePath = profilePath;
        }

    }
}
