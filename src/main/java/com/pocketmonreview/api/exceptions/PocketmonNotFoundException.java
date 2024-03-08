package com.pocketmonreview.api.exceptions;

public class PocketmonNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1;

    public PocketmonNotFoundException(String message){
        super(message);
    }
}
