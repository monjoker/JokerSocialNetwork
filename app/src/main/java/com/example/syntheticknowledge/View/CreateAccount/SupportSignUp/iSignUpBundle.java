package com.example.syntheticknowledge.View.CreateAccount.SupportSignUp;


import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import com.example.syntheticknowledge.Model.DatabaseHelper.DatabaseHelper;

public interface iSignUpBundle {
    void setFragmentCreate(Fragment value);
    ActionBar getToolBarActivity();
    void nextFragment(Fragment value);
    void addField(String key,Object value);
    Object getField(String key);
    void finalSignUp();
    
    DatabaseHelper getDB();
}