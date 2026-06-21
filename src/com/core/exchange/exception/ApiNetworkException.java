package com.core.exchange.exception;
public class ApiNetworkException extends Exception {
    public ApiNetworkException(String message) { super(message); }
    public ApiNetworkException(String message, Throwable cause) { super(message, cause); }
}