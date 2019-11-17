package edu.illinois.cs465.myquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class HomeScreen extends AppCompatActivity implements View.OnClickListener {
    private TextView location;
    private String locationtext;
    private Button ChangeLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        location = (TextView)findViewById(R.id.location);
        locationtext = "Champaign";
        SpannableString content = new SpannableString(locationtext);
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        location.setText(content);
        ChangeLocation = (Button)findViewById(R.id.changeLocation);
        ChangeLocation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.changeLocation){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("New Location");

// Set up the input
            final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);

// Set up the buttons
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    locationtext = input.getText().toString();
                    SpannableString content = new SpannableString(locationtext);
                    content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
                    location.setText(content);
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.show();
        }
    }
}
