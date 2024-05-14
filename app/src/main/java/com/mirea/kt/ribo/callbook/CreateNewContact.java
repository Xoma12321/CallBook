package com.mirea.kt.ribo.callbook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.mirea.kt.ribo.callbook.databinding.FragmentCreateBinding;

import io.realm.Realm;

public class CreateNewContact extends Fragment {
    private Button backBtn;
    private Button createBtn;
    private EditText etName;
    private EditText etPhone;

    private FragmentCreateBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        binding = FragmentCreateBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        Realm realm = Realm.getDefaultInstance();
        NavController controller = Navigation.findNavController(requireActivity(), R.id.fragment_container_view);
        backBtn = binding.backBtn;
        createBtn = binding.createBtn;
        etName = binding.etCreateName;
        etPhone = binding.etCreateNumber;
        createBtn.setOnClickListener(v -> {
            String etCreateNumber = etPhone.getText().toString();
            String etCreateName = etName.getText().toString();
            if(etCreateName.isEmpty() || etCreateNumber.isEmpty()) {
                Toast.makeText(getActivity(),"Invalid terms", Toast.LENGTH_SHORT).show();
            }else {
                realm.beginTransaction();
                Contact contact = realm.createObject(Contact.class);
                contact.setContactName(etCreateName);
                contact.setContactPhoneNumber(etCreateNumber);
                Toast.makeText(getActivity(), "Контакт успешно добавлен", Toast.LENGTH_SHORT).show();
                controller.navigate(R.id.mainFragment);
                realm.commitTransaction();
                realm.close();
            }
        });
        backBtn.setOnClickListener(v -> {
            controller.navigate(R.id.mainFragment);
        });

        return view;
    }
}
