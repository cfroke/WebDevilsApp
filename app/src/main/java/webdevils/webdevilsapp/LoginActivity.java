package webdevils.webdevilsapp;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.DialogInterface;
import android.app.AlertDialog.Builder;


public class LoginActivity extends AppCompatActivity {

    final String testUser = "user";
    final String testPassword = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button login = (Button) findViewById(R.id.button);
        final AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);


        // Gets username and password and turns them into strings for validation
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText inputName = (EditText) findViewById(R.id.userNameEntry);
                EditText inputPass = (EditText) findViewById(R.id.passwordEntry);
                String uName = inputName.getText().toString();
                String uPass = inputPass.getText().toString();
                if (validateUser(uName, uPass)) {
                    dlgAlert.setMessage("User authenticated.");
                    dlgAlert.setTitle("Verification");
                    dlgAlert.setPositiveButton("OK", null);
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();
                    dlgAlert.setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //dismiss the dialog
                                }
                            });
                    Intent i = new Intent(getApplicationContext(), LandingActvity.class);
                    startActivity(i);

                }else{
                    dlgAlert.setMessage("Username or password is incorrect.");
                    dlgAlert.setTitle("Verification");
                    dlgAlert.setPositiveButton("OK", null);
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();
                    dlgAlert.setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //dismiss the dialog
                                }
                            });
                }

            }
        });
    }

    //checks username and password submission against list of known users and credentials
    public boolean validateUser(String u, String p) {
        if(u.equals(testUser) && p.equals(testPassword)){
            return true;
        }else{
            return false;
        }
    }

}
