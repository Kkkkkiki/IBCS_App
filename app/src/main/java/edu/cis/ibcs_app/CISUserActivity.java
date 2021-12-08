package edu.cis.ibcs_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import edu.cis.ibcs_app.Controllers.CISAdapter;
import edu.cis.ibcs_app.Models.Request;
import edu.cis.ibcs_app.Models.SimpleClient;
import edu.cis.ibcs_app.Utils.CISConstants;

import androidx.recyclerview.widget.RecyclerView;

public class CISUserActivity extends AppCompatActivity {
    RecyclerView recView;
    String[] allStuff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cisuser);

        this.recView = findViewById(R.id.recView);

        //👮‍👮‍👮‍👮‍👮‍👮‍👮‍👮‍👮‍LOOK HERE!!!!!!! 👮‍👮‍👮‍👮‍👮‍👮‍👮‍👮‍👮‍👮‍👮‍👮‍👮‍👮
        //1. We are not yet logged in, where can we update the userid?
        //3. This page crashes

        //we give CIS adapter the data, and the data will pass through constructor, then the constructor will goto
        //CISAdapter's arraylist
        //Then the Arraylist will be used by the view holder binder for whatever index
        this.askServerForMenu(this.recView);
        CISAdapter myAdapter = new CISAdapter(allStuff);
        recView.setAdapter(myAdapter);
        recView.setLayoutManager(new LinearLayoutManager(this));
    }

    //This activity is meant to be used by students. It will have a UI that displays current items and lets students order.
    //This is for recycler view to ask for menuItems
    public void askServerForMenu(View v) {
        try {//make a request
            Request askMenuRequest = new Request(CISConstants.GET_MENU);

            //send request, save them into all stuff
            String menuResultFromServer = SimpleClient.makeRequest(CISConstants.HOST, askMenuRequest);
            System.out.println("MENU: "+ menuResultFromServer);
//            String menuResultFromServer = "Hamburger|tasty|10.0|10|lunch|abcd0;french fries|tasty|9.0|10|breakfast|efgh1;Hamburger|tasty|10.0|10|lunch|abcd0;french fries|tasty|9.0|10|breakfast|efgh1;Hamburger|tasty|10.0|10|lunch|abcd0;french fries|tasty|9.0|10|breakfast|efgh1;Hamburger|tasty|10.0|10|lunch|abcd0;french fries|tasty|9.0|10|breakfast|efgh1;Hamburger|tasty|10.0|10|lunch|abcd0;french fries|tasty|9.0|10|breakfast|efgh1;Hamburger|tasty|10.0|10|lunch|abcd0;french fries|tasty|9.0|10|breakfast|efgh1";
            String[] parts = menuResultFromServer.split(";");

            this.allStuff = parts;
        }
        catch (Exception err) {
            Toast errorServerAsk = Toast.makeText(this, "ARGHGHGHGH......" + err.toString(), Toast.LENGTH_LONG);
            errorServerAsk.show();
        }
    }

    public void placeSomeOrder(View view) throws IOException {
        try {
            //make a request
            Request placeOrderRequest = new Request(CISConstants.PLACE_ORDER);

            //TBD, do we have to send parameters?
//    placeOrderRequest.addParam(CISConstants.ORDER_ID_PARAM, userIdTextbox.getText().toString());
//    placeOrderRequest.addParam(CISConstants.ORDER_TYPE_PARAM, userNameTextbox.getText().toString());
//    placeOrderRequest.addParam(CISConstants.ITEM_ID_PARAM,);

            //send request to URL
            String placeOrderResult = SimpleClient.makeRequest(CISConstants.HOST, placeOrderRequest);

            //Check the return message from the server
            Toast orderMessageToPlaceOrder = Toast.makeText(this, "Order has been placed successfully!" + placeOrderResult, Toast.LENGTH_LONG);

            //Give appropriate message to user
            orderMessageToPlaceOrder.show();
        } catch (Exception err) {
            Toast errorOrderMessageToUser = Toast.makeText(this, "and I whoop..." + err.toString(), Toast.LENGTH_LONG);
            errorOrderMessageToUser.show();
        }
    }
    public void getOrders(View view){
        try {
            //create request by command getOrders
            Request orderRequest = new Request(CISConstants.GET_ORDER);

            //send request to URL
            String serverResultForGetOrder = SimpleClient.makeRequest(CISConstants.HOST, orderRequest);
            System.out.println("result from server is: " + serverResultForGetOrder);

            //Check the return message from the server
            Toast messageToUser = Toast.makeText(this, "User has been created successfully!" + serverResultForGetOrder, Toast.LENGTH_LONG);

            //Give appropriate message to user
            messageToUser.show();

        } catch (Exception err) {
            Toast errorMessageToUser = Toast.makeText(this, "and I whoop..." + err.toString(), Toast.LENGTH_LONG);
            errorMessageToUser.show();
        }
    }

    public void deleteOrders(View view){
        try{
            Request deleteOrderRequest = new Request(CISConstants.DELETE_ORDER);

            EditText deleteOrderIDTextbox = (EditText)findViewById(R.id.deleteorder);
            deleteOrderRequest.addParam(CISConstants.ORDER_ID_PARAM, deleteOrderIDTextbox.getText().toString());

            String deleteResult = SimpleClient.makeRequest(CISConstants.HOST, deleteOrderRequest);

            Toast deleteMessage = Toast.makeText(this, "Deleted succesfully!" + deleteResult, Toast.LENGTH_LONG);
            deleteMessage.show();
        }
        catch(Exception err){
            Toast errMessage = Toast.makeText(this, "and I whoop..." + err.toString(), Toast.LENGTH_LONG);
            errMessage.show();
        }
    }


}