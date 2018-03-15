package homework.l3

case class Book(title: String, author: String, genre: String, height: Int, publisher: String)

object TextParser {
  def parse(text: String): Seq[Book] = ???
}
