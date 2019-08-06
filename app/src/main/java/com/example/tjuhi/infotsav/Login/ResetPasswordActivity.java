package com.example.tjuhi.infotsav.Login;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tjuhi.infotsav.Login.models.ServerRequest;
import com.example.tjuhi.infotsav.Login.models.ServerResponse;
import com.example.tjuhi.infotsav.Login.models.User;
import com.example.tjuhi.infotsav.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Shivam on 6/18/2017.
 */

public class ResetPasswordActivity extends AppCompatActivity {

    private AppCompatButton btn_reset;
    private EditText et_email, et_code, et_password;
    private TextView tv_timer;
    private ProgressBar progress;
    private boolean isResetInitiated = false;
    private String email;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        btn_reset = (AppCompatButton) findViewById(R.id.btn_reset);
        tv_timer = (TextView) findViewById(R.id.timer);
        et_code = (EditText) findViewById(R.id.et_code);
        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);
        et_password.setVisibility(View.GONE);
        et_code.setVisibility(View.GONE);
        tv_timer.setVisibility(View.GONE);
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isResetInitiated) {


                    email = et_email.getText().toString();
                    if (!email.isEmpty()) {
                        progress.setVisibility(View.VISIBLE);
                        initiateResetPasswordProcess(email);
                    } else {

                        Toast.makeText(ResetPasswordActivity.this, "Fields are empty !", Toast.LENGTH_LONG).show();
                    }
                } else {

                    String code = et_code.getText().toString();
                    String password = et_password.getText().toString();

                    if (!code.isEmpty() && !password.isEmpty()) {

                        finishResetPasswordProcess(email, code, password);
                    } else {

                        Toast.makeText(ResetPasswordActivity.this, "Fields are empty !", Toast.LENGTH_LONG).show();
                    }

                }

            }
        });
        progress = (ProgressBar) findViewById(R.id.progress);
    }

    private void initiateResetPasswordProcess(String email) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        User user = new User();
        user.setEmail(email);
        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.RESET_PASSWORD_INITIATE);
        request.setUser(user);
        Call<ServerResponse> response = requestInterface.operation(request);

        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                ServerResponse resp = response.body();
                Toast.makeText(ResetPasswordActivity.this, resp.getMessage(), Toast.LENGTH_LONG).show();

                if (resp.getResult().equals(Constants.SUCCESS)) {

                    Toast.makeText(ResetPasswordActivity.this, resp.getMessage(), Toast.LENGTH_LONG).show();

                    et_email.setVisibility(View.GONE);
                    et_code.setVisibility(View.VISIBLE);
                    et_password.setVisibility(View.VISIBLE);
                    tv_timer.setVisibility(View.VISIBLE);
                    btn_reset.setText("Change Password");
                    isResetInitiated = true;
                    startCountdownTimer();

                } else {

                    Toast.makeText(ResetPasswordActivity.this, resp.getMessage(), Toast.LENGTH_LONG).show();


                }
                progress.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                progress.setVisibility(View.INVISIBLE);
                Log.d(Constants.TAG, "failed");
                Toast.makeText(ResetPasswordActivity.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();


            }
        });
    }

    private void finishResetPasswordProcess(String email, String code, String password) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        User user = new User();
        user.setEmail(email);
        user.setCode(code);
        user.setPassword(password);
        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.RESET_PASSWORD_FINISH);
        request.setUser(user);
        Call<ServerResponse> response = requestInterface.operation(request);

        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                ServerResponse resp = response.body();
                Toast.makeText(ResetPasswordActivity.this, resp.getMessage(), Toast.LENGTH_LONG).show();
                if (resp.getResult().equals(Constants.SUCCESS)) {

                    Toast.makeText(ResetPasswordActivity.this, resp.getMessage(), Toast.LENGTH_LONG).show();
                    countDownTimer.cancel();
                    isResetInitiated = false;
                    goToLogin();

                } else {

                    Toast.makeText(ResetPasswordActivity.this, resp.getMessage(), Toast.LENGTH_LONG).show();

                }
                progress.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                progress.setVisibility(View.INVISIBLE);
                Log.d(Constants.TAG, "failed");
                Toast.makeText(ResetPasswordActivity.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

    private void startCountdownTimer() {
        countDownTimer = new CountDownTimer(120000, 1000) {

            public void onTick(long millisUntilFinished) {
                tv_timer.setText("Time remaining : " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                Toast.makeText(ResetPasswordActivity.this, "Time Out ! Request again to reset password.", Toast.LENGTH_LONG).show();
                goToLogin();
            }
        }.start();
    }

    private void goToLogin() {

        startActivity(new Intent(ResetPasswordActivity.this, LoginActivity.class));
    }
}
