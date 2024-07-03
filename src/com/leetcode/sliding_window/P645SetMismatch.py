class Solution:
    '''
    https://leetcode.com/problems/set-mismatch/submissions/1302479440/
    You have a set of integers s, which originally contains all the numbers from 1 to n. Unfortunately, due to some error, one of the numbers in s got duplicated to another number in the set, which results in repetition of one number and loss of another number.

    You are given an integer array nums representing the data status of this set after the error.

    Find the number that occurs twice and the number that is missing and return them in the form of an array.
    '''
    def findErrorNums(self, nums: List[int]) -> List[int]:
        n = len(nums) # 4
        Sn = (n * (n + 1)) / 2 # 10
        Sa = sum(nums) # 9
        mMinusD = Sn - Sa  # 1

        SnSq = (n*(n+1)*(2*n+1))/6 #
        SaSq = sum([n**2 for n in nums])
        mPlusD = (SnSq - SaSq) / mMinusD

        m = (mPlusD + mMinusD) / 2
        d = (mPlusD - mMinusD) / 2

        return [int(d), int(m)]