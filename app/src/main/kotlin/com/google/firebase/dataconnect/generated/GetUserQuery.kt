
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


public interface GetUserQuery :
    com.google.firebase.dataconnect.generated.GeneratedQuery<
      ExampleConnector,
      GetUserQuery.Data,
      GetUserQuery.Variables
    >
{
  
    @kotlinx.serialization.Serializable
  public data class Variables(
  
    val uid: String
  ) {
    
    
  }
  

  
    @kotlinx.serialization.Serializable
  public data class Data(
  
    val appUser: AppUser?
  ) {
    
      
        @kotlinx.serialization.Serializable
  public data class AppUser(
  
    val uid: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val role: @kotlinx.serialization.Serializable(with = UserRole.EnumValueSerializer::class) EnumValue<UserRole>
  ) {
    
    
  }
      
    
    
  }
  

  public companion object {
    public val operationName: String = "GetUser"

    public val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data> =
      kotlinx.serialization.serializer()

    public val variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables> =
      kotlinx.serialization.serializer()
  }
}

public fun GetUserQuery.ref(
  
    uid: String,

  
  
): com.google.firebase.dataconnect.QueryRef<
    GetUserQuery.Data,
    GetUserQuery.Variables
  > =
  ref(
    
      GetUserQuery.Variables(
        uid=uid,
  
      )
    
  )

public suspend fun GetUserQuery.execute(

  
    
      uid: String,
  fetchPolicy: com.google.firebase.dataconnect.QueryRef.FetchPolicy = com.google.firebase.dataconnect.QueryRef.FetchPolicy.PREFER_CACHE,
  

  ): com.google.firebase.dataconnect.QueryResult<
    GetUserQuery.Data,
    GetUserQuery.Variables
  > =
  ref(
    
      uid=uid,
  
    
  ).execute(fetchPolicy = fetchPolicy)


  public fun GetUserQuery.flow(
    
      uid: String,

  
    
    ): kotlinx.coroutines.flow.Flow<GetUserQuery.Data> =
    ref(
        
          uid=uid,
  
        
      ).subscribe()
      .flow
      ._flow_map { querySubscriptionResult -> querySubscriptionResult.result.getOrNull() }
      ._flow_filterNotNull()
      ._flow_map { it.data }

