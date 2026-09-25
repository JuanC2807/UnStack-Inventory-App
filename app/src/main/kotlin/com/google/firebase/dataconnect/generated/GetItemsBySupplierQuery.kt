
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


public interface GetItemsBySupplierQuery :
    com.google.firebase.dataconnect.generated.GeneratedQuery<
      ExampleConnector,
      GetItemsBySupplierQuery.Data,
      GetItemsBySupplierQuery.Variables
    >
{
  
    @kotlinx.serialization.Serializable
  public data class Variables(
  
    val supplierId: @kotlinx.serialization.Serializable(with = com.google.firebase.dataconnect.serializers.UUIDSerializer::class) java.util.UUID
  ) {
    
    
  }
  

  
    @kotlinx.serialization.Serializable
  public data class Data(
  
    val items: List<ItemsItem>
  ) {
    
      
        @kotlinx.serialization.Serializable
  public data class ItemsItem(
  
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
    public val operationName: String = "GetItemsBySupplier"

    public val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data> =
      kotlinx.serialization.serializer()

    public val variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables> =
      kotlinx.serialization.serializer()
  }
}

public fun GetItemsBySupplierQuery.ref(
  
    supplierId: java.util.UUID,

  
  
): com.google.firebase.dataconnect.QueryRef<
    GetItemsBySupplierQuery.Data,
    GetItemsBySupplierQuery.Variables
  > =
  ref(
    
      GetItemsBySupplierQuery.Variables(
        supplierId=supplierId,
  
      )
    
  )

public suspend fun GetItemsBySupplierQuery.execute(

  
    
      supplierId: java.util.UUID,
  fetchPolicy: com.google.firebase.dataconnect.QueryRef.FetchPolicy = com.google.firebase.dataconnect.QueryRef.FetchPolicy.PREFER_CACHE,
  

  ): com.google.firebase.dataconnect.QueryResult<
    GetItemsBySupplierQuery.Data,
    GetItemsBySupplierQuery.Variables
  > =
  ref(
    
      supplierId=supplierId,
  
    
  ).execute(fetchPolicy = fetchPolicy)


  public fun GetItemsBySupplierQuery.flow(
    
      supplierId: java.util.UUID,

  
    
    ): kotlinx.coroutines.flow.Flow<GetItemsBySupplierQuery.Data> =
    ref(
        
          supplierId=supplierId,
  
        
      ).subscribe()
      .flow
      ._flow_map { querySubscriptionResult -> querySubscriptionResult.result.getOrNull() }
      ._flow_filterNotNull()
      ._flow_map { it.data }

