package com.proteccion.fibonacci.core.models;

import jakarta.persistence.*;

@Entity
@Table(name = "fibonacci_sequence")
public class FibonacciSequence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sequence")
    private String sequence;

    @Column(name = "time")
    private String time;

    public FibonacciSequence(Long id, String sequence, String time) {
        this.id = id;
        this.sequence = sequence;
        this.time = time;
    }

    public FibonacciSequence() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
