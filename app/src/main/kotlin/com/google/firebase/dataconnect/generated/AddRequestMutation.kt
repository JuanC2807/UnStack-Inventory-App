
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



public interface AddRequestMutation :
    com.google.firebase.dataconnect.generated.GeneratedMutation<
      ExampleConnector,
      AddRequestMutation.Data,
      AddRequestMutation.Variables
    >
{
  
    @kotlinx.serialization.Serializable
  public data class Variables(
  
    val itemId: @kotlinx.serialization.Serializable(with = com.google.firebase.dataconnect.serializers.UUIDSerializer::class) java.util.UUID,
    val supplierId: @kotlinx.serialization.Serializable(with = com.google.firebase.dataconnect.serializers.UUIDSerializer::class) java.util.UUID,
    val dateRequested: @kotlinx.serialization.Serializable(with = com.google.firebase.dataconnect.serializers.TimestampSerializer::class) com.google.firebase.Timestamp
  ) {
    
    
  }
  

  
    @kotlinx.serialization.Serializable
  public data class Data(
  
    val request_insert: RequestKey
  ) {
    
    
  }
  

  public companion object {
    public val operationName: String = "AddRequest"

    public val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data> =
      kotlinx.serialization.serializer()

    public val variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables> =
      kotlinx.serialization.serializer()
  }
}

public fun AddRequestMutation.ref(
  
    itemId: java.util.UUID,supplierId: java.util.UUID,dateRequested: com.google.firebase.Timestamp,

  
  
): com.google.firebase.dataconnect.MutationRef<
    AddRequestMutation.Data,
    AddRequestMutation.Variables
  > =
  ref(
    
      AddRequestMutation.Variables(
        itemId=itemId,supplierId=supplierId,dateRequested=dateRequested,
  
      )
    
  )

public suspend fun AddRequestMutation.execute(

  
    
      itemId: java.util.UUID,supplierId: java.util.UUID,dateRequested: com.google.firebase.Timestamp,

  

  ): com.google.firebase.dataconnect.MutationResult<
    AddRequestMutation.Data,
    AddRequestMutation.Variables
  > =
  ref(
    
      itemId=itemId,supplierId=supplierId,dateRequested=dateRequested,
  
    
  ).execute()


