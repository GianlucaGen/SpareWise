package com.example.sparewise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListActivity extends AppCompatActivity {

    private EditText editTextPartName;
    private EditText editTextPartDescription;
    private Spinner spinnerCarMake;
    private Spinner spinnerYear;
    private EditText editTextPrice;
    private EditText editTextMobileNumber;
    private Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        editTextPartName = findViewById(R.id.editTextPartName);
        editTextPartDescription = findViewById(R.id.editTextPartDescription);
        spinnerCarMake = findViewById(R.id.spinnerCarMake);
        spinnerYear = findViewById(R.id.spinnerYear);
        editTextPrice = findViewById(R.id.editTextPrice);
        editTextMobileNumber = findViewById(R.id.editTextMobileNumber);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        // Initialize and populate the Car Make spinner
        ArrayAdapter<String> carMakeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{"Nissan", "BMW", "Hyundai", "Fiat"});
        carMakeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCarMake.setAdapter(carMakeAdapter);

        // Initialize and populate the Year spinner
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, generateYearList(1950, 2023));
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerYear.setAdapter(yearAdapter);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String partName = editTextPartName.getText().toString().trim();
                String partDescription = editTextPartDescription.getText().toString().trim();
                String carMake = spinnerCarMake.getSelectedItem().toString();
                String year = spinnerYear.getSelectedItem().toString();
                String price = editTextPrice.getText().toString().trim();
                String mobileNumber = editTextMobileNumber.getText().toString().trim();

                // Validate input restrictions
                if (partName.length() <= 2) {
                    editTextPartName.setError("Part Name must be larger than 3 characters");
                    editTextPartName.requestFocus();
                    return;
                }

                if (partDescription.length() <= 5) {
                    editTextPartDescription.setError("Part Description is too short");
                    editTextPartDescription.requestFocus();
                    return;
                }

                if (price.isEmpty() || !price.matches("[1-9]\\d{0,4}")) {
                    editTextPrice.setError("Invalid Price (1-99999)");
                    editTextPrice.requestFocus();
                    return;
                }

                if (mobileNumber.isEmpty() || !mobileNumber.matches("\\d{8}")) {
                    editTextMobileNumber.setError("Invalid Mobile Number");
                    editTextMobileNumber.requestFocus();
                    return;
                }

                // Create a new part object
                Map<String, Object> part = new HashMap<>();
                part.put("partName", partName);
                part.put("partDescription", partDescription);
                part.put("carMake", carMake);
                part.put("year", year);
                part.put("price", price);
                part.put("mobileNumber", mobileNumber);

                // Save the part object to Cloud Firestore
                FirebaseFirestore firestore = FirebaseFirestore.getInstance();
                firestore.collection("parts")
                        .add(part)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(ListActivity.this, "Part listed successfully", Toast.LENGTH_SHORT).show();
                                // Redirect to the BuyActivity after successful listing
                                Intent intent = new Intent(ListActivity.this, BuyActivity.class);
                                startActivity(intent);
                                finish(); // Optional: Finish the current activity to prevent going back to it with the back button
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ListActivity.this, "Failed to list part", Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });
    }

    private List<String> generateYearList(int startYear, int endYear) {
        List<String> yearList = new ArrayList<>();
        for (int year = startYear; year <= endYear; year++) {
            yearList.add(String.valueOf(year));
        }
        return yearList;
    }
}

