package cs477.gmu.unstack;

import androidx.fragment.app.FragmentActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.graphics.Typeface;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.maps.model.Marker;

import cs477.gmu.unstack.databinding.ActivitySupplierMapBinding;

public class SupplierMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivitySupplierMapBinding binding;

    private String itemName;
    private String supplierId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySupplierMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Button backToRestockButton = findViewById(R.id.BackToRestockButton);

        backToRestockButton.setOnClickListener(v -> {
            finish();
        });

        itemName = getIntent().getStringExtra("itemName");
        supplierId = getIntent().getStringExtra("supplierId");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        } else {
            Toast.makeText(this, "Map failed to load", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        setupCustomInfoWindow();

        if (supplierId == null || supplierId.isEmpty()) {
            Toast.makeText(this, "Supplier ID missing", Toast.LENGTH_SHORT).show();
            return;
        }
        loadSupplier();
    }

    private void loadSupplier() {
        DBHelper.getSupplierById(supplierId, (supplier, message) -> {
            if (supplier == null) {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
                return null;
            }

            showSupplierOnMap(
                    supplier.getName(),
                    supplier.getAddress()
            );
            return null;
        });
    }

    //default info window kept getting cut off, so made a slightly bigger one
    private void setupCustomInfoWindow() {
        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                LinearLayout layout = new LinearLayout(SupplierMap.this);
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.setPadding(20, 12, 20, 12);

                TextView title = new TextView(SupplierMap.this);
                title.setText(marker.getTitle());
                title.setTypeface(null, Typeface.BOLD);
                title.setTextSize(16);

                TextView snippet = new TextView(SupplierMap.this);
                snippet.setText(marker.getSnippet());
                snippet.setTextSize(14);
                snippet.setSingleLine(false);
                snippet.setMaxWidth(600);

                layout.addView(title);
                layout.addView(snippet);
                return layout;
            }
        });
    }

    private void showSupplierOnMap(String supplierName, String supplierAddress) {
        new Thread(() -> {
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());

                List<Address> results = geocoder.getFromLocationName(supplierAddress, 1);

                if (results == null || results.isEmpty()) {
                    runOnUiThread(() -> {
                        Toast.makeText(
                                this,
                                "Could not find address: " + supplierAddress,
                                Toast.LENGTH_LONG
                        ).show();
                    });
                    return;
                }

                Address address = results.get(0);
                LatLng supplierLocation = new LatLng(
                        address.getLatitude(),
                        address.getLongitude()
                );

                runOnUiThread(() -> {
                    mMap.clear();
                    String displayItemName = itemName;

                    if (displayItemName == null || displayItemName.isEmpty()) {
                        displayItemName = "Unknown item";
                    }

                    Marker marker = mMap.addMarker(new MarkerOptions().position(supplierLocation).title(supplierName).snippet("Item: " + displayItemName + "\nAddress: " + supplierAddress));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(supplierLocation, 14f));

                    if (marker != null) {
                        marker.showInfoWindow();
                    }
                });

            } catch (IOException e) {
                runOnUiThread(() -> {
                    Toast.makeText(
                            this,
                            "Map address lookup failed: " + e.getMessage(),
                            Toast.LENGTH_LONG
                    ).show();
                });
            }
        }).start();
    }
}