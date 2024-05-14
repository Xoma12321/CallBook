package com.mirea.kt.ribo.callbook;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import io.realm.RealmResults;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    private RealmResults<Contact> contacts;
//    interface OnContactClickListener{
//        void onContactClick(Contact contact, int position);
//    }

    private NavController controller;

    //private final OnContactClickListener onClickListener;
    public ContactAdapter(RealmResults<Contact> contacts, NavController controller) {//,OnContactClickListener OnClickListener){
        this.contacts = contacts;
        this.controller = controller;
        //this.onClickListener = OnClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameView;
        private final TextView numberView;
        private final CircleImageView avaterView;

        ViewHolder(View view) {
            super(view);
            nameView = view.findViewById(R.id.tvPersonName);
            numberView = view.findViewById(R.id.tvPersonNumber);
            avaterView = view.findViewById(R.id.profile_image);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.nameView.setText(contact.getContactName());
        holder.numberView.setText(contact.getContactPhoneNumber());

        holder.itemView.setOnClickListener(v -> {

            Bundle bundle = new Bundle();
            bundle.putString("name", contact.getContactName());
            bundle.putString("number", contact.getContactPhoneNumber());
            bundle.putString("avatar", contact.getContactAvatar());
            controller.navigate(R.id.openContactView, bundle);

//        holder.avaterView.setImageDrawable(Drawable.createFromPath(contact.getContactAvatar()));'

        });
        holder.itemView.setOnLongClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            Log.d("phoneDO", contact.getContactPhoneNumber());
            intent.setData(Uri.parse("tel:" + contact.getContactPhoneNumber()));
            Log.d("phonePOSLE", contact.getContactPhoneNumber());
            // intent.putExtra("android.intent.extra.PHONE_NUMBER", contact.getContactPhoneNumber());
            Context context = v.getContext();
            context.startActivity(intent);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

}
