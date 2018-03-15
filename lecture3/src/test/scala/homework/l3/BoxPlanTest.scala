package homework.l3

import org.scalatest.{FlatSpecLike, Matchers}

/**
  * В этом задании необходимо реализовать функцию BoxPlan.plan, которая возвращает список коробок
  * Также необходимо дописать тест для проверки решения.
  *
  * Существует несколько видов коробок:
  * * PlayStationBox может в себе хранить только PlayStation
  * * GuitarBox только Guitar
  * * EaselBox только Easel
  * * BasicBox может хранить любые вещи, но у каждой коробки есть максимальная вместительность - 4
  * * BigBox также может хранить любые вещи, но у него тоже есть максимальная вместительность
  *
  * Между BasicBox и BigBox лучше выбирать BasicBox.
  */
class BoxPlanTest extends FlatSpecLike with Matchers {
  "BoxPlan.plan" should "return seq of boxes" in {
    BoxPlan.plan(Seq(
      Guitar(),
      TV(5),
      Guitar(),
      Shoes(),
      PlayStation(),
      Shoes(),
      Cat(),
      Shoes(),
      Shoes(),
      Dish(),
      Shoes(),
      Easel(),
      Uculele(),
      Dish()
    )) should contain theSameElementsAs ???
  }
}
