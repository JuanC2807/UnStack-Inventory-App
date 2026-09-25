package cs477.gmu.unstack

import com.google.firebase.dataconnect.generated.ExampleConnector
import com.google.firebase.dataconnect.generated.UserRole
import com.google.firebase.dataconnect.generated.execute
import com.google.firebase.dataconnect.generated.instance
import com.google.firebase.dataconnect.QueryRef
import com.google.firebase.dataconnect.generated.ListAllSuppliersQuery
import kotlinx.coroutines.*
import java.sql.Timestamp
import java.util.UUID

object DBHelper {

    data class Item(
        val id: String,
        val name: String,
        val supplierId: String,
        val cost: Double,
        val curQuant: Int,
        val par: Int
    )

    data class Supplier(
        val id: String,
        val name: String,
        val address: String
    )

    data class User(
        val uid: String,
        val firstName: String,
        val lastName: String,
        val email: String,
        val role: UserRole
    )

    data class Request(
        val id: String,
        val supplierId: String,
        val itemId: String,
        val dateRequested: com.google.firebase.Timestamp,
        val supplier: Supplier
    )

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val connector: ExampleConnector by lazy {
        ExampleConnector.instance
    }

    // ----------------------------
    // SEED DATABASE (RUN ONCE)
    // ----------------------------
    @JvmStatic
    fun seedOnce(onResult: (String) -> Unit) {
        scope.launch {
            try {

                // ----------------------------
                // CREATE SUPPLIERS (SAFE)
                // ----------------------------
                val s1 = connector.createSupplier.execute(
                    name = "Global Foods",
                    address = "123 Supply Way"
                ).data?.supplier_insert ?: throw Exception("Supplier 1 failed")

                val s2 = connector.createSupplier.execute(
                    name = "Fresh Produce Co",
                    address = "456 Garden Lane"
                ).data?.supplier_insert ?: throw Exception("Supplier 2 failed")

                val s3 = connector.createSupplier.execute(
                    name = "Prime Meats Inc",
                    address = "789 Butcher Road"
                ).data?.supplier_insert ?: throw Exception("Supplier 3 failed")

                val s4 = connector.createSupplier.execute(
                    name = "Ocean Seafood Supply",
                    address = "321 Harbor Drive"
                ).data?.supplier_insert ?: throw Exception("Supplier 4 failed")

                val s5 = connector.createSupplier.execute(
                    name = "Bakery Essentials",
                    address = "654 Flour Street"
                ).data?.supplier_insert ?: throw Exception("Supplier 5 failed")

                // ----------------------------
                // USERS (ignore results safely)
                // ----------------------------
                connector.addUser.execute(
                    firstName = "Ayden",
                    lastName = "Admin",
                    email = "ayden@example.com"
                )

                connector.addUserWithProvidedId.execute(
                    firstName = "John",
                    lastName = "Doe",
                    email = "john@example.com",
                    uid = "johnDoeID"
                )

                connector.addUserWithProvidedId.execute(
                    firstName = "Sarah",
                    lastName = "Smith",
                    email = "sarah@example.com",
                    uid = "sarahSmithID"
                )

                connector.addUserWithProvidedId.execute(
                    firstName = "Michael",
                    lastName = "Brown",
                    email = "michael@example.com",
                    uid = "michaelBrownID"
                )

                connector.addUserWithProvidedId.execute(
                    firstName = "Emily",
                    lastName = "Davis",
                    email = "emily@example.com",
                    uid = "emilyDavisID"
                )

                // ----------------------------
                // ITEMS (SAFE FK USAGE)
                // ----------------------------
                suspend fun addItem(
                    name: String,
                    supplierId: String,
                    cost: Double,
                    curQuant: Int,
                    par: Int
                ) {
                    connector.addItemToDb.execute(
                        name = name,
                        supplierId = UUID.fromString(supplierId),
                        cost = cost,
                        curQuant = curQuant,
                        par = par
                    ).data?.item_insert
                        ?: throw Exception("Item insert failed: $name")
                }

                addItem("Whole Milk", s1.id.toString(), 3.50, 20, 10)
                addItem("Organic Apples", s2.id.toString(), 1.20, 50, 30)
                addItem("Chicken Breast", s3.id.toString(), 6.75, 40, 25)
                addItem("Ground Beef", s3.id.toString(), 5.40, 35, 20)
                addItem("Salmon Fillet", s4.id.toString(), 9.99, 18, 12)
                addItem("Shrimp", s4.id.toString(), 8.25, 22, 15)
                addItem("All Purpose Flour", s5.id.toString(), 2.80, 60, 40)
                addItem("Granulated Sugar", s5.id.toString(), 2.10, 55, 35)
                addItem("Butter", s1.id.toString(), 4.60, 28, 18)
                addItem("Bananas", s2.id.toString(), 0.89, 75, 50)

                // ----------------------------
                // SUCCESS
                // ----------------------------
                withContext(Dispatchers.Main) {
                    onResult("Seed success")
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onResult("Seed failed: ${e.message}")
                }
            }
        }
    }

