package com.example.cooking.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.cooking.R;
import com.example.cooking.domain.DisplayIngredientsAdapter;

public class DisplayIngredientsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_ingredients);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
       DisplayIngredientsAdapter adapter = new DisplayIngredientsAdapter(new DisplayIngredientsAdapter.IngredientDiff());
       recyclerView.setAdapter(adapter);
       recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}