package module

import sangria.schema._

/**
  * Defines a GraphQL schema for the current project
  */
object GraphQLSchema {

  val UserQueryViaCassandra = ObjectType(
    "Query", UserResolver.getQueries
  )

  val schema = Schema(UserQueryViaCassandra)
}
