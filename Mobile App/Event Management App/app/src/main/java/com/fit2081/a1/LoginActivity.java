package com.fit2081.a1;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences sharedPreferences = getSharedPreferences("USER_DETAILS", MODE_PRIVATE);
        String UsernameRestored = sharedPreferences.getString("KEY_USERNAME", "DEFAULT VALUE");

        TextView tvUsername = findViewById(R.id.editTextLoginUsername);
        tvUsername.setText(UsernameRestored);

        // set when the back pressed of login activity to go to the sign up page
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);


    }


    public void onLoginButtonClick(View view) {

        SharedPreferences sharedPreferences = getSharedPreferences("USER_DETAILS", MODE_PRIVATE);
        String UsernameRestored = sharedPreferences.getString("KEY_USERNAME", "");
        String PasswdRestored = sharedPreferences.getString("KEY_PASSWD", "");

        TextView tvUsername = findViewById(R.id.editTextLoginUsername);
        TextView tvPassword = findViewById(R.id.editTextLoginPassword);


        String usernameString = tvUsername.getText().toString();
        String passwdString = tvPassword.getText().toString();

        if (usernameString.isEmpty() || passwdString.isEmpty()){
            Toast.makeText(getApplicationContext(),"Invalid username or password",Toast.LENGTH_SHORT).show();
            return;
        }

        if (usernameString.equals(UsernameRestored) && passwdString.equals(PasswdRestored)){
            Intent intent = new Intent(this, DashboardActivity.class);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("LAST_SAVED_USERNAME", usernameString);
            editor.apply();
            Toast.makeText(getApplicationContext(),"Login Successfully!",Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(),"Authentication failure: Username or Password incorrect",Toast.LENGTH_SHORT).show();
        }


    }

}