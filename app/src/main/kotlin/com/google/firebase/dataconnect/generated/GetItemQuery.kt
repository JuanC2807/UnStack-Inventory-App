
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


import kotlinx.coroutines.flow.filterNotNull as _flow_filterNotNull
import kotlinx.coroutines.flow.map as _flow_map


public interface GetItemQuery :
    com.google.firebase.dataconnect.generated.GeneratedQuery<
      ExampleConnector,
      GetItemQuery.Data,
      GetItemQuery.Variables
    >
{
  
    @kotlinx.serialization.Serializable
  public data class Variables(
  
    val itemId: @kotlinx.serialization.Serializable(with = com.google.firebase.dataconnect.serializers.UUIDSerializer::class) java.util.UUID
  ) {
    
    
  }
  

  
    @kotlinx.serialization.Serializable
  public data class Data(
  
    val item: Item?
  ) {
    
      
        @kotlinx.serialization.Serializable
  public data class Item(
  
    val id: @kotlinx.serialization.Serializable(with = com.google.firebase.dataconnect.serializers.UUIDSerializer::class) java.util.UUID,
    val name: String,
    val cost: Double,
    val curQuant: Int,
    val par: Int,
    val supplier: Supplier
  ) {
    
      
        @kotlinx.serialization.Serializable
  public data class Supplier(
  
    val id: @kotlinx.serialization.Serializable(with = com.google.firebase.dataconnect.serializers.UUIDSerializer::class) java.util.UUID,
    val name: String
  ) {
    
    
  }
      
    
    
  }
      
    
    
  }
  

  public companion object {
    public val operationName: String = "GetItem"

    public val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data> =
      kotlinx.serialization.serializer()

    public val variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables> =
      kotlinx.serialization.serializer()
  }
}

public fun GetItemQuery.ref(
  
    itemId: java.util.UUID,

  
  
): com.google.firebase.dataconnect.QueryRef<
    GetItemQuery.Data,
    GetItemQuery.Variables
  > =
  ref(
    
      GetItemQuery.Variables(
        itemId=itemId,
  
      )
    
  )

public suspend fun GetItemQuery.execute(

  
    
      itemId: java.util.UUID,
  fetchPolicy: com.google.firebase.dataconnect.QueryRef.FetchPolicy = com.google.firebase.dataconnect.QueryRef.FetchPolicy.PREFER_CACHE,
  

  ): com.google.firebase.dataconnect.QueryResult<
    GetItemQuery.Data,
    GetItemQuery.Variables
  > =
  ref(
    
      itemId=itemId,
  
    
  ).execute(fetchPolicy = fetchPolicy)


  public fun GetItemQuery.flow(
    
      itemId: java.util.UUID,

  
    
    ): kotlinx.coroutines.flow.Flow<GetItemQuery.Data> =
    ref(
        
          itemId=itemId,
  
        
      ).subscribe()
      .flow
      ._flow_map { querySubscriptionResult -> querySubscriptionResult.result.getOrNull() }
      ._flow_filterNotNull()
      ._flow_map { it.data }

