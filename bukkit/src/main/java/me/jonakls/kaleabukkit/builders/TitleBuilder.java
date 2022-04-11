package me.jonakls.kaleabukkit.builders;

import me.jonakls.kaleaapi.TitleModel;

public class TitleBuilder {

    private final TitleModel titleModel;

    private TitleBuilder(String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        this.titleModel = new TitleModel();
        this.titleModel.setTitle(title);
        this.titleModel.setSubtitle(subtitle);
        this.titleModel.setFadeIn(fadeIn);
        this.titleModel.setStay(stay);
        this.titleModel.setFadeOut(fadeOut);
    }

    public static TitleBuilder from(String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        return new TitleBuilder(title, subtitle, fadeIn, stay, fadeOut);
    }

    public static TitleBuilder from(String title, String subtitle) {
        return new TitleBuilder(title, subtitle, 2, 5, 2);
    }

    public TitleBuilder setTitle(String title) {
        this.titleModel.setTitle(title);
        return this;
    }

    public TitleBuilder setSubtitle(String subtitle) {
        this.titleModel.setSubtitle(subtitle);
        return this;
    }

    public TitleBuilder setFadeIn(int fadeIn) {
        this.titleModel.setFadeIn(fadeIn);

        return this;
    }

    public TitleBuilder setStay(int stay) {
        this.titleModel.setStay(stay);
        return this;
    }

    public TitleBuilder setFadeOut(int fadeOut) {
        this.titleModel.setFadeOut(fadeOut);
        return this;
    }

    public TitleBuilder setTimes(int fadeIn, int stay, int fadeOut) {
        this.titleModel.setFadeIn(fadeIn);
        this.titleModel.setStay(stay);
        this.titleModel.setFadeOut(fadeOut);
        return this;
    }

    public TitleModel build() {
        return titleModel;
    }
}
