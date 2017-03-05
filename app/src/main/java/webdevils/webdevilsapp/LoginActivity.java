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


public class LoginActivity extends AppCompatActivity {

    Services services = new Services();
    //Initialize Test Data
    //Do Not Instantiate Anywhere Else
    TestData data = TestData.getInstance();
    User currentUser;
    private String serverIP = "localhost";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //starts server via the command line (should be run first)
        //NOTE: Android Studio emulator cannot connect to remote server!
        //Example:
        //C:/ ... /WebDevilsApp/server/dist>java -jar WebDevilsServer.jar

        //connect to server ...
        //new Conn().execute();

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

    //checks username and password submission against list of known users and credentials
    public boolean validateUser(String u, String p) {
        currentUser = services.validateUser( u , p );
        if(currentUser != null){
            if( currentUser.getUserName().equals(u) && currentUser.tryPassword(p)) {
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    //Example server call
    //connect to server to get test user ...
    class Conn extends AsyncTask<Void, Void, LoginActivity> {

        @Override
        protected LoginActivity doInBackground(Void... params) {
            Looper.prepare();
            try {
                CallHandler callHandler = new CallHandler();
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