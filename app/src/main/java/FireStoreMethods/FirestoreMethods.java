package FireStoreMethods;

import android.widget.TextView;

import com.example.managertabs.Master;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

public class FirestoreMethods extends Master {


   public String getItemLocation(DocumentReference anItem){
        // THIS WORKS

        DocumentReference docRef = db.collection("Inventory").document("Green Beans");
        return anItem.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String location = documentSnapshot.get("Location").toString();
//               TextView tv =(TextView)findViewById(R.id.textView2);
//               tv.setText(location);
            }

        }).toString();
    }


}
