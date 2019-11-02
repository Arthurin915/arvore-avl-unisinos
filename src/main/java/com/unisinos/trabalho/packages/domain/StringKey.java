package com.unisinos.trabalho.packages.domain;

import com.unisinos.trabalho.packages.generic.INodeValue;

public class StringKey implements INodeValue<String> {

    String key;

    int index;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public StringKey(String key) {
        this.key = key;
    }

    public StringKey(String key, int index) {
        this.key = key;
        this.index = index;
    }

    @Override
    public int compareTo(INodeValue<String> otherValue) {
        return key.compareToIgnoreCase(otherValue.getValue());
    }

    @Override
    public String getValue() {
        return key;
    }

    @Override
    public void setValue(String cpf) {
        this.key = cpf;
    }
}
