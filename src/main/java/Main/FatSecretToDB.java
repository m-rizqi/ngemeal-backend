package Main;

import platformfatsecret.model.*;
import platformfatsecret.services.FatsecretService;
import platformfatsecret.services.Response;

import java.sql.*;
import java.util.List;

public class FatSecretToDB {
    static Connection con;
    static String key = "c8f6dbd68d1e4e4293b33a47bd4d21df";
    static String secret = "9ca35ea3c449451cabc9732f24086c8d";
    static FatsecretService service = new FatsecretService(key, secret);

    public static void main(String[] args) {
        creatConnection();
//        searchFoods("ayam bakar");
    }
    public static void creatConnection(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ngemeal_2","root","");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static int getLatestIdServing(){
        int id = 0;
        try {
            Statement stat = con.createStatement();
            ResultSet resultSet = stat.executeQuery("SELECT MAX(id) FROM servings");
            while (resultSet.next()){
                id = resultSet.getInt("MAX(id)");
                System.out.println("LATEST ID : "+id);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id;
    }

    public static int getLatestIdIngredients(){
        int id = 0;
        try {
            Statement stat = con.createStatement();
            ResultSet resultSet = stat.executeQuery("SELECT MAX(id) FROM ingredients");
            while (resultSet.next()){
                id = resultSet.getInt("MAX(id)");
                System.out.println("LATEST ID : "+id);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id;
    }

    public static void insertFood(Food food){
        try {
            Statement statement = con.createStatement();
            int latestServingId = getLatestIdServing();
            latestServingId++;
            int[] servingId = new int[food.getServings().size()];
            int a = 0;
            servingId[a++] = latestServingId;
            String servingIdString = "["+latestServingId;
            latestServingId++;
            for (int i = latestServingId; i <= latestServingId + food.getServings().size()-2; i++) {
                servingId[a++] = i;
                servingIdString += ","+i;
            }
            servingIdString += "]";
            String query = "INSERT INTO `foods`(`name`, `type`, `description`, `url`, `brandname`, `servings`, `created_at`, `updated_at`) VALUES ('"+food.getName()+"','"+food.getType()+"','"+food.getDescription()+"','"+food.getUrl()+"','"+food.getBrandName().replace("\'","")+"','"+servingIdString+"',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)";
            statement.execute(query);
            insertServings(food.getServings(),servingId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void insertRecipe(Recipe recipe){
            try {
                Statement statement = con.createStatement();

                int latestIngredientId = getLatestIdIngredients();
                latestIngredientId++;
                int[] ingredientsId = new int[recipe.getIngredients().size()];
                int a = 0;
                ingredientsId[a++] = latestIngredientId;
                String ingredientsIdString = "["+latestIngredientId;
                latestIngredientId++;
                for (int i = latestIngredientId; i <= latestIngredientId + recipe.getIngredients().size()-2; i++) {
                    ingredientsId[a++] = i;
                    ingredientsIdString += ","+i;
                }
                ingredientsIdString += "]";

                String imagesString = "["+recipe.getImages().get(0);
                for (int i = 1; i <recipe.getImages().size() ; i++) {
                    imagesString += ","+recipe.getImages().get(i);
                }
                imagesString += "]";

                String categoriesString = "["+recipe.getCategories().get(0).getName();
                for (int i = 1; i <recipe.getImages().size() ; i++) {
                    categoriesString += ","+recipe.getCategories().get(i).getName();
                }
                categoriesString += "]";

                String query = "INSERT INTO `recipes`(`name`, `description`, `url`, `images`, `categories`, `ingredients`, `created_at`, `updated_at`) VALUES ('"+recipe.getName()+"','"+recipe.getDescription()+"','"+recipe.getUrl()+"','"+imagesString+"','"+categoriesString+"','"+ingredientsIdString+"',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)";
                statement.execute(query);
                insertServings(food.getServings(),servingId);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    public static void searchFoods(String search){
        Response<CompactFood> response = service.searchFoods(search);
        List<CompactFood> results = response.getResults();
        for (CompactFood compactFood:
             results) {
            Long id = compactFood.getId();
            try {
                Statement statement = con.createStatement();
                String query = "SELECT `id` FROM `foods` WHERE `id`="+id;
                ResultSet resultSet = statement.executeQuery(query);
                if (!resultSet.next()){
                    Food food = service.getFood(id);
                    insertFood(food);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void searchRecipe(String search){
            Response<CompactRecipe> response = service.searchRecipes(search);
            List<CompactRecipe> results = response.getResults();
            for (CompactRecipe compactRecipe:
                 results) {
                Long id = compactRecipe.getId();
                try {
                    Statement statement = con.createStatement();
                    String query = "SELECT `id` FROM `recipes` WHERE `id`="+id;
                    ResultSet resultSet = statement.executeQuery(query);
                    if (!resultSet.next()){
                        Recipe recipe = service.getRecipe(id);
                        insertRecipe(recipe);
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

    public static void insertServings(List<Serving> servings, int[] servingId){
        int a = 0;
        for (Serving serving: servings) {
            String query = "INSERT INTO `servings`(`id`, `description`, `url`, `measurement_description`, `metric_serving_amount`, `metric_serving_unit`, `number_of_units`, `iron`, `calcium`, `calories`, `fat`, `carbohydrate`, `cholesterol`, `fiber`, `monosaturated_fat`, `polyunsaturated_fat`, `potassium`, `protein`, `saturated_fat`, `sodium`, `sugar`, `trans_fat`, `vitamin_a`, `vitamin_c`) VALUES ("+servingId[a++]+",'"+serving.getServingDescription()+"','"+serving.getServingUrl()+"','"+serving.getMeasurementDescription()+"',"+serving.getMetricServingAmount()+",'"+serving.getMetricServingUnit()+"',"+serving.getNumberOfUnits()+","+serving.getIron()+","+serving.getCalcium()+","+serving.getCalories()+","+serving.getFat()+","+serving.getCarbohydrate()+","+serving.getCholesterol()+","+serving.getFiber()+","+serving.getMonounsaturatedFat()+","+serving.getPolyunsaturatedFat()+","+serving.getPotassium()+","+serving.getProtein()+","+serving.getSaturatedFat()+","+serving.getSodium()+","+serving.getSugar()+","+serving.getTransFat()+","+serving.getVitaminA()+","+serving.getVitaminC()+")";
            Statement statement = null;
            try {
                statement = con.createStatement();
                statement.execute(query);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
