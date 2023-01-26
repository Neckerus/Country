package com.example.springboot.model.exception;

public class NoRoadFoundException extends RuntimeException {
    public NoRoadFoundException() {
        super("No road found");
    }

}
