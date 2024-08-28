import scala.collection.mutable.ArrayBuffer;

object MergeTwoSortedArrays {
  def main(args:Array[String]): Unit = {
    println(solve(List.of(1,3,5), List.of(2,4)))
  }
  def solve(A: Array[Int], B: Array[Int]): Array[Int]  = {

    var a = 0;
    var b = 0;
    var ans = new ArrayBuffer[Int](A.length + B.length)

    while(a < A.length && b < B.length) {
      if(A(a) < B(b)) {
        ans += A(a)
        a += 1
      } else {
        ans += B(b)
        b += 1
      }
    }

    if(a<A.length) {
      ans ++= A.slice(a, A.length)
    }

    if(b<B.length) {
      ans ++= B.slice(b, B.length)
    }

    ans.toArray
  }
}