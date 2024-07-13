package com.datastructures.probabilistic.bloom_filter;

class BitAddress {
    int arrayIndex;
    int bitNumber;

    public BitAddress(int arrayIndex, int bitNumber) {
        this.arrayIndex = arrayIndex;
        this.bitNumber = bitNumber;
    }
}
