package lectures.l5

import org.scalatest.{FlatSpec, Matchers}

class TestExample extends FlatSpec with Matchers {

  behavior of "plus operator"
  it should "correctly sum Ints" in {
    1 + 1 shouldBe 2
  }

  it should "correctly sum Doubles" in {
    1d + 1d shouldBe 2d +- 0.00000001
  }

  "Division by zero" should "throw an exception" in {
    intercept[ArithmeticException] {
      1 / 0
    }

    an [ArithmeticException] should be thrownBy 1 / 0

    the [ArithmeticException] thrownBy 1 / 0 should have message "/ by zero"
  }

  "Division by one" should "not throw an exception" in {
    noException should be thrownBy 0 / 1
  }
}
