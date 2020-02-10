package com.example.syntheticknowledge.View.MainSystem.FragmentMain.NewFeedFragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.syntheticknowledge.Model.DatabaseHelper.FirebaseHelper.FirebaseDatabaseHelper;
import com.example.syntheticknowledge.Model.Data.Status;
import com.example.syntheticknowledge.Model.Data.User;
import com.example.syntheticknowledge.Model.DatabaseHelper.iDatabaseConnect;
import com.example.syntheticknowledge.View.MainSystem.MainActivity;
import com.example.syntheticknowledge.R;
import com.google.firebase.Timestamp;

import java.util.Date;

public class WriterStatusActivity extends AppCompatActivity {
    private EditText content;
    private TextView fullName;
    private iDatabaseConnect db;
    private String username;
    private MenuItem postBtn;
    private ActionBar actionBar;
    private TextWatcher textEvent;
    
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_writer_status );
        init();
        content.addTextChangedListener( textEvent);
        actionBar = getSupportActionBar();
        assert actionBar != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle( R.string.status_title );
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate( R.menu.actionbar_write_activity, menu );
        postBtn = menu.getItem( 0 );
        return super.onCreateOptionsMenu( menu );
    }
    
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.post_btn:
                db.createStatus( new Status( username, fullName.getText().toString(), content.getText().toString(), new Timestamp( new Date(  ) ) ) );
                startActivity( new Intent( this, MainActivity.class ).putExtra( User.USERNAME, username ) );
                break;
        }
        return super.onOptionsItemSelected( item );
    }
    
    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
    
    private void init(){
        content = findViewById( R.id.post_content_et );
        fullName = findViewById( R.id.fullname_write_stt );
        username = getIntent().getStringExtra( User.USERNAME );
        db = new FirebaseDatabaseHelper( this, username) {
            @Override
            public void applyUserInUi(String username){
                getDb().collection( iDatabaseConnect.NAME_DB_USER )
                       .document(username)
                       .addSnapshotListener( (documentSnapshot, e) -> fullName.setText( String.format( "%s %s", documentSnapshot.getString( User.FIRST_NAME ), documentSnapshot.getString( User.LAST_NAME ) ) ) );
            }
            
            @Override
            public boolean login(String username, String pass){
                return false;
            }
        };
        textEvent = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){
            
            }
            
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){
            
            }
            
            @Override
            public void afterTextChanged(Editable s){
                if(s.toString().equals( "" ))
                    postBtn.setEnabled( false );
                else postBtn.setEnabled( true );
            }
        };
    }
}
