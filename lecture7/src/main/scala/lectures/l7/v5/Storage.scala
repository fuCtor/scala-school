package lectures.l7.v5

import lectures.l7.Item


trait Storage[K, V <: Item[K]] extends Traversable[(K, V)] {
  def persist[C >: V <: Item[K]](id: K, item: C): Storage[K, C]
}

class MemoryStorage[K, V <: Item[K]](private val storage: Map[K, V]) extends Storage[K, V] {
  def persist[C >: V <: Item[K]](item: C): MemoryStorage[K, C] = persist(item.id, item)

  override def persist[C >: V <: Item[K]](id: K, item: C): MemoryStorage[K, C] = {
    val ns = storage.mapValues({ v: C => v })
    new MemoryStorage[K, C](ns.updated(id, item))
  }

  def concat[C >: V <: Item[K]](ms: MemoryStorage[K, C]) =
    new MemoryStorage[K, C](storage.mapValues({ v: C => v }) ++ ms.storage)

  override def toString: String = storage.toString()

  override def foreach[U](f: ((K, V)) => U): Unit = storage.foreach(f)
}

object MemoryStorage {
  def empty[K, V <: Item[K]] = new MemoryStorage[K, V](Map.empty)

  def apply[K, V <: Item[K]](item: V): MemoryStorage[K, V] = empty.persist(item)
}

