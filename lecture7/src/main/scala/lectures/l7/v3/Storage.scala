package lectures.l7.v3


/*
 * Реализация должна поддерживать все что в v2, но добавляется:
 * должен позволять помещать в хранилище объекты родительского типа С по-отношению к V,
 * результатом будет хранилище с ключом K и типом C
 *
 * Способ использования смотреть в Main
 *
 * Допустимо менять ограничения Type Parameter в исходном trait
 */

import lectures.l7.Item
trait Storage[K, V] {
  def persist[C](id: K, item: C): Storage[K, C]
}

class MemoryStorage