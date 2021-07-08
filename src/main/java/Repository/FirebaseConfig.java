package Repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class FirebaseConfig {
    public static void initFirebase() throws IOException, ExecutionException, InterruptedException {
        FileInputStream serviceAccount = null;
        serviceAccount = new FileInputStream("./ServiceAccountKey.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();
        FirebaseApp.initializeApp(options);
    }
}
