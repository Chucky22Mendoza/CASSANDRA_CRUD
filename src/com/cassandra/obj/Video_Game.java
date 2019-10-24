/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cassandra.obj;

/**
 *
 * @author loginlock
 */
public class Video_Game {
    private String id;
    private String title;
    private String director;
    private String producer;
    private String platform;
    private String release_date;

    public Video_Game() {
    }

    public Video_Game(String id, String title, String director, String producer, String platform, String release_date) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.producer = producer;
        this.platform = platform;
        this.release_date = release_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    
}
