package edu.cis.ibcs_app.Controllers;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import edu.cis.ibcs_app.CISUserActivity;
import edu.cis.ibcs_app.Models.Request;
import edu.cis.ibcs_app.Models.SimpleClient;
import edu.cis.ibcs_app.R;
import edu.cis.ibcs_app.Utils.CISConstants;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //This is not great, for extra credit you can fix this so that network calls happen on a different thread
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_main);
    }

    //ping your server from main activity
    //This will make sure that your app and server can talk to each other.
    //MainActivity is meant to be the interface for the CISEatrium admin staff.
    public void pingServer(View v) {
        try {
            //create request by command ping
            Request egRequest = new Request("ping");

            //send request to URL
            String serverResult = SimpleClient.makeRequest(CISConstants.HOST, egRequest);
            System.out.println("result from server is: " + serverResult);

        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    //Create CIS USERS AND MENU ITEMS FROM THE CLIENT
    public void createUserID(View view) {
        try {
            //Make a request
            Request userIDRequest = new Request(CISConstants.CREATE_USER);

            EditText userNameTextbox = (EditText) findViewById(R.id.nameHere);
            EditText userIdTextbox = (EditText) findViewById(R.id.userIDHere);

            userIDRequest.addParam(CISConstants.USER_ID_PARAM, userIdTextbox.getText().toString());
            userIDRequest.addParam(CISConstants.USER_NAME_PARAM, userNameTextbox.getText().toString());
            userIDRequest.addParam(CISConstants.YEAR_LEVEL_PARAM, "10");

            //send request to URL
            String serverResult = SimpleClient.makeRequest(CISConstants.HOST, userIDRequest);
            System.out.println("result from server is: " + serverResult);

            //Send request to the host
//           String createUserIDResult = SimpleClient.makeRequest(CISConstants.CREATE_USER, userIDRequest);

            //Check the return message from the server
            Toast messagetoUser = Toast.makeText(this, "User has been created successfully!" + serverResult, Toast.LENGTH_LONG);

            //Give appropriate message to user
            messagetoUser.show();

        } catch (Exception err) {
            Toast errorMessagetoUser = Toast.makeText(this, "and I whoop..." + err.toString(), Toast.LENGTH_LONG);
            errorMessagetoUser.show();
        }
    }

    public void createMenuItem(View view) {
        try {
            //make a request
            Request menuItemRequest = new Request(CISConstants.ADD_MENU_ITEM);

            EditText menuItemName = (EditText) findViewById(R.id.buttonMenuItemName);
            EditText menuItemDescription = (EditText) findViewById(R.id.buttonMenuItemDescription);
            EditText menuItemPrice = (EditText) findViewById(R.id.buttonMenuItemPrice);
            EditText menuItemType = (EditText) findViewById(R.id.buttonMenuItemType);
            EditText menuItemID = (EditText) findViewById(R.id.buttonMenuItemID);

            menuItemRequest.addParam(CISConstants.ITEM_NAME_PARAM, menuItemName.getText().toString());
            menuItemRequest.addParam(CISConstants.DESC_PARAM, menuItemDescription.getText().toString());
            menuItemRequest.addParam(CISConstants.PRICE_PARAM, menuItemPrice.getText().toString());
            menuItemRequest.addParam(CISConstants.ITEM_TYPE_PARAM, menuItemType.getText().toString());
            menuItemRequest.addParam(CISConstants.ITEM_ID_PARAM, menuItemID.getText().toString());

            //send request to URL
            String serverResult = SimpleClient.makeRequest(CISConstants.HOST, menuItemRequest);
            System.out.println("result from server is: " + serverResult);

            //Check the return message from the server
            Toast menuItemMessage = Toast.makeText(this, "Item has been added successfully!" + serverResult, Toast.LENGTH_LONG);

            //Give appropriate message to user
            menuItemMessage.show();

        } catch (Exception err) {
            Toast errorMenuItem = Toast.makeText(this, "and I whoop..." + err.toString(), Toast.LENGTH_LONG);
            errorMenuItem.show();
        }
    }

    public void getUser(View view){
        try {
            //make a request
            Request getUserRequest = new Request(CISConstants.GET_USER);

            EditText getUserText = (EditText) findViewById(R.id.getUser);

            getUserRequest.addParam(CISConstants.USER_ID_PARAM, getUserText.getText().toString());

            //send request to URL
            String serverResultforUser = SimpleClient.makeRequest(CISConstants.HOST, getUserRequest);
            System.out.println("result from server is: " + serverResultforUser);

            //Check the return message from the server
            Toast userMessage = Toast.makeText(this, "Item has been added successfully!" + serverResultforUser, Toast.LENGTH_LONG);

            //Give appropriate message to user
            userMessage.show();

        } catch (Exception err) {
            Toast errorGetUser = Toast.makeText(this, "and I whoop..." + err.toString(), Toast.LENGTH_LONG);
            errorGetUser.show();
        }
    }
}