package com.xiliang.exception;

public class NoTheUserException extends RuntimeException {
    public NoTheUserException() {
    }
    public NoTheUserException(String message) {
        super(message);
    }
}
