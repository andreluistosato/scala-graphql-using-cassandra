package module

import com.datastax.driver.core.LocalDate

case class UserSchema(
   id: String,
   name: String,
   address: String,
   active: Boolean,
   age: Double,
   birth_date: LocalDate)
