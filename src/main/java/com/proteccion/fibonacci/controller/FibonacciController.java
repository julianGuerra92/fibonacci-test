package com.proteccion.fibonacci.controller;

import com.proteccion.fibonacci.core.DTOs.FibonacciRequest;
import com.proteccion.fibonacci.core.DTOs.FibonacciResponse;
import com.proteccion.fibonacci.core.interfaces.IFibonacciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fibonacci")
public class FibonacciController {

    @Autowired
    private IFibonacciService fibonacciService;

    @GetMapping
    public ResponseEntity<FibonacciResponse> getFibonacciSequence() {
        String fibonacciSequence = fibonacciService.getFibonacciSequence();
        return ResponseEntity.ok(new FibonacciResponse(fibonacciSequence));
    }

    @PostMapping
    public ResponseEntity<FibonacciResponse> getFibonacciSequence(@RequestBody FibonacciRequest request) {
        String fibonacciSequence = fibonacciService.getFibonacciSequence(request.getTime());
        return ResponseEntity.ok(new FibonacciResponse(fibonacciSequence));
    }

}
