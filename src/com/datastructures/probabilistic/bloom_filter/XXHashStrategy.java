package com.datastructures.probabilistic.bloom_filter;

import net.jpountz.xxhash.XXHash32;
import net.jpountz.xxhash.XXHashFactory;

class XXHashStrategy implements HashFunctionStrategy {
    int seed;

    XXHashStrategy(int seed) {
        this.seed = seed;
    }

    public int getHash(String input) {
        byte[] data = input.getBytes();

        XXHashFactory factory = XXHashFactory.fastestInstance();
        XXHash32 hash32 = factory.hash32();

        int hash = hash32.hash(data, 0, data.length, seed);
        return Math.abs(hash);
    }
}
