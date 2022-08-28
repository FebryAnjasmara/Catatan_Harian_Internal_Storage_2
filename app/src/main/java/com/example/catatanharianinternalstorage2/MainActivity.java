package com.example.catatanharianinternalstorage2;

import static com.example.catatanharianinternalstorage2.SignUp.FILE_NAME_1;
import static com.example.catatanharianinternalstorage2.SignUp.FILE_NAME_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    Button login, signUp;
    EditText name1, password1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.btLogin);
        signUp = findViewById(R.id.btSignUp);
        name1 = findViewById(R.id.etUsernameLogin);
        password1 = findViewById(R.id.etPasswordLogin);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goSignUp = new Intent(MainActivity.this, SignUp.class);
                startActivity(goSignUp);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load();
            }
        });
    }

    private void load(){
        FileInputStream fis1 = null;
        FileInputStream fis2 = null;

        try {
            fis1 = openFileInput(FILE_NAME_1);
            InputStreamReader isr1 = new InputStreamReader(fis1);
            BufferedReader br1 = new BufferedReader(isr1);
            StringBuilder sb1 = new StringBuilder();
            String text1;

            while ((text1 = br1.readLine()) != null){
                sb1.append(text1);
            }

            fis2 = openFileInput(FILE_NAME_2);
            InputStreamReader isr2 = new InputStreamReader(fis2);
            BufferedReader br2 = new BufferedReader(isr2);
            StringBuilder sb2 = new StringBuilder();
            String text2;

            while ((text2 = br2.readLine()) != null){
                sb2.append(text2);
            }

            if (sb1.toString().equals(name1.getText().toString()) && sb2.toString().equals(password1
                    .getText().toString())){
                Intent goMainMenu = new Intent(MainActivity.this, CatatanHarian.class);
                startActivity(goMainMenu);
                Toast.makeText(this, "Berhasil Login", Toast.LENGTH_SHORT).show();
            } else if(name1.getText().toString().equals("") || password1.getText().toString().equals("")){
                Toast.makeText(this, "Masukkan username atau password anda", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Username atau password anda salah, silahkan coba lagi",
                        Toast.LENGTH_SHORT).show();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis1 != null){
                try {
                    fis1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis2 != null){
                try {
                    fis2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}