    // ----------------------------
    // LIST ITEMS
    // ----------------------------
    @JvmStatic
    fun listItems(onResult: (List<Item>) -> Unit) {
        scope.launch {
            try {

                val result = connector.listItemsBySupplier.execute()

                val items = result.data.items.map { item ->

                    Item(
                        id = item.id.toString(),
                        name = item.name,
                        supplierId = item.supplier.id.toString(),
                        cost = item.cost,
                        curQuant = item.curQuant,
                        par = item.par
                    )
                }

                withContext(Dispatchers.Main) {
                    onResult(items)
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onResult(emptyList())
                }
            }
        }
    }

    @JvmStatic
    fun deleteAllData() {
        scope.launch {
            try {
                connector.deleteAllData.execute()
            } catch (_: Exception) {
            }
        }
    }

    /**
     * Updates all values of an item besides its name and ID
     *
     * returns the ID as a String if successful
     */
    fun updateItem(
        id: String,
        supplierId: String,
        curQuant: Int,
        par: Int,
        cost: Double,
        onResult: (Result<String>) -> String
    ) {
        scope.launch {
            try {
                connector.updateItem.execute(
                    id = UUID.fromString(id),
                    supplierId = UUID.fromString(supplierId),
                    curQuant = curQuant,
                    par = par,
                    cost = cost
                )

                withContext(Dispatchers.Main) {
                    onResult(Result.success(id))
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onResult(Result.failure(e))
                }
            }
        }
    }

    /**
     * Updates the current quantity of the item passed in by ID
     * Returns the ID as a String if successful
     *
     */
    @JvmStatic
    fun updateItemQuantity(
        itemId: String,
        newCurQuant: Int,
        onResult: (Boolean, String) -> Unit
    ) {
        scope.launch {
            try {
                connector.updateItemCurrentQuantity.execute(
                    itemId = UUID.fromString(itemId),
                    newCurQuant = newCurQuant
                )

                withContext(Dispatchers.Main) {
                    onResult(true, "Stock updated successfully")
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onResult(false, "Update failed: ${e.message}")
                }
            }
        }
    }

    @JvmStatic
    fun createSupplier(
        name: String,
        address: String,
        onResult: (Supplier?, String) -> Unit
    ) {
        scope.launch {
            try {
                val result = connector.createSupplier.execute(
                    name = name,
                    address = address
                ).data

                val supplier = result.supplier_insert

                val querResult = connector.getSupplierById.execute(
                    supplier.id
                ).data

                val insertedSupplier = querResult.supplier

                if (insertedSupplier == null) {
                    onResult(null, "Supplier not found")
                    return@launch
                }

                val retSupplier = Supplier(
                    insertedSupplier.id.toString(),
                    insertedSupplier.name,
                    insertedSupplier.address
                )

                withContext(Dispatchers.Main) {
                    onResult(retSupplier, "Supplier created successfully")
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onResult(null, "Create supplier failed: ${e.message}")
                }
            }
        }
    }

