package com.scaler.array.prefixsum

object CountingSubArraysEasy {
  def main(args: Array[String]): Unit = {
    assert(solve(Array(1, 23), 10) == 1)
    assert(solve(Array(1, 11, 2, 3, 15), 10) == 4)
    assert(solve(Array(2, 5, 6), 10) == 4)
  }

  private def solve(A: Array[Int], B: Int): Int = {

    var count: Int = 0
    var start: Int = 0
    var end = 0
    var currentSum = 0

    while (end < A.length) {

      currentSum += A(end)

      while (currentSum >= B && start <= end) {
        currentSum -= A(start)
        start += 1
      }

      count += (end - start + 1)
      end += 1
    }
    count
  }
}