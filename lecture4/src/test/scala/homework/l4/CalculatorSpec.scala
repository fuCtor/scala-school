package homework.l4

import org.scalatest.{FlatSpec, Matchers}

class CalculatorSpec extends FlatSpec with Matchers {
  "Calculator" should "sum values" in {

    val expression = Map(
      "a" -> Literal(1),
      "b" -> Literal(2),
      "c" -> Plus(Ref("a"), Ref("b"))
    )

    Calculator.computeValues(expression)("c") shouldBe 3
  }

  "Calculator" should "diff values" in {
    val expression = Map(
      "a" -> Literal(2),
      "b" -> Literal(1),
      "c" -> Minus(Ref("a"), Ref("b"))
    )

    Calculator.computeValues(expression)("c") shouldBe 1
  }

  "Calculator" should "multiply values" in {
    val expression = Map(
      "a" -> Literal(2),
      "b" -> Literal(3),
      "c" -> Times(Ref("a"), Ref("b"))
    )

    Calculator.computeValues(expression)("c") shouldBe 6
  }

  "Calculator" should "divide values" in {
    val expression = Map(
      "a" -> Literal(6),
      "b" -> Literal(3),
      "c" -> Divide(Ref("a"), Ref("b"))
    )

    Calculator.computeValues(expression)("c") shouldBe 2
  }

  "Calculator" should "evaluate complex expression" in {
    val expression = Map(
      "a" -> Literal(6),
      "b" -> Literal(3),
      "c" -> Literal(2),
      "d" -> Minus(Ref("c"), Divide(Ref("a"), Ref("b"))) // d = c - a / b
    )

    Calculator.computeValues(expression)("d") shouldBe 0
  }

  "Calculator" should "return NaN for cyclic expression" in {
    val expression = Map(
      "a" -> Plus(Ref("c"), Literal(2)),
      "b" -> Literal(3),
      "c" -> Minus(Ref("a"), Ref("b")) // d = c - a / b
    )

    assert(Calculator.computeValues(expression)("c").isNaN)
  }
}
