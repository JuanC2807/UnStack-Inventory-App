package cs477.gmu.unstack;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.FirebaseApp;


public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            goToHome();
            return;
        }

        Button loginButton = findViewById(R.id.LoginButton);
        loginButton.setOnClickListener(v -> showLoginDialog());
        Button registerButton = findViewById(R.id.RegisterButton);
        //Register Dialog
        registerButton.setOnClickListener(view -> showRegisterDialog());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void showLoginDialog() {
        View loginView = LayoutInflater.from(this).inflate(R.layout.login, null);

        EditText emailEditText = loginView.findViewById(R.id.email_login);
        EditText passwordEditText = loginView.findViewById(R.id.password_login);
        AlertDialog dialog = new AlertDialog.Builder(this).setTitle("Sign In")
                .setView(loginView)
                .setPositiveButton("Sign In", null)
                .setNegativeButton("Cancel", null)
                .create();

        dialog.setOnShowListener(d -> {
            Button signInButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
            signInButton.setOnClickListener(v -> {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter email and password", Toast.LENGTH_SHORT).show();
                    return;
                }

                signInUser(email, password, dialog);
            });
        });

        dialog.show();
    }


    private void showRegisterDialog() {
        View registerView = LayoutInflater.from(this).inflate(R.layout.register, null);

        EditText emailEditText = registerView.findViewById(R.id.email_register);
        EditText passwordEditText = registerView.findViewById(R.id.password_register);
        EditText nameEditText = registerView.findViewById(R.id.name_register);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Register")
                .setView(registerView)
                .setPositiveButton("Register", null)
                .setNegativeButton("Cancel", null)
                .create();

        dialog.setOnShowListener(d -> {
            Button registerButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);

            registerButton.setOnClickListener(v -> {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String name = nameEditText.getText().toString().trim();

                if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter name, email, and password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(MainActivity.this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
                    return;
                }

                registerUser(name, email, password, dialog);
            });
        });
        dialog.show();
    }

    private void registerUser(String name, String email, String password, AlertDialog dialog) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser firebaseUser = mAuth.getCurrentUser();

                        if (firebaseUser == null) {
                            Toast.makeText(this, "Registration failed: user not found", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        String uid = firebaseUser.getUid();
                        String firstName;
                        String lastName;

                        String[] nameParts = name.trim().split("\\s+", 2);

                        firstName = nameParts[0];

                        if (nameParts.length > 1) {
                            lastName = nameParts[1];
                        } else {
                            lastName = "";
                        }

                        DBHelper.addUserWithProvidedId(
                                uid, firstName, lastName, email, (userId, message) -> {
                                    if (userId != null) {
                                        Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                        goToHome();
                                    } else {
                                        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
                                    }
                                    return null;
                                }
                        );

                    } else {
                        Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void signInUser(String email, String password, AlertDialog dialog) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        goToHome();
                    } else {
                        Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void goToHome() {
        Intent intent = new Intent(MainActivity.this, Home.class);
        startActivity(intent);
        finish();
    }
}