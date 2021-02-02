package com.suwardi.icheck;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

public class upload_data_pasien extends AppCompatActivity {

    FirebaseUser User;
    FirebaseAuth firebaseAuth;
    private static final int REQUEST_CODE_IMAGE = 101 ;
    private DatabaseReference mDatabase;
    StorageReference storageRef;
    private Button simpan;
    private FrameLayout data;
    private LinearLayout loadnya;
    private TextView text_load;
    private ProgressBar load;
    private ImageView photo;
    Uri imageuri;
    boolean isImageAdded = false;
    private RadioGroup jk;
    private RadioButton lk,wn;
    private EditText nama,umur,nope,nope_wali,tgl,alamat,bb,rp,nik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_data_pasien);


        photo = findViewById(R.id.up_photo);
        nik = findViewById(R.id.nik);
        jk = findViewById(R.id.jk);
        bb = findViewById(R.id.bb);
        rp = findViewById(R.id.rp);
        lk = findViewById(R.id.lk);
        wn = findViewById(R.id.wn);
        nama = findViewById(R.id.nama);
        umur = findViewById(R.id.umur);
        nope = findViewById(R.id.nope);
        nope_wali = findViewById(R.id.nope_wali);
        tgl = findViewById(R.id.tgl_lahir);
        alamat = findViewById(R.id.alamat);
        simpan = findViewById(R.id.upload_data);
        data = findViewById(R.id.data);
        loadnya = findViewById(R.id.loadingnya);
        text_load = findViewById(R.id.text_proggessBar);
        load = findViewById(R.id.proggessBar);

        firebaseAuth = FirebaseAuth.getInstance() ;
        User = firebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        storageRef = FirebaseStorage.getInstance().getReference().child("Photo_pasien");

        String ID = getIntent().getStringExtra("namanya");

        if(ID != null){
            DatabaseReference databaseReference = mDatabase.child("Data_pasien").child(ID);
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){

                        String url = snapshot.child("url_photo").getValue().toString();
                        Picasso.get().load(url).into(photo);

                        nik.setText(snapshot.child("nik").getValue().toString());
                        bb.setText(snapshot.child("berat_badan").getValue().toString());
                        rp.setText(snapshot.child("riwayat_penyakit").getValue().toString());

                        nama.setText(snapshot.child("nama").getValue().toString());
                        umur.setText(snapshot.child("umur").getValue().toString());
                        nope.setText(snapshot.child("nomer_hp").getValue().toString());
                        nope_wali.setText(snapshot.child("nomer_hp_wali").getValue().toString());
                        tgl.setText(snapshot.child("tanggal_lahir").getValue().toString());
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
                DatabaseReference databaseReference = mDatabase.child("Data_pasien").child(Nnama);
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String unip = getIntent().getStringExtra("nip");
                        String Nnik = nik.getText().toString();
                        String Nbb = bb.getText().toString();
                        String Nrp = rp.getText().toString();
                        String Nnama = nama.getText().toString();
                        String Numur = umur.getText().toString();
                        String Nnomer_hp = nope.getText().toString();
                        String Nnomer_hp_wali = nope_wali.getText().toString();
                        String Ntgl = tgl.getText().toString();
                        String Nalamat = alamat.getText().toString();
                        String email = User.getEmail();

                        if(isImageAdded != false  && Nnik != null  && Nbb != null && Nnama != null && Numur != "" && Nnomer_hp_wali != "" && Ntgl != "" && Nnomer_hp != "" && Nalamat != ""){
                            sendData(unip,Nnik,Nrp,Nbb,Nnama,Numur,Nnomer_hp,Nnomer_hp_wali,Ntgl,Nalamat,email);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(upload_data_pasien.this,databaseError.getCode(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void sendData(final String unip,final String Nnik,final String Nrp,final String Nbb,final String Nnama,final String Numur,final String Nnomer_hp,final String Nnomer_hp_wali,final String Ntgl,final String Nalamat,final String email) {

        data.setVisibility(View.GONE);
        loadnya.setVisibility(View.VISIBLE);
        final String key = mDatabase.push().getKey();
        storageRef.child(key+".jpg").putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storageRef.child(key+".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        if(jk.getCheckedRadioButtonId() == lk.getId()){
                            String Njk = "Pria";
                            HashMap hashMap = new HashMap();
                            hashMap.put("nip_dokter",unip);
                            hashMap.put("riwayat_penyakit",Nrp);
                            hashMap.put("berat_badan",Nbb);
                            hashMap.put("nik",Nnik);
                            hashMap.put("jenis_kelamin",Njk);
                            hashMap.put("nama",Nnama);
                            hashMap.put("umur",Numur);
                            hashMap.put("nomer_hp",Nnomer_hp);
                            hashMap.put("nomer_hp_wali",Nnomer_hp_wali);
                            hashMap.put("tanggal_lahir",Ntgl);
                            hashMap.put("alamat",Nalamat);
                            hashMap.put("email",email);
                            hashMap.put("url_photo",uri.toString());

                            mDatabase.child("Data_pasien").child(Nnama).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(upload_data_pasien.this,"Berhasil di tambahkan",Toast.LENGTH_SHORT).show();
                                    finish();
                                    startActivity(new Intent(upload_data_pasien.this, MenuUtama.class));
                                }
                            });
                        }
                        else if(jk.getCheckedRadioButtonId() == wn.getId()){
                            String Njk = "Wanita";
                            HashMap hashMap = new HashMap();
                            hashMap.put("nip_dokter",unip);
                            hashMap.put("riwayat_penyakit",Nrp);
                            hashMap.put("berat_badan",Nbb);
                            hashMap.put("nik",Nnik);
                            hashMap.put("jenis_kelamin",Njk);
                            hashMap.put("nama",Nnama);
                            hashMap.put("umur",Numur);
                            hashMap.put("nomer_hp",Nnomer_hp);
                            hashMap.put("nomer_hp_wali",Nnomer_hp_wali);
                            hashMap.put("tanggal_lahir",Ntgl);
                            hashMap.put("alamat",Nalamat);
                            hashMap.put("email",email);
                            hashMap.put("url_photo",uri.toString());

                            mDatabase.child("Data_pasien").child(Nnama).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(upload_data_pasien.this,"Berhasil di tambahkan",Toast.LENGTH_SHORT).show();
                                    finish();
                                    startActivity(new Intent(upload_data_pasien.this, MenuUtama.class));
                                }
                            });
                        }
                        else{
                            Toast.makeText(upload_data_pasien.this,"Periksa Kolom Jenis Kelamin",Toast.LENGTH_SHORT).show();
                        }


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
        startActivity(new Intent(upload_data_pasien.this, MenuUtama.class));
    }
}