    @JvmStatic
    fun addItemToDb(
        name: String,
        supplierId: String,
        cost: Double,
        curQuant: Int,
        par: Int,
        onResult: (Item?, String) -> Unit
    ) {
        scope.launch {
            try {

                val result = connector.addItemToDb.execute(
                    name = name,
                    supplierId = UUID.fromString(supplierId),
                    cost = cost,
                    curQuant = curQuant,
                    par = par
                ).data

                val inserted = result.item_insert

                val resultForGet = connector.getItem.execute(
                    inserted.id
                ) .data

                val insertedItem = resultForGet.item

                if (insertedItem == null) {
                    onResult(null, "Item not found")
                    return@launch
                }

                val item = Item(
                    insertedItem.id.toString(),
                    insertedItem.name,
                    insertedItem.supplier.id.toString(),
                    insertedItem.cost,
                    insertedItem.curQuant,
                    insertedItem.par
                )

                withContext(Dispatchers.Main) {
                    onResult(item, "Item created successfully")
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onResult(null, "Add item failed: ${e.message}")
                }
            }
        }
    }

    @JvmStatic
    fun addUser(
        firstName: String,
        lastName: String,
        email: String,
        onResult: (String?, String) -> Unit
    ) {
        scope.launch {
            try {
                val result = connector.addUser.execute(
                    firstName = firstName,
                    lastName = lastName,
                    email = email
                ).data

                val userId = result.appUser_upsert.uid

                withContext(Dispatchers.Main) {
                    onResult(userId, "User created successfully")
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onResult(null, "Add user failed: ${e.message}")
                }
            }
        }
    }

    @JvmStatic
    fun addUserWithProvidedId(
        uid: String,
        firstName: String,
        lastName: String,
        email: String,
        onResult: (String?, String) -> Unit
    ) {
        scope.launch {
            try {
                val result = connector.addUserWithProvidedId.execute(
                    uid = uid,
                    firstName = firstName,
                    lastName = lastName,
                    email = email
                ).data

                val userId = result.appUser_insert.uid

                withContext(Dispatchers.Main) {
                    onResult(userId, "User created successfully")
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onResult(null, "Add provided user failed: ${e.message}")
                }
            }
        }
    }


    @JvmStatic
    fun getUserByEmail(
        email: String,
        onResult: (User?, String) -> Unit
    ) {
        scope.launch {
            try {
                val result = connector.getUserByEmail.execute(
                    email = email,
                    fetchPolicy = QueryRef.FetchPolicy.SERVER_ONLY
                ).data

                val dbUser = result.appUsers.firstOrNull()

                if (dbUser == null) {
                    withContext(Dispatchers.Main) {
                        onResult(null, "User not found in database")
                    }
                    return@launch
                }

                val role = dbUser.role.value ?: UserRole.EMPLOYEE

                val user = User(
                    uid = dbUser.uid,
                    firstName = dbUser.firstName,
                    lastName = dbUser.lastName,
                    email = dbUser.email,
                    role = role
                )

                withContext(Dispatchers.Main) {
                    onResult(user, "User found")
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onResult(null, "Get user failed: ${e.message}")
                }
            }
        }
    }

    @JvmStatic
    fun addItemAndSupplier(
        itemName: String,
        itemCost: Double,
        itemCurQuant: Int,
        itemPar: Int,
        supplierName: String,
        supplierAddress: String,
        onResult: (Supplier?, Item?, String) -> Unit
    ) {
        scope.launch {
            try {

                // 1. Run combined mutation
                val result = connector.addItemAndSupplier.execute(
                    itemName = itemName,
                    itemCost = itemCost,
                    itemCurQuant = itemCurQuant,
                    itemPar = itemPar,
                    supplierName = supplierName,
                    supplierAddress = supplierAddress
                ).data

                val supplierKey = result.addSupplier
                val itemKey = result.addItem

                // 2. Fetch full supplier object
                val supplierQuery = connector.getSupplierById.execute(
                    supplierKey.id
                ).data

                val insertedSupplier = supplierQuery.supplier
                if (insertedSupplier == null) {
                    withContext(Dispatchers.Main) {
                        onResult(null, null, "Supplier not found after insert")
                    }
                    return@launch
                }

                val supplier = Supplier(
                    id = insertedSupplier.id.toString(),
                    name = insertedSupplier.name,
                    address = insertedSupplier.address
                )

                // 3. Fetch full item object
                val itemQuery = connector.getItem.execute(
                    itemKey.id
                ).data

                val insertedItem = itemQuery.item
                if (insertedItem == null) {
                    withContext(Dispatchers.Main) {
                        onResult(supplier, null, "Item not found after insert")
                    }
                    return@launch
                }

                val item = Item(
                    id = insertedItem.id.toString(),
                    name = insertedItem.name,
                    supplierId = insertedItem.supplier.id.toString(),
                    cost = insertedItem.cost,
                    curQuant = insertedItem.curQuant,
                    par = insertedItem.par
                )

                // 4. Return both
                withContext(Dispatchers.Main) {
                    onResult(supplier, item, "Success")
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onResult(null, null, "Add failed: ${e.message}")
                }
            }
        }
    }

