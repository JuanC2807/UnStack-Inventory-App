
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



public interface UpdateItemCurrentQuantityMutation :
    com.google.firebase.dataconnect.generated.GeneratedMutation<
      ExampleConnector,
      UpdateItemCurrentQuantityMutation.Data,
      UpdateItemCurrentQuantityMutation.Variables
    >
{
  
    @kotlinx.serialization.Serializable
  public data class Variables(
  
    val itemId: @kotlinx.serialization.Serializable(with = com.google.firebase.dataconnect.serializers.UUIDSerializer::class) java.util.UUID,
    val newCurQuant: Int
  ) {
    
    
  }
  

  
    @kotlinx.serialization.Serializable
  public data class Data(
  
    val item_update: ItemKey?
  ) {
    
    
  }
  

  public companion object {
    public val operationName: String = "UpdateItemCurrentQuantity"

    public val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data> =
      kotlinx.serialization.serializer()

    public val variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables> =
      kotlinx.serialization.serializer()
  }
}

public fun UpdateItemCurrentQuantityMutation.ref(
  
    itemId: java.util.UUID,newCurQuant: Int,

  
  
): com.google.firebase.dataconnect.MutationRef<
    UpdateItemCurrentQuantityMutation.Data,
    UpdateItemCurrentQuantityMutation.Variables
  > =
  ref(
    
      UpdateItemCurrentQuantityMutation.Variables(
        itemId=itemId,newCurQuant=newCurQuant,
  
      )
    
  )

public suspend fun UpdateItemCurrentQuantityMutation.execute(

  
    
      itemId: java.util.UUID,newCurQuant: Int,

  

  ): com.google.firebase.dataconnect.MutationResult<
    UpdateItemCurrentQuantityMutation.Data,
    UpdateItemCurrentQuantityMutation.Variables
  > =
  ref(
    
      itemId=itemId,newCurQuant=newCurQuant,
  
    
  ).execute()


