
@file:Suppress(
  "KotlinRedundantDiagnosticSuppress",
  "LocalVariableName",
  "MayBeConstant",
  "RedundantVisibilityModifier",
  "RedundantCompanionReference",
  "RemoveEmptyClassBody",
  "SpellCheckingInspection",
  "LocalVariableName",
  "unused",
)

package com.google.firebase.dataconnect.generated



public interface AddUserMutation :
    com.google.firebase.dataconnect.generated.GeneratedMutation<
      ExampleConnector,
      AddUserMutation.Data,
      AddUserMutation.Variables
    >
{
  
    @kotlinx.serialization.Serializable
  public data class Variables(
  
    val firstName: String,
    val lastName: String,
    val email: String
  ) {
    
    
  }
  

  
    @kotlinx.serialization.Serializable
  public data class Data(
  
    val appUser_upsert: AppUserKey
  ) {
    
    
  }
  

  public companion object {
    public val operationName: String = "AddUser"

    public val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data> =
      kotlinx.serialization.serializer()

    public val variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables> =
      kotlinx.serialization.serializer()
  }
}

public fun AddUserMutation.ref(
  
    firstName: String,lastName: String,email: String,

  
  
): com.google.firebase.dataconnect.MutationRef<
    AddUserMutation.Data,
    AddUserMutation.Variables
  > =
  ref(
    
      AddUserMutation.Variables(
        firstName=firstName,lastName=lastName,email=email,
  
      )
    
  )

public suspend fun AddUserMutation.execute(

  
    
      firstName: String,lastName: String,email: String,

  

  ): com.google.firebase.dataconnect.MutationResult<
    AddUserMutation.Data,
    AddUserMutation.Variables
  > =
  ref(
    
      firstName=firstName,lastName=lastName,email=email,
  
    
  ).execute()


