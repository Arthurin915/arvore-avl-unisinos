package com.unisinos.trabalho.packages.domain;

import com.unisinos.trabalho.packages.generic.INodeValue;

import java.time.LocalDate;

public class LocalDateKey implements INodeValue<LocalDate> {

    private LocalDate key;

    private int index;

    public LocalDateKey(LocalDate key, int index) {
        this.key = key;
        this.index = index;
    }

    public LocalDateKey(LocalDate key) {
        this.key = key;
    }

    @Override
    public int compareTo(INodeValue<LocalDate> otherValue) {
        return key.compareTo(otherValue.getValue());
    }

    @Override
    public LocalDate getValue() {
        return key;
    }

    @Override
    public void setValue(LocalDate v) {
        this.key = v;
    }

    public LocalDate getKey() {
        return key;
    }

    public void setKey(LocalDate key) {
        this.key = key;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
