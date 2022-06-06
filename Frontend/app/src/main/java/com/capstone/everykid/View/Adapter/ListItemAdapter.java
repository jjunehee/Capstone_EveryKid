package com.capstone.everykid.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.capstone.everykid.Model.ListItem;
import com.capstone.everykid.R;
import com.capstone.everykid.View.Activity.LoadActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class ListItemAdapter extends BaseAdapter {
    private static final String TAG = "ListItemAdapter";
    private List<String> fileContainList = new ArrayList<>();
    List<ListItem> items = new ArrayList<>();
    Context context;

    public void setFileContainList(List<String> fileContainList) {
        this.fileContainList = fileContainList;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        context = parent.getContext();
        ListItem listItem = items.get(position);

        //listview item을 inflate해서 convertview를 참조
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }
        TextView Date = convertView.findViewById(R.id.date);
        Date.setText(listItem.getDate());
//        등원버튼
        Button btn1 = (Button) convertView.findViewById(R.id.btn_attend);
        if (this.fileContainList.contains("Junhee " + listItem.getDate() + " 등원.jpg")) {
            Log.i("ListItemAdapter", "color changed");
            btn1.setBackgroundTintList(context.getResources().getColorStateList(R.color.Green));
            btn1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Log.i(TAG, "clicked button");
                    Intent intent = new Intent(context, LoadActivity.class);
                    intent.putExtra("name", "Junhee");
                    intent.putExtra("date", listItem.getDate());
                    intent.putExtra("time", "등원");
                    context.startActivity(intent);
                }
            });

        }

//         하원버튼
        Button btn2 = (Button) convertView.findViewById(R.id.btn_exit);
        Log.i("ListItemAdapter", "Junhee " + listItem.getDate() + " 하원.jpg");
        if (this.fileContainList.contains("Junhee " + listItem.getDate() + " 하원.jpg")) {
            Log.i("ListItemAdapter", "color changed 하원");
            btn2.setBackgroundTintList(context.getResources().getColorStateList(R.color.Red));
            btn2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(context, LoadActivity.class);
                    intent.putExtra("name", "Junhee");
                    intent.putExtra("date", listItem.getDate());
                    intent.putExtra("time", "하원");
                    context.startActivity(intent);
                }
            });
        }

//        FirebaseStorage storage = FirebaseStorage.getInstance();
//        StorageReference storageReference = storage.getReference();
//        StorageReference pathReference = storageReference.child("image_store");
//        if(pathReference == null) {
//
//        } else {
//            StorageReference submitProfile = storageReference.child("image_store/" +"Junhee " + listItem.getDate() + " 하원" + ".jpg");
//            submitProfile.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                @Override
//                public void onSuccess(Uri uri) {
//                    btn2.setBackgroundColor(Color.RED);
//
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//
//                }
//            });
//        }


        return convertView;
    }

    public void addItem(ListItem item) {
        items.add(item);
    }
}
