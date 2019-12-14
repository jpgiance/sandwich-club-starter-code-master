package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.drawable.Drawable;

import java.util.List;
import java.util.ArrayList;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private TextView alsoKnownAsText;
    private TextView originText;
    private TextView descriptionText;
    private TextView ingredientText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        alsoKnownAsText = (TextView) findViewById(R.id.also_known_tv);
        originText = (TextView) findViewById(R.id.origin_tv);
        descriptionText = (TextView) findViewById(R.id.description_tv);
        ingredientText = (TextView) findViewById(R.id.ingredients_tv);


        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = null;


        sandwich = JsonUtils.parseSandwichJson(json);

        if (sandwich == null) {
            // Sandwich data unavailable
            Log.d("DetailActivity", "sandwich object is null");
            closeOnError();
            return;
        }


        populateUI(sandwich);

        /*
         * Picasso utility will load the corresponding image file contained
         * in the JSON String.
         *
         *If the link to the image file does not work, a placeholder image will be loaded.
         *
         */

        Picasso.with(this)
                .load(sandwich.getImage())
                .placeholder(R.mipmap.sandwich_icon_foreground)
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());

        populateUI(sandwich);
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }




    /*
     * this method populateUI() takes a Sandwich object as argument and populate textViews in
     * activity_detail.xml view.
     *
     */

    private void populateUI(Sandwich sandwich) {

        List<String> knownAs = sandwich.getAlsoKnownAs();
        List<String> ingredients = sandwich.getIngredients();

        originText.setText(sandwich.getPlaceOfOrigin());
        descriptionText.setText(sandwich.getDescription());

        for (int i = 0; i < knownAs.size(); i++) {
            if (i != 0) {
                alsoKnownAsText.append("; ");
            }
            alsoKnownAsText.append(knownAs.get(i));

        }
        for (int i = 0; i < ingredients.size(); i++) {
            if (i != 0) {
                ingredientText.append("; ");
            }
            ingredientText.append(ingredients.get(i));

        }

    }
}
