package com.example.syntheticknowledge.Model.Data;


import com.example.syntheticknowledge.Model.DatabaseHelper.iDatabaseSetting;
import com.google.firebase.Timestamp;

import java.util.List;

public class User implements iDatabaseSetting {
    //Label field
    public static String
            USERNAME = "username",
            PASSWORD = "password",
            FIRST_NAME = "firstName",
            LAST_NAME ="lastName",
            SEX = "sex",
            ADDRESS = "address",
            BIRTHDAY = "birthDay",
            FRIENDS_LIST = "friendsList",
            REQUESTFRIENDS_LIST = "requestFriendList",
            GMAIL = "gmail",
            PHONE_NUMBER = "phoneNumber";
    
    
    //Field
    private String username, password;
    private String firstName, lastName , address;
    private boolean sex;
    private Timestamp birthDay;
    private List<User> friendsList, requestFriendList;
    private String gmail, phoneNumber;
    
    //Constructers
    public User(){
    }
    
    public User(String username, String password, String firstName, String lastName, String address, boolean sex, Timestamp birthDay, String gmail, String phoneNumber){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.sex = sex;
        this.birthDay = birthDay;
        this.gmail = gmail;
        this.phoneNumber = phoneNumber;
    }
    
    public User(String username, String password, String firstName, String lastName, String address, boolean sex, Timestamp birthDay, List<User> friendsList, List<User> requestFriendList, String gmail, String phoneNumber){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.sex = sex;
        this.birthDay = birthDay;
        this.friendsList = friendsList;
        this.requestFriendList = requestFriendList;
        this.gmail = gmail;
        this.phoneNumber = phoneNumber;
    }
    
    //Get set field
    
    public String getUsername(){
        return username;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public String getFirstName(){
        return firstName;
    }
    
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    
    public String getAddress(){
        return address;
    }
    
    public void setAddress(String address){
        this.address = address;
    }
    
    public boolean isSex(){
        return sex;
    }
    
    public void setSex(boolean sex){
        this.sex = sex;
    }
    
    public Timestamp getBirthDay(){
        return birthDay;
    }
    
    public void setBirthDay(Timestamp birthDay){
        this.birthDay = birthDay;
    }
    
    public List<User> getFriendsList(){
        return friendsList;
    }
    
    public void setFriendsList(List<User> friendsList){
        this.friendsList = friendsList;
    }
    
    public List<User> getRequestFriendList(){
        return requestFriendList;
    }
    
    public void setRequestFriendList(List<User> requestFriendList){
        this.requestFriendList = requestFriendList;
    }
    
    public String getGmail(){
        return gmail;
    }
    
    public void setGmail(String gmail){
        this.gmail = gmail;
    }
    
    public String getPhoneNumber(){
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
}