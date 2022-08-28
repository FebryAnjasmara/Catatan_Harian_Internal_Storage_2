package com.example.catatanharianinternalstorage2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SignUp extends AppCompatActivity {

    public static final String FILE_NAME_1 = "username.txt";
    public static final String FILE_NAME_2 = "password.txt";
    EditText name2, password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name2 = findViewById(R.id.etUsernameSignUp);
        password2 = findViewById(R.id.etPasswordSignUp);
    }

    public void save(View v){
        String name = name2.getText().toString();
        String pass = password2.getText().toString();
        FileOutputStream fos1 = null;
        FileOutputStream fos2 = null;

        try {
            fos1 = openFileOutput(FILE_NAME_1, MODE_PRIVATE);
            fos2 = openFileOutput(FILE_NAME_2, MODE_PRIVATE);
            fos1.write(name.getBytes());
            fos2.write(pass.getBytes());

            name2.getText().clear();
            password2.getText().clear();

            Intent goLogin = new Intent(SignUp.this, MainActivity.class);
            startActivity(goLogin);

            Toast.makeText(this, "Disimpan di" + getFilesDir() + "/" + FILE_NAME_1 + "\n"
                    + "Disimpan di" + getFilesDir() + "/" + FILE_NAME_2, Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos1 != null){
                try {
                    fos1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos2 != null){
                try {
                    fos2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}