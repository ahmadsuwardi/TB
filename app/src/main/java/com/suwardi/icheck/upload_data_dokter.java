package com.suwardi.icheck;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class upload_data_dokter extends AppCompatActivity {

    private static final int REQUEST_CODE_IMAGE = 101 ;
    private DatabaseReference mDatabase;
    StorageReference storageRef;
    private EditText nip,pw,nama,spesialis,nope,alamat;
    private Button simpan;
    private FrameLayout data;
    private LinearLayout loadnya;
    private TextView text_load;
    private ProgressBar load;
    private ImageView photo;
    Uri imageuri;
    boolean isImageAdded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_data_dokter);

        photo = findViewById(R.id.up_photo);
        nip = findViewById(R.id.nip);
        pw = findViewById(R.id.pw);
        nama = findViewById(R.id.nama);
        spesialis = findViewById(R.id.spesialis);
        nope = findViewById(R.id.nope);
        alamat = findViewById(R.id.alamat);
        simpan = findViewById(R.id.upload_data);
        data = findViewById(R.id.data);
        loadnya = findViewById(R.id.loadingnya);
        text_load = findViewById(R.id.text_proggessBar);
        load = findViewById(R.id.proggessBar);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        storageRef = FirebaseStorage.getInstance().getReference().child("Photo_dokter");


        String ID = getIntent().getStringExtra("IDNYA");

        if(ID != null){
            DatabaseReference databaseReference = mDatabase.child("Data_dokter").child(ID);
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        String url = snapshot.child("url_photo").getValue().toString();
                        Picasso.get().load(url).into(photo);
                        nip.setText(snapshot.child("nip").getValue().toString());
                        nama.setText(snapshot.child("nama").getValue().toString());
                        spesialis.setText(snapshot.child("spesialis").getValue().toString());
                        nope.setText(snapshot.child("nomer_hp").getValue().toString());
                        alamat.setText(snapshot.child("alamat").getValue().toString());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,REQUEST_CODE_IMAGE);
            }
        });


        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Nnama = nama.getText().toString();
                Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
                Matcher m = p.matcher(Nnama);
                boolean b = m.find();

                if (b){
                    Toast.makeText(upload_data_dokter.this,"Dilarang menggunakan simbol khusus pada inputan nama",Toast.LENGTH_SHORT).show(); }
                else{
                DatabaseReference databaseReference = mDatabase.child("Data_dokter").child(Nnama);
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String Nnip = nip.getText().toString();
                        String Npw = pw.getText().toString();
                        String Nnama = nama.getText().toString();
                        String Nspesialis = spesialis.getText().toString();
                        String Nnomer_hp = nope.getText().toString();
                        String Nalamat = alamat.getText().toString();



                            if(isImageAdded != false && Nnama != null && Nspesialis != "" && Nnomer_hp != "" && Nalamat != "")
                                sendData(Nnip,Npw,Nnama,Nspesialis,Nnomer_hp,Nalamat);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(upload_data_dokter.this,databaseError.getCode(),Toast.LENGTH_SHORT).show();
                    }
                });}
            }
        });
    }

    private void sendData(final String Nnip,final String Npw,final String Nnama,final String Nspesialis,final String Nnomer_hp,final String Nalamat) {

        data.setVisibility(View.GONE);
        loadnya.setVisibility(View.VISIBLE);
        final String key = mDatabase.push().getKey();
        storageRef.child(key+".jpg").putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storageRef.child(key+".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        HashMap hashMap = new HashMap();
                        hashMap.put("nip",Nnip);
                        hashMap.put("password",Npw);
                        hashMap.put("nama",Nnama);
                        hashMap.put("spesialis",Nspesialis);
                        hashMap.put("nomer_hp",Nnomer_hp);
                        hashMap.put("alamat",Nalamat);
                        hashMap.put("url_photo",uri.toString());

                        mDatabase.child("Data_dokter").child(Nnama).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(upload_data_dokter.this,"Berhasil di tambahkan",Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(upload_data_dokter.this, list_data_dokter.class));
                            }
                        });
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                double proses = (taskSnapshot.getBytesTransferred()*100)/taskSnapshot.getTotalByteCount();
                load.setProgress((int)  proses);
                text_load.setText(proses+" %");
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_IMAGE && data != null) {
            imageuri = data.getData();
            isImageAdded = true;
            photo.setImageURI(imageuri);

        }
    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(upload_data_dokter.this, list_data_dokter.class));
    }
}
