package com.mirea.kt.ribo.callbook;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;


public class MainActivity extends AppCompatActivity{
//    private TextView tvTextView;
//    private EditText etLogin;
//    private EditText etPassword;
//    private Button entrButton;
//
//    @Override
//    public void onClick(View v) {
//        if (v.getId() == R.id.entryButton) {
//            String etLoginText = etLogin.getText().toString();
//            String etPasswordText = etPassword.getText().toString();
//            String server = "https://android-for-students.ru";
//            String serverPath = "/coursework/login.php";
//            if(etLoginText.isEmpty() && etPasswordText.isEmpty()){
//                Toast.makeText(getApplicationContext(), "Invalid terms", Toast.LENGTH_SHORT).show();
//            }
//            else {
//                HashMap<String, String> map = new HashMap();
//                map.put("lgn", etLoginText);
//                map.put("pwd", etPasswordText);
//                map.put("g", "RIBO-05-22");
//                Log.d("TEST54", etLoginText);
//                Log.d("TEST55", etPasswordText);
//                HTTPRunnable httpRunnable = new HTTPRunnable(server + serverPath, map);
//                Thread th = new Thread(httpRunnable);
//                th.start();
//                try {
//                    th.join();
//                    JSONObject jsonObject = new JSONObject(httpRunnable.getReasponseBody());
//                    int result = jsonObject.getInt("result_code");
//                    Log.d("TEST56", String.valueOf(result));
//                    if(result == 1){
//                        tvTextView.setText("Добро пожаловать!");
//                    }else {
//                        tvTextView.setText("Ошибка, поробуйте еще раз");
//                    }
//                } catch (InterruptedException e) {
//                } catch (JSONException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }
//    }
private NavOptions options;
private NavController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(this);
        setContentView(R.layout.activity_main);
        EdgeToEdge.enable(this);

//        entrButton = findViewById(R.id.entryButton);
//        tvTextView = findViewById(R.id.tvTEST);
//        etLogin = findViewById(R.id.etLogin);
//        etPassword = findViewById(R.id.etPassword);
//        entrButton.setOnClickListener(this);

    }
}