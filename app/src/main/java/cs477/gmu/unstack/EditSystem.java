package cs477.gmu.unstack;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class EditSystem extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_system);

        Button backHomeButton = findViewById(R.id.BackHomeButton);
        backHomeButton.setOnClickListener(v -> {
            finish();
        });

        setupAddItemButton();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void setupAddItemButton() {
        EditText itemNameEdit = findViewById(R.id.ItemNameEdit);
        EditText startingQuantityEdit = findViewById(R.id.StartingQuantityEdit);
        EditText parLevelEdit = findViewById(R.id.ParLevelEdit);
        EditText costEdit = findViewById(R.id.CostEdit);

        Spinner supplierSpinner = findViewById(R.id.SupplierSpinner);

        EditText supplierNameEdit = findViewById(R.id.SupplierNameEdit);
        EditText supplierAddressEdit = findViewById(R.id.SupplierAddressEdit);

        Button addItemButton = findViewById(R.id.AddItemButton);
        Button clearFormButton = findViewById(R.id.ClearFormButton);

        ArrayList<DBHelper.Supplier> supplierList = new ArrayList<>();
        ArrayList<String> supplierNames = new ArrayList<>();

        supplierNames.add("Select Existing Supplier");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                supplierNames
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        supplierSpinner.setAdapter(adapter);

        //This helps load suppliers from Data Base
        DBHelper.getAllSuppliers(suppliers -> {
            runOnUiThread(() -> {
                supplierList.clear();
                supplierNames.clear();

                supplierNames.add("Select Existing Supplier");

                for (DBHelper.Supplier supplier : suppliers) {
                    supplierList.add(supplier);
                    supplierNames.add(supplier.getName());
                }

                adapter.notifyDataSetChanged();
            });

            return null;
        });

        addItemButton.setOnClickListener(v -> {
            String itemName = itemNameEdit.getText().toString().trim();
            String startingQuantityStr = startingQuantityEdit.getText().toString().trim();
            String parLevelStr = parLevelEdit.getText().toString().trim();
            String costStr = costEdit.getText().toString().trim();

            String selectedSupplier = supplierSpinner.getSelectedItem().toString();

            String supplierName = supplierNameEdit.getText().toString().trim();
            String supplierAddress = supplierAddressEdit.getText().toString().trim();

            if (
                    itemName.isEmpty() ||
                            startingQuantityStr.isEmpty() ||
                            parLevelStr.isEmpty() ||
                            costStr.isEmpty()
            ) {
                Toast.makeText(this, "Please fill out item fields", Toast.LENGTH_SHORT).show();
                return;
            }

            int startingQuantity = Integer.parseInt(startingQuantityStr);
            int parLevel = Integer.parseInt(parLevelStr);
            double cost = Double.parseDouble(costStr);

            //Handles if an existing supplier was selected when editing system
            if (!selectedSupplier.equals("Select Existing Supplier")) {

                int selectedPosition = supplierSpinner.getSelectedItemPosition();

                // subtract 1 because index 0 is placeholder text
                DBHelper.Supplier selectedSupplierObj = supplierList.get(selectedPosition - 1);
                String supplierId = selectedSupplierObj.getId();

                DBHelper.addItemToDb(
                        itemName,
                        supplierId,
                        cost,
                        startingQuantity,
                        parLevel,
                        (item, message) -> {

                            runOnUiThread(() -> {
                                Toast.makeText(this, message, Toast.LENGTH_LONG).show();

                                if (item != null) {
                                    System.out.println("Item Created: " + item.getName());
                                    System.out.println("Item ID: " + item.getId());
                                }
                            });

                            return null;
                        }
                );
            }

            //Creating new supplier + item
            else {
                if (supplierName.isEmpty() || supplierAddress.isEmpty()) {
                    Toast.makeText(this, "Enter supplier info or choose existing supplier", Toast.LENGTH_SHORT).show();
                    return;
                }

                DBHelper.addItemAndSupplier(
                        itemName,
                        cost,
                        startingQuantity,
                        parLevel,
                        supplierName,
                        supplierAddress,
                        (supplier, item, message) -> {

                            runOnUiThread(() -> {
                                Toast.makeText(this, message, Toast.LENGTH_LONG).show();

                                if (item != null && supplier != null) {
                                    System.out.println("Item Created: " + item.getName());
                                    System.out.println("Item ID: " + item.getId());
                                    System.out.println("Supplier Created: " + supplier.getName());
                                    System.out.println("Supplier ID: " + supplier.getId());
                                }
                            });

                            return null;
                        }
                );
            }

            itemNameEdit.setText("");
            startingQuantityEdit.setText("");
            parLevelEdit.setText("");
            costEdit.setText("");
            supplierNameEdit.setText("");
            supplierAddressEdit.setText("");
            supplierSpinner.setSelection(0);
            Toast.makeText(this, "Item Added", Toast.LENGTH_SHORT).show();
        });

        clearFormButton.setOnClickListener(v -> {
            itemNameEdit.setText("");
            startingQuantityEdit.setText("");
            parLevelEdit.setText("");
            costEdit.setText("");
            supplierNameEdit.setText("");
            supplierAddressEdit.setText("");
            supplierSpinner.setSelection(0);

            Toast.makeText(this, "Form cleared", Toast.LENGTH_SHORT).show();
        });
    }

}