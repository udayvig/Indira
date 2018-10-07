package com.example.android.indira;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddContactsActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;

    EditText name1, name2, name3, name4, number1, number2, number3, number4;
    FloatingActionButton saveFab;

    List<ContactClass> contactClassList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contacts);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        databaseReference = firebaseDatabase.getReference("Indira/" + firebaseUser.getUid() + "/Contacts/");

        name1 = findViewById(R.id.name1);
        name2 = findViewById(R.id.name2);
        name3 = findViewById(R.id.name3);
        name4 = findViewById(R.id.name4);

        number1 = findViewById(R.id.number1);
        number2 = findViewById(R.id.number2);
        number3 = findViewById(R.id.number3);
        number4 = findViewById(R.id.number4);

        saveFab = findViewById(R.id.saveFab);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                contactClassList.clear();

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    ContactClass contact = snapshot.getValue(ContactClass.class);
                    contactClassList.add(contact);
                }

                int size = contactClassList.size();

                switch (size){
                    case 0:
                        break;
                    case 1:
                        number1.setText(contactClassList.get(0).number);
                        name1.setText(contactClassList.get(0).name);
                        break;
                    case 2:
                        number1.setText(contactClassList.get(0).number);
                        name1.setText(contactClassList.get(0).name);
                        number2.setText(contactClassList.get(1).number);
                        name2.setText(contactClassList.get(1).name);
                        break;
                    case 3:
                        number1.setText(contactClassList.get(0).number);
                        name1.setText(contactClassList.get(0).name);
                        number2.setText(contactClassList.get(1).number);
                        name2.setText(contactClassList.get(1).name);
                        number3.setText(contactClassList.get(2).number);
                        name3.setText(contactClassList.get(2).name);
                        break;
                    case 4:
                        number1.setText(contactClassList.get(0).number);
                        name1.setText(contactClassList.get(0).name);
                        number2.setText(contactClassList.get(1).number);
                        name2.setText(contactClassList.get(1).name);
                        number3.setText(contactClassList.get(2).number);
                        name3.setText(contactClassList.get(2).name);
                        number4.setText(contactClassList.get(3).number);
                        name4.setText(contactClassList.get(3).name);
                        break;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        saveFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.setValue(null);

                if(!name1.getText().toString().equals("") && !number1.getText().toString().equals("") && !name1.getText().toString().trim().isEmpty() && !number1.getText().toString().trim().isEmpty()){
                    DatabaseReference databaseReference1 = firebaseDatabase.getReference("Indira/" + firebaseUser.getUid() + "/Contacts/");
                    String key1 = databaseReference1.push().getKey();
                    ContactClass contactClass1 = new ContactClass(key1, name1.getText().toString().trim(), number1.getText().toString().trim());
                    databaseReference1.child(key1).setValue(contactClass1);
                }

                if(!name2.getText().toString().equals("") && !number2.getText().toString().equals("") && !name2.getText().toString().trim().isEmpty() && !number2.getText().toString().trim().isEmpty()){
                    DatabaseReference databaseReference2 = firebaseDatabase.getReference("Indira/" + firebaseUser.getUid() + "/Contacts/");
                    String key2 = databaseReference2.push().getKey();
                    ContactClass contactClass2 = new ContactClass(key2, name2.getText().toString().trim(), number2.getText().toString().trim());
                    databaseReference2.child(key2).setValue(contactClass2);
                }

                if(!name3.getText().toString().equals("") && !number3.getText().toString().equals("") && !name3.getText().toString().trim().isEmpty() && !number3.getText().toString().trim().isEmpty()){
                    DatabaseReference databaseReference3 = firebaseDatabase.getReference("Indira/" + firebaseUser.getUid() + "/Contacts/");
                    String key3 = databaseReference3.push().getKey();
                    ContactClass contactClass3 = new ContactClass(key3, name1.getText().toString().trim(), number1.getText().toString().trim());
                    databaseReference3.child(key3).setValue(contactClass3);
                }

                if(!name4.getText().toString().equals("") && !number4.getText().toString().equals("") && !name4.getText().toString().trim().isEmpty() && !number4.getText().toString().trim().isEmpty()){
                    DatabaseReference databaseReference4 = firebaseDatabase.getReference("Indira/" + firebaseUser.getUid() + "/Contacts/");
                    String key4 = databaseReference4.push().getKey();
                    ContactClass contactClass4 = new ContactClass(key4, name4.getText().toString().trim(), number4.getText().toString().trim());
                    databaseReference4.child(key4).setValue(contactClass4);
                }

                Snackbar.make(findViewById(android.R.id.content), "Contacts Saved!", Snackbar.LENGTH_LONG)
                        .show();
            }
        });
    }
}
