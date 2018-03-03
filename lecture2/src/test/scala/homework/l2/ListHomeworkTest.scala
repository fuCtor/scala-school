package homework.l2

import org.scalatest.{FlatSpec, Matchers}

class ListHomeworkTest extends FlatSpec with Matchers {

  "ListHomework.sumOfSquares" should "return sum of squares for non empty list" in {
    ListHomework.sumOfSquares(List(1, 2, 3)) shouldBe 14
  }

  "ListHomework.sumOfSquares" should "return 0 for empty list" in {
    ListHomework.sumOfSquares(List.empty) shouldBe 0
  }

  "ListHomework.multiplicationOfCubes" should "return multiplication of cubes for non empty list" in {
    ListHomework.sumOfSquares(List(1, 2, 3)) shouldBe 216
  }

  "ListHomework.multiplicationOfCubes" should "return 1 for empty list" in {
    ListHomework.sumOfSquares(List.empty) shouldBe 1
  }
}
