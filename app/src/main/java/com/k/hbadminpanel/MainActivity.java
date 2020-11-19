package com.k.hbadminpanel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef ;
    private EditText adi;
    private CheckBox kadin,erkek;
    private CheckBox yedi,onuc,onsekiz,yirmibes,kirk,altmis;
    private CheckBox spor,bilim,sanat,teknoloji,sinema,sosyal;
    private CheckBox dogum,sevgililer,yil,anneler,babalar,mesleki;
    private Button ekle;

    private String hediyeAdi,url,cinsiyet,yas,ilgi,ozel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adi=findViewById(R.id.editAdi);
        kadin=findViewById(R.id.boxKadin);
        erkek=findViewById(R.id.boxErkek);
        yedi=findViewById(R.id.boxYedi);
        onuc=findViewById(R.id.boxOnuc);
        onsekiz=findViewById(R.id.boxOnsekiz);
        yirmibes=findViewById(R.id.box25);
        kirk=findViewById(R.id.box40);
        altmis=findViewById(R.id.box60);
        spor=findViewById(R.id.spor);
        bilim=findViewById(R.id.bilim);
        sanat=findViewById(R.id.sanat);
        teknoloji=findViewById(R.id.teknoloji);
        sinema=findViewById(R.id.sinema);
        sosyal=findViewById(R.id.sosyal);
        dogum=findViewById(R.id.dogum);
        sevgililer=findViewById(R.id.sevgili);
        yil=findViewById(R.id.yilbasi);
        anneler=findViewById(R.id.anneler);
        babalar=findViewById(R.id.babalar);
        mesleki=findViewById(R.id.meslek);

        ekle=findViewById(R.id.ekleButton);

        final HediyeModel hediyeModel = new HediyeModel();
        myRef=database.getReference().child("Hediyeler");

        ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cinsiyetEkle();
                yasEkle();
                ilgiEkle();
                ozelEkle();
                hediyeAdi=adi.getText().toString();


                hediyeModel.setResimUrl("yok");
                hediyeModel.setHediyeAdi(hediyeAdi);
                hediyeModel.setCinsiyet(cinsiyet);
                hediyeModel.setYas(yas);
                hediyeModel.setIlgi(ilgi);
                hediyeModel.setOzel(ozel);
                myRef.push().setValue(hediyeModel);
                Toast.makeText(MainActivity.this, "Başarılı", Toast.LENGTH_SHORT).show();
                yas=null;
                ilgi=null;
                ozel=null;
            }
        });


    }

    private void ozelEkle() {
        String[] strings = new String[6];
        if(dogum.isChecked()){
            strings[0]="Doğum Günü";
        }
        if(sevgililer.isChecked()){
            strings[1]="Sevgililer Günü";
        }
        if(yil.isChecked()){
            strings[2]="Yıl Başı";
        }
        if(anneler.isChecked()){
            strings[3]="Anneler Günü";
        }
        if(babalar.isChecked()){
            strings[4]="Babalar Günü";
        }
        if(mesleki.isChecked()){
            strings[5]="Meslek Günü";
        }
        int i;
        for(i=0; i<6;i++){
            if(strings[i]!=null){
                if(ozel==null){
                    ozel=strings[i];
                } else if(ozel!=null){
                    ozel=ozel+","+strings[i];
                }

            }
        }
    }

    private void ilgiEkle(){
        String[] strings = new String[6];
        if(spor.isChecked()){
            strings[0]="Spor";
        }
        if(bilim.isChecked()){
            strings[1]="Bilim";
        }
        if(sanat.isChecked()){
            strings[2]="Sanat";
        }
        if(teknoloji.isChecked()){
            strings[3]="Teknoloji";
        }
        if(sinema.isChecked()){
            strings[4]="Sinema";
        }
        if(sosyal.isChecked()){
            strings[5]="Sosyal";
        }
        int i;
        for(i=0; i<6;i++){
            if(strings[i]!=null){
                if(ilgi==null){
                    ilgi=strings[i];
                } else if(ilgi!=null){
                    ilgi=ilgi+","+strings[i];
                }

            }
        }
    }

    private void yasEkle() {
        String[] strings = new String[6];
        if(yedi.isChecked()){
            strings[0]="7-12";
        }
        if(onuc.isChecked()){
            strings[1]="13-17";
        }
        if(onsekiz.isChecked()){
            strings[2]="18-25";
        }
        if(yirmibes.isChecked()){
            strings[3]="25-40";
        }
        if(kirk.isChecked()){
            strings[4]="40-60";
        }
        if(altmis.isChecked()){
            strings[5]="60+";
        }
        int i;
        for(i=0; i<6;i++){
            if(strings[i]!=null){
                if(yas==null){
                    yas=strings[i];
                } else if(yas!=null){
                    yas=yas+","+strings[i];
                }

            }
        }
      //  yas=strings[0]+","+strings[1]+","+strings[2]+","+strings[3]+","+strings[4]+","+strings[5];

        /*if(yedi.isChecked()&&onuc.isChecked()&&onsekiz.isChecked()&&yirmibes.isChecked()&&kirk.isChecked()&&altmis.isChecked()){

        }*/
    }

    private void cinsiyetEkle() {
        if (kadin.isChecked()&&!erkek.isChecked()){
            cinsiyet="Kadın";
        }else if (erkek.isChecked()&&!kadin.isChecked()){
            cinsiyet="Erkek";
        } else if(kadin.isChecked()&&erkek.isChecked()){
            cinsiyet="Kadın"+","+"Erkek";
        }else{
           // Toast.makeText(MainActivity.this, "Cinsiyet Başarısız!!!", Toast.LENGTH_SHORT).show();
        }
    }
}
