
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



public interface DeleteAllDataMutation :
    com.google.firebase.dataconnect.generated.GeneratedMutation<
      ExampleConnector,
      DeleteAllDataMutation.Data,
      Unit
    >
{
  

  
    @kotlinx.serialization.Serializable
  public data class Data(
  
    val item_deleteMany: Int,
    val supplier_deleteMany: Int,
    val appUser_deleteMany: Int
  ) {
    
    
  }
  

  public companion object {
    public val operationName: String = "DeleteAllData"

    public val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data> =
      kotlinx.serialization.serializer()

    public val variablesSerializer: kotlinx.serialization.SerializationStrategy<Unit> =
      kotlinx.serialization.serializer()
  }
}

public fun DeleteAllDataMutation.ref(
  
): com.google.firebase.dataconnect.MutationRef<
    DeleteAllDataMutation.Data,
    Unit
  > =
  ref(
    
      Unit
    
  )

public suspend fun DeleteAllDataMutation.execute(

  

  ): com.google.firebase.dataconnect.MutationResult<
    DeleteAllDataMutation.Data,
    Unit
  > =
  ref(
    
  ).execute()


