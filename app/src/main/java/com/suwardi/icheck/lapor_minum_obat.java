package com.suwardi.icheck;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class lapor_minum_obat extends AppCompatActivity {
    private boolean timerHasStarted = false;
    private CountDownTimer countDownTimer;
    HashMap hashMap = new HashMap();
    private DatabaseReference myref, mDatabase;
    FirebaseAuth firebaseAuth;
    FirebaseUser User;
    private Button no,yes;
    TextView timer;
    private final long startTime = 600000;
    private final long interval = 1 * 1000;
    private static final int PERMISSION_SEND_SMS = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lapor_minum_obat);


        mDatabase = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance() ;
        User = firebaseAuth.getCurrentUser();
        timer = findViewById(R.id.timer);
        no = findViewById(R.id.no);
        yes = findViewById(R.id.yes);

        countDownTimer = new MyCountDownTimer(startTime, interval);
        countDownTimer.start();
        timerHasStarted = true;

        requestSmsPermission();

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference databaseReference = mDatabase.child("Data_pasien");
                final String ID = User.getEmail();
                databaseReference.orderByChild("email").startAt(ID).endAt(ID+"\uf88f").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        data_pasien data_pasien = snapshot.getValue(data_pasien.class);
                        String nama = data_pasien.getNama();
                        String nope = data_pasien.getNomer_hp_wali();
                        try {
                            // Get the default instance of the SmsManager
                            SmsManager smsManager = SmsManager.getDefault();
                            smsManager.sendTextMessage(nope,
                                    null,
                                    "Pasien atas nama "+nama+" belum meminum obat",
                                    null,
                                    null);
                            Toast.makeText(getApplicationContext(), "Terimakasih",
                                    Toast.LENGTH_LONG).show();
                            finish();
                        } catch (Exception ex) {
                            Toast.makeText(getApplicationContext(),"Pengiriman SMS Gagal...",
                                    Toast.LENGTH_LONG).show();
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = mDatabase.child("Data_pasien");
                final String ID = User.getEmail();
                databaseReference.orderByChild("email").startAt(ID).endAt(ID+"\uf88f").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        data_pasien data_pasien = snapshot.getValue(data_pasien.class);
                        String nip = data_pasien.getNip_dokter();
                        String Uid = data_pasien.getNik();
                        mDatabase = FirebaseDatabase.getInstance().getReference();
                        myref = mDatabase.child("Data_minum_obat").child(nip).child(Uid).child("laporan");
                        myref.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.exists()) {
                                    for(DataSnapshot ds : snapshot.getChildren()) {
                                        String value = ds.getValue(String.class);
                                        int hitung = Integer.valueOf(value)+1;
                                        hashMap.put("total", String.valueOf(hitung));
                                        myref.setValue(hashMap);
                                        Toast.makeText(lapor_minum_obat.this, "Terimakasih" , Toast.LENGTH_SHORT).show();
                                        finish();
                                    }}else {
                                    hashMap.put("total", "1");
                                    myref.setValue(hashMap);
                                    Toast.makeText(lapor_minum_obat.this, "Terimakasih" , Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
    class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }
        @Override
        public void onFinish() {


            DatabaseReference databaseReference = mDatabase.child("Data_pasien");
            final String ID = User.getEmail();
            databaseReference.orderByChild("email").startAt(ID).endAt(ID+"\uf88f").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    data_pasien data_pasien = snapshot.getValue(data_pasien.class);
                    String nama = data_pasien.getNama();
                    String nope = data_pasien.getNomer_hp_wali();

                    try {
                        // Get the default instance of the SmsManager
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(nope,
                                null,
                                "Pasien atas nama "+nama+" belum meminum obat",
                                null,
                                null);
                        Toast.makeText(getApplicationContext(), "Terimakasih",
                                Toast.LENGTH_LONG).show();
                        finish();
                    } catch (Exception ex) {
                        Toast.makeText(getApplicationContext(),"Pengiriman SMS Gagal...",
                                Toast.LENGTH_LONG).show();
                        ex.printStackTrace();
                        finish();
                    }
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        @Override
        public void onTick(long millisUntilFinished) {
            timer.setText("waktu tersisa : " + (millisUntilFinished /60000) +" menit "+ (millisUntilFinished %60000) /1000 +" detik");
        }
    }

    private void requestSmsPermission() {

        // check permission is given
        if (ContextCompat.checkSelfPermission(lapor_minum_obat.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            // request permission (see result in onRequestPermissionsResult() method)
            ActivityCompat.requestPermissions(lapor_minum_obat.this,
                    new String[]{Manifest.permission.SEND_SMS},
                    PERMISSION_SEND_SMS);
        }
    }

    @Override
    public void onBackPressed() {
    }
}
