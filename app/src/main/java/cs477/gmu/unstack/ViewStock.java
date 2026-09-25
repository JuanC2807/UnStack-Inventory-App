package cs477.gmu.unstack;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ViewStock extends AppCompatActivity {

    LinearLayout viewStockItemsContainer;
    Button refreshStockButton;
    Button backHomeButton;
    TextView lastUpdatedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_stock);

        viewStockItemsContainer = findViewById(R.id.viewStockItemsContainer);
        refreshStockButton = findViewById(R.id.RefreshStockButton);
        lastUpdatedText = findViewById(R.id.LastUpdatedText);
        backHomeButton = findViewById(R.id.BackHomeButton);

        backHomeButton.setOnClickListener(v -> finish());

        refreshStockButton.setOnClickListener(v -> {
            loadItems();
        });

        loadItems();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadItems();
    }

    private void loadItems() {
        DBHelper.listItems(items -> {
            viewStockItemsContainer.removeAllViews();

            if (items.isEmpty()) {
                TextView emptyText = new TextView(this);
                emptyText.setText("No inventory items found.");
                emptyText.setTextSize(16);
                viewStockItemsContainer.addView(emptyText);
            } else {
                for (DBHelper.Item item : items) {
                    addItemCard(item);
                }
            }

            String currentTime = new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());
            lastUpdatedText.setText("Last updated: " + currentTime);

            return null;
        });
    }

    private void addItemCard(DBHelper.Item item) {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(24, 24, 24, 24);
        card.setBackgroundColor(0xFFE6DDEB);

        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        cardParams.setMargins(0, 0, 0, 24);
        card.setLayoutParams(cardParams);

        TextView nameText = new TextView(this);
        nameText.setText(item.getName());
        nameText.setTextSize(18);
        nameText.setTypeface(null, Typeface.BOLD);

        TextView quantityText = new TextView(this);
        quantityText.setText("Current Quantity: " + item.getCurQuant());

        TextView parText = new TextView(this);
        parText.setText("Par Level: " + item.getPar());

        TextView costText = new TextView(this);
        costText.setText(String.format(Locale.getDefault(), "Cost: $%.2f", item.getCost()));

        TextView statusText = new TextView(this);
        statusText.setTypeface(null, Typeface.BOLD);

        if (item.getCurQuant() <= 0) {
            statusText.setText("Status: Out of Stock");
        } else if (item.getCurQuant() < item.getPar()) {
            statusText.setText("Status: Low Stock");
        } else {
            statusText.setText("Status: In Stock");
        }

        card.addView(nameText);
        card.addView(quantityText);
        card.addView(parText);
        card.addView(costText);
        card.addView(statusText);

        viewStockItemsContainer.addView(card);
    }
}