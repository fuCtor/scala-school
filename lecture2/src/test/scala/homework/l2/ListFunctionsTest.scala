package homework.l2

import org.scalatest.{FlatSpec, Matchers}

class ListFunctionsTest extends FlatSpec with Matchers {
  "ListFunctions.fold" should "concatenate digits in List" in {
    ListFunctions.fold("", List(1, 2, 3))((s, i) => s + " " + i) shouldBe "1 2 3"
  }

  "ListFunctions.fold" should "return start value if List is empty" in {
    ListFunctions.fold("", List.empty[Int])((s, i) => s + " " + i) shouldBe ""
  }
}
