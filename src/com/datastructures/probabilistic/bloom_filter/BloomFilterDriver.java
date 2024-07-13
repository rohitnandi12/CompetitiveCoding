package com.datastructures.probabilistic.bloom_filter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class BloomFilterDriver {
    private static final Random random = new Random();

    private static int effectiveBitSeize(int noOfHashFunctions, int estimatedTotalItems, double desitedFalsePostiveRate) {
        double pPow = Math.pow(desitedFalsePostiveRate, 1.0 / noOfHashFunctions);
        double numerator = -estimatedTotalItems * Math.log(pPow);
        double denominator = Math.pow((1.0 / noOfHashFunctions), 2);
        return (int) Math.ceil(numerator / denominator);
    }

    private static List<String> generateStrings(int requiredNumberOfStrings) {

        List<String> strings = new ArrayList<>();

        while (requiredNumberOfStrings > 0) {
            int length = random.nextInt(50) + 1;
            StringBuilder sb = new StringBuilder(length);
            String characters = "abcdefghijklmnopqrstuvwxyz";

            for (int i = 0; i < length; i++) {
                sb.append(characters.charAt(random.nextInt(characters.length())));
            }
            strings.add(sb.toString());
            requiredNumberOfStrings--;
        }

        return strings;
    }

    public static void main(String[] args) {
        int sampleSpace = 10_000;
        int totalNumberOfBits = effectiveBitSeize(2, sampleSpace, 0.001); // 138156
        BloomFilter bf = new BloomFilter(totalNumberOfBits + 1000);
        bf.addHashFunction(new XXHashStrategy((int) (Math.random() * 1000)));
        bf.addHashFunction(new MurmurHashStrategy((int) (Math.random() * 1000)));
//        bf.addHashFunction(new LengthCountHashStrategy());


        generateStrings(sampleSpace).forEach(bf::add);
        AtomicInteger countFalsePositiveRate = new AtomicInteger();
        generateStrings(sampleSpace).forEach(s -> countFalsePositiveRate.addAndGet(bf.exists(s) ? 1 : 0));

        System.out.println("False positive Rate was " + (100.0 * countFalsePositiveRate.get() / sampleSpace));

    }
}
