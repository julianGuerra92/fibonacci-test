package com.proteccion.fibonacci.core.DTOs;

public class FibonacciResponse {
    private String fibonacciSequence;

    public FibonacciResponse(String fibonacciSequence) {
        this.fibonacciSequence = fibonacciSequence;
    }

    public String getFibonacciSequence() {
        return fibonacciSequence;
    }

    public void setFibonacciSequence(String fibonacciSequence) {
        this.fibonacciSequence = fibonacciSequence;
    }
}
