package com.example.syntheticknowledge.View.MainSystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.syntheticknowledge.R;

public class SearchActivity extends AppCompatActivity {
    private Toolbar toolbarSearch;
    private ActionBar supportActionBar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_search );
        toolbarSearch = findViewById( R.id.toolbar_search );
        setSupportActionBar( toolbarSearch );
        supportActionBar = getSupportActionBar();
        assert supportActionBar != null;
        supportActionBar.setDisplayHomeAsUpEnabled( true );
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        return super.onCreateOptionsMenu( menu );
    }
    
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected( item );
    }
    
    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}