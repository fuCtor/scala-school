package lectures.l7.v4

/*
 * Реализация должна поддерживать все что в v3, но добавляется:
 * возможность объедиение двух хранилищ, результируеще хранилище будет общего для обоих типа
 *
 * Способ использования смотреть в Main
 */

import lectures.l7.Item
trait Storage[K, V] {
  def persist[C](id: K, item: C): Storage[K, C]
}

class MemoryStorage