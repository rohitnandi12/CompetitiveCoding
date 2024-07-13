package com.datastructures.probabilistic.bloom_filter;

public interface HashFunctionStrategy {
    int getHash(String key);
}