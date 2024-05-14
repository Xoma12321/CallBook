package com.mirea.kt.ribo.callbook;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.mirea.kt.ribo.callbook.databinding.FragmentStartBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import io.realm.Realm;
import io.realm.RealmResults;

public class StartFragment extends Fragment {


    private TextView tvTextView;
    private EditText etLogin;
    private EditText etPassword;
    private Button entrButton;
    private FragmentStartBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentStartBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Contact> contacts = realm.where(Contact.class).findAll();
        NavController controller = Navigation.findNavController(requireActivity(), R.id.fragment_container_view);
        entrButton = binding.entryButton;
        tvTextView = binding.tvTEST;
        etLogin = binding.etLogin;
        etPassword = binding.etPassword;
        entrButton.setOnClickListener(v -> {
            String etLoginText = etLogin.getText().toString();
            String etPasswordText = etPassword.getText().toString();
            String server = "https://android-for-students.ru";
            String serverPath = "/coursework/login.php";
            if (etLoginText.isEmpty() || etPasswordText.isEmpty()) {
                Toast.makeText(getActivity(), "Invalid terms", Toast.LENGTH_SHORT).show();
            } else {
                HashMap<String, String> map = new HashMap<>();
                map.put("lgn", etLoginText);
                map.put("pwd", etPasswordText);
                map.put("g", "RIBO-05-22");
                Log.d("TEST54", etLoginText);
                Log.d("TEST55", etPasswordText);
                HTTPRunnable httpRunnable = new HTTPRunnable(server + serverPath, map);
                Thread th = new Thread(httpRunnable);
                th.start();
                try {
                    th.join();
                    JSONObject jsonObject = new JSONObject(httpRunnable.getReasponseBody());
                    int result = jsonObject.getInt("result_code");
                    if (!SharedPrefs.getBool(requireActivity(), "data_ready_state")) {
                        JSONArray jsonArray = jsonObject.getJSONArray("data");

                        realm.beginTransaction();
                        try {

                            // Перебираем элементы JSONArray
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObjectArray = jsonArray.getJSONObject(i);

                                // Создаем объект модели данных Realm
                                Contact contact = realm.createObject(Contact.class);

                                // Заполняем поля объекта значениями из JSONObject
                                contact.setContactPhoneNumber(jsonObjectArray.getString("phone"));
                                contact.setContactName(jsonObjectArray.getString("name"));
                                contact.setContactAvatar(jsonObjectArray.getString("avatar"));
                            }
                            // Завершаем транзакцию
                            realm.commitTransaction();
                        } catch (JSONException e) {
                            // Обработка ошибок при парсинге JSON
                            e.printStackTrace();
                            // Откатываем транзакцию в случае ошибки
                            realm.cancelTransaction();
                        } finally {
                            SharedPrefs.saveBool(requireActivity(), "data_ready_state", true);
                            // Закрываем экземпляр Realm
                            realm.close();
                        }
                    }
                    Log.d("TEST57", String.valueOf(result));
                    if (result == 1) {
                        tvTextView.setText("Добро пожаловать!");
                        controller.navigate(R.id.mainFragment);
                    } else {
                        tvTextView.setText("Ошибка, поробуйте еще раз");
                    }
                } catch (InterruptedException e) {
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        return view;
    }

}