package com.example.syntheticknowledge.Model.DatabaseHelper;

import com.example.syntheticknowledge.Model.Data.Status;
import com.example.syntheticknowledge.Model.Data.User;

public interface iDatabaseConnect{
    //Name database
    String NAME_DB_USER = "User";
    String NAME_DB_STT ="Status";
    
    //Method hadling database
    //User code
    void applyUserInUi(String username);
    User getUser(String username);
    String createUser(User value);
    void setUser(String username, User value);
    boolean login(String username, String pass);
    
    //Status code
    String createStatus(Status value);
    boolean isHaveDB(String value);
}