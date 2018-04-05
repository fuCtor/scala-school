package lectures.l7.v2

/*
 * Реализовать класс, который будет хранить в памяти элементы с типом V и ключом K
 * Элементы должны реализовывать интерфейс Item, с типом ключа K
 * Реализовать метод, который будет принимать только элемент для сохранения, без явного указания ключа
 */

import lectures.l7.Item
trait Storage[K, V] {
  def persist(id: K, item: V): Storage[K, V]
}

class MemoryStorage
