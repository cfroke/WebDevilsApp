package webdevils.webdevilsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button login = (Button) findViewById(R.id.button);

        // Gets username and password and turns them into strings for validation
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText inputName = (EditText) findViewById(R.id.userNameEntry);
                EditText inputPass = (EditText) findViewById(R.id.passwordEntry);
                String uName = inputName.getText().toString();
                String uPass = inputPass.getText().toString();
            }
        });
    }
}
