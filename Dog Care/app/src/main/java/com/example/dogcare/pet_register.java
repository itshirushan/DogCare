package com.example.dogcare;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.dogcare.databinding.ActivityPetRegisterBinding; // Ensure your binding is correctly imported

public class pet_register extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView uploadImageButton;
    private Button selectImageButton;
    private Button signupButton;
    private EditText petNameInput, ageInput, colorInput, sexInput, breedInput;
    private Uri imageUri;

    private DatabaseHelper databaseHelper;
    private ActivityPetRegisterBinding binding; // Declare the binding

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize the binding
        binding = ActivityPetRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        uploadImageButton = binding.uploadImageButton;
        selectImageButton = binding.selectImageButton;
        signupButton = binding.signupButton;
        petNameInput = binding.petNameInput;
        ageInput = binding.ageInput;
        colorInput = binding.colorInput;
        sexInput = binding.sexInput;
        breedInput = binding.breedInput;

        databaseHelper = new DatabaseHelper(this);

        selectImageButton.setOnClickListener(v -> openFileChooser());
        signupButton.setOnClickListener(v -> savePetData());

        // Add the additional button listeners
        binding.footerProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(pet_register.this, UserProfileActivity.class);
                startActivity(intent);
            }
        });

        binding.footerWelcomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(pet_register.this, articleTypeActivity.class);
                startActivity(intent);
            }
        });

        binding.footerFoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(pet_register.this, menu.class);
                startActivity(intent);
            }
        });

        binding.footerCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(pet_register.this, AddedItemsActivity.class);
                startActivity(intent);
            }
        });
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
        String sex = sexInput.getText().toString().trim().toLowerCase();
        String breed = breedInput.getText().toString().trim();
        String imageUriString = imageUri != null ? imageUri.toString() : "";

        if (petName.isEmpty() || ageStr.isEmpty() || color.isEmpty() || sex.isEmpty() || breed.isEmpty()) {
            Toast.makeText(this, "All fields except image are required", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!sex.equals("male") && !sex.equals("female")) {
            Toast.makeText(this, "Sex must be 'male' or 'female'", Toast.LENGTH_SHORT).show();
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid age", Toast.LENGTH_SHORT).show();
            return;
        }

        if (age >= 30) {
            Toast.makeText(this, "Age must be below 30", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean isInserted = databaseHelper.addPet(petName, age, color, sex, breed, imageUriString);

        if (isInserted) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Registered successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
        }
    }
}
