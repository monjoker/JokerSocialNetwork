package com.example.syntheticknowledge.View.MainSystem.FragmentMain;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.syntheticknowledge.Model.DatabaseHelper.iDatabaseConnect;
import com.example.syntheticknowledge.View.MainSystem.FragmentMain.NewFeedFragment.NewFeedFragment;
import com.example.syntheticknowledge.View.MainSystem.FragmentMain.OptionsFragment.OptionsFragment;
import com.example.syntheticknowledge.View.MainSystem.SupportTransportDataMain.iMainBundle;
import com.example.syntheticknowledge.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class MainFragment extends Fragment {
    private BottomNavigationView botNav;
    private String username = "";
    private Fragment selectFrag;
    private iDatabaseConnect db;
    private iMainBundle mainBundle;
    private long backPressTime = 0;
    private NewFeedFragment newFeedFragment;
    private ProfileFragment profileFragment;
    private NotificationsFragment notificationsFragment;
    private OptionsFragment optionsFragment;
    FragmentTransaction ft;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate( R.layout.fragment_main, container, false );
        botNav = v.findViewById( R.id.bot_nav_main );
        username = mainBundle.getUsername();
        newFeedFragment = new NewFeedFragment();
        profileFragment = new ProfileFragment();
        notificationsFragment = new NotificationsFragment();
        optionsFragment = new OptionsFragment();
        ft = Objects.requireNonNull( getActivity() ).getSupportFragmentManager().beginTransaction();
        setFragment( new NewFeedFragment() );
        botNav.setOnNavigationItemSelectedListener( item -> {
            selectFrag = null;
            return setFragNav( item.getItemId(), false );
        } );
        username = mainBundle.getUsername();
        return v;
    }
    
    public boolean setFragNav(int idItem, boolean type){
        switch(idItem) {
            case R.id.new_feed:
                selectFrag = newFeedFragment;
                break;
            case R.id.profile:
                selectFrag = profileFragment;
                break;
            case R.id.notifications:
                selectFrag = notificationsFragment;
                break;
            case R.id.options:
                selectFrag = optionsFragment;
        }
        if(type)
            botNav.setSelectedItemId( idItem );
        return setFragment( selectFrag );
    }
    
    @org.jetbrains.annotations.Contract(value = "null -> false")
    private boolean setFragment(Fragment frag){
        selectFrag = frag;
        if(selectFrag != null){
            ft.replace( R.id.frag_main, frag);
            ft.commit();
            return true;
        } else return false;
    }
    
    public void onBackPressedFrag(){
        if(selectFrag instanceof NewFeedFragment){
            if(backPressTime + 2000 > System.currentTimeMillis()) System.exit( 0 );
            else
                Toast.makeText( getContext(), getResources().getString( R.string.back_press_notification ), Toast.LENGTH_SHORT ).show();
            backPressTime = System.currentTimeMillis();
            return;
        }
        botNav.setSelectedItemId( R.id.new_feed );
        setFragment( new NewFeedFragment(  ) );
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated( view, savedInstanceState );
    }
    
    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach( context );
        if(context instanceof iMainBundle){
            mainBundle = (iMainBundle) context;
        }
    }
    
    public Fragment getSelectFrag(){
        return selectFrag;
    }
}
