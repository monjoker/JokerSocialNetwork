package com.example.syntheticknowledge.View.CreateAccount.FragmentSigUp.Step8;

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

import com.example.syntheticknowledge.Model.Data.User;
import com.example.syntheticknowledge.View.CreateAccount.FragmentSigUp.FinalStepSignUpFragment;
import com.example.syntheticknowledge.View.CreateAccount.SupportSignUp.iSignUpBundle;
import com.example.syntheticknowledge.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class PasswordStepSignUpFragment extends Fragment {
    private iSignUpBundle signUpBundle;
    private ActionBar toolBarActivity;
    private TextInputLayout passwordInput;
    private Button nextButton;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate( R.layout.fragment_password_step_sign_up, container, false );
        toolBarActivity = signUpBundle.getToolBarActivity();
        passwordInput = v.findViewById( R.id.password_create );
        nextButton = v.findViewById( R.id.next_password_btn );
        return v;
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated( view, savedInstanceState );
        toolBarActivity.setTitle( getString( R.string.create_password_title ) );
        nextButton.setOnClickListener( this::nextPasswordAction );
    }
    
    private void nextPasswordAction(View view){
        String password = Objects.requireNonNull( passwordInput.getEditText() ).getText().toString();
        boolean flag = false;
        if(password.isEmpty()){
            passwordInput.setError( getResources().getText( R.string.empty_password ) );
            flag = true;
        }
        if(password.length() < 5 | password.length() > 16){
            passwordInput.setError( getResources().getText( R.string.length_password ) );
            flag = true;
        }
        if(flag) return;
        passwordInput.setError( null );
        signUpBundle.addField( User.PASSWORD, password );
        signUpBundle.finalSignUp();
    }
    
    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach( context );
        if(context instanceof iSignUpBundle)
            signUpBundle = (iSignUpBundle) context;
    }
}
