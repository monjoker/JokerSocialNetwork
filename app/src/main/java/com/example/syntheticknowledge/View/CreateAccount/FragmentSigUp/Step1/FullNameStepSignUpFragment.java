package com.example.syntheticknowledge.View.CreateAccount.FragmentSigUp.Step1;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import com.example.syntheticknowledge.View.CreateAccount.FragmentSigUp.Step2.BirthDayStepSignUpFragment;
import com.example.syntheticknowledge.View.CreateAccount.SupportSignUp.iSignUpBundle;
import com.example.syntheticknowledge.R;
import com.example.syntheticknowledge.Model.Data.User;
import com.google.android.material.textfield.TextInputLayout;

public class FullNameStepSignUpFragment extends Fragment {
    private iSignUpBundle signUpBundle;
    private ActionBar toolBarActivity;
    private Button nextBtn;
    private TextInputLayout firstName, lastName;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate( R.layout.fragment_full_name_step_sign_up, container, false );
        toolBarActivity = signUpBundle.getToolBarActivity();
        nextBtn = v.findViewById( R.id.next_full_name_btn );
        firstName = v.findViewById( R.id.first_name_create_et );
        lastName = v.findViewById( R.id.last_name_create_et );
        return v;
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated( view, savedInstanceState );
        toolBarActivity.setTitle( getString( R.string.create_full_name_title ) );
        nextBtn.setOnClickListener( this::nextFullnameAction );
    }
    
    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach( context );
        if(context instanceof iSignUpBundle)
            signUpBundle = (iSignUpBundle) context;
    }
    
    private void nextFullnameAction(View v){
        String firstnameString = firstName.getEditText().getText().toString(),
                lastnameString = lastName.getEditText().getText().toString();
        if(firstnameString.isEmpty())
            firstName.setError( getResources().getText( R.string.empty_first_name ) );
        if(lastnameString.isEmpty())
            firstName.setError( getResources().getText( R.string.empty_last_name ) );
        if(! firstnameString.isEmpty() && ! lastnameString.isEmpty()){
            firstName.setError( null );
            firstName.setError( null );
            signUpBundle.addField( User.FIRST_NAME, firstName.getEditText().getText().toString() );
            signUpBundle.addField( User.LAST_NAME, lastName.getEditText().getText().toString() );
            signUpBundle.nextFragment( new BirthDayStepSignUpFragment() );
        }
    }
}
