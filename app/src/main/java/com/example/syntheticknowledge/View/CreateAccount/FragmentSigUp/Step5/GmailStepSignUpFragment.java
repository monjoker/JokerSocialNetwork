package com.example.syntheticknowledge.View.CreateAccount.FragmentSigUp.Step5;

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
import com.example.syntheticknowledge.View.CreateAccount.FragmentSigUp.Step6.PhoneStepSignUpFragment;
import com.example.syntheticknowledge.View.CreateAccount.SupportSignUp.iSignUpBundle;
import com.example.syntheticknowledge.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class GmailStepSignUpFragment extends Fragment {
    private iSignUpBundle signUpBundle;
    private ActionBar toolBarActivity;
    private TextInputLayout gmailInput;
    private Button nextButton;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate( R.layout.fragment_gmail_step_sign_up, container, false );
        toolBarActivity = signUpBundle.getToolBarActivity();
        gmailInput = v.findViewById( R.id.gmail_create );
        nextButton = v.findViewById( R.id.next_gmail_btn );
        return v;
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated( view, savedInstanceState );
        toolBarActivity.setTitle( getString( R.string.create_gmail_title ) );
        nextButton.setOnClickListener( this::nextGmailAction );
    }
    
    private void nextGmailAction(View view){
        String gmail = Objects.requireNonNull( gmailInput.getEditText() ).getText().toString();
        if(gmail.isEmpty())
            gmailInput.setError( getResources().getText( R.string.empty_gmail ) );
        else{
            gmailInput.setError( null );
            signUpBundle.addField( User.GMAIL, gmail );
            signUpBundle.nextFragment( new PhoneStepSignUpFragment() );
        }
    }
    
    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach( context );
        if(context instanceof iSignUpBundle)
            signUpBundle = (iSignUpBundle) context;
    }
}
