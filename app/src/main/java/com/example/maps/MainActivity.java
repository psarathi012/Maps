package com.example.maps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {
private DatabaseReference mDatabase;
    private Button btnsave;
    private Button btnproceed;
    private EditText editTextName;
    private EditText editTextlatitute;
    private EditText editTextlongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnproceed=(Button) findViewById(R.id.btnproceed);
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Users");
        editTextName=(EditText)findViewById(R.id.editTextName);
        editTextlatitute=(EditText)findViewById(R.id.editTextlatitude);
        editTextlongitude=(EditText)findViewById(R.id.editTextlongitude);
        btnsave=(Button) findViewById(R.id.btnsave);
        btnsave.setOnClickListener(this);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,MapsActivity.class);
                startActivity(i);

            }
        });


    }
private void saveuserInformation(){
        String name=editTextName.getText().toString().trim();
        double latitute= Double.parseDouble(editTextlatitute.getText().toString().trim());
        double longitute= Double.parseDouble(editTextlongitude.getText().toString().trim());
        Userinformation userinformation=new Userinformation(name,latitute,longitute);
        mDatabase.child("Users").setValue(userinformation);
        Toast.makeText(this,"saved",Toast.LENGTH_LONG).show();
}
    @Override
    public void onClick(View v){
        if( v ==btnproceed){
            finish();
        }
if(v ==btnsave) {
    saveuserInformation();
    editTextName.getText().clear();
    editTextlatitute.getText().clear();
    editTextlongitude.getText().clear();


 }

    }
}
