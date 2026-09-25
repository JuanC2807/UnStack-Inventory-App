
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



public interface UpdateItemMutation :
    com.google.firebase.dataconnect.generated.GeneratedMutation<
      ExampleConnector,
      UpdateItemMutation.Data,
      UpdateItemMutation.Variables
    >
{
  
    @kotlinx.serialization.Serializable
  public data class Variables(
  
    val id: @kotlinx.serialization.Serializable(with = com.google.firebase.dataconnect.serializers.UUIDSerializer::class) java.util.UUID,
    val supplierId: @kotlinx.serialization.Serializable(with = com.google.firebase.dataconnect.serializers.UUIDSerializer::class) java.util.UUID,
    val curQuant: Int,
    val par: Int,
    val cost: Double
  ) {
    
    
  }
  

  
    @kotlinx.serialization.Serializable
  public data class Data(
  
    val item_update: ItemKey?
  ) {
    
    
  }
  

  public companion object {
    public val operationName: String = "UpdateItem"

    public val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data> =
      kotlinx.serialization.serializer()

    public val variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables> =
      kotlinx.serialization.serializer()
  }
}

public fun UpdateItemMutation.ref(
  
    id: java.util.UUID,supplierId: java.util.UUID,curQuant: Int,par: Int,cost: Double,

  
  
): com.google.firebase.dataconnect.MutationRef<
    UpdateItemMutation.Data,
    UpdateItemMutation.Variables
  > =
  ref(
    
      UpdateItemMutation.Variables(
        id=id,supplierId=supplierId,curQuant=curQuant,par=par,cost=cost,
  
      )
    
  )

public suspend fun UpdateItemMutation.execute(

  
    
      id: java.util.UUID,supplierId: java.util.UUID,curQuant: Int,par: Int,cost: Double,

  

  ): com.google.firebase.dataconnect.MutationResult<
    UpdateItemMutation.Data,
    UpdateItemMutation.Variables
  > =
  ref(
    
      id=id,supplierId=supplierId,curQuant=curQuant,par=par,cost=cost,
  
    
  ).execute()


