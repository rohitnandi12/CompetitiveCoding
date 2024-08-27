package com.codesignal

object SortByHeight {

  def main(args: Array[String]): Unit = {
    println(solution(Array(-1, 150, 190, 170, -1, -1, 160, 180)).mkString("Array(", ", ", ")"))
    // solution(a) = [-1, 150, 160, 170, -1, -1, 180, 190]
  }

  def solution(a: Array[Int]): Array[Int] = {
    // Extract and sort only the non-negative elements
    val sortedValues = a.filter(_ != -1).sorted

    // Initialize a pointer for the sorted values array
    var idx = 0

    // Replace non-negative elements in the original array with sorted values
    for (i <- a.indices) {
      if (a(i) != -1) {
        a(i) = sortedValues(idx)
        idx += 1
      }
    }
    a
  }
}
