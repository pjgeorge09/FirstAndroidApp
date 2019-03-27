package com.example.managertabs;

public class Other extends Master{

    private String message;

    public Other(){

    }

    public Other(String aMessage){
        this.message = aMessage;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
