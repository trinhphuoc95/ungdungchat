package com.example.dinhtrinhphuoc.friendlychat;

/**
 * Created by TrinhPhuoc on 04-Nov-16.
 */

public class UserMessage {
    private String id ;
    private String text;
    private String name;
    private String photoUrl;


    public UserMessage() {

    }

    public UserMessage(String id ,String text, String name, String photoUrl) {
        this.id = id;
        this.text = text;
        this.name = name;
        this.photoUrl = photoUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
