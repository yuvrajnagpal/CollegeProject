package com.example.collegeproject;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.RegionIterator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.BreakIterator;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Formatter;

public class RegisterActivity extends AppCompatActivity {

    TextView loginText;
    Button signupButton,dob;
    EditText firstName,lastName,percentage,email,password;
    RequestQueue requestQueue;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);

        requestQueue= Volley.newRequestQueue(this);
        sp=getSharedPreferences("file1",0);
        editor=sp.edit();

        pd=new ProgressDialog(this);

        firstName=findViewById(R.id.first_name);
        lastName=findViewById(R.id.last_name);
        dob=findViewById(R.id.dob);
        percentage=findViewById(R.id.percent);
        email=findViewById(R.id.Remail_edittext);
        password=findViewById(R.id.Rpassword_edittext);


        loginText=findViewById(R.id.log_in);
        signupButton=findViewById(R.id.signup_btn);


        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));

                finish();



            }
        });

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDateTimeField();
            }
        });


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              register();
            }
        });
    }

    void register(){

        String url="http://searchkero.com/faizan_demo/register.php";
        pd.setMessage("Signing up");
        pd.show();

        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                toast("registerd succesfully");
                pd.dismiss();
                startActivity(new Intent(RegisterActivity.this,MainActivity.class));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                toast("not completed");
            }
        }


        ){


            @Override
            protected Map<String, String> getParams()  {
                HashMap<String,String> params=new HashMap<>();

                params.put("fnameid",firstName.getText().toString().trim());
                params.put("lnameid",lastName.getText().toString().trim());
                params.put("dobid",dob.getText().toString().trim());
                params.put("percentageid",percentage.getText().toString().trim());
                params.put("emailid",email.getText().toString().trim());
                params.put("passwordid",password.getText().toString().trim());


                editor.putString("firstname",firstName.getText().toString());
                editor.putString("lastname",lastName.getText().toString());
                editor.putString("dob",dob.getText().toString());
                editor.putString("percentage",percentage.getText().toString());
                editor.putString("email",email.getText().toString());
                editor.putString("password",password.getText().toString());






                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }

        };


        requestQueue.add(request);



    }


    void toast(String message){

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    private void setDateTimeField() {
        final Calendar dateSelected = Calendar.getInstance();

        Calendar newCalendar = dateSelected;
        final DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy");
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                dateSelected.set(year, monthOfYear, dayOfMonth, 0, 0);

                dob.setText(df.format (dateSelected.getTime()));
                Toast.makeText(RegisterActivity.this, dob.getText().toString(), Toast.LENGTH_SHORT).show();
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        dob.setText(df.format(dateSelected.getTime()));

        datePickerDialog.show();
    }

}
