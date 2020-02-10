package com.example.syntheticknowledge.View.CreateAccount;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.syntheticknowledge.Model.Data.User;
import com.example.syntheticknowledge.Model.DatabaseHelper.DatabaseHelper;
import com.example.syntheticknowledge.Model.DatabaseHelper.FirebaseHelper.FirebaseDatabaseHelper;
import com.example.syntheticknowledge.View.CreateAccount.FragmentSigUp.FirstStepSignUpFragment;
import com.example.syntheticknowledge.View.CreateAccount.SupportSignUp.iSignUpBundle;
import com.example.syntheticknowledge.R;
import com.example.syntheticknowledge.View.LoginActivity;
import com.google.firebase.Timestamp;

import java.util.ArrayList;
import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity implements iSignUpBundle {
    private ArrayList<Object> listField;
    private ActionBar supportActionBar;
    private Toolbar toolbarSignUp;
    private HashMap<String, Object> dataSave;
    private DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_sign_up );
        init();
        assert supportActionBar != null;
        supportActionBar.setDisplayHomeAsUpEnabled( true );
        supportActionBar.setDisplayShowTitleEnabled( true );
        listField = null;
        db = new DatabaseHelper( );
        setFragmentCreate( new FirstStepSignUpFragment() );
    }
    
    private void init(){
        toolbarSignUp = findViewById( R.id.toolbar_sign_up );
        setSupportActionBar( toolbarSignUp );
        supportActionBar = getSupportActionBar();
        dataSave = new HashMap<>();
    }
    
    @Override
    public void setFragmentCreate(Fragment value){
        getSupportFragmentManager().beginTransaction().replace( R.id.sigup_main_frame,  value).commit();
    }
    
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected( item );
    }
    
    @Override
    public ActionBar getToolBarActivity(){
        return supportActionBar;
    }
    
    @Override
    public void nextFragment(Fragment value){
        getSupportFragmentManager().beginTransaction().replace( R.id.sigup_main_frame, value ).addToBackStack( value.getClass().getSimpleName() ).commit();
    }
    
    @Override
    public void addField(String key,Object value){
        dataSave.put( key, value );
    }
    
    @Override
    public Object getField(String key){
        return dataSave.get( key );
    }
    
    @Override
    public void finalSignUp(){
        db.createUser( new User(
                (String)dataSave.get( User.USERNAME ),
                (String) dataSave.get( User.PASSWORD ),
                (String) dataSave.get( User.FIRST_NAME ),
                (String) dataSave.get( User.LAST_NAME ),
                (String) dataSave.get( User.ADDRESS ),
                (boolean)dataSave.get( User.SEX ),
                (Timestamp) dataSave.get( User.BIRTHDAY ),
                (String) dataSave.get( User.GMAIL ),
                (String) dataSave.get( User.PHONE_NUMBER )
        ) );
        new Intent( getApplicationContext(), LoginActivity.class );
    }
    
    @Override
    public DatabaseHelper getDB(){
        return db;
    }
}
