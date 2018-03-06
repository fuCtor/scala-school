package lectures.l3

/**
  * Необходимо реализовать функции pipeline,
  * которая принимать список PartialFunction и возращает Function1[Int, Int].
  *
  * Каждая следующая функцию в списке принимает результат предыдущей функции.
  *
  * Важно понимать, что PartialFunction может не работать на всех значениях аргумента.
  * В этом случае необходимо не менять значение и передавать его как есть в следующую функцию.
  *
  * Менять f1, f2, f3 нельзя.
  */
class PartialFunction1 extends App {
  val f1: PartialFunction[Int, Int] = {
    case x if x % 2 == 0 => x - 1
  }

  val f2: PartialFunction[Int, Int] = {
    case x if x > 5 && x < 10 => 10
  }

  val f3: PartialFunction[Int, Int] = {
    case x => x * 3
  }

  for (x <- 1 to 100) {
    val f = pipeline(Seq(f1, f2, f3))
    println(s"$x -> ${f(x)}")
  }

  def pipeline(fx: Seq[PartialFunction[Int, Int]]): Int => Int = ???
}
