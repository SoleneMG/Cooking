package com.example.cooking.domain;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cooking.R;
import com.example.cooking.model.Ingredient;


public class DisplayIngredientsAdapter extends ListAdapter<Ingredient, DisplayIngredientsAdapter.IngredientViewHolder> {

    public DisplayIngredientsAdapter(@NonNull DiffUtil.ItemCallback<Ingredient> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.ingredient_item, parent, false);
        return new IngredientViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        Ingredient ingredient = getItem(position);
        holder.item.setText(ingredient.name);

    }

    public static class IngredientViewHolder extends RecyclerView.ViewHolder {
        private final TextView item;

        public IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.ingredient_item);
        }
    }

    public static class IngredientDiff extends DiffUtil.ItemCallback<Ingredient> {

        @Override
        public boolean areItemsTheSame(@NonNull Ingredient oldItem, @NonNull Ingredient newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Ingredient oldItem, @NonNull Ingredient newItem) {
            return oldItem.name.equalsIgnoreCase(newItem.name);
        }
    }

}
