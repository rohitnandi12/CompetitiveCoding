package com.datastructures.probabilistic.bloom_filter;

import java.util.ArrayList;
import java.util.List;

/**
 * Gives you False Positive but definitely doesn't give you false negative
 */

public class BloomFilter {

    byte[] filter;
    int totalBits;

    List<HashFunctionStrategy> hashFunctionStrategies;

    BloomFilter(int totalBits) {
        if (totalBits <= 0) {
            throw new IllegalArgumentException("totalBits must be positive");
        }
        this.totalBits = totalBits;
        int filterSize = (totalBits + 7) >> 3; // equals to (totalBits / 8) + (totalBits % 8 == 0 ? 0 : 1);
        this.filter = new byte[filterSize];
        this.hashFunctionStrategies = new ArrayList<>();
    }

    public void addHashFunction(HashFunctionStrategy strategy) {
        hashFunctionStrategies.add(strategy);
    }

    public void printFilter() {
        for (byte b : this.filter) {
            System.out.print(new StringBuilder(Integer.toBinaryString(b)).reverse() + " | ");
        }
        System.out.println();
    }

    public void add(String key) {
        if (key.isEmpty())
            throw new RuntimeException("String can't be empty");
        for (HashFunctionStrategy strategy : hashFunctionStrategies) {
            int hash = strategy.getHash(key);
            BitAddress bitAddress = getBitAddress(hash);
            this.filter[bitAddress.arrayIndex] = (byte) ((this.filter[bitAddress.arrayIndex] & 0xff) | ((byte) 1 << bitAddress.bitNumber));
        }
    }

    public boolean exists(String key) {
        if (key.isEmpty())
            return false;
        for (HashFunctionStrategy strategy : hashFunctionStrategies) {
            int hash = strategy.getHash(key);
            BitAddress bitAddress = getBitAddress(hash);
            if ((((this.filter[bitAddress.arrayIndex] & 0xff) & (1 << bitAddress.bitNumber)) == 0)) {
                return false;
            }
        }
        return true;
    }

    private BitAddress getBitAddress(int hash) {
        if (hash < 0)
            throw new RuntimeException("Hash cannot be negative");
        int hashInBitRange = hash % this.totalBits;
        int arrayIndex = hashInBitRange / 8;
        int bitNumber = hashInBitRange % 8;
        return new BitAddress(arrayIndex, bitNumber);
    }
}
