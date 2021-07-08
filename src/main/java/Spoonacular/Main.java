package Spoonacular;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class Main {
    private static HttpURLConnection connection;
    public static void main(String[] args) {
        BufferedReader reader;
        String line;
        StringBuffer response = new StringBuffer();
        try {
            URL url = new URL("https://api.spoonacular.com/recipes/findByIngredients?apiKey=0c1fc9beb2f84ad393a028b74c5aac27&number=2&ingredients=5%spice%powder");
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while((line = reader.readLine()) != null){
                response.append(line);
            }
            reader.close();
            System.out.println(response.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
