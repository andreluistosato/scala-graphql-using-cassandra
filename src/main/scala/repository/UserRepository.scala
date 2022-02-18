package repository

import akka.actor._
import akka.stream._
import akka.stream.alpakka.cassandra.scaladsl._
import akka.stream.scaladsl._
import com.datastax.driver.core._
import config.CassandraClient
import module.UserSchema

class UserRepository(implicit actorSystem: ActorSystem, actorMaterializer: ActorMaterializer) {

  import scala.concurrent._
  import duration._

  implicit val session: Session = CassandraClient.getSession.connect()

  def findById(user_id: String): Option[UserSchema] = {
    val stmt = new SimpleStatement("select * from domain.user where id = '" + user_id + "'") // PreparedStatement ?
    val rows = CassandraSource(stmt).runWith(Sink.seq)
    val reifiedRows = Await.result(rows, 3.second)
    reifiedRows.headOption match {
      case None ⇒ None
      case Some(row) ⇒ Some(
        mapUser(row))
    }
  }

  def findAll: List[UserSchema] = {
    val stmt = new SimpleStatement("select * from domain.user limit 100").setFetchSize(50)
    val rows = CassandraSource(stmt).runWith(Sink.seq)
    val reifiedRows = Await.result(rows, 5.second)
    reifiedRows.map(
      row ⇒ mapUser(row)
    ).toList
  }

  private def mapUser(row: Row): UserSchema = {
    return UserSchema(
      row.getString("id"),
      row.getString("name"),
      row.getString("address"),
      row.getBool("active"),
      row.getDecimal("age").doubleValue(),
      row.getDate("birth_date"));
  }
}