package com.example.syntheticknowledge.View.CreateAccount.FragmentSigUp.Step4;

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

import com.example.syntheticknowledge.View.CreateAccount.FragmentSigUp.Step5.GmailStepSignUpFragment;
import com.example.syntheticknowledge.View.CreateAccount.SupportSignUp.iSignUpBundle;
import com.example.syntheticknowledge.R;
import com.example.syntheticknowledge.Model.Data.User;
import com.google.android.material.textfield.TextInputLayout;

public class AddressStepSignUpFragment extends Fragment {
    private iSignUpBundle signUpBundle;
    private ActionBar toolBarActivity;
    private TextInputLayout addressInput;
    private Button nextBtn;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate( R.layout.fragment_address_step_sign_up, container, false );
        toolBarActivity = signUpBundle.getToolBarActivity();
        addressInput = v.findViewById( R.id.address_create );
        nextBtn = v.findViewById( R.id.next_address_btn );
        return v;
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated( view, savedInstanceState );
        toolBarActivity.setTitle( getString( R.string.create_address_title ) );
        nextBtn.setOnClickListener( this::nextAddressAction );
    }
    
    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach( context );
        if(context instanceof iSignUpBundle)
            signUpBundle = (iSignUpBundle) context;
    }
    
    private void nextAddressAction(View v){
        if(addressInput.getEditText().getText().toString().isEmpty()){
            addressInput.setError( getResources().getString( R.string.empty_address ) );
        } else {
            addressInput.setError( null );
            signUpBundle.addField( User.ADDRESS, addressInput.getEditText().getText().toString() );
            signUpBundle.nextFragment( new GmailStepSignUpFragment() );
        }
    }
}
