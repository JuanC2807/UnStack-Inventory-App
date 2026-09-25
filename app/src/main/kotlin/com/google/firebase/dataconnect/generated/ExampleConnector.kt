
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

import com.google.firebase.dataconnect.getInstance as _fdcGetInstance
import kotlin.time.Duration.Companion.milliseconds as _milliseconds

public interface ExampleConnector : com.google.firebase.dataconnect.generated.GeneratedConnector<ExampleConnector> {
  override val dataConnect: com.google.firebase.dataconnect.FirebaseDataConnect

  
    public val addItemAndSupplier: AddItemAndSupplierMutation
  
    public val addItemToDb: AddItemToDbMutation
  
    public val addRequest: AddRequestMutation
  
    public val addUser: AddUserMutation
  
    public val addUserWithProvidedId: AddUserWithProvidedIdMutation
  
    public val createSupplier: CreateSupplierMutation
  
    public val deleteAllData: DeleteAllDataMutation
  
    public val deleteRequestById: DeleteRequestByIdMutation
  
    public val getItem: GetItemQuery
  
    public val getItemsBySupplier: GetItemsBySupplierQuery
  
    public val getSupplierById: GetSupplierByIdQuery
  
    public val getUser: GetUserQuery
  
    public val getUserByEmail: GetUserByEmailQuery
  
    public val getUsersByRole: GetUsersByRoleQuery
  
    public val listAllRequests: ListAllRequestsQuery
  
    public val listAllSuppliers: ListAllSuppliersQuery
  
    public val listItemsBySupplier: ListItemsBySupplierQuery
  
    public val updateItem: UpdateItemMutation
  
    public val updateItemCurrentQuantity: UpdateItemCurrentQuantityMutation
  

  public companion object {
    @Suppress("MemberVisibilityCanBePrivate")
    public val config: com.google.firebase.dataconnect.ConnectorConfig = com.google.firebase.dataconnect.ConnectorConfig(
      connector = "example",
      location = "us-east4",
      serviceId = "unstack-32125-2-service",
    )

    public fun getInstance(
      dataConnect: com.google.firebase.dataconnect.FirebaseDataConnect
    ):ExampleConnector = synchronized(instances) {
      instances.getOrPut(dataConnect) {
        ExampleConnectorImpl(dataConnect)
      }
    }

    private val instances = java.util.WeakHashMap<com.google.firebase.dataconnect.FirebaseDataConnect, ExampleConnectorImpl>()

    
    public val defaultCacheSettings: com.google.firebase.dataconnect.CacheSettings =
      com.google.firebase.dataconnect.CacheSettings(
        
        
      )

    public val defaultDataConnectSettings: com.google.firebase.dataconnect.DataConnectSettings =
      com.google.firebase.dataconnect.DataConnectSettings(
        cacheSettings = defaultCacheSettings,
      )
    
  }
}

public val ExampleConnector.Companion.instance:ExampleConnector
  get() = getInstance(com.google.firebase.dataconnect.FirebaseDataConnect._fdcGetInstance(
    config, defaultDataConnectSettings
  ))

public fun ExampleConnector.Companion.getInstance(
  settings: com.google.firebase.dataconnect.DataConnectSettings = defaultDataConnectSettings
):ExampleConnector =
  getInstance(com.google.firebase.dataconnect.FirebaseDataConnect._fdcGetInstance(config, settings))

public fun ExampleConnector.Companion.getInstance(
  app: com.google.firebase.FirebaseApp,
  settings: com.google.firebase.dataconnect.DataConnectSettings = defaultDataConnectSettings
):ExampleConnector =
  getInstance(com.google.firebase.dataconnect.FirebaseDataConnect._fdcGetInstance(app, config, settings))

