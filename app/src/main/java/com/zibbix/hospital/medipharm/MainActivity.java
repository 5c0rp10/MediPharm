package com.zibbix.hospital.medipharm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    appointAdapter adapter;
    List<appoint> appointList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(getApplicationContext());

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        appointList = new ArrayList<>();

        loadAppointment();
    }


    private void loadAppointment(){
        FirebaseApp.initializeApp(getApplicationContext());
        DatabaseReference databaseRefConsult= FirebaseDatabase.getInstance().getReference().child("Consultation");
        databaseRefConsult.addListenerForSingleValueEvent(new ValueEventListener() {
            List<String> appointID = new ArrayList<>();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dSnapshot : dataSnapshot.getChildren()) {
                    appointID.add(dSnapshot.getKey());

                }
                //   Toast.makeText(AppointActivity.this,appointID,Toast.LENGTH_SHORT).show();
                for (int j=0;j<appointID.size();j++) {
                    DatabaseReference databaseRefappoint = FirebaseDatabase.getInstance().getReference().child("Consultation").child(appointID.get(j));
                    final List<String> appointlist = new ArrayList<>();
                    final String appoin = appointID.get(j);
                    databaseRefappoint.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            int i = 0;
                            for (DataSnapshot dSnapshot : dataSnapshot.getChildren()) {

                                if (i == 0) {
                                    appointlist.add(dSnapshot.getValue().toString());

                                }
                                if (i == 1) {
                                    appointlist.add(dSnapshot.getValue().toString());

                                }


                                if (i == 2) {
                                    appointlist.add(appoin);


                                }

                                i++;

                            }
                            appoint app = new appoint(
                                    appointlist.get(0),
                                    appointlist.get(1),
                                    appointlist.get(2)
                            );

                            appointList.add(app);
                            adapter = new appointAdapter(appointList, getApplicationContext());
                            recyclerView.setAdapter(adapter);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });



                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });



    }
}
