package lectures.l7.v1

/*
 * Реализовать класс, который будет хранить в памяти элементы с типом V и ключом K
 */

import lectures.l7.Item
trait Storage[K, V] {
  def persist(id: K, item: V): Storage[K, V]
}

class MemoryStorage