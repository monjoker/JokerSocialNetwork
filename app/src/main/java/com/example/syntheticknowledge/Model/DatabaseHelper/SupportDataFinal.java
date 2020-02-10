package com.example.syntheticknowledge.Model.DatabaseHelper;

public class SupportDataFinal<T> {
    private T data;
    
    public SupportDataFinal(){
        data = null;
    }
    
    public void set(T data){
        this.data = data;
    }
    
    public T get(){
        return data;
    }
}
