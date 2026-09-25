package cs477.gmu.unstack;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UpdateStock extends AppCompatActivity {

    LinearLayout itemsListContainer;
    EditText itemNameUpdate;
    EditText currentQuantityUpdate;
    EditText newQuantityUpdate;
    EditText updateReason;
    Button submitStockUpdateButton;
    Button backHomeButton;

    DBHelper.Item selectedItem = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_stock);

        itemsListContainer = findViewById(R.id.itemsListContainer);

        itemNameUpdate = findViewById(R.id.itemNameUpdate);
        currentQuantityUpdate = findViewById(R.id.currentQuantityUpdate);
        newQuantityUpdate = findViewById(R.id.newQuantityUpdate);
        updateReason = findViewById(R.id.updateReason);

        submitStockUpdateButton = findViewById(R.id.SubmitStockUpdateButton);
        backHomeButton = findViewById(R.id.BackHomeButton);

        itemNameUpdate.setEnabled(false);
        currentQuantityUpdate.setEnabled(false);

        backHomeButton.setOnClickListener(v -> finish());

        submitStockUpdateButton.setOnClickListener(v -> updateSelectedItem());

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
            itemsListContainer.removeAllViews();

            if (items.isEmpty()) {
                TextView emptyText = new TextView(this);
                emptyText.setText("No inventory items found.");
                emptyText.setTextSize(16);
                itemsListContainer.addView(emptyText);
            } else {
                for (DBHelper.Item item : items) {
                    addSelectableItemCard(item);
                }
            }
            return null;
        });
    }

    private void addSelectableItemCard(DBHelper.Item item) {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(24, 24, 24, 24);
        card.setBackgroundColor(0xFFE6DDEB);
        card.setClickable(true);
        card.setFocusable(true);

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

        card.addView(nameText);
        card.addView(quantityText);
        card.addView(parText);

        card.setOnClickListener(v -> {
            selectedItem = item;
            itemNameUpdate.setText(item.getName());
            currentQuantityUpdate.setText(String.valueOf(item.getCurQuant()));
            newQuantityUpdate.setText("");

            Toast.makeText(this, item.getName() + " selected", Toast.LENGTH_SHORT).show();
        });
        itemsListContainer.addView(card);
    }

    private void updateSelectedItem() {
        if (selectedItem == null) {
            Toast.makeText(this, "Select an item first", Toast.LENGTH_SHORT).show();
            return;
        }

        String newQuantityText = newQuantityUpdate.getText().toString().trim();

        if (newQuantityText.isEmpty()) {
            Toast.makeText(this, "Enter a new quantity", Toast.LENGTH_SHORT).show();
            return;
        }

        int newQuantity;

        try {
            newQuantity = Integer.parseInt(newQuantityText);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "New quantity must be a number", Toast.LENGTH_SHORT).show();
            return;
        }

        DBHelper.updateItemQuantity(
                selectedItem.getId(),
                newQuantity,
                (success, message) -> {
                    if (success) {
                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                        selectedItem = null;
                        itemNameUpdate.setText("");
                        currentQuantityUpdate.setText("");
                        newQuantityUpdate.setText("");
                        updateReason.setText("");
                        loadItems();
                    } else {
                        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
                    }
                    return null;
                }
        );
    }
}