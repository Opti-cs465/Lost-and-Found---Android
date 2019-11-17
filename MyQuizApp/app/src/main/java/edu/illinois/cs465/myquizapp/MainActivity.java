package edu.illinois.cs465.myquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button loginButton;
    private Button signupButton;
    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = (Button) findViewById(R.id.loginbutton);
        signupButton = (Button) findViewById(R.id.signupbutton);
        username = (EditText) findViewById(R.id.UsernameInput);
        password = (EditText) findViewById(R.id.PasswordInput);

        loginButton.setOnClickListener(this);
        signupButton.setOnClickListener(this);


    }

    public void onClick(View v) {
        if (!username.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
            if (v.getId() == R.id.loginbutton) {
                Intent intent = new Intent(getBaseContext(), HomeScreen.class);
                intent.putExtra("USERNAME", username.getText().toString());
                intent.putExtra("PASSWORD", password.getText().toString());
                startActivity(intent);
            } else if (v.getId() == R.id.signupbutton){
                Intent intent = new Intent(getBaseContext(), ShowAnswer.class);
                intent.putExtra("USERNAME", username.getText().toString());
                intent.putExtra("PASSWORD", password.getText().toString());
                startActivity(intent);
            }
        } else {
            if (username.getText().toString().isEmpty()){
                username.setError("Username Must Not Be Empty");
            }
            if (password.getText().toString().isEmpty()){
                password.setError("Password Must Not Be Empty");
            }
        }

    }
}
