package app.hoocchi.perfectdemo.transition_demo;

/**
 * Created by st on 2016/11/4.
 */
public class TransitionItem {

    private int color ;
    private String title ;

    public TransitionItem(int color, String title) {
        this.color = color;
        this.title = title;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
