
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



public interface CreateSupplierMutation :
    com.google.firebase.dataconnect.generated.GeneratedMutation<
      ExampleConnector,
      CreateSupplierMutation.Data,
      CreateSupplierMutation.Variables
    >
{
  
    @kotlinx.serialization.Serializable
  public data class Variables(
  
    val name: String,
    val address: String
  ) {
    
    
  }
  

  
    @kotlinx.serialization.Serializable
  public data class Data(
  
    val supplier_insert: SupplierKey
  ) {
    
    
  }
  

  public companion object {
    public val operationName: String = "CreateSupplier"

    public val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data> =
      kotlinx.serialization.serializer()

    public val variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables> =
      kotlinx.serialization.serializer()
  }
}

public fun CreateSupplierMutation.ref(
  
    name: String,address: String,

  
  
): com.google.firebase.dataconnect.MutationRef<
    CreateSupplierMutation.Data,
    CreateSupplierMutation.Variables
  > =
  ref(
    
      CreateSupplierMutation.Variables(
        name=name,address=address,
  
      )
    
  )

public suspend fun CreateSupplierMutation.execute(

  
    
      name: String,address: String,

  

  ): com.google.firebase.dataconnect.MutationResult<
    CreateSupplierMutation.Data,
    CreateSupplierMutation.Variables
  > =
  ref(
    
      name=name,address=address,
  
    
  ).execute()


