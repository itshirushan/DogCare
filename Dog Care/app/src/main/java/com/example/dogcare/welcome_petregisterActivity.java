package com.example.dogcare;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class welcome_petregisterActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView uploadImageButton;
    private Button selectImageButton;
    private Button welcomeSignupButton;
    private EditText petNameInput, ageInput, colorInput, sexInput, breedInput;
    private Uri imageUri;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_register);

        uploadImageButton = findViewById(R.id.uploadImageButton);
        selectImageButton = findViewById(R.id.selectImageButton);
        welcomeSignupButton = findViewById(R.id.signupButton);
        petNameInput = findViewById(R.id.petNameInput);
        ageInput = findViewById(R.id.ageInput);
        colorInput = findViewById(R.id.colorInput);
        sexInput = findViewById(R.id.sexInput);
        breedInput = findViewById(R.id.breedInput);

        databaseHelper = new DatabaseHelper(this);

        selectImageButton.setOnClickListener(v -> openFileChooser());
        welcomeSignupButton.setOnClickListener(v -> savePetData());
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            uploadImageButton.setImageURI(imageUri);
        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }

    private void savePetData() {
        String petName = petNameInput.getText().toString().trim();
        String ageStr = ageInput.getText().toString().trim();
        String color = colorInput.getText().toString().trim();
        String sex = sexInput.getText().toString().trim();
        String breed = breedInput.getText().toString().trim();
        String imageUriString = imageUri != null ? imageUri.toString() : "";

        // Check if any required field is empty
        if (petName.isEmpty() || ageStr.isEmpty() || color.isEmpty() || sex.isEmpty() || breed.isEmpty()) {
            Toast.makeText(this, "All fields except image are required", Toast.LENGTH_SHORT).show();
            return;
        }

        // Convert age to integer and handle parsing errors
        int age;
        try {
            age = Integer.parseInt(ageStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid age", Toast.LENGTH_SHORT).show();
            return;
        }

        // Insert pet data into database
        boolean isInserted = databaseHelper.addPet(petName, age, color, sex, breed, imageUriString);

        if (isInserted) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Registered successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
        }
    }
}
