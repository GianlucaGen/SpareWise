package com.example.sparewise;

import com.example.sparewise.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;



import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
public class HomeActivity extends AppCompatActivity {

    private Button buttonBuyPart;
    private Button buttonListPart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        buttonBuyPart = findViewById(R.id.buttonBuyPart);
        buttonListPart = findViewById(R.id.buttonListPart);

        buttonBuyPart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Buy Part button click
                Intent intent = new Intent(HomeActivity.this, BuyActivity.class);
                startActivity(intent);
            }
        });

        buttonListPart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle List Part button click
                Intent intent = new Intent(HomeActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            // Handle Logout button click
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
