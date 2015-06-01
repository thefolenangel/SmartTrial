package com.example.tfa.smarttrial;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;


public class MainActivity extends ActionBarActivity {

    final String ENDPOINT = "https://test-api.smart-trial.dk/api";
    final String pathwayId = "5566e151817a62021b1ea809";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /* As per requirements the origin of the requrest must be defined*/
         final RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("User-Agent", "Smart-Trial-App");
            }
        };
        /*Setting the button and the listner*/
       Button login = (Button) findViewById(R.id.button);
       login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /*Getting String values from the fields*/
                TextView fn = (TextView) findViewById(R.id.firstname);
                TextView ln = (TextView) findViewById(R.id.lastname);
                TextView em = (TextView) findViewById(R.id.email);
                String firn = fn.getText().toString();
                String last = ln.getText().toString();
                String email = em.getText().toString();

                /*Setting the adapter for comunication between the server and the app
                * +added an extra loglevel for debugging purposes*/
                RestAdapter restAdapter = new RestAdapter.Builder()
                        .setEndpoint(ENDPOINT)
                        .setRequestInterceptor(requestInterceptor)
                        .setLogLevel(RestAdapter.LogLevel.FULL)
                        .build();

                SmartTrialAPI api = restAdapter.create(SmartTrialAPI.class);

               /*Sending the info needed based on the API*/
                api.createUser(pathwayId, new User(firn, last, email), new retrofit.Callback<String>() {
                    @Override
                    public void success(String s, retrofit.client.Response response) {
                       // System.out.println("YESSSSSSSSS" Response);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                       // System.out.println("Fail in the life of the programmer!" + error);
                    }
                });
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
