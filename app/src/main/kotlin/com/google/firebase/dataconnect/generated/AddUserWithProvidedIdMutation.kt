
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



public interface AddUserWithProvidedIdMutation :
    com.google.firebase.dataconnect.generated.GeneratedMutation<
      ExampleConnector,
      AddUserWithProvidedIdMutation.Data,
      AddUserWithProvidedIdMutation.Variables
    >
{
  
    @kotlinx.serialization.Serializable
  public data class Variables(
  
    val uid: String,
    val firstName: String,
    val lastName: String,
    val email: String
  ) {
    
    
  }
  

  
    @kotlinx.serialization.Serializable
  public data class Data(
  
    val appUser_insert: AppUserKey
  ) {
    
    
  }
  

  public companion object {
    public val operationName: String = "AddUserWithProvidedID"

    public val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data> =
      kotlinx.serialization.serializer()

    public val variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables> =
      kotlinx.serialization.serializer()
  }
}

public fun AddUserWithProvidedIdMutation.ref(
  
    uid: String,firstName: String,lastName: String,email: String,

  
  
): com.google.firebase.dataconnect.MutationRef<
    AddUserWithProvidedIdMutation.Data,
    AddUserWithProvidedIdMutation.Variables
  > =
  ref(
    
      AddUserWithProvidedIdMutation.Variables(
        uid=uid,firstName=firstName,lastName=lastName,email=email,
  
      )
    
  )

public suspend fun AddUserWithProvidedIdMutation.execute(

  
    
      uid: String,firstName: String,lastName: String,email: String,

  

  ): com.google.firebase.dataconnect.MutationResult<
    AddUserWithProvidedIdMutation.Data,
    AddUserWithProvidedIdMutation.Variables
  > =
  ref(
    
      uid=uid,firstName=firstName,lastName=lastName,email=email,
  
    
  ).execute()


