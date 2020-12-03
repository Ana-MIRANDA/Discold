package com.ana.discold.Beans;

public class ErrorBean {

    private String errorMessage;

    public ErrorBean(String errorMessage) {
        this.errorMessage = errorMessage;
    }



    public ErrorBean() {
    }

    //getters and setters
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
