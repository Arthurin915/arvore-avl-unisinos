package com.unisinos.trabalho.packages.domain;

import com.unisinos.trabalho.packages.generic.INodeSearchTerm;
import com.unisinos.trabalho.packages.generic.INodeValue;

public class CpfSearchTerm implements INodeSearchTerm<String> {

    private String key;

    public CpfSearchTerm(String key) {
        this.key = key;
    }

    @Override
    public int compareTo(INodeValue<String> value) {
        return key.compareTo(value.getValue());
    }

    @Override
    public boolean isValid(INodeValue<String> value) {
        return key.equals(value.getValue());
    }
}
