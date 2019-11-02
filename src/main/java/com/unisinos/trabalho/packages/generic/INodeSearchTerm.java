package com.unisinos.trabalho.packages.generic;

public interface INodeSearchTerm<T> {

    int compareTo(INodeValue<T> value);

    boolean isValid(INodeValue<T> value);

}
