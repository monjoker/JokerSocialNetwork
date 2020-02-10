package com.example.syntheticknowledge.Model.Data;

import com.example.syntheticknowledge.Model.DatabaseHelper.iDatabaseSetting;
import com.google.firebase.Timestamp;

import java.util.ArrayList;

public class Status implements iDatabaseSetting {
    //Label field
    public static String
            USERNAME = "username",
            FULL_NAME = "fullName",
            CONTENT = "content",
            WRITE_TIME = "writeTime",
            LIKES_LIST = "likesList",
            SHARE_LIST = "sharesList",
            CMT_LIST = "commentsList";
    
    //Field
    private String username;
    private String fullName, content;
    private Timestamp writeTime;
    private ArrayList<User> likesList, sharesList;
    private ArrayList<Comment> commentsList;
    
    public Status(){
    }
    
    public Status(String username, String fullName, String content, Timestamp writeTime){
        this.username = username;
        this.fullName = fullName;
        this.content = content;
        this.writeTime = writeTime;
        this.likesList = new ArrayList<>(  );
        this.sharesList = new ArrayList<>(  );
        this.commentsList = new ArrayList<>(  );
    }
    
    public Status(String username, String fullName, String content, Timestamp writeTime, ArrayList<User> likesList, ArrayList<User> sharesList, ArrayList<Comment> commentsList){
        this.username = username;
        this.fullName = fullName;
        this.content = content;
        this.writeTime = writeTime;
        this.likesList = likesList;
        this.sharesList = sharesList;
        this.commentsList = commentsList;
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
    
    public ArrayList<User> getSharesList(){
        return sharesList;
    }
    
    public void setSharesList(ArrayList<User> sharesList){
        this.sharesList = sharesList;
    }
    
    public ArrayList<Comment> getCommentsList(){
        return commentsList;
    }
    
    public void setCommentsList(ArrayList<Comment> commentsList){
        this.commentsList = commentsList;
    }
}