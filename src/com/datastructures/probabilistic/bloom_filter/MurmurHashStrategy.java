package com.datastructures.probabilistic.bloom_filter;

import org.apache.commons.codec.digest.MurmurHash3;

public class MurmurHashStrategy implements HashFunctionStrategy {

    private int seed;
    MurmurHashStrategy(int seed) {
        this.seed = seed;
    }
    @Override
    public int getHash(String input) {
        byte[] bytes = input.getBytes();
        return Math.abs(MurmurHash3.hash32x86(bytes, 0, bytes.length, seed));
    }
}
