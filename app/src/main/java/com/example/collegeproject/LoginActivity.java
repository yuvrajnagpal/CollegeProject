package com.example.collegeproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.onesignal.OSNotification;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import io.realm.Realm;
import jp.wasabeef.blurry.Blurry;

public class LoginActivity extends AppCompatActivity {



    TextView signupText;
    Button loginButton;
    EditText email,password;
    RequestQueue requestQueue;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    Realm realm;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);
        Realm.init(this);
        realm=Realm.getDefaultInstance();
        pd=new ProgressDialog(this);

        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .setNotificationReceivedHandler(new OneSignal.NotificationReceivedHandler() {
                    @Override
                    public void notificationReceived(OSNotification notification) {
                        JSONObject data = notification.payload.additionalData;
                        if (data != null) {
                            String title = data.optString("title", null);
                            String content = data.optString("content", null);
                            DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
                            String date = df.format(Calendar.getInstance().getTime());


                            addtoRealm(title,content,date);
                        }
                    }
                })
                .init();

        requestQueue= Volley.newRequestQueue(this);
        sp=getSharedPreferences("file2",0);
        editor=sp.edit();

        email=findViewById(R.id.lemail_edittext);
        password=findViewById(R.id.lpassword_edittext);


        signupText=findViewById(R.id.sign_up);
        loginButton=findViewById(R.id.login_btn);


        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                finish();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();

            }
        });


    }


    void login(){
        String url="http://searchkero.com/faizan_demo/login.php";

        pd.setMessage("Loging in");
        pd.show();

        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                toast("registerd successfuly");
                pd.dismiss();

                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                finish();

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


                params.put("emailid",email.getText().toString());
                params.put("passwordid",password.getText().toString());

                editor.putString("emailid",email.getText().toString());
                editor.putString("passwordid",password.getText().toString());
                editor.apply();

                return params;
            }



        };

        requestQueue.add(request);

    }

    void addtoRealm(String title,String content,String date){

        realm.beginTransaction();
        Notificationn notificationn=realm.createObject(Notificationn.class);
        notificationn.setTitle(title);
        notificationn.setContent(content);
        notificationn.setTime(date);
        realm.commitTransaction();

    }


    void toast(String message){

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
