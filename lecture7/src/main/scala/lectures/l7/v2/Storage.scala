package lectures.l7.v2

import lectures.l7.Item

trait Storage[K, V] {
  def persist(id: K, item: V): Storage[K, V]
}

class MemoryStorage[K, V <: Item[K]](storage: Map[K, V]) extends Storage[K, V] {
  def persist(item: V): MemoryStorage[K, V] = persist(item.id, item)
  override def persist(id: K, item: V): MemoryStorage[K, V] = new MemoryStorage(storage.updated(id, item))
  override def toString: String = storage.toString()
}

object MemoryStorage {
  def empty[K, V <: Item[K]] = new MemoryStorage[K, V](Map.empty)

  def apply[K, V <: Item[K]](item: V): MemoryStorage[K, V] = empty.persist(item)
}

