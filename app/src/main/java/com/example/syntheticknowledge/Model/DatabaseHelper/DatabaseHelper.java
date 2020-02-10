package com.example.syntheticknowledge.Model.DatabaseHelper;

import com.example.syntheticknowledge.Model.Data.Status;
import com.example.syntheticknowledge.Model.Data.User;

public class DatabaseHelper {
    private iDatabaseConnect data;
    
    public DatabaseHelper(){
        data = null;
    }
    
    public DatabaseHelper(iDatabaseConnect data){
        this.data = data;
    }
    
    public void setData(iDatabaseConnect data){
        this.data = data;
    }
    
    public void applyUserInUi(String username){
        data.applyUserInUi( username );
    }
    public User getUser(String username){
        return  data.getUser( username );
    }
    public String createUser(User value){
        return data.createUser( value );
    }
    public void setUser(String username, User value){
        data.setUser( username, value );
    }
    public boolean login(String username, String pass){
        return data.login( username, pass );
    }
    
    //Status code
    public String createStatus(Status value){
        return data.createStatus( value );
    }
    public boolean isHaveDB(String value){
        return data.isHaveDB( value );
    }
}
