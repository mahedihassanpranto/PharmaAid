package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class signupform extends AppCompatActivity {

    EditText name,username,email;
    Button button;
    DatabaseReference ref;
    Member member;
    long maxid=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupform);

        name=findViewById(R.id.name_id);
        username=findViewById(R.id.user_id);
        email=findViewById(R.id.mail_id);
        button=findViewById(R.id.join_btn);
        member=new Member();

        ref= FirebaseDatabase.getInstance().getReference().child("Member");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                    maxid=snapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                member.setName(name.getText().toString().trim());
                member.setUsername(username.getText().toString().trim());
                member.setEmail(email.getText().toString().trim());

                ref.child(String.valueOf(maxid+1)).setValue(member);

                Toast.makeText(signupform.this,"you are registered",Toast.LENGTH_LONG).show();



          }
        });



    }
}