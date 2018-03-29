/**
  * Необходимо реализовать функциональность сохранения пользователей в базу данных.
  *
  * Сейчас написаны функции, которые помогут вам написать реализацию.
  * Вашей задачей является создание абстракций, реализаций и взаимодействия между ними.
  *
  * При сохраненнии пользователя необходимо обновлять поля:
  * * id - при создании пользователя
  * * createdAt - при первом сохранении пользователя
  * * updatedAt - при любом обновлении пользователя
  *
  * Учитывайте, что каждая из реализаций может поменяться или может потребует расширения.
  */

import java.time._
import java.util.concurrent.atomic.AtomicLong

import scala.collection.concurrent.TrieMap

object UR {
  case class User(id: Option[Long] = None,
                  name: String,
                  createdAt: Option[Instant] = None,
                  updatedAt: Option[Instant] = None)

  // Генерация ID пользователей
  val idSeq: AtomicLong = new AtomicLong(0)
  def nextUserId: Long = idSeq.incrementAndGet()

  // Получение текущего времени
  def currentInstant: Instant = Instant.now()

  // Сохранение и чтенне пользователей
  val storage = TrieMap.empty[Long, User]
  def find(id: Long): Option[User] = storage.get(id)
  def persist(user: User): User = {
    storage.update(
      user.id.getOrElse(throw new IllegalArgumentException("Missing user.id")),
      user
    )
    user
  }
}

