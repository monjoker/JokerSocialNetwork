package com.example.syntheticknowledge.View.CreateAccount.FragmentSigUp.Step6;

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
import com.example.syntheticknowledge.View.CreateAccount.FragmentSigUp.Step7.UsernameStepSignUpFragment;
import com.example.syntheticknowledge.View.CreateAccount.SupportSignUp.iSignUpBundle;
import com.example.syntheticknowledge.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class PhoneStepSignUpFragment extends Fragment {
    private iSignUpBundle signUpBundle;
    private ActionBar toolBarActivity;
    private TextInputLayout phoneInput;
    private Button nextButton;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate( R.layout.fragment_phone_step_sign_up, container, false );
        toolBarActivity = signUpBundle.getToolBarActivity();
        phoneInput = v.findViewById( R.id.phone_create );
        nextButton = v.findViewById( R.id.next_phone_btn );
        return v;
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated( view, savedInstanceState );
        toolBarActivity.setTitle( getString( R.string.create_phone_title ) );
        nextButton.setOnClickListener( this::nextPhoneAction );
    }
    
    private void nextPhoneAction(View view){
        String phone = Objects.requireNonNull( phoneInput.getEditText() ).getText().toString();
        if(phone.isEmpty())
            phoneInput.setError( getResources().getText( R.string.empty_phone ) );
        else {
            phoneInput.setError( null );
            signUpBundle.addField( User.PHONE_NUMBER, phone );
            signUpBundle.nextFragment( new UsernameStepSignUpFragment() );
        }
    }
    
    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach( context );
        if(context instanceof iSignUpBundle)
            signUpBundle = (iSignUpBundle) context;
    }
}
