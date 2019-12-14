package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonUtils {


    /*
     * This method parseSandwichJson() takes a String object with JSON format as argument
     * and parses it by initializing internal variables in a Sandwich object. This Sandwich object
     * is returned.
     *
     */

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = new Sandwich();
        try {
            JSONObject sandwichDetails = new JSONObject(json);
            JSONObject sandwichName = sandwichDetails.getJSONObject("name");


            List<String> knownAsList = new ArrayList<>();
            List<String> ingredientsList = new ArrayList<>();

            JSONArray alsoKnowAs = sandwichName.getJSONArray("alsoKnownAs");
            for (int i = 0; i < alsoKnowAs.length(); i++) {
                knownAsList.add(alsoKnowAs.getString(i));
            }

            JSONArray ingredients = sandwichDetails.getJSONArray("ingredients");
            for (int i = 0; i < ingredients.length(); i++) {
                ingredientsList.add(ingredients.getString(i));
            }

            /*
             * sandwich initialization
             */

            sandwich.setMainName(sandwichName.getString("mainName"));
            sandwich.setAlsoKnownAs(knownAsList);
            sandwich.setPlaceOfOrigin(sandwichDetails.getString("placeOfOrigin"));
            sandwich.setDescription(sandwichDetails.getString("description"));
            sandwich.setImage(sandwichDetails.getString("image"));
            sandwich.setIngredients(ingredientsList);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sandwich;

    }


}
