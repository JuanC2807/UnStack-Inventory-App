package cs477.gmu.unstack;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.List;
import java.util.Locale;
import com.google.firebase.Timestamp;
import java.text.SimpleDateFormat;

public class Restock extends AppCompatActivity {

    LinearLayout restockRequestsContainer;
    Button backHomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_restock);

        restockRequestsContainer = findViewById(R.id.restockRequestsContainer);
        backHomeButton = findViewById(R.id.BackHomeButton);
        backHomeButton.setOnClickListener(v -> finish());
        loadRestockRequests();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadRestockRequests();
    }

    //loading the data from the database
    private void loadRestockRequests() {

        DBHelper.getAllRequests(requests -> {

            restockRequestsContainer.removeAllViews();
            if (requests.isEmpty()) {
                TextView emptyText = new TextView(this);
                emptyText.setText("No restock requests yet.");
                emptyText.setTextSize(16);
                restockRequestsContainer.addView(emptyText);
                return null;
            }

            for (DBHelper.Request request : requests) {
                addRequestCard(request);
            }

            return null;
        });
    }

    //Make UI cards upon request for screen to display
    private void addRequestCard(DBHelper.Request request) {

        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(24, 24, 24, 24);
        card.setBackgroundColor(0xFFE6DDEB);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 0, 0, 24);
        card.setLayoutParams(params);

        TextView itemText = new TextView(this);
        itemText.setText("Item ID: " + request.getItemId());
        itemText.setTypeface(null, Typeface.BOLD);
        itemText.setTextSize(16);

        TextView supplierText = new TextView(this);
        supplierText.setText("Supplier: " + request.getSupplier().getName());

        TextView dateText = new TextView(this);
        dateText.setText("Requested at: " + formatTimestamp(request.getDateRequested()));

        //Will take users to map screen
        Button mapButton = new Button(this);
        mapButton.setText("Find Supplier");

        mapButton.setOnClickListener(v -> {
            Intent intent = new Intent(Restock.this, SupplierMap.class);
            intent.putExtra("itemId", request.getItemId());
            intent.putExtra("supplierId", request.getSupplierId());
            intent.putExtra("itemName", itemText.getText().toString());
            startActivity(intent);
        });

        //When we finish ordering items we want to mark requests as done so it removes them from list
        Button deleteButton = new Button(this);
        deleteButton.setText("Mark as Processed");

        deleteButton.setOnClickListener(v -> {

            DBHelper.deleteRequestById(request.getId(), (success, message) -> {

                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

                if (success) {
                    // refresh UI after deletion
                    loadRestockRequests();
                }

                return null;
            });
        });

        card.addView(itemText);
        card.addView(supplierText);
        card.addView(dateText);
        card.addView(mapButton);
        card.addView(deleteButton);

        restockRequestsContainer.addView(card);

        DBHelper.listItems(items -> {
            for (DBHelper.Item item : items) {
                if (item.getId().equals(request.getItemId())) {
                    itemText.setText(item.getName());
                    break;
                }
            }
            return null;
        });
    }
    private String formatTimestamp(Timestamp timestamp) {
        if (timestamp == null) {
            return "Error with time";
        }

        SimpleDateFormat formatter = new SimpleDateFormat(
                "MM/dd/yyyy hh:mm a",
                Locale.getDefault()
        );
        return formatter.format(timestamp.toDate());
    }
}