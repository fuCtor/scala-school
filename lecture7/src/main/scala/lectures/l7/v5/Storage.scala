package lectures.l7.v5

/*
 * Реализация должна поддерживать все что в v4, но добавляется:
 * возможность использования foreach
 *
 * Способ использования смотреть в Main
 */

import lectures.l7.Item
trait Storage[K, V]{
  def persist[C](id: K, item: C): Storage[K, C]
}

class MemoryStorage