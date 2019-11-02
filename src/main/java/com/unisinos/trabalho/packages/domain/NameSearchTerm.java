package com.unisinos.trabalho.packages.domain;

import com.unisinos.trabalho.packages.generic.INodeSearchTerm;
import com.unisinos.trabalho.packages.generic.INodeValue;

public class NameSearchTerm implements INodeSearchTerm<String> {

    String searchKey;

    public NameSearchTerm(String searchKey) {
        this.searchKey = searchKey;
    }

    @Override
    public int compareTo(INodeValue<String> value) {
        return searchKey.compareToIgnoreCase(value.getValue());
    }

    @Override
    public boolean isValid(INodeValue<String> value) {
        return value.getValue().toLowerCase().startsWith(searchKey.toLowerCase());
    }
}
