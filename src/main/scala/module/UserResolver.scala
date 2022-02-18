package module

import repository.UserRepository
import sangria.macros.derive.deriveObjectType
import sangria.schema.{Argument, Field, ListType, OptionType, StringType, fields}

object UserResolver {

  val userId = Argument("id", StringType, description = "The user id")

  def getQueries: List[Field[UserRepository, Unit]] = {
    return fields[UserRepository, Unit](

      Field("userFindById", OptionType(deriveObjectType[Unit, UserSchema]()),
        description = Some("Returns a user with specific id."),
        arguments = userId :: Nil, // arguments to the query i.e. `id`
        resolve = c ⇒ c.ctx.findById(c arg userId)
      ),

      Field("userFindAll", ListType(deriveObjectType[Unit, UserSchema]()),
        description = Some("Returns a list of all users."),
        // notice that there is NO arguments
        resolve = _.ctx.findAll)
    )
  }
}
