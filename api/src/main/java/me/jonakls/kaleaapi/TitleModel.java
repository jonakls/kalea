package me.jonakls.kaleaapi;

public class TitleModel {

    private String title;
    private String subtitle;
    private int fadeIn;
    private int stay;
    private int fadeOut;

    public TitleModel(String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        this.title = title;
        this.subtitle = subtitle;
        this.fadeIn = fadeIn;
        this.stay = stay;
        this.fadeOut = fadeOut;
    }

    public TitleModel(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
        this.fadeIn = 2;
        this.stay = 5;
        this.fadeOut = 2;
    }

    public TitleModel () { }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public int getFadeIn() {
        return fadeIn;
    }

    public void setFadeIn(int fadeIn) {
        this.fadeIn = fadeIn;
    }

    public int getStay() {
        return stay;
    }

    public void setStay(int stay) {
        this.stay = stay;
    }

    public int getFadeOut() {
        return fadeOut;
    }

    public void setFadeOut(int fadeOut) {
        this.fadeOut = fadeOut;
    }
}
