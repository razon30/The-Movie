package saddam.razon.themovie.model;

/**
 * Created by razon.hossain on 3/12/2018.
 */

public class CastCrewSync {

    String name;
    String role;
    String id;
    String image;

    public CastCrewSync(String name, String role, String id, String image) {
        this.name = name;
        this.role = role;
        this.id = id;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
