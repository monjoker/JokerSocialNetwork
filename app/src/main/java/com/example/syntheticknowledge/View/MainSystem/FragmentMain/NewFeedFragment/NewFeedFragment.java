package com.example.syntheticknowledge.View.MainSystem.FragmentMain.NewFeedFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.syntheticknowledge.Model.DatabaseHelper.FirebaseHelper.FirebaseDatabaseHelper;
import com.example.syntheticknowledge.Model.Data.Status;
import com.example.syntheticknowledge.Model.DatabaseHelper.iDatabaseConnect;
import com.example.syntheticknowledge.View.MainSystem.SearchActivity;
import com.example.syntheticknowledge.View.MainSystem.SupportTransportDataMain.MainViewModel;
import com.example.syntheticknowledge.View.MainSystem.SupportTransportDataMain.iMainBundle;
import com.example.syntheticknowledge.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.appbar.AppBarLayout;
import com.google.firebase.firestore.Query;

public class NewFeedFragment extends Fragment {
    private RecyclerView newFeed;
    private String username;
    private iMainBundle mainBundle;
    private iNewFeedAdapter newFeedAdapter;
    private FirestoreRecyclerOptions<Status> option;
    private Query query;
    private LinearLayoutManager linearLayoutManager;
    private AppBarLayout appBarLayout;
    private Toolbar toolbarNewFeed;
    private MainViewModel mainViewModel;
    private ImageButton searchNf;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate( R.layout.fragment_new_feed, container, false );
        newFeed = v.findViewById( R.id.new_feed_list );
        appBarLayout = v.findViewById( R.id.appbar_new_feed );
        toolbarNewFeed = v.findViewById( R.id.toolbar_new_feed );
        searchNf = v.findViewById( R.id.search_nf_btn );
        mainViewModel = ViewModelProviders.of( this ).get( MainViewModel.class );
        mainViewModel.setData( toolbarNewFeed );
        username = mainBundle.getUsername();
        query = FirebaseDatabaseHelper.getDb().collection( iDatabaseConnect.NAME_DB_STT );
        linearLayoutManager = new LinearLayoutManager( getContext(), LinearLayoutManager.VERTICAL, false );
        option = new FirestoreRecyclerOptions.Builder<Status>().setQuery( query, Status.class ).build();
        newFeedAdapter = new NewFeedAdapterFirebase( option, username, linearLayoutManager );
        return v;
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated( view, savedInstanceState );
        newFeed.setLayoutManager(linearLayoutManager);
        newFeed.setHasFixedSize( true );
        newFeed.setAdapter( (FirestoreRecyclerAdapter) newFeedAdapter );
        searchNf.setOnClickListener( v -> getActivity().startActivity( new Intent( getContext(), SearchActivity.class ) ) );
        appBarLayout.setEnabled( false );
    }
    
    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach( context );
        if(context instanceof iMainBundle) mainBundle = (iMainBundle) context;
    }
    
    @Override
    public void onStart(){
        super.onStart();
        FirestoreRecyclerAdapter adapter = (FirestoreRecyclerAdapter) newFeedAdapter;
        adapter.startListening();
    }
    
    @Override
    public void onStop(){
        super.onStop();
        FirestoreRecyclerAdapter adapter = (FirestoreRecyclerAdapter) newFeedAdapter;
        adapter.stopListening();
    }
}