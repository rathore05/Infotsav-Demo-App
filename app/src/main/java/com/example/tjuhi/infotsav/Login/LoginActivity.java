package com.example.tjuhi.infotsav.Login;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tjuhi.infotsav.Login.models.ServerRequest;
import com.example.tjuhi.infotsav.Login.models.ServerResponse;
import com.example.tjuhi.infotsav.Login.models.User;
import com.example.tjuhi.infotsav.MainActivity;
import com.example.tjuhi.infotsav.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Shivam on 6/18/2017.
 */

public class LoginActivity extends AppCompatActivity {

    private ProgressBar progress;
    private SharedPreferences pref1;
    public static String s;

    EditText etUsername;

    EditText etPassword;

    TextView res_Password;

    Button btGo1;

    CardView cv;

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pref1=getSharedPreferences("pref", Context.MODE_PRIVATE);
        if ((pref1.getBoolean(Constants.IS_LOGGED_IN,false))){
            startActivity(new Intent(LoginActivity.this,LoginSuccessActivity.class));
        }



        etUsername = (EditText) findViewById(R.id.et_email);
        etPassword = (EditText) findViewById(R.id.et_password);
        res_Password = (TextView) findViewById(R.id.tv_reset_password);
        btGo1 = (Button) findViewById(R.id.btn_login);
        cv = (CardView) findViewById(R.id.cv);
        fab = (FloatingActionButton) findViewById(R.id.fab);


        pref1 = getSharedPreferences("pref", Context.MODE_PRIVATE);
        progress = (ProgressBar) findViewById(R.id.progress);

        res_Password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegister();
            }
        });
        btGo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                if (!email.isEmpty() && !password.isEmpty()) {

                    progress.setVisibility(View.VISIBLE);
                    loginProcess(email, password);

                } else {

                    Toast.makeText(LoginActivity.this, "Fields are empty !", Toast.LENGTH_LONG).show();
                }
            }
        });

    }




    private void loginProcess(String email, String password) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.LOGIN_OPERATION);
        request.setUser(user);
        Call<ServerResponse> response = requestInterface.operation(request);

        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                ServerResponse resp = response.body();
                Toast.makeText(LoginActivity.this, resp.getMessage(), Toast.LENGTH_LONG).show();

                if (resp.getResult().equals(Constants.SUCCESS)) {
                    SharedPreferences.Editor editor = pref1.edit();
                    editor.putBoolean(Constants.IS_LOGGED_IN, true);
                    editor.putString(Constants.EMAIL, resp.getUser().getEmail());
                    editor.putString(Constants.NAME, resp.getUser().getName());
                    editor.putString(Constants.UNIQUE_ID, resp.getUser().getUnique_id());
                    editor.apply();
                    s = pref1.getString(Constants.NAME, "");
                    goToProfile();

                }
                progress.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                progress.setVisibility(View.INVISIBLE);
                Log.d(Constants.TAG, "failed");
                Toast.makeText(LoginActivity.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }


    private void goToRegister() {

        getWindow().setExitTransition(null);
        getWindow().setEnterTransition(null);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options =
                    ActivityOptions.makeSceneTransitionAnimation(this, fab, fab.getTransitionName());
            startActivity(new Intent(this, RegisterActivity.class), options.toBundle());
        } else {
            startActivity(new Intent(this, RegisterActivity.class));
        }
    }

    private void goToProfile() {

        Explode explode = new Explode();
        explode.setDuration(500);

        getWindow().setExitTransition(explode);
        getWindow().setEnterTransition(explode);
        ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
        Intent i2 = new Intent(this, LoginSuccessActivity.class);
        startActivity(i2, oc2.toBundle());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(LoginActivity.this,MainActivity.class));
    }
}
