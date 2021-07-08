package Main;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import Repository.FirebaseConfig;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        FirebaseConfig.initFirebase();
        Firestore db = FirestoreClient.getFirestore();
        HashMap<String,Double> gpa = new HashMap<>();
        gpa.put("Math",90.5);
        gpa.put("Bio",80.0);
        HashMap<String,String> user = new HashMap<>();
        user.put("Nama", "Rizqi");
        ApiFuture<WriteResult> future = db.collection("test").document("testing").set(user);
        System.out.println("Updated "+future.get().getUpdateTime());
    }
}

