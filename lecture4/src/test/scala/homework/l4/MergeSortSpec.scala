package homework.l4

import org.scalatest.{FlatSpec, Matchers}

import scala.util.Random

class MergeSortSpec extends FlatSpec with Matchers {

  "sorted seq " should "equal to already sorted seq" in {
    val randomSeq = Seq.fill(Random.nextInt(16) + 16)(Random.nextInt(10000))
    val sorted = MergeSort.mergeSort(randomSeq)
    sorted shouldBe randomSeq.sorted
  }

}