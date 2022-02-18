package config

import com.datastax.driver.core._

object CassandraClient {

  def getSession: Cluster = {
    return Cluster.builder.addContactPoint("127.0.0.1").withPort(9042).build
  }
}