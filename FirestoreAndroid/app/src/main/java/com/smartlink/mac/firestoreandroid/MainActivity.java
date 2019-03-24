package com.smartlink.mac.firestoreandroid;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ArrayList<User> listOfUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        listOfUsers = new ArrayList<>();

//        ArrayList<String> list =new ArrayList<>();
//        list.add("Name is Samsung");
//        list.add("Type is Android");
//        list.add("Model is Note5");

        // Create a new user with a first and last name
//        Map<String, Object> user = new HashMap<>();
//        user.put("first", "Rameez");
//        user.put("last", "Ahmed");
//        user.put("born", 1321);
//        user.put("list", list);


        // Add a new document with a generated ID
//        db.collection("users")
//                .add(user)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w("TAG", "Error adding document", e);
//                    }
//                });

//get
//        db.collection("users")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.d("TAGGER", document.getId() + " => " + document.getData());
//                                User user = (User) document.toObject(User.class);
//                                listOfUsers.add(user);
//                                Log.d("TAGGER", "First Name is ::::" + user.getList().size());
//
//                            }
//                        } else {
//                            Log.w("TAGGER", "Error getting documents.", task.getException());
//                        }
//                    }
//                });
//
//        db.collection("users")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.d("TAGGER", document.getId() + " => " + document.getData());
//                                User user = (User) document.toObject(User.class);
//                                listOfUsers.add(user);
//                                Log.d("TAGGER", "First Name is ::::" + user.getList().size());
//
//                            }
//                        } else {
//                            Log.w("TAGGER", "Error getting documents.", task.getException());
//                        }
//                    }
//                });

        db.collection("users")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot snapshots,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w("TAG", "listen:error", e);
                            return;
                        }

                        for (DocumentChange dc : snapshots.getDocumentChanges()) {
                            switch (dc.getType()) {
                                case ADDED:
                                    Log.d("TAG", "New city: ");
                                    break;
                                case MODIFIED:
                                    Log.d("TAG", "Modified city: ");
                                    break;
                                case REMOVED:
                                    Log.d("TAG", "Removed city: ");
                                    break;
                            }
                        }

                    }
                });

//        db.collection("users")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.d("TAGGER", document.getId() + " => " + document.getData());
//                                User user = (User) document.toObject(User.class);
//                                listOfUsers.add(user);
//                                Log.d("TAGGER", "First Name is ::::" + user.getList().size());
//
//                            }
//                        } else {
//                            Log.w("TAGGER", "Error getting documents.", task.getException());
//                        }
//                    }
//                });
//
//
//

        //get single document
//        DocumentReference docRef = db.collection("users").document("CaNJnFDqOth6Hq02DkZF");
//        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//                    DocumentSnapshot document = task.getResult();
//                    if (document.exists()) {
//                        Log.d("TAGGER", "DocumentSnapshot data: " + document.getData());
//                        User user = (User) document.toObject(User.class);
////                        Log.d("TAGGER", "First Name is ::::"+user.getList().size());
//                    } else {
//                        Log.d("TAGGER", "No such document");
//                    }
//                } else {
//                    Log.d("TAGGER", "get failed with ", task.getException());
//                }
//            }
//        });

    }

}
