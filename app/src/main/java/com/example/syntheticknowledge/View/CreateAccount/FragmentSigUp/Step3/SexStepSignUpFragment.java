package com.example.syntheticknowledge.View.CreateAccount.FragmentSigUp.Step3;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import com.example.syntheticknowledge.View.CreateAccount.FragmentSigUp.Step4.AddressStepSignUpFragment;
import com.example.syntheticknowledge.View.CreateAccount.SupportSignUp.iSignUpBundle;
import com.example.syntheticknowledge.R;
import com.example.syntheticknowledge.Model.Data.User;

public class SexStepSignUpFragment extends Fragment {
    private iSignUpBundle signUpBundle;
    private ActionBar toolBarActivity;
    private RadioButton maleRBtn, famaleRBtn;
    private CompoundButton.OnCheckedChangeListener event;
    private boolean sexBoolean;
    private Button nextBtn;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate( R.layout.fragment_sex_step_sign_up, container, false );
        toolBarActivity = signUpBundle.getToolBarActivity();
        maleRBtn = v.findViewById( R.id.male_sex );
        famaleRBtn = v.findViewById( R.id.famale_sex );
        nextBtn = v.findViewById( R.id.next_sex_btn );
        event = (buttonView, isChecked) -> {
            if(isChecked){
                switch(buttonView.getId()){
                    case R.id.male_sex:
                        sexBoolean = true;
                        break;
                    case R.id.famale_sex:
                        sexBoolean = false;
                }
            }
        };
        return v;
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated( view, savedInstanceState );
        toolBarActivity.setTitle( getString( R.string.create_sex_title ) );
        maleRBtn.setOnCheckedChangeListener( event );
        famaleRBtn.setOnCheckedChangeListener( event );
        nextBtn.setOnClickListener( this::nextSexAction );
    }
    
    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach( context );
        if(context instanceof iSignUpBundle)
            signUpBundle = (iSignUpBundle) context;
    }
    
    private void nextSexAction(View v){
        signUpBundle.addField( User.SEX, sexBoolean );
        signUpBundle.nextFragment( new AddressStepSignUpFragment() );
    }
}
