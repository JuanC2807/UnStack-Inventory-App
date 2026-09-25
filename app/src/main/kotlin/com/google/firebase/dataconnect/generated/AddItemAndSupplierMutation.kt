
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



public interface AddItemAndSupplierMutation :
    com.google.firebase.dataconnect.generated.GeneratedMutation<
      ExampleConnector,
      AddItemAndSupplierMutation.Data,
      AddItemAndSupplierMutation.Variables
    >
{
  
    @kotlinx.serialization.Serializable
  public data class Variables(
  
    val itemName: String,
    val itemCost: Double,
    val itemCurQuant: Int,
    val itemPar: Int,
    val supplierName: String,
    val supplierAddress: String
  ) {
    
    
  }
  

  
    @kotlinx.serialization.Serializable
  public data class Data(
  
    val addSupplier: SupplierKey,
    val addItem: ItemKey
  ) {
    
    
  }
  

  public companion object {
    public val operationName: String = "AddItemAndSupplier"

    public val dataDeserializer: kotlinx.serialization.DeserializationStrategy<Data> =
      kotlinx.serialization.serializer()

    public val variablesSerializer: kotlinx.serialization.SerializationStrategy<Variables> =
      kotlinx.serialization.serializer()
  }
}

public fun AddItemAndSupplierMutation.ref(
  
    itemName: String,itemCost: Double,itemCurQuant: Int,itemPar: Int,supplierName: String,supplierAddress: String,

  
  
): com.google.firebase.dataconnect.MutationRef<
    AddItemAndSupplierMutation.Data,
    AddItemAndSupplierMutation.Variables
  > =
  ref(
    
      AddItemAndSupplierMutation.Variables(
        itemName=itemName,itemCost=itemCost,itemCurQuant=itemCurQuant,itemPar=itemPar,supplierName=supplierName,supplierAddress=supplierAddress,
  
      )
    
  )

public suspend fun AddItemAndSupplierMutation.execute(

  
    
      itemName: String,itemCost: Double,itemCurQuant: Int,itemPar: Int,supplierName: String,supplierAddress: String,

  

  ): com.google.firebase.dataconnect.MutationResult<
    AddItemAndSupplierMutation.Data,
    AddItemAndSupplierMutation.Variables
  > =
  ref(
    
      itemName=itemName,itemCost=itemCost,itemCurQuant=itemCurQuant,itemPar=itemPar,supplierName=supplierName,supplierAddress=supplierAddress,
  
    
  ).execute()


