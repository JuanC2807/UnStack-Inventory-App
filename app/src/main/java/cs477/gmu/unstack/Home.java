package cs477.gmu.unstack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.dataconnect.generated.UserRole;

public class Home extends AppCompatActivity {

    Button logoutButton;

    LinearLayout editStockButton;
    LinearLayout updateStockButton;
    LinearLayout viewStockButton;
    LinearLayout restockButton;
    LinearLayout requestRestockButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        logoutButton = findViewById(R.id.LogoutAppButton);

        editStockButton = findViewById(R.id.editStockButton);
        updateStockButton = findViewById(R.id.updateStockButton);
        viewStockButton = findViewById(R.id.ViewStockButton);
        restockButton = findViewById(R.id.RestockButton);
        requestRestockButton = findViewById(R.id.RequestRestockButton);

        //Testing this
        editStockButton.setVisibility(View.GONE);
        restockButton.setVisibility(View.GONE);
        requestRestockButton.setVisibility(View.GONE);
        checkUserRole();
        //Testing this

        editStockButton.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, EditSystem.class);
            startActivity(intent);
        });

        updateStockButton.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, UpdateStock.class);
            startActivity(intent);
        });

        viewStockButton.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, ViewStock.class);
            startActivity(intent);
        });

        logoutButton.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(Home.this, MainActivity.class));
            finish();
        });

        restockButton.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, Restock.class);
            startActivity(intent);
        });

        requestRestockButton.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, RequestRestock.class);
            startActivity(intent);
        });




        /*
        TESTING FUNCTIONS CAN DELETE FROM HERE--------------------------------------------------------------
         */
//        DBHelper.deleteAllData();
//
//        DBHelper.seedOnce(result -> {
//
//            // after seeding → fetch items
//            DBHelper.listItems(items -> {
//                runOnUiThread(() -> {
//                    for (DBHelper.Item item : items) {
//                        System.out.println(item.getName() + " -- " + item.getCost());
//                    }
//                });
//                return null;
//            });
//
//            return null;
//        });
//
//        DBHelper.addItemAndSupplier(
//                "TestingItemTestingItem",
//                12.99,
//                12,
//                10,
//                "Testing's Grocery",
//                "222 John Street Fairfax",
//                (newSupplier,newItem, message)->{
//                    System.out.println("Message: " + message);
//                    System.out.println("Supplier ID: " + newSupplier.getId());
//                    System.out.println("Item ID: " + newItem.getName());
//
//                    return null;
//                }
//                );
//
//        DBHelper.listItems(items -> {
//            for (DBHelper.Item item : items) {
//                System.out.println(item.getName());
//            }
//            return null;
//        });

        /*
         * TO HERE
         * -------------------------------------------------------------------------------------------
         */

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    private void checkUserRole() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser == null || firebaseUser.getEmail() == null) {
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show();
            return;
        }

        String email = firebaseUser.getEmail();
        DBHelper.getUserByEmail(email, (user, message) -> {
            if (user != null && user.getRole() == UserRole.MANAGER) {
                //Manager can see these
                editStockButton.setVisibility(View.VISIBLE);
                restockButton.setVisibility(View.VISIBLE);

                //Manager does not need to see the request button employees will use
                requestRestockButton.setVisibility(View.GONE);
            } else {
                //Employees can see these
                editStockButton.setVisibility(View.GONE);
                restockButton.setVisibility(View.GONE);
                //Display employee buttons
                requestRestockButton.setVisibility(View.VISIBLE);
            }

            return null;
        });
    }
}