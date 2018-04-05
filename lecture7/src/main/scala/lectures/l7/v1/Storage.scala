package lectures.l7.v1

import lectures.l7.Item

trait Storage[K, V] {
  def persist(id: K, item: V): Storage[K, V]
}

class MemoryStorage[K](storage: Map[K, Item[K]]) extends Storage[K, Item[K]] {
  def persist(item: Item[K]): MemoryStorage[K] = persist(item.id, item)
  override def persist(id: K, item: Item[K]): MemoryStorage[K] = new MemoryStorage(storage.updated(id, item))
  override def toString: String = storage.toString()
}

object MemoryStorage {
  def empty[K] = new MemoryStorage[K](Map.empty)

  def apply[K](item: Item[K]): MemoryStorage[K] = empty.persist(item)
}

