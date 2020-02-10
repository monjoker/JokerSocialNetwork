package com.example.syntheticknowledge.View.CreateAccount.FragmentSigUp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.syntheticknowledge.View.CreateAccount.SupportSignUp.iSignUpBundle;
import com.example.syntheticknowledge.View.LoginActivity;

public class FinalStepSignUpFragment extends Fragment {
    private iSignUpBundle signUpBundle;
    private Intent compeleteIntent;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        compeleteIntent = new Intent( getContext(), LoginActivity.class );
        startActivity( compeleteIntent );
        return super.onCreateView( inflater, container, savedInstanceState );
    }
    
    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach( context );
        if(context instanceof iSignUpBundle)
            signUpBundle = (iSignUpBundle) context;
    }
}
