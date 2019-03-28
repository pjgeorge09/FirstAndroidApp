package com.example.managertabs;

public class Other extends Master{

    private String memo;

    public Other(){

    }

    public Other(String aMemo){
        this.memo = aMemo;
    }


    public String getMemo() {
        return memo;
    }

    public void setMemo(String aMemo) {
        this.memo = aMemo;
    }

}
