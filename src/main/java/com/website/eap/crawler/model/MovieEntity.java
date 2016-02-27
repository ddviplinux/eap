package com.website.eap.crawler.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * User: zhizunbao@wacai.com
 * Date: 15/12/22
 * Time: 19:04
 * Desc:
 */
public class MovieEntity {

    private long id;
    private float rate;
    @JSONField(name="cover_x")
    private int coverX;
    @JSONField(name="cover_y")
    private int coverY;
    private String cover;
    @JSONField(name="is_new")
    private boolean isNew;
    private boolean playable;
    @JSONField(name="is_beetle_subject")
    private boolean isBeetleSubject;
    private String title;
    private String url;

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCoverX() {
        return coverX;
    }

    public void setCoverX(int coverX) {
        this.coverX = coverX;
    }

    public int getCoverY() {
        return coverY;
    }

    public void setCoverY(int coverY) {
        this.coverY = coverY;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean isNew) {
        this.isNew = isNew;
    }

    public boolean isPlayable() {
        return playable;
    }

    public void setPlayable(boolean playable) {
        this.playable = playable;
    }

    public boolean isBeetleSubject() {
        return isBeetleSubject;
    }

    public void setBeetleSubject(boolean isBeetleSubject) {
        this.isBeetleSubject = isBeetleSubject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
