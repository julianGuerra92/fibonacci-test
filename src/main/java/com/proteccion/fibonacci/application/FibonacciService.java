package com.proteccion.fibonacci.application;

import com.proteccion.fibonacci.core.dao.FibonacciSequenceRepository;
import com.proteccion.fibonacci.core.interfaces.IFibonacciService;
import com.proteccion.fibonacci.core.models.FibonacciSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FibonacciService implements IFibonacciService {

    @Autowired
    private FibonacciSequenceRepository fibonacciSequenceRepository;

    @Override
    public String getFibonacciSequence(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        System.out.println("time: " + time);
        try {
            LocalTime.parse(time, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid time format. Expected format is HH:mm:ss");
        }
        List<Integer> initialNumbers = getDigitsFromMinutes(time);
        int count = LocalTime.parse(time, formatter).getSecond();
        List<BigInteger> fibonacciSequence = generateFibonacciSequence(initialNumbers, count);
        fibonacciSequence.sort((a, b) -> b.compareTo(a));

        try {
            FibonacciSequence fibonacci = new FibonacciSequence();
            fibonacci.setTime(time);
            fibonacci.setSequence(fibonacciSequence.toString());
            fibonacciSequenceRepository.save(fibonacci);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return fibonacciSequence.toString();
    }

    @Override
    public String getFibonacciSequence() {
        LocalTime now = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        int count = now.getSecond();
        List<Integer> initialNumbers = getDigitsFromMinutes(now.format(formatter));
        List<BigInteger> fibonacciSequence = generateFibonacciSequence(initialNumbers, count);
        fibonacciSequence.sort((a, b) -> b.compareTo(a));

        try {
            FibonacciSequence fibonacci = new FibonacciSequence();
            fibonacci.setTime(now.format(formatter));
            fibonacci.setSequence(fibonacciSequence.toString());
            fibonacciSequenceRepository.save(fibonacci);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return fibonacciSequence.toString();
    }

    private static List<Integer> getDigitsFromMinutes(String time) {
        String[] parts = time.split(":");
        int minutes = Integer.parseInt(parts[1]);
        List<Integer> digits = new ArrayList<>();
        digits.add(minutes / 10);
        digits.add(minutes % 10);
        return digits;
    }

    public List<BigInteger> generateFibonacciSequence(List<Integer> initialNumbers, int count) {
        List<BigInteger> series = new ArrayList<>();
        if (count <= 0) {
            return series;
        }
        series.add(BigInteger.valueOf(initialNumbers.get(0)));
        if (count > 1) {
            series.add(BigInteger.valueOf(initialNumbers.get(1)));
            for (int i = 2; i < count + 2; i++) {
                BigInteger nextNumber = series.get(i - 1).add(series.get(i - 2));
                series.add(nextNumber);
            }
        }
        return series;
    }

}
