package com.example.syntheticknowledge.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.syntheticknowledge.View.CreateAccount.SignUpActivity;
import com.example.syntheticknowledge.Model.DatabaseHelper.FirebaseHelper.FirebaseDatabaseHelper;
import com.example.syntheticknowledge.Model.Data.User;
import com.example.syntheticknowledge.Model.DatabaseHelper.iDatabaseConnect;
import com.example.syntheticknowledge.View.MainSystem.MainActivity;
import com.example.syntheticknowledge.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.Timestamp;

import java.util.Date;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private TextInputLayout username, password;
    private TextView noti;
    private iDatabaseConnect db;
    private Resources res;
    private TextWatcher event;
    
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );
        this.init();
        Objects.requireNonNull( username.getEditText() ).addTextChangedListener( event );
        Objects.requireNonNull( password.getEditText() ).addTextChangedListener( event );
        initData();
    }
    
    public void loginActionButton(View v){
        if(!db.login( Objects.requireNonNull( username.getEditText() ).getText().toString().toLowerCase(), Objects.requireNonNull( password.getEditText() ).getText().toString() ))
            noti.setText( res.getString( R.string.error_login ) );
    }
    
    public void forgotPasswordActionButton(View v){
    
    }
    
    public void signUpActionButton(View v){
        startActivity( new Intent( this, SignUpActivity.class ) );
    }
    
    private void init(){
        username = findViewById( R.id.username_input );
        password = findViewById( R.id.password_input );
        noti = findViewById( R.id.notification_login_tv );
        db = new FirebaseDatabaseHelper( this ) {
            @Override
            public void applyUserInUi(String username){}
    
            @Override
            public boolean login(String username, String pass){
                if(username.equals( "" )) return false;
                getDb().collection( iDatabaseConnect.NAME_DB_USER ).document( username ).addSnapshotListener( (documentSnapshot, e) -> {
                    if(documentSnapshot!= null && documentSnapshot.exists())
                        if(Objects.equals( documentSnapshot.get( User.PASSWORD ), pass ))
                            startActivity( new Intent( getApplicationContext(), MainActivity.class ).putExtra( User.USERNAME, username ) );
                        else
                            noti.setText( res.getString( R.string.error_login ) );
                    else
                        noti.setText( res.getString( R.string.error_login ) );
                } );
                return true;
            }
    
            @Override
            public boolean isHaveDB(String value){
                return false;
            }
        };
        res = getResources();
    
        event = new TextWatcher() {
            //username and password change
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){
            
            }
        
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){
            
            }
        
            @Override
            public void afterTextChanged(Editable s){
                noti.setText( "" );
            }
        };
    }
    
    private void initData(){
        String e = db.createUser( new User(
                "phuongnam",
                "phuongnam",
                "Phạm",
                "Phương Nam",
                "97 Man Thiện, Hiệp Phú, Quận 9, Tp.HCM",
                true,
                new Timestamp( new Date( 1999, 01, 19 ) ),
                "phuongnamp99@gmail.com",
                "0983861748"
        ) );
        if(e != null)
            Toast.makeText( this, e, Toast.LENGTH_SHORT ).show();
    }
}