package com.example.syntheticknowledge.View.CreateAccount.FragmentSigUp;

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

import com.example.syntheticknowledge.View.CreateAccount.FragmentSigUp.Step1.FullNameStepSignUpFragment;
import com.example.syntheticknowledge.View.CreateAccount.SupportSignUp.iSignUpBundle;
import com.example.syntheticknowledge.R;

public class FirstStepSignUpFragment extends Fragment {
    private Button nextStepBtn;
    private iSignUpBundle signUpBundle;
    private ActionBar toolBarActivity;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate( R.layout.fragment_first_step_create_acc, container, false );
        nextStepBtn = v.findViewById( R.id.continues_btn );
        toolBarActivity = signUpBundle.getToolBarActivity();
        toolBarActivity.hide();
        return v;
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated( view, savedInstanceState );
        nextStepBtn.setOnClickListener( v -> {
            toolBarActivity.show();
            signUpBundle.setFragmentCreate( new FullNameStepSignUpFragment() );
        } );
    }
    
    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach( context );
        if(context instanceof iSignUpBundle)
            signUpBundle = (iSignUpBundle) context;
    }
}