    @JvmStatic
    fun getAllSuppliers(
        onResult: (List<Supplier>) -> Unit
    ) {
        scope.launch {
            try {

                val result = connector.listAllSuppliers.execute()

                val suppliers = result.data.suppliers.map { supplier ->

                    Supplier(
                        id = supplier.id.toString(),
                        name = supplier.name,
                        address = supplier.address
                    )
                }

                withContext(Dispatchers.Main) {
                    onResult(suppliers)
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onResult(emptyList())
                }
            }
        }
    }

    @JvmStatic
    fun getAllRequests(
        onResult: (List<Request>) -> Unit
    ) {
        scope.launch {
            try {

                val result = connector.listAllRequests.execute().data

                val requests = result?.requests?.mapNotNull { request ->

                    val item = request.item
                    val supplier = request.supplier

                    // guard against null nested objects
                    if (request == null || item == null || supplier == null) {
                        return@mapNotNull null
                    }

                    Request(
                        id = request.id.toString(),
                        supplierId = supplier.id.toString(),
                        itemId = item.id.toString(),
                        dateRequested = request.dateRequested,
                        supplier = Supplier(
                            supplier.id.toString(),
                            supplier.name,
                            supplier.address
                        )
                    )
                } ?: emptyList()

                withContext(Dispatchers.Main) {
                    onResult(requests)
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onResult(emptyList())
                }
            }
        }
    }

    @JvmStatic
    fun deleteRequestById(
        id: String,
        onResult: (Boolean, String) -> Unit
    ) {
        scope.launch {
            try {

                connector.deleteRequestById.execute(
                    id = UUID.fromString(id)
                )

                withContext(Dispatchers.Main) {
                    onResult(true, "Request deleted successfully")
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onResult(false, "Delete request failed: ${e.message}")
                }
            }
        }
    }

    @JvmStatic
    fun createRequest(
        itemId: String,
        supplierId: String,
        onResult: (Boolean, String) -> Unit
    ) {
        scope.launch {
            try {

                connector.addRequest.execute(
                    itemId = UUID.fromString(itemId),
                    supplierId = UUID.fromString(supplierId),
                    dateRequested = com.google.firebase.Timestamp.now()
                )

                withContext(Dispatchers.Main) {
                    onResult(true, "Request created")
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onResult(false, "Request failed: ${e.message}")
                }
            }
        }
    }

    @JvmStatic
    fun getSupplierById(
        supplierId: String,
        onResult: (Supplier?, String) -> Unit
    ) {
        scope.launch {
            try {

                val result = connector.getSupplierById.execute(
                    UUID.fromString(supplierId)
                ).data

                val supplier = result?.supplier

                if (supplier == null) {
                    withContext(Dispatchers.Main) {
                        onResult(null, "Not found")
                    }
                    return@launch
                }

                val retSupplier = Supplier(
                    id = supplier.id.toString(),
                    name = supplier.name,
                    address = supplier.address
                )

                withContext(Dispatchers.Main) {
                    onResult(retSupplier, "Success")
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onResult(null, "Error: ${e.message}")
                }
            }
        }
    }


    fun clear() {
        scope.cancel()
    }
}