package com.mirea.kt.ribo.callbook;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.mirea.kt.ribo.callbook.databinding.FragmentContactBinding;

import io.realm.Realm;

public class OpenContactView extends Fragment {
    private FragmentContactBinding binding;
    private Button backBtn;
    private Button deleteBtn;
    private Button refactorBtn;
    private Button shareBtn;
    private TextView tvContactName;
    private TextView tvContactPhone;
    private ImageView ivPersonPhoto;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentContactBinding.inflate(inflater, container, false);
        NavController controller = Navigation.findNavController(requireActivity(), R.id.fragment_container_view);
        View view = binding.getRoot();
        tvContactName = binding.tvPersonName;
        tvContactPhone = binding.tvPersonNumber;
        deleteBtn = binding.deleteBtn;
        refactorBtn = binding.refactorBtn;
        shareBtn = binding.shareBtn;
        ivPersonPhoto = binding.personPhoto;
        Bundle bundle = getArguments();
        String contactName = bundle.getString("name");
        String contactPhone = bundle.getString("number");
        String contactPhoto  = bundle.getString("avatar");
        if (bundle != null) {
            tvContactName.setText(contactName);
            tvContactPhone.setText(contactPhone);
        }
//        if (contactPhoto!=null){
//            Glide.with(this).load(contactPhoto).into(ivPersonPhoto);
//        }else {
//            Glide.with(this).load("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='1em' height='1em' viewBox='0 0 36 36'%3E%3Cpath fill='%23000' d='M18 17a7 7 0 1 0-7-7a7 7 0 0 0 7 7m0-12a5 5 0 1 1-5 5a5 5 0 0 1 5-5' class='clr-i-outline clr-i-outline-path-1'/%3E%3Cpath fill='%23000' d='M30.47 24.37a17.16 17.16 0 0 0-24.93 0A2 2 0 0 0 5 25.74V31a2 2 0 0 0 2 2h22a2 2 0 0 0 2-2v-5.26a2 2 0 0 0-.53-1.37M29 31H7v-5.27a15.17 15.17 0 0 1 22 0Z' class='clr-i-outline clr-i-outline-path-2'/%3E%3Cpath fill='none' d='M0 0h36v36H0z'/%3E%3C/svg%3E").into(ivPersonPhoto);
//        }
        backBtn = binding.backBtn;
        backBtn.setOnClickListener(v -> {
            controller.navigate(R.id.mainFragment);
        });
        deleteBtn.setOnClickListener(v -> {
            showDeleteConfirmationDialog();
        });
        shareBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT,contactName +"\n"+ contactPhone);
            intent.setType("text/plain");
            startActivity(intent);
        });
        return view;
    }

    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        NavController controller = Navigation.findNavController(requireActivity(), R.id.fragment_container_view);
        builder.setMessage("Вы действительно хотите удалить контакт?")
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        deleteContact();
                        controller.navigate(R.id.mainFragment);
                    }
                })
                .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void deleteContact() {
        Bundle bundle = getArguments();
        Realm realm = Realm.getDefaultInstance();
        String ContactName = bundle.getString("name");
        if (ContactName != null) {
            realm.beginTransaction();
            Contact contactToDelete = realm.where(Contact.class)
                    .equalTo("contactName",ContactName)
                    .findFirst();
            Log.d("delete", ContactName);
            contactToDelete.deleteFromRealm();
            realm.commitTransaction();
            realm.close();
        } else{
            Toast.makeText(getActivity(),"Ошибка",Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(getActivity(), "Контакт удален", Toast.LENGTH_SHORT).show();
    }
}

