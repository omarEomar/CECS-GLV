package com.beshara.csc.nl.reg.presentation.backingbean.regulation;

public class EditorBean {

    private String data;

    public EditorBean() {
    }
    
    public void saveNewsData(){
        System.out.println(data);
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
