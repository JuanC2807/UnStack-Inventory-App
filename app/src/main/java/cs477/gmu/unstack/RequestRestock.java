package cs477.gmu.unstack;

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

public class RequestRestock extends AppCompatActivity {

    LinearLayout container;
    Button backHomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_request_restock);
        container = findViewById(R.id.lowStockItemsContainer);
        backHomeButton = findViewById(R.id.BackHomeButton);
        backHomeButton.setOnClickListener(v -> finish());
        loadData();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }


    private void loadData() {

        DBHelper.listItems(items -> {

            DBHelper.getAllRequests(requests -> {

                runOnUiThread(() -> {

                    container.removeAllViews();

                    int shown = 0;

                    for (DBHelper.Item item : items) {

                        if (!isLowStock(item)) continue;

                        boolean alreadyRequested = hasRequest(item.getId(), requests);

                        addCard(item, alreadyRequested);

                        shown++;
                    }

                    if (shown == 0) {
                        TextView empty = new TextView(this);
                        empty.setText("No low stock items 🎉");
                        empty.setTextSize(16);
                        container.addView(empty);
                    }
                });

                return null;
            });

            return null;
        });
    }


    private boolean isLowStock(DBHelper.Item item) {
        return item.getCurQuant() <= 0 || item.getCurQuant() < item.getPar();
    }

    private boolean hasRequest(String itemId, List<DBHelper.Request> requests) {
        for (DBHelper.Request r : requests) {
            if (r.getItemId().equals(itemId)) {
                return true;
            }
        }
        return false;
    }

    //Makes UI Cards to display on requesting screen
    private void addCard(DBHelper.Item item, boolean alreadyRequested) {

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

        TextView name = new TextView(this);
        name.setText(item.getName());
        name.setTypeface(null, Typeface.BOLD);
        name.setTextSize(18);

        TextView qty = new TextView(this);
        qty.setText("Current Quantity: " + item.getCurQuant());
        TextView par = new TextView(this);
        par.setText("Par Level: " + item.getPar());
        Button requestBtn = new Button(this);

        if (alreadyRequested) {
            requestBtn.setText("Requested");
            requestBtn.setEnabled(false);
        } else {
            requestBtn.setText("Request Restock");
            requestBtn.setOnClickListener(v -> {
                DBHelper.createRequest(
                        item.getId(),
                        item.getSupplierId(),
                        (success, message) -> {
                            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                            if (success) {
                                requestBtn.setText("Requested");
                                requestBtn.setEnabled(false);
                            }
                            return null;
                        }
                );
            });
        }
        card.addView(name);
        card.addView(qty);
        card.addView(par);
        card.addView(requestBtn);
        container.addView(card);
    }
}