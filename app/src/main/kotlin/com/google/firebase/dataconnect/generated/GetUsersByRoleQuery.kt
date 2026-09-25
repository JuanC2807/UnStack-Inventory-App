
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


public interface GetUsersByRoleQuery :
    com.google.firebase.dataconnect.generated.GeneratedQuery<
      ExampleConnector,
      GetUsersByRoleQuery.Data,
      GetUsersByRoleQuery.Variables
    >
{
  
    @kotlinx.serialization.Serializable
  public data class Variables(
  
    val role: UserRole
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
    public val operationName: String = "GetUsersByRole"

    public val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data> =
      kotlinx.serialization.serializer()

    public val variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables> =
      kotlinx.serialization.serializer()
  }
}

public fun GetUsersByRoleQuery.ref(
  
    role: UserRole,

  
  
): com.google.firebase.dataconnect.QueryRef<
    GetUsersByRoleQuery.Data,
    GetUsersByRoleQuery.Variables
  > =
  ref(
    
      GetUsersByRoleQuery.Variables(
        role=role,
  
      )
    
  )

public suspend fun GetUsersByRoleQuery.execute(

  
    
      role: UserRole,
  fetchPolicy: com.google.firebase.dataconnect.QueryRef.FetchPolicy = com.google.firebase.dataconnect.QueryRef.FetchPolicy.PREFER_CACHE,
  

  ): com.google.firebase.dataconnect.QueryResult<
    GetUsersByRoleQuery.Data,
    GetUsersByRoleQuery.Variables
  > =
  ref(
    
      role=role,
  
    
  ).execute(fetchPolicy = fetchPolicy)


  public fun GetUsersByRoleQuery.flow(
    
      role: UserRole,

  
    
    ): kotlinx.coroutines.flow.Flow<GetUsersByRoleQuery.Data> =
    ref(
        
          role=role,
  
        
      ).subscribe()
      .flow
      ._flow_map { querySubscriptionResult -> querySubscriptionResult.result.getOrNull() }
      ._flow_filterNotNull()
      ._flow_map { it.data }

