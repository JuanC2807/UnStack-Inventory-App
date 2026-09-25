
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



public interface AddItemToDbMutation :
    com.google.firebase.dataconnect.generated.GeneratedMutation<
      ExampleConnector,
      AddItemToDbMutation.Data,
      AddItemToDbMutation.Variables
    >
{
  
    @kotlinx.serialization.Serializable
  public data class Variables(
  
    val name: String,
    val supplierId: @kotlinx.serialization.Serializable(with = com.google.firebase.dataconnect.serializers.UUIDSerializer::class) java.util.UUID,
    val cost: Double,
    val curQuant: Int,
    val par: Int
  ) {
    
    
  }
  

  
    @kotlinx.serialization.Serializable
  public data class Data(
  
    val item_insert: ItemKey
  ) {
    
    
  }
  

  public companion object {
    public val operationName: String = "AddItemToDB"

    public val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data> =
      kotlinx.serialization.serializer()

    public val variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables> =
      kotlinx.serialization.serializer()
  }
}

public fun AddItemToDbMutation.ref(
  
    name: String,supplierId: java.util.UUID,cost: Double,curQuant: Int,par: Int,

  
  
): com.google.firebase.dataconnect.MutationRef<
    AddItemToDbMutation.Data,
    AddItemToDbMutation.Variables
  > =
  ref(
    
      AddItemToDbMutation.Variables(
        name=name,supplierId=supplierId,cost=cost,curQuant=curQuant,par=par,
  
      )
    
  )

public suspend fun AddItemToDbMutation.execute(

  
    
      name: String,supplierId: java.util.UUID,cost: Double,curQuant: Int,par: Int,

  

  ): com.google.firebase.dataconnect.MutationResult<
    AddItemToDbMutation.Data,
    AddItemToDbMutation.Variables
  > =
  ref(
    
      name=name,supplierId=supplierId,cost=cost,curQuant=curQuant,par=par,
  
    
  ).execute()


