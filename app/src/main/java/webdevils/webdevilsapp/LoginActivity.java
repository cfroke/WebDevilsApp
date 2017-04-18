/**
 *    SER 401 / 402 -- Senior Project -- WebDevils -- Project 11
 */
package webdevils.webdevilsapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

import common.IServices;
import common.User;
import lipermi.handler.CallHandler;
import lipermi.net.Client;
import server.Services;
import server.TestData;
import webdevils.webdevilsapp.Employee.EmpMainActivity;

/**
 * The login activity is the first page seen when accessing
 * the application. User must be present in the server in order
 * to login. Users are created under server.TestData
 */
public class LoginActivity extends AppCompatActivity {

    private final Services services = new Services();
    //Initialize Test Data
    //Do Not Instantiate Anywhere Else
    TestData data = TestData.getInstance();
    public static User currentUser;

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
                    if(currentUser.isMember()){
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        i.putExtra("userName", uName); //sending username to main page
                        i.putExtra("userObject", currentUser); // temp passing of object
                        startActivity(i);
                    }else if(currentUser.isEmployee()){
                        Intent i = new Intent(getApplicationContext(), EmpMainActivity.class);
                        i.putExtra("userName", uName); //sending username to main page
                        i.putExtra("userObject", currentUser); // temp passing of object
                        startActivity(i);
                    }


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

    /**
     * Checks username and password submission against list of known users and credentials
     * @param u String
     * @param p String
     * @return boolean
     */
    private boolean validateUser(String u, String p) {
        currentUser = services.validateUser( u , p );
        if(currentUser != null){
            return currentUser.getUserName().equals(u) && currentUser.tryPassword(p);
        }else{
            return false;
        }
    }

    /**
     * Example server call
     * connect to server to get test user ...
     */
    private class Conn extends AsyncTask<Void, Void, LoginActivity> {

        @Override
        protected LoginActivity doInBackground(Void... params) {
            Looper.prepare();
            try {
                CallHandler callHandler = new CallHandler();
                String serverIP = "localhost";
                Client client = new Client(serverIP, 8080, callHandler);
                IServices Services = (IServices) client.getGlobal(IServices.class);

                //create new user
                User testUSER = Services.createMemberUser("user", "password");
                System.out.println(testUSER.getUserName());

                //validate user
                User validatedUser = Services.validateUser("user","password");
                if(validatedUser != null){
                    System.out.println(validatedUser.getUserName() + " Has been validated and returned from the server!");
                }else{
                    System.out.println("Server returned null meaning that the user name already exists, or an invalid" +
                            " password was used ...");
                }
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Looper.loop();
            return null;
        }
    }
}