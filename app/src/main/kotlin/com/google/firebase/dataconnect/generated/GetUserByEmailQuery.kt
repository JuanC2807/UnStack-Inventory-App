
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


public interface GetUserByEmailQuery :
    com.google.firebase.dataconnect.generated.GeneratedQuery<
      ExampleConnector,
      GetUserByEmailQuery.Data,
      GetUserByEmailQuery.Variables
    >
{
  
    @kotlinx.serialization.Serializable
  public data class Variables(
  
    val email: String
  ) {
    
    
  }
  

  
    @kotlinx.serialization.Serializable
  public data class Data(
  
    val appUsers: List<AppUsersItem>
  ) {
    
      
        @kotlinx.serialization.Serializable
  public data class AppUsersItem(
  
    val uid: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val role: @kotlinx.serialization.Serializable(with = UserRole.EnumValueSerializer::class) EnumValue<UserRole>
  ) {
    
    
  }
      
    
    
  }
  

  public companion object {
    public val operationName: String = "GetUserByEmail"

    public val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data> =
      kotlinx.serialization.serializer()

    public val variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables> =
      kotlinx.serialization.serializer()
  }
}

public fun GetUserByEmailQuery.ref(
  
    email: String,

  
  
): com.google.firebase.dataconnect.QueryRef<
    GetUserByEmailQuery.Data,
    GetUserByEmailQuery.Variables
  > =
  ref(
    
      GetUserByEmailQuery.Variables(
        email=email,
  
      )
    
  )

public suspend fun GetUserByEmailQuery.execute(

  
    
      email: String,
  fetchPolicy: com.google.firebase.dataconnect.QueryRef.FetchPolicy = com.google.firebase.dataconnect.QueryRef.FetchPolicy.PREFER_CACHE,
  

  ): com.google.firebase.dataconnect.QueryResult<
    GetUserByEmailQuery.Data,
    GetUserByEmailQuery.Variables
  > =
  ref(
    
      email=email,
  
    
  ).execute(fetchPolicy = fetchPolicy)


  public fun GetUserByEmailQuery.flow(
    
      email: String,

  
    
    ): kotlinx.coroutines.flow.Flow<GetUserByEmailQuery.Data> =
    ref(
        
          email=email,
  
        
      ).subscribe()
      .flow
      ._flow_map { querySubscriptionResult -> querySubscriptionResult.result.getOrNull() }
      ._flow_filterNotNull()
      ._flow_map { it.data }

