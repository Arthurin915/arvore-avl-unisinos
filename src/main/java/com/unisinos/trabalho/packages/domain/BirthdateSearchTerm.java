package com.unisinos.trabalho.packages.domain;

import com.unisinos.trabalho.packages.generic.INodeSearchTerm;
import com.unisinos.trabalho.packages.generic.INodeValue;

import java.time.LocalDate;

public class BirthdateSearchTerm implements INodeSearchTerm<LocalDate> {

    LocalDate startKey;

    LocalDate endKey;

    public BirthdateSearchTerm(LocalDate startKey, LocalDate endKey) {
        this.startKey = startKey;
        this.endKey = endKey;
    }

    /*
     *Return 0 if equal
     *Return greater than 0 if this value is greater than otherValue
     *Return less than 0 if this value is less than otherValue
     */
    @Override
    public int compareTo(INodeValue<LocalDate> value) {
        final LocalDate key = value.getValue();

        final int startCompare = startKey.compareTo(key);
        final boolean isSmallerThanMin = startCompare > 0;

        if (isSmallerThanMin) return 1;

        final int endCompare = endKey.compareTo(key);
        final boolean isBiggerThanMax = endCompare < 0;

        if (isBiggerThanMax) return -1;

        return 0;
    }

    @Override
    public boolean isValid(INodeValue<LocalDate> value) {
        return this.compareTo(value) == 0;
    }
}
