package com.proteccion.fibonacci;

import com.proteccion.fibonacci.application.FibonacciService;
import com.proteccion.fibonacci.core.dao.FibonacciSequenceRepository;
import com.proteccion.fibonacci.core.models.FibonacciSequence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigInteger;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FibonacciServiceTest {

    @Mock
    private FibonacciSequenceRepository fibonacciSequenceRepository;

    @InjectMocks
    private FibonacciService fibonacciService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetFibonacciSequenceWithValidTime() {
        String time = "12:34:56";
        List<BigInteger> expectedSequence = Arrays.asList(
                BigInteger.valueOf(3),
                BigInteger.valueOf(2),
                BigInteger.valueOf(1),
                BigInteger.valueOf(1),
                BigInteger.valueOf(2),
                BigInteger.valueOf(3),
                BigInteger.valueOf(5),
                BigInteger.valueOf(8)
        );

        String result = fibonacciService.getFibonacciSequence(time);

        assertTrue(result.contains(expectedSequence.toString()));
        verify(fibonacciSequenceRepository, times(1)).save(any(FibonacciSequence.class));
    }

    @Test
    void testGetFibonacciSequenceWithInvalidTime() {
        String time = "invalid_time";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            fibonacciService.getFibonacciSequence(time);
        });

        assertEquals("Invalid time format. Expected format is HH:mm:ss", exception.getMessage());
        verify(fibonacciSequenceRepository, times(0)).save(any(FibonacciSequence.class));
    }

    @Test
    void testGetFibonacciSequenceWithoutTime() {
        LocalTime now = LocalTime.now();
        int count = now.getSecond();
        List<BigInteger> expectedSequence = fibonacciService.generateFibonacciSequence(Arrays.asList(3, 4), count);

        String result = fibonacciService.getFibonacciSequence();

        assertTrue(result.contains(expectedSequence.toString()));
        verify(fibonacciSequenceRepository, times(1)).save(any(FibonacciSequence.class));
    }
}