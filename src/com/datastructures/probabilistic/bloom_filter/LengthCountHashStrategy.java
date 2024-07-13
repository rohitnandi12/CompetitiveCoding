package com.datastructures.probabilistic.bloom_filter;

public class LengthCountHashStrategy implements HashFunctionStrategy {

    @Override
    public int getHash(String key) {
        return key.length();
    }
}
