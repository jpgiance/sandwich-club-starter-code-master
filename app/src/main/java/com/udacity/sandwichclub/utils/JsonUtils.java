package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json){

        Sandwich sandwich= new Sandwich();

        try{
        JSONObject sandwichDetails = new JSONObject(json);
        JSONObject sandwichName = sandwichDetails.getJSONObject("name");
        JSONArray alsoKnownAs = sandwichName.getJSONArray("alsoKnownAs");
        JSONArray ingredients = sandwichName.getJSONArray("ingredients");


//            String mainName;
//            List<String> knownAs;
//            String placeOrigin;
//            String description;
//            String image;
//            List<String> ing;
//
//
//            mainName = sandwichName.getString("mainName");
//            knownAs = getList(alsoKnownAs);
//            placeOrigin = sandwichDetails.getString("placeOfOrigin");
//            description = sandwichDetails.getString("description");
//            image = sandwichDetails.getString("image");
//            ing = getList(ingredients);



            sandwich.setMainName(sandwichName.getString("mainName"));
            sandwich.setAlsoKnownAs(getList(alsoKnownAs));
            sandwich.setPlaceOfOrigin(sandwichDetails.getString("placeOfOrigin"));
            sandwich.setDescription(sandwichDetails.getString("description"));
            sandwich.setImage(sandwichDetails.getString("image"));
            sandwich.setIngredients(getList(ingredients));

        }catch(JSONException e){
            e.printStackTrace();
        }
//        Sandwich sandwich = new Sandwich(
//                mainName,
//                knownAs,
//                placeOrigin,
//                description,
//                image,
//                ing);

            return sandwich;

    }

    private static List<String> getList(JSONArray jArray){

        List<String> list = new ArrayList<>();

        for(int i =0; i<jArray.length(); i++){

            try {
                String value = jArray.getString(i);
                list.add(value);
            }catch (JSONException e) {
                e.printStackTrace();
            }


        }

        return list;
    }


}
