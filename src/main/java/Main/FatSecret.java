package Main;

import com.fatsecret.platform.model.CompactFood;
import com.fatsecret.platform.model.Food;
import com.fatsecret.platform.model.Serving;
import com.fatsecret.platform.services.FatsecretService;
import com.fatsecret.platform.services.Response;

import java.util.List;

public class FatSecret {
    public static void main(String[] args) {
    String key = "c8f6dbd68d1e4e4293b33a47bd4d21df";
    String secret = "9ca35ea3c449451cabc9732f24086c8d";
    FatsecretService service = new FatsecretService(key, secret);

    String query = "rendang";

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
            System.out.println("Serving     : ");
            for (Serving serving:
                 food.getServings()) {
                System.out.println("             -Calcium                 : "+serving.getCalcium());
                System.out.println("             -Calories                : "+serving.getCalories());
                System.out.println("             -Fat                     : "+serving.getFat());
                System.out.println("             -Serving Id              : "+serving.getServingId());
                System.out.println("             -Carbohydrate            : "+serving.getCarbohydrate());
                System.out.println("             -Cholesterol             : "+serving.getCholesterol());
                System.out.println("             -Fiber                   : "+serving.getFiber());
                System.out.println("             -Iron                    : "+serving.getIron());
                System.out.println("             -Measurement Description : "+serving.getMeasurementDescription());
                System.out.println("             -Metric Serving Amount   : "+serving.getMetricServingAmount());
                System.out.println("             -Metric Serving Unit     : "+serving.getMetricServingUnit());
                System.out.println("             -Monosaturated Fat       : "+serving.getMonounsaturatedFat());
                System.out.println("             -Number of Units         : "+serving.getNumberOfUnits());
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
            }
        }

    }

}
