package com.fit2081.a1;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSignUpButtonClick(View view) {

        TextView tvUsername = findViewById(R.id.editTextUsername);
        TextView tvPassword = findViewById(R.id.editTextPassword);
        TextView tvConfirmPassword = findViewById(R.id.editTextConfirmPassword);


        String usernameString = tvUsername.getText().toString();
        String passwdString = tvPassword.getText().toString();
        String confirmPasswdString = tvConfirmPassword.getText().toString();

        if (usernameString.isEmpty() || passwdString.isEmpty() || confirmPasswdString.isEmpty()){
            Toast.makeText(this,"Invalid username or passwords",Toast.LENGTH_SHORT).show();
            return;

        }

        if (passwdString.equals(confirmPasswdString)){
            saveDataToSharedPreference(usernameString,passwdString);
            Intent intent = new Intent(this, LoginActivity.class);
            Toast.makeText(this,"Registration successfully!",Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
        else{

            Toast.makeText(this,"Invalid username or passwords",Toast.LENGTH_SHORT).show();
        }

    }

    private void saveDataToSharedPreference(String usernameValue, String passwdValue){
        SharedPreferences sharedPreferences = getSharedPreferences("USER_DETAILS", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("KEY_USERNAME", usernameValue);
        editor.putString("KEY_PASSWD", passwdValue);

        editor.apply();

    }

    public void onGoToLoginButtonClick(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}