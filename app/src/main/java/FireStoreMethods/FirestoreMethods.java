package FireStoreMethods;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.TextView;

import com.example.managertabs.Master;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import org.w3c.dom.Document;

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

    //Should work as a getter for item quantity
    public int getItemQuantity(DocumentReference anItem){
        Task<DocumentSnapshot> quantity = anItem.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                double quant = documentSnapshot.getDouble("Quantity");
            }
        });
        return 0;
    }
    public String getItemThreshold(String itemName){
        DocumentReference dr = db.collection("Inventory").document(itemName);
        String test = dr.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot doc = task.getResult();
                    String threshold = doc.get("Threshold").toString();
                }
            }
        }).getResult().get("Threshold").toString();
        String test2 = dr.get().getResult().get("Quantity").toString();
        return test2;
    }

}
