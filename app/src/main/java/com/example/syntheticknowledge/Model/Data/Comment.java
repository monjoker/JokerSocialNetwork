package com.example.syntheticknowledge.Model.Data;


import com.example.syntheticknowledge.Model.DatabaseHelper.iDatabaseSetting;
import com.google.firebase.Timestamp;

import java.util.ArrayList;

public class Comment implements iDatabaseSetting {
    //Label field
    public static String
            USERNAME = "username",
            FULL_NAME = "fullName",
            CONTENT = "content",
            WRITE_TIME = "writeTime",
            LIKES_LIST = "likesList";
    
    //Field
    private String username;
    private String fullName, content;
    private Timestamp writeTime;
    private ArrayList<User> likesList;
    public Comment(){
    
    }
    
    public Comment(String username, String fullName, String content, Timestamp writeTime){
        this.username = username;
        this.fullName = fullName;
        this.content = content;
        this.writeTime = writeTime;
        this.likesList = new ArrayList<>(  );
    }
    
    public Comment(String username, String fullName, String content, Timestamp writeTime, ArrayList<User> likesList){
        this.username = username;
        this.fullName = fullName;
        this.content = content;
        this.writeTime = writeTime;
        this.likesList = likesList;
    }
    
    public String getUsername(){
        return username;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public String getFullName(){
        return fullName;
    }
    
    public void setFullName(String fullName){
        this.fullName = fullName;
    }
    
    public String getContent(){
        return content;
    }
    
    public void setContent(String content){
        this.content = content;
    }
    
    public Timestamp getWriteTime(){
        return writeTime;
    }
    
    public void setWriteTime(Timestamp writeTime){
        this.writeTime = writeTime;
    }
    
    public ArrayList<User> getLikesList(){
        return likesList;
    }
    
    public void setLikesList(ArrayList<User> likesList){
        this.likesList = likesList;
    }
}
