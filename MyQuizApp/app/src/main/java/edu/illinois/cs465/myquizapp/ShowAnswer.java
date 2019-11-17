package edu.illinois.cs465.myquizapp;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ShowAnswer extends AppCompatActivity implements View.OnClickListener {
    private EditText phone_number;
    private Button signupButton;
    private EditText username;
    private EditText password;
    private EditText confirmpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_answer);

        phone_number = (EditText)findViewById(R.id.PhoneNumberInput);
        phone_number.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        username = (EditText) findViewById(R.id.UsernameInput);
        username.setText(getIntent().getStringExtra("USERNAME"));
        password = (EditText) findViewById(R.id.PasswordInput);
        password.setText(getIntent().getStringExtra("PASSWORD"));
        signupButton = (Button) findViewById(R.id.signupbutton);
        confirmpassword = (EditText) findViewById(R.id.ConfirmPasswordInput);

        signupButton.setOnClickListener(this);

        ImageButton fab = findViewById(R.id.phonebutton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Opti uses your phone number to contact you if your item has been found",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onClick(View v){
        if(v.getId() == R.id.signupbutton){
            boolean ok = true;
            if (username.getText().toString().isEmpty()){
                username.setError("Username Must Not Be Empty");
                ok = false;
            }
            if (password.getText().toString().isEmpty()){
                password.setError("Password Must Not Be Empty");
                ok = false;
            }
            if (phone_number.getText().toString().isEmpty()){
                phone_number.setError("Please Enter a Phone Number");
                ok = false;
            }
            if (!password.getText().toString().equals(confirmpassword.getText().toString())){
                confirmpassword.setError("Passwords Must Match");
                ok = false;
            }
            if(ok){
                Intent intent = new Intent(getBaseContext(), HomeScreen.class);
                intent.putExtra("USERNAME", username.getText().toString());
                intent.putExtra("PASSWORD", password.getText().toString());
                startActivity(intent);
            }
        }
    }

}
