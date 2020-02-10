package com.example.syntheticknowledge.View.CreateAccount.FragmentSigUp.Step2;

import android.app.DatePickerDialog;
import android.content.Context;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import com.example.syntheticknowledge.View.CreateAccount.FragmentSigUp.Step3.SexStepSignUpFragment;
import com.example.syntheticknowledge.View.CreateAccount.SupportSignUp.iSignUpBundle;
import com.example.syntheticknowledge.R;
import com.example.syntheticknowledge.Model.Data.User;
import com.google.firebase.Timestamp;

import java.util.Objects;

public class BirthDayStepSignUpFragment extends Fragment {
    private iSignUpBundle signUpBundle;
    private ActionBar toolBarActivity;
    private TextView dayTv, monthTv, yearTv;
    DatePickerDialogCustom datePickerDialog;
    private Button nextBtn;
    private Calendar date;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate( R.layout.fragment_birth_day_step_sign_up, container, false );
        toolBarActivity = signUpBundle.getToolBarActivity();
        dayTv = v.findViewById( R.id.day_create );
        monthTv = v.findViewById( R.id.month_create );
        yearTv = v.findViewById( R.id.year_create );
        nextBtn = v.findViewById( R.id.next_birth_day_btn );
        date = Calendar.getInstance();
        datePickerDialog =
                new DatePickerDialogCustom( Objects.requireNonNull( getContext() ),
                                            (view, year, month, dayOfMonth) -> {
                                                date.set( year, month, dayOfMonth );
                                                dayTv.setText( String.valueOf( date.get( Calendar.DAY_OF_MONTH ) ) );
                                                monthTv.setText( String.valueOf( date.get( Calendar.MONTH ) + 1 ) );
                                                yearTv.setText( String.valueOf( date.get( Calendar.YEAR ) ) );
                                            }, date.get( Calendar.YEAR ), date.get( Calendar.MONTH ), date.get( Calendar.DAY_OF_MONTH )
                );
        return v;
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated( view, savedInstanceState );
        toolBarActivity.setTitle( getString( R.string.create_birthday_title ) );
        datePickerDialog.show();
        dayTv.setText( String.valueOf( date.get( Calendar.DAY_OF_MONTH ) ) );
        monthTv.setText( String.valueOf( date.get( Calendar.MONTH ) + 1 ) );
        yearTv.setText( String.valueOf( date.get( Calendar.YEAR ) ) );
        nextBtn.setOnClickListener( this::nextBirthDayAction );
    }
    
    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach( context );
        if(context instanceof iSignUpBundle)
            signUpBundle = (iSignUpBundle) context;
    }
    
    private void nextBirthDayAction(View v){
        signUpBundle.addField( User.BIRTHDAY, new Timestamp( date.getTime() ) );
        signUpBundle.nextFragment( new SexStepSignUpFragment() );
    }
    
    private class DatePickerDialogCustom extends DatePickerDialog {
        
        DatePickerDialogCustom(@NonNull Context context, @NonNull OnDateSetListener listener, int year, int monthOfYear, int dayOfMonth){
            super( context, android.R.style.Theme_Holo_Light, listener, year, monthOfYear, dayOfMonth );
        }
        
        @Override
        public void cancel(){
            Objects.requireNonNull( getActivity() ).onBackPressed();
            super.cancel();
        }
    }
}