package com.example.sparewise;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class BuyActivity extends AppCompatActivity {

    private ListView listViewParts;
    private FirebaseFirestore firestore;
    private List<Part> partList;
    private PartAdapter partAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        listViewParts = findViewById(R.id.listViewParts);
        firestore = FirebaseFirestore.getInstance();
        partList = new ArrayList<>();
        partAdapter = new PartAdapter(this, partList);
        listViewParts.setAdapter(partAdapter);

        retrievePartsFromFirestore();
    }

    private void retrievePartsFromFirestore() {
        CollectionReference partsRef = firestore.collection("parts");
        partsRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {
                    List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot document : documents) {
                        Part part = document.toObject(Part.class);
                        partList.add(part);
                    }
                    partAdapter.notifyDataSetChanged();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(BuyActivity.this, "Failed to retrieve parts", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
