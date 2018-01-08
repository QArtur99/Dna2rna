package com.artf.dna;

import java.util.List;

/**
 * Created by ART_F on 2018-01-08.
 */

public class RnaObject {
    private List<Integer> position;
    private int amount;
    private String rnaValue;

    public RnaObject(String rnaValue) {
        this.rnaValue = rnaValue;
    }

    public List<Integer> getPosition() {
        return position;
    }

    public void setPosition(List<Integer> position) {
        this.position = position;
    }

    public String getRnaValue() {
        return rnaValue;
    }

    public void setRnaValue(String rnaValue) {
        this.rnaValue = rnaValue;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
