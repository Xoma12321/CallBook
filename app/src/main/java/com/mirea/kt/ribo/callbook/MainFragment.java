package com.mirea.kt.ribo.callbook;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;

import com.mirea.kt.ribo.callbook.databinding.FragmentMainBinding;
import com.mirea.kt.ribo.callbook.databinding.FragmentStartBinding;

import java.util.ArrayList;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MainFragment extends Fragment {

    private SearchView etSearch;
    private Button  cncButton;
    private FragmentMainBinding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        NavController controller = Navigation.findNavController(requireActivity(), R.id.fragment_container_view);
        View view = binding.getRoot();
        Realm realm = Realm.getDefaultInstance();

        RealmResults<Contact> contacts = realm.where(Contact.class).findAll();
        Log.d("qwq",contacts.toString());
        RecyclerView rcView = binding.recyclerView;
        ContactAdapter adapter = new ContactAdapter(contacts,controller);
        rcView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL,
                false));
        rcView.setAdapter(adapter);
        cncButton = binding.cncButton;
        cncButton.setOnClickListener(v -> {
            controller.navigate(R.id.createNewContact);
        });
//        etSearch = binding.etSearch;
//        RealmResults<Contact> searchResults = searchContacts(etSearch.toString());


        return view;
    }
//    public RealmResults<Contact> searchContacts(String query) {
//        Realm realm = Realm.getDefaultInstance();
//        // Выполните запрос к базе данных для поиска контактов по заданному запросу
//        RealmQuery<Contact> queryBuilder = realm.where(Contact.class);
//        queryBuilder.contains("name", query, Case.INSENSITIVE); // Ищем по имени (регистронезависимый поиск)
//        RealmResults<Contact> results = queryBuilder.findAll();
//        return results;
//    }
}