private class ExampleConnectorImpl(
  override val dataConnect: com.google.firebase.dataconnect.FirebaseDataConnect
) : ExampleConnector {
  
    override val addItemAndSupplier by lazy(LazyThreadSafetyMode.PUBLICATION) {
      AddItemAndSupplierMutationImpl(this)
    }
  
    override val addItemToDb by lazy(LazyThreadSafetyMode.PUBLICATION) {
      AddItemToDbMutationImpl(this)
    }
  
    override val addRequest by lazy(LazyThreadSafetyMode.PUBLICATION) {
      AddRequestMutationImpl(this)
    }
  
    override val addUser by lazy(LazyThreadSafetyMode.PUBLICATION) {
      AddUserMutationImpl(this)
    }
  
    override val addUserWithProvidedId by lazy(LazyThreadSafetyMode.PUBLICATION) {
      AddUserWithProvidedIdMutationImpl(this)
    }
  
    override val createSupplier by lazy(LazyThreadSafetyMode.PUBLICATION) {
      CreateSupplierMutationImpl(this)
    }
  
    override val deleteAllData by lazy(LazyThreadSafetyMode.PUBLICATION) {
      DeleteAllDataMutationImpl(this)
    }
  
    override val deleteRequestById by lazy(LazyThreadSafetyMode.PUBLICATION) {
      DeleteRequestByIdMutationImpl(this)
    }
  
    override val getItem by lazy(LazyThreadSafetyMode.PUBLICATION) {
      GetItemQueryImpl(this)
    }
  
    override val getItemsBySupplier by lazy(LazyThreadSafetyMode.PUBLICATION) {
      GetItemsBySupplierQueryImpl(this)
    }
  
    override val getSupplierById by lazy(LazyThreadSafetyMode.PUBLICATION) {
      GetSupplierByIdQueryImpl(this)
    }
  
    override val getUser by lazy(LazyThreadSafetyMode.PUBLICATION) {
      GetUserQueryImpl(this)
    }
  
    override val getUserByEmail by lazy(LazyThreadSafetyMode.PUBLICATION) {
      GetUserByEmailQueryImpl(this)
    }
  
    override val getUsersByRole by lazy(LazyThreadSafetyMode.PUBLICATION) {
      GetUsersByRoleQueryImpl(this)
    }
  
    override val listAllRequests by lazy(LazyThreadSafetyMode.PUBLICATION) {
      ListAllRequestsQueryImpl(this)
    }
  
    override val listAllSuppliers by lazy(LazyThreadSafetyMode.PUBLICATION) {
      ListAllSuppliersQueryImpl(this)
    }
  
    override val listItemsBySupplier by lazy(LazyThreadSafetyMode.PUBLICATION) {
      ListItemsBySupplierQueryImpl(this)
    }
  
    override val updateItem by lazy(LazyThreadSafetyMode.PUBLICATION) {
      UpdateItemMutationImpl(this)
    }
  
    override val updateItemCurrentQuantity by lazy(LazyThreadSafetyMode.PUBLICATION) {
      UpdateItemCurrentQuantityMutationImpl(this)
    }
  

  @com.google.firebase.dataconnect.ExperimentalFirebaseDataConnect
  override fun operations(): List<com.google.firebase.dataconnect.generated.GeneratedOperation<ExampleConnector, *, *>> =
    queries() + mutations()

  @com.google.firebase.dataconnect.ExperimentalFirebaseDataConnect
  override fun mutations(): List<com.google.firebase.dataconnect.generated.GeneratedMutation<ExampleConnector, *, *>> =
    listOf(
      addItemAndSupplier,
        addItemToDb,
        addRequest,
        addUser,
        addUserWithProvidedId,
        createSupplier,
        deleteAllData,
        deleteRequestById,
        updateItem,
        updateItemCurrentQuantity,
        
    )

  @com.google.firebase.dataconnect.ExperimentalFirebaseDataConnect
  override fun queries(): List<com.google.firebase.dataconnect.generated.GeneratedQuery<ExampleConnector, *, *>> =
    listOf(
      getItem,
        getItemsBySupplier,
        getSupplierById,
        getUser,
        getUserByEmail,
        getUsersByRole,
        listAllRequests,
        listAllSuppliers,
        listItemsBySupplier,
        
    )

  @com.google.firebase.dataconnect.ExperimentalFirebaseDataConnect
  override fun copy(dataConnect: com.google.firebase.dataconnect.FirebaseDataConnect) =
    ExampleConnectorImpl(dataConnect)

  override fun equals(other: Any?): Boolean =
    other is ExampleConnectorImpl &&
    other.dataConnect == dataConnect

  override fun hashCode(): Int =
    java.util.Objects.hash(
      "ExampleConnectorImpl",
      dataConnect,
    )

  override fun toString(): String =
    "ExampleConnectorImpl(dataConnect=$dataConnect)"
}



private open class ExampleConnectorGeneratedQueryImpl<Data, Variables>(
  override val connector: ExampleConnector,
  override val operationName: String,
  override val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data>,
  override val variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables>,
) : com.google.firebase.dataconnect.generated.GeneratedQuery<ExampleConnector, Data, Variables> {

  @com.google.firebase.dataconnect.ExperimentalFirebaseDataConnect
  override fun copy(
    connector: ExampleConnector,
    operationName: String,
    dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data>,
    variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables>,
  ) =
    ExampleConnectorGeneratedQueryImpl(
      connector, operationName, dataDeserializer, variablesSerializer
    )

  @com.google.firebase.dataconnect.ExperimentalFirebaseDataConnect
  override fun <NewVariables> withVariablesSerializer(
    variablesSerializer: kotlinx.serialization.SerializationStrategy<NewVariables>
  ) =
    ExampleConnectorGeneratedQueryImpl(
      connector, operationName, dataDeserializer, variablesSerializer
    )

  @com.google.firebase.dataconnect.ExperimentalFirebaseDataConnect
  override fun <NewData> withDataDeserializer(
    dataDeserializer: kotlinx.serialization.DeserializationStrategy<NewData>
  ) =
    ExampleConnectorGeneratedQueryImpl(
      connector, operationName, dataDeserializer, variablesSerializer
    )

  override fun equals(other: Any?): Boolean =
    other is ExampleConnectorGeneratedQueryImpl<*,*> &&
    other.connector == connector &&
    other.operationName == operationName &&
    other.dataDeserializer == dataDeserializer &&
    other.variablesSerializer == variablesSerializer

  override fun hashCode(): Int =
    java.util.Objects.hash(
      "ExampleConnectorGeneratedQueryImpl",
      connector, operationName, dataDeserializer, variablesSerializer
    )

  override fun toString(): String =
    "ExampleConnectorGeneratedQueryImpl(" +
    "operationName=$operationName, " +
    "dataDeserializer=$dataDeserializer, " +
    "variablesSerializer=$variablesSerializer, " +
    "connector=$connector)"
}

private open class ExampleConnectorGeneratedMutationImpl<Data, Variables>(
  override val connector: ExampleConnector,
  override val operationName: String,
  override val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data>,
  override val variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables>,
) : com.google.firebase.dataconnect.generated.GeneratedMutation<ExampleConnector, Data, Variables> {

  @com.google.firebase.dataconnect.ExperimentalFirebaseDataConnect
  override fun copy(
    connector: ExampleConnector,
    operationName: String,
    dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data>,
    variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables>,
  ) =
    ExampleConnectorGeneratedMutationImpl(
      connector, operationName, dataDeserializer, variablesSerializer
    )

  @com.google.firebase.dataconnect.ExperimentalFirebaseDataConnect
  override fun <NewVariables> withVariablesSerializer(
    variablesSerializer: kotlinx.serialization.SerializationStrategy<NewVariables>
  ) =
    ExampleConnectorGeneratedMutationImpl(
      connector, operationName, dataDeserializer, variablesSerializer
    )

  @com.google.firebase.dataconnect.ExperimentalFirebaseDataConnect
  override fun <NewData> withDataDeserializer(
    dataDeserializer: kotlinx.serialization.DeserializationStrategy<NewData>
  ) =
    ExampleConnectorGeneratedMutationImpl(
      connector, operationName, dataDeserializer, variablesSerializer
    )

  override fun equals(other: Any?): Boolean =
    other is ExampleConnectorGeneratedMutationImpl<*,*> &&
    other.connector == connector &&
    other.operationName == operationName &&
    other.dataDeserializer == dataDeserializer &&
    other.variablesSerializer == variablesSerializer

  override fun hashCode(): Int =
    java.util.Objects.hash(
      "ExampleConnectorGeneratedMutationImpl",
      connector, operationName, dataDeserializer, variablesSerializer
    )

  override fun toString(): String =
    "ExampleConnectorGeneratedMutationImpl(" +
    "operationName=$operationName, " +
    "dataDeserializer=$dataDeserializer, " +
    "variablesSerializer=$variablesSerializer, " +
    "connector=$connector)"
}



private class AddItemAndSupplierMutationImpl(
  connector: ExampleConnector
):
  AddItemAndSupplierMutation,
  ExampleConnectorGeneratedMutationImpl<
      AddItemAndSupplierMutation.Data,
      AddItemAndSupplierMutation.Variables
  >(
    connector,
    AddItemAndSupplierMutation.Companion.operationName,
    AddItemAndSupplierMutation.Companion.dataDeserializer,
    AddItemAndSupplierMutation.Companion.variablesSerializer,
  )


private class AddItemToDbMutationImpl(
  connector: ExampleConnector
):
  AddItemToDbMutation,
  ExampleConnectorGeneratedMutationImpl<
      AddItemToDbMutation.Data,
      AddItemToDbMutation.Variables
  >(
    connector,
    AddItemToDbMutation.Companion.operationName,
    AddItemToDbMutation.Companion.dataDeserializer,
    AddItemToDbMutation.Companion.variablesSerializer,
  )


private class AddRequestMutationImpl(
  connector: ExampleConnector
):
  AddRequestMutation,
  ExampleConnectorGeneratedMutationImpl<
      AddRequestMutation.Data,
      AddRequestMutation.Variables
  >(
    connector,
    AddRequestMutation.Companion.operationName,
    AddRequestMutation.Companion.dataDeserializer,
    AddRequestMutation.Companion.variablesSerializer,
  )


private class AddUserMutationImpl(
  connector: ExampleConnector
):
  AddUserMutation,
  ExampleConnectorGeneratedMutationImpl<
      AddUserMutation.Data,
      AddUserMutation.Variables
  >(
    connector,
    AddUserMutation.Companion.operationName,
    AddUserMutation.Companion.dataDeserializer,
    AddUserMutation.Companion.variablesSerializer,
  )


private class AddUserWithProvidedIdMutationImpl(
  connector: ExampleConnector
):
  AddUserWithProvidedIdMutation,
  ExampleConnectorGeneratedMutationImpl<
      AddUserWithProvidedIdMutation.Data,
      AddUserWithProvidedIdMutation.Variables
  >(
    connector,
    AddUserWithProvidedIdMutation.Companion.operationName,
    AddUserWithProvidedIdMutation.Companion.dataDeserializer,
    AddUserWithProvidedIdMutation.Companion.variablesSerializer,
  )


private class CreateSupplierMutationImpl(
  connector: ExampleConnector
):
  CreateSupplierMutation,
  ExampleConnectorGeneratedMutationImpl<
      CreateSupplierMutation.Data,
      CreateSupplierMutation.Variables
  >(
    connector,
    CreateSupplierMutation.Companion.operationName,
    CreateSupplierMutation.Companion.dataDeserializer,
    CreateSupplierMutation.Companion.variablesSerializer,
  )


private class DeleteAllDataMutationImpl(
  connector: ExampleConnector
):
  DeleteAllDataMutation,
  ExampleConnectorGeneratedMutationImpl<
      DeleteAllDataMutation.Data,
      Unit
  >(
    connector,
    DeleteAllDataMutation.Companion.operationName,
    DeleteAllDataMutation.Companion.dataDeserializer,
    DeleteAllDataMutation.Companion.variablesSerializer,
  )


private class DeleteRequestByIdMutationImpl(
  connector: ExampleConnector
):
  DeleteRequestByIdMutation,
  ExampleConnectorGeneratedMutationImpl<
      DeleteRequestByIdMutation.Data,
      DeleteRequestByIdMutation.Variables
  >(
    connector,
    DeleteRequestByIdMutation.Companion.operationName,
    DeleteRequestByIdMutation.Companion.dataDeserializer,
    DeleteRequestByIdMutation.Companion.variablesSerializer,
  )


private class GetItemQueryImpl(
  connector: ExampleConnector
):
  GetItemQuery,
  ExampleConnectorGeneratedQueryImpl<
      GetItemQuery.Data,
      GetItemQuery.Variables
  >(
    connector,
    GetItemQuery.Companion.operationName,
    GetItemQuery.Companion.dataDeserializer,
    GetItemQuery.Companion.variablesSerializer,
  )


private class GetItemsBySupplierQueryImpl(
  connector: ExampleConnector
):
  GetItemsBySupplierQuery,
  ExampleConnectorGeneratedQueryImpl<
      GetItemsBySupplierQuery.Data,
      GetItemsBySupplierQuery.Variables
  >(
    connector,
    GetItemsBySupplierQuery.Companion.operationName,
    GetItemsBySupplierQuery.Companion.dataDeserializer,
    GetItemsBySupplierQuery.Companion.variablesSerializer,
  )


private class GetSupplierByIdQueryImpl(
  connector: ExampleConnector
):
  GetSupplierByIdQuery,
  ExampleConnectorGeneratedQueryImpl<
      GetSupplierByIdQuery.Data,
      GetSupplierByIdQuery.Variables
  >(
    connector,
    GetSupplierByIdQuery.Companion.operationName,
    GetSupplierByIdQuery.Companion.dataDeserializer,
    GetSupplierByIdQuery.Companion.variablesSerializer,
  )


private class GetUserQueryImpl(
  connector: ExampleConnector
):
  GetUserQuery,
  ExampleConnectorGeneratedQueryImpl<
      GetUserQuery.Data,
      GetUserQuery.Variables
  >(
    connector,
    GetUserQuery.Companion.operationName,
    GetUserQuery.Companion.dataDeserializer,
    GetUserQuery.Companion.variablesSerializer,
  )


private class GetUserByEmailQueryImpl(
  connector: ExampleConnector
):
  GetUserByEmailQuery,
  ExampleConnectorGeneratedQueryImpl<
      GetUserByEmailQuery.Data,
      GetUserByEmailQuery.Variables
  >(
    connector,
    GetUserByEmailQuery.Companion.operationName,
    GetUserByEmailQuery.Companion.dataDeserializer,
    GetUserByEmailQuery.Companion.variablesSerializer,
  )


private class GetUsersByRoleQueryImpl(
  connector: ExampleConnector
):
  GetUsersByRoleQuery,
  ExampleConnectorGeneratedQueryImpl<
      GetUsersByRoleQuery.Data,
      GetUsersByRoleQuery.Variables
  >(
    connector,
    GetUsersByRoleQuery.Companion.operationName,
    GetUsersByRoleQuery.Companion.dataDeserializer,
    GetUsersByRoleQuery.Companion.variablesSerializer,
  )


private class ListAllRequestsQueryImpl(
  connector: ExampleConnector
):
  ListAllRequestsQuery,
  ExampleConnectorGeneratedQueryImpl<
      ListAllRequestsQuery.Data,
      Unit
  >(
    connector,
    ListAllRequestsQuery.Companion.operationName,
    ListAllRequestsQuery.Companion.dataDeserializer,
    ListAllRequestsQuery.Companion.variablesSerializer,
  )


private class ListAllSuppliersQueryImpl(
  connector: ExampleConnector
):
  ListAllSuppliersQuery,
  ExampleConnectorGeneratedQueryImpl<
      ListAllSuppliersQuery.Data,
      Unit
  >(
    connector,
    ListAllSuppliersQuery.Companion.operationName,
    ListAllSuppliersQuery.Companion.dataDeserializer,
    ListAllSuppliersQuery.Companion.variablesSerializer,
  )


private class ListItemsBySupplierQueryImpl(
  connector: ExampleConnector
):
  ListItemsBySupplierQuery,
  ExampleConnectorGeneratedQueryImpl<
      ListItemsBySupplierQuery.Data,
      Unit
  >(
    connector,
    ListItemsBySupplierQuery.Companion.operationName,
    ListItemsBySupplierQuery.Companion.dataDeserializer,
    ListItemsBySupplierQuery.Companion.variablesSerializer,
  )


private class UpdateItemMutationImpl(
  connector: ExampleConnector
):
  UpdateItemMutation,
  ExampleConnectorGeneratedMutationImpl<
      UpdateItemMutation.Data,
      UpdateItemMutation.Variables
  >(
    connector,
    UpdateItemMutation.Companion.operationName,
    UpdateItemMutation.Companion.dataDeserializer,
    UpdateItemMutation.Companion.variablesSerializer,
  )


private class UpdateItemCurrentQuantityMutationImpl(
  connector: ExampleConnector
):
  UpdateItemCurrentQuantityMutation,
  ExampleConnectorGeneratedMutationImpl<
      UpdateItemCurrentQuantityMutation.Data,
      UpdateItemCurrentQuantityMutation.Variables
  >(
    connector,
    UpdateItemCurrentQuantityMutation.Companion.operationName,
    UpdateItemCurrentQuantityMutation.Companion.dataDeserializer,
    UpdateItemCurrentQuantityMutation.Companion.variablesSerializer,
  )


