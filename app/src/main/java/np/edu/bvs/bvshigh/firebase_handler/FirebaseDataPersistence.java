package np.edu.bvs.bvshigh.firebase_handler;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Bivav on 7/15/2017.
 */

public class FirebaseDataPersistence extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
