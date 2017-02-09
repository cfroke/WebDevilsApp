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


public class LoginActivity extends AppCompatActivity {

    final String testUser = "user";
    final String testPassword = "password";
    User testUSER;

    private String serverIP = "localhost";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //starts server via the command line (should be run first)
        //Example:
        //C:/ ... /WebDevilsApp/server/dist>java -jar WebDevilsServer.jar

        //connect to server ...
        //new Conn().execute();

        //Work around for now....
        Services services = new Services();
        testUSER = services.createMemberUser(testUser , testPassword);

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
                    i.putExtra("userName", uName); //sending username to landing page
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
        if( testUSER.getUserName().equals(u) && testUSER.tryPassword(p)){
            return true;
        }else{
            return false;
        }
    }

    //connect to server to get test user ...
    class Conn extends AsyncTask<Void, Void, LoginActivity> {

        @Override
        protected LoginActivity doInBackground(Void... params) {
            Looper.prepare();
            try {
                CallHandler callHandler = new CallHandler();
                Client client = new Client(serverIP, 8080, callHandler);
                IServices services = (IServices) client.getGlobal(IServices.class);

                //create new user
                testUSER = services.createMemberUser(testUser, testPassword);
                System.out.println(testUSER.getUserName());

                //validate user
                User validatedUser = services.validateUser(testUser,testPassword);
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
