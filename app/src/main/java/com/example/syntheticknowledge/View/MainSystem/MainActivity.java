package com.example.syntheticknowledge.View.MainSystem;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.syntheticknowledge.Model.Data.User;
import com.example.syntheticknowledge.View.MainSystem.FragmentMain.MainFragment;
import com.example.syntheticknowledge.View.MainSystem.FragmentMain.NewFeedFragment.NewFeedFragment;
import com.example.syntheticknowledge.View.MainSystem.SupportTransportDataMain.MainViewModel;
import com.example.syntheticknowledge.View.MainSystem.SupportTransportDataMain.iMainBundle;
import com.example.syntheticknowledge.R;

public class MainActivity extends AppCompatActivity implements iMainBundle {
    private String username = "";
    private Fragment mainFrag;
    private MainViewModel mainViewModel;
    
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        init();
        getSupportFragmentManager().beginTransaction().replace( R.id.main_frag, mainFrag ).commit();
    }
    @Override
    public void onBackPressed(){
        ((MainFragment)mainFrag).onBackPressedFrag();
    }
    
    @Override
    public String getUsername(){
        return username;
    }
    
    @Override
    public MainFragment getMainFrag(){
        return (MainFragment) mainFrag;
    }
    
    private void init(){
        username = getIntent().getStringExtra( User.USERNAME );
        mainFrag = new MainFragment();
        mainViewModel = ViewModelProviders.of( this).get(MainViewModel.class);
        if(((MainFragment) mainFrag).getSelectFrag() instanceof NewFeedFragment){
            if(mainViewModel.getToolbar() != null)
                mainViewModel.getToolbarMutableLiveData().observe( this, this::setSupportActionBar );
        }
    }
}