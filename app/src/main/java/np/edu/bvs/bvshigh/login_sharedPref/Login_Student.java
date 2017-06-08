package np.edu.bvs.bvshigh.login_sharedPref;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import np.edu.bvs.bvshigh.Constants;
import np.edu.bvs.bvshigh.Dashboard;
import np.edu.bvs.bvshigh.Main_activity;
import np.edu.bvs.bvshigh.R;

public class Login_Student extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextUsername, editTextPassword;
    private Button log_in;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        // Checking if user is logged in -> profile is displayed
        if (SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(getApplicationContext(), Dashboard.class));
            return;
        }

        log_in = (Button)findViewById(R.id.log_in);
        //Button register = (Button) findViewById(R.id.register);

        editTextUsername = (EditText)findViewById(R.id.editTextUsername);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");

        log_in.setOnClickListener(this);
        //register.setOnClickListener(this);

    }

    private void userLogin(){

        final String username = editTextUsername.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        progressDialog.show();
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_LOGIN,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressDialog.dismiss();
                        try {

                            // creating JSONobject to get data
                            JSONObject object = new JSONObject(response);
                            if (!object.getBoolean("error")){

                                // getting details from sharedPref activity
                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(
                                        object.getInt("id"),
                                        object.getString("username"),
                                        object.getString("email"),
                                        object.getString("fullname"),
                                        object.getString("grade"),
                                        object.getString("sec"),
                                        object.getString("branch"));

                                Toast.makeText(getApplicationContext(), "User Login Successful!",
                                        Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), Dashboard.class));
                                finish();

                            }else {
                                Toast.makeText(getApplicationContext(), "Username or Password is wrong. Please try again",
                                        Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Can't Connect. Please try again.",
                                Toast.LENGTH_LONG).show();
                    }
                })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);

                return params;
            }
        };

        Request_Queue_Handler.getInstance(this).addToRequestQueue(stringRequest);

    }

    @Override
    public void onClick(View view) {
        if (view == log_in){
            userLogin();
        }else
            startActivity(new Intent(getApplicationContext(), Register_Student.class));
    }
}