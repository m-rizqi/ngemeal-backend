package Main;

import android.net.Uri;
import android.util.Base64;
import org.json.JSONObject;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FatSecretRest {
    final static private String APP_METHOD = "GET";
    final static private String APP_KEY = "c8f6dbd68d1e4e4293b33a47bd4d21df";
    final static private String APP_SECRET = "9ca35ea3c449451cabc9732f24086c8d";
    final static private String APP_URL = "http://platform.fatsecret.com/rest/server.api";
    final static private String HMAC_SHA1_ALGORITHM = "HmacSHA1";

    public static void main(String[] args) throws UnsupportedEncodingException {
        searchFood("nasi goreng");
    }

    public static JSONObject getFood(Long id){
        List<String> params = new ArrayList<>(Arrays.asList(generateOauthParams()));
        String[] template = new String[1];
        params.add("method=food.get");
        params.add("food_id="+id);
        params.add("oauth_signature="+sign(APP_METHOD, APP_URL, params.toArray(template)));
        JSONObject food = null;
        try {
            URL url = new URL(APP_URL+'?'+paramify(params.toArray(template)));
            URLConnection api = url.openConnection();
            String line;
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(api.getInputStream()));
            while ((line = reader.readLine()) != null){
                builder.append(line);
            }
            JSONObject foodGet = new JSONObject(builder.toString());
            food = foodGet.getJSONObject("food");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return food;
    }

    public static JSONObject searchFood(String search) throws UnsupportedEncodingException {
        List<String> params = new ArrayList<>(Arrays.asList(generateOauthParams()));
        String[] template = new String[1];
        params.add("method=foods.search");
        params.add("search_expression=" + URLEncoder.encode(search));
        String signature = sign(APP_METHOD, APP_URL, params.toArray(template));
        signature = signature.replace("%25","%");
        signature = signature.replace("+","");
        signature = signature.replace("%2B","+");
        signature = signature.replace("%2F","/");
        signature = signature.replace("%3D","=");
        System.out.println("Signature : "+signature);
        params.add("oauth_signature=" + signature);

        JSONObject foods = null;
        try {
            URL url = new URL(APP_URL + "?" + paramify(params.toArray(template)));
            URLConnection api = url.openConnection();
            api.setConnectTimeout(9999999);
            System.out.println("URL = "+url);
            String line;
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(api.getInputStream()));
            while ((line = reader.readLine()) != null) builder.append(line);
            System.out.println(builder.toString());
            JSONObject food = new JSONObject(builder.toString());   // { first
            foods = food.getJSONObject("foods");                    // { second
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return foods;
    }

    private static String[] generateOauthParams(){
        //System.out.println("oauth_timestamp = "+Long.valueOf(System.currentTimeMillis()*2).toString());
        return new String[]{
          "oauth_consumer_key="+APP_KEY,
          "oauth_signature_method=HMAC-SHA1",
          "oauth_timestamp="+Long.valueOf(System.currentTimeMillis()*2).toString(),
          "oauth_nonce="+nonce(),
          "oauth_version=1.0",
          "format=json"
        };
    }

    private static String nonce(){
        Random r = new Random();
        StringBuilder n = new StringBuilder();
        for (int i = 0; i < r.nextInt(8)+2; i++) {
            n.append(r.nextInt(26)+"a");
        }
        //System.out.println("nonce = "+n.toString());
        return n.toString();
    }

    private static String sign(String method, String uri, String[] params) {
        String[] p = new String[0];
            p = new String[]{method, URLEncoder.encode(uri), URLEncoder.encode(paramify(params))};
        String s = join(p,"&");
        SecretKey sk = new SecretKeySpec(APP_SECRET.getBytes(), HMAC_SHA1_ALGORITHM);
        Mac m = null;
        try {
            m = Mac.getInstance(HMAC_SHA1_ALGORITHM);
            m.init(sk);
            return URLEncoder.encode(new String(Base64.encode(m.doFinal(s.getBytes()), Base64.DEFAULT))).trim();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("FAIL : "+e.getMessage());
            return null;
        } catch (InvalidKeyException e) {
            System.out.println("Fail : "+e.getMessage());
            return null;
        }
    }

    private static String paramify(String[] params){
        String[] p = Arrays.copyOf(params, params.length);
        Arrays.sort(p);
        return join(p, "&");
    }

    private static String join(String[] array, String separator){
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (i>0)
                b.append(separator);
            b.append(array[i]);
        }
        return b.toString();
    }

}
