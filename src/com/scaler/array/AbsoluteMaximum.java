package com.scaler.array;

import java.util.List;

public class AbsoluteMaximum {

    public static void main(String[] args) {

        int result = solve(List.of(1,2), List.of(3,4), List.of(5,6), List.of(7,8));
        System.out.println(result);
    }
    public static int solve(List<Integer> A, List<Integer> B, List<Integer> C, List<Integer> D) {
        int N = A.size();
        int res = Integer.MIN_VALUE;

        for(int i=0; i<16; i++) {
            int maxi = Integer.MIN_VALUE;
            int mini = Integer.MAX_VALUE;

            for(int j=0; j<N; j++) {
                int sum = A.get(j);
                int temp = 0;
                for(int k=0; k<4; k++) {
                    if(k == 0)
                        temp = B.get(j);
                    else if(k == 1)
                        temp = C.get(j);
                    else if(k == 2)
                        temp = D.get(j);
                    else
                        temp = j;

                    if((i & (1 << k)) != 0) {
                        sum += temp;
                    } else {
                        sum -= temp;
                    }
                }
                maxi = Math.max(maxi, sum);
                mini = Math.min(mini, sum);
            }
            res = Math.max(res, maxi - mini);
        }

        return res;
    }
}

