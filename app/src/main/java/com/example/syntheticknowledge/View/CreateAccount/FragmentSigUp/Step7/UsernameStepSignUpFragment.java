package com.example.syntheticknowledge.View.CreateAccount.FragmentSigUp.Step7;

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
import com.example.syntheticknowledge.Model.DatabaseHelper.DatabaseHelper;
import com.example.syntheticknowledge.Model.DatabaseHelper.FirebaseHelper.FirebaseDatabaseHelper;
import com.example.syntheticknowledge.View.CreateAccount.FragmentSigUp.Step8.PasswordStepSignUpFragment;
import com.example.syntheticknowledge.View.CreateAccount.SupportSignUp.iSignUpBundle;
import com.example.syntheticknowledge.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class UsernameStepSignUpFragment extends Fragment {
    private iSignUpBundle signUpBundle;
    private ActionBar toolBarActivity;
    private TextInputLayout usernameInput;
    private Button nextButton;
    private DatabaseHelper db;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate( R.layout.fragment_username_step_sign_up, container, false );
        toolBarActivity = signUpBundle.getToolBarActivity();
        usernameInput = v.findViewById( R.id.username_create );
        nextButton = v.findViewById( R.id.next_username_btn );
        db = signUpBundle.getDB();
        db.setData( new FirebaseDatabaseHelper(getContext()) {
            @Override
            public void applyUserInUi(String username){
        
            }
    
            @Override
            public boolean login(String username, String pass){
                return false;
            }
    
            @Override
            public boolean isHaveDB(String value){
                return true;
            }
        } );
        return v;
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated( view, savedInstanceState );
        toolBarActivity.setTitle( getString( R.string.create_username_title ) );
        nextButton.setOnClickListener( this::nextUsernameAction );
    }
    
    private void nextUsernameAction(View view){
        String username = Objects.requireNonNull( usernameInput.getEditText() ).getText().toString();
        boolean flag = false;
        if(username.isEmpty()){
            usernameInput.setError( getResources().getText( R.string.empty_username ) );
            flag = true;
        }
        if(username.length() < 5 | username.length() > 16){
            usernameInput.setError( getResources().getText( R.string.length_username ) );
        }
        if(db.isHaveDB( username )){
        
        }
        if(flag) return;
        usernameInput.setError( null );
        signUpBundle.addField( User.USERNAME, username.toLowerCase() );
        signUpBundle.nextFragment( new PasswordStepSignUpFragment() );
    }
    
    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach( context );
        if(context instanceof iSignUpBundle)
            signUpBundle = (iSignUpBundle) context;
    }
}
