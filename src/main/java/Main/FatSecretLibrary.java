package Main;

import platformfatsecret.model.*;
import platformfatsecret.services.FatsecretService;
import platformfatsecret.services.Response;

import java.util.List;

public class FatSecretLibrary {
    static String key = "c8f6dbd68d1e4e4293b33a47bd4d21df";
    static String secret = "9ca35ea3c449451cabc9732f24086c8d";
    static FatsecretService service = new FatsecretService(key, secret);

    public static void main(String[] args) {
        gettingRecipes("mie ayam");
    }

    static void gettingFoods(String query){
        Response<CompactFood> response = service.searchFoods(query);

        List<CompactFood> results = response.getResults();

        for (CompactFood compactFood:
                results) {
            Long id = compactFood.getId();
            Food food = service.getFood(id);
            System.out.println("Food");
            System.out.println("------------------------------------------");
            System.out.printf("Id          : %d\n",id);
            System.out.printf("Name        : %s\n",food.getName());
            System.out.printf("Type        : %s\n",food.getType());
            System.out.printf("Description : %s\n",food.getDescription());
            System.out.printf("URL         : %s\n",food.getUrl());
            System.out.printf("Brandname   : %s\n",food.getBrandName());
            System.out.println("Servings   : ");
            for (int i = 0; i < food.getServings().size(); i++) {
                Serving serving = food.getServings().get(i);
                System.out.println("Serving "+(i+1));
                System.out.println("----------------------");
                System.out.println("             -Serving Id              : "+serving.getServingId());
                System.out.println("             -Measurement Description : "+serving.getMeasurementDescription());
                System.out.println("             -Metric Serving Amount   : "+serving.getMetricServingAmount());
                System.out.println("             -Metric Serving Unit     : "+serving.getMetricServingUnit());
                System.out.println("             -Number of Units         : "+serving.getNumberOfUnits());
                System.out.println("             -Iron                    : "+serving.getIron());
                System.out.println("             -Calcium                 : "+serving.getCalcium());
                System.out.println("             -Calories                : "+serving.getCalories());
                System.out.println("             -Fat                     : "+serving.getFat());
                System.out.println("             -Carbohydrate            : "+serving.getCarbohydrate());
                System.out.println("             -Cholesterol             : "+serving.getCholesterol());
                System.out.println("             -Fiber                   : "+serving.getFiber());
                System.out.println("             -Monosaturated Fat       : "+serving.getMonounsaturatedFat());
                System.out.println("             -Polyunsaturated Fat     : "+serving.getPolyunsaturatedFat());
                System.out.println("             -Potassium               : "+serving.getPotassium());
                System.out.println("             -Protein                 : "+serving.getProtein());
                System.out.println("             -Saturated Fat           : "+serving.getSaturatedFat());
                System.out.println("             -Serving Description     : "+serving.getServingDescription());
                System.out.println("             -Serving Url             : "+serving.getServingUrl());
                System.out.println("             -Sodium                  : "+serving.getSodium());
                System.out.println("             -Sugar                   : "+serving.getSugar());
                System.out.println("             -Trans Fat               : "+serving.getTransFat());
                System.out.println("             -Vitamin A               : "+serving.getVitaminA());
                System.out.println("             -Vitamin C               : "+serving.getVitaminC());
                System.out.println();
            }
            System.out.println("\n");
        }
    }

    static void gettingRecipes(String query){
        Response<CompactRecipe> response = service.searchRecipes(query);
        List<CompactRecipe> recipeList = response.getResults();
        for (CompactRecipe compactRecipe:
             recipeList) {
            Long id = compactRecipe.getId();
            Recipe recipe = service.getRecipe(id);
            System.out.println("Recipe");
            System.out.println("------------------------------------------");
            System.out.printf("Id          : %d\n",id);
            System.out.printf("Name        : %s\n",recipe.getName() == null ? "" : recipe.getName());
            System.out.printf("Description : %s\n",recipe.getDescription());
            System.out.printf("URL         : %s\n",recipe.getUrl());
            System.out.println("Images     :");
            for (int i = 0; i < compactRecipe.getImages().size(); i++) {
                System.out.println("           -"+i+compactRecipe.getImages().get(i));
            }
            System.out.println("Categories : ");
            for (int i = 0; i < recipe.getCategories().size(); i++) {
                Category category = recipe.getCategories().get(i);
                System.out.println("           -Category "+i);
                System.out.println("                     + Name : "+category.getName());
                System.out.println("                     + Url  : "+category.getUrl());
            }
            System.out.println("Cooking Time : "+recipe.getCookingTime());
            System.out.println("Preparation Time : "+recipe.getPreparationTime());
            System.out.println("Ingredients : ");
            for (int i = 0; i < recipe.getIngredients().size(); i++) {
                Ingredient ingredient = recipe.getIngredients().get(i);
                System.out.println("                     + Name : "+ingredient.getName());
                System.out.println("                     + Url  : "+ingredient.getUrl());
                System.out.println("                     + Description  : "+ingredient.getDescription());
                System.out.println("                     + Measurement Description  : "+ingredient.getMeasurementDescription());
                System.out.println("                     + Serving Id  : "+ingredient.getServingId());
                System.out.println("                     + Food Id  : "+ingredient.getFoodId());
                System.out.println("                     + Number Of Unit : "+ingredient.getNumberOfUnits());
            }
        }
    }
}
