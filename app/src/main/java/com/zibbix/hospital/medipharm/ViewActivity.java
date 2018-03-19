package com.zibbix.hospital.medipharm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        final String appointID = getIntent().getExtras().getString("appointID");
        final TextView pres = (TextView) findViewById(R.id.presct);



        DatabaseReference databaseRefConsultpres = FirebaseDatabase.getInstance().getReference().child("Consultation").child(appointID).child("Prescriptiton");
        databaseRefConsultpres.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pres.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        final EditText cash = findViewById(R.id.cash);


        Button proceed = (Button)findViewById(R.id.Proceed);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(ViewActivity.this, cash.getText(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ViewActivity.this,QRActivity.class);
                intent.putExtra("cash", cash.getText().toString());
                startActivity(intent);

            }
        });
    }
}
