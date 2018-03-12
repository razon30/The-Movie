package saddam.razon.themovie.model;

/**
 * Created by razon.hossain on 2/22/2018.
 */

public class Trailers {

    private Images.Backdrop backdrop;
    private Videos.Result result;

    public Trailers(Images.Backdrop backdrop, Videos.Result result) {
        this.backdrop = backdrop;
        this.result = result;
    }

    public Images.Backdrop getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(Images.Backdrop backdrop) {
        this.backdrop = backdrop;
    }

    public Videos.Result getResult() {
        return result;
    }

    public void setResult(Videos.Result result) {
        this.result = result;
    }
}
