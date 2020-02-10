package com.example.syntheticknowledge.View.MainSystem.FragmentMain.OptionsFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.syntheticknowledge.View.LoginActivity;
import com.example.syntheticknowledge.View.MainSystem.SupportTransportDataMain.iMainBundle;
import com.example.syntheticknowledge.R;

import java.util.ArrayList;
import java.util.Arrays;

public class OptionsFragment extends Fragment implements AdapterView.OnItemClickListener {
    private ListView lv;
    private  OptionsAdapter oa;
    private String username;
    private iMainBundle mainBundle;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate ( R.layout.fragment_options, container, false );
        lv = v.findViewById( R.id.list_options );
        return v;
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated( view, savedInstanceState );
        username = mainBundle.getUsername();
        oa = new OptionsAdapter(new ArrayList<>( Arrays.asList(
                getString( R.string.to_profile_btn, username, getString( R.string.to_profile_btn_text ) ),
                getString( R.string.friend_list_op_btn ),
                getString( R.string.reqest_friend_list_op_btn ),
                getString( R.string.log_out_op_btn )
        ) ) );
        lv.setAdapter( oa );
        lv.setOnItemClickListener(this);
    }
    
    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach( context );
        if(context instanceof iMainBundle) mainBundle = (iMainBundle) context;
    }
    
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        switch(position){
            case 0:
                mainBundle.getMainFrag().setFragNav( R.id.profile, true );
                break;
            case 1:
                
                break;
            case 2:
                
                break;
            default:
                getActivity().startActivity( new Intent( getActivity().getApplicationContext(), LoginActivity.class ) );
        }
    }
}
