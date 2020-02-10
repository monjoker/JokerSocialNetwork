package com.example.syntheticknowledge.View.MainSystem.SupportTransportDataMain;

import android.app.Application;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class MainViewModel extends AndroidViewModel {
    public MutableLiveData<Toolbar> getToolbarMutableLiveData(){
        return toolbarMutableLiveData;
    }
    
    public void setToolbarMutableLiveData(MutableLiveData<Toolbar> toolbarMutableLiveData){
        this.toolbarMutableLiveData = toolbarMutableLiveData;
    }
    
    private MutableLiveData<Toolbar> toolbarMutableLiveData= new MutableLiveData<>(  );
    public Toolbar getToolbar(){
        return toolbarMutableLiveData.getValue();
    }
    
    public void setData(Toolbar value){
        toolbarMutableLiveData.setValue( value );
    }
    public MainViewModel(Application application){
        super(application);
        toolbarMutableLiveData.setValue( null );
    }
}
