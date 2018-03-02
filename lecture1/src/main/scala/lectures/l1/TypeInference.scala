package lectures.l1

/**
  * Не запуская приложения, предположите, чему будет равен 'result'.
  * Почему?
  *
  */
object TypeInference extends App {

  def printSomething(): String = "2 плюс 3 - это "

  def calculateSomething(): Int = 1 + 1

  def result: String = printSomething + 3 + calculateSomething

  print(result)

}

