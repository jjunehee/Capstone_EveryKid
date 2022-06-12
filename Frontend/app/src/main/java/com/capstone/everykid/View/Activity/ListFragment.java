package com.capstone.everykid.View.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.capstone.everykid.Model.CreateAccountItem;
import com.capstone.everykid.R;

import com.capstone.everykid.Model.ListItem;
import com.capstone.everykid.View.Adapter.ListItemAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class ListFragment extends Fragment {
    private static final String TAG = "TTT";
    private View view;
    ListView listView;
    ListItemAdapter adapter;
    Button notiBtn;
    TextView textView;

    final Calendar c = Calendar.getInstance();
    int mYear = c.get(Calendar.YEAR);
    int mMonth = c.get(Calendar.MONTH)+1;
    int mDay = c.get(Calendar.DAY_OF_MONTH);
    String Month,Day;
    ImageView load;
    FirebaseDatabase mdatabase;
    DatabaseReference myRef;
    CreateAccountItem createAccountItem;


    public ListFragment() {
        // Required empty public constructor
    }

    public static ListFragment newInstance(String param1, String param2) {
        ListFragment fragment = new ListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_list, container, false);
        listView=view.findViewById(R.id.list);
        setExistOnlyDatestpAdaper();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH)+1;
        mDay = c.get(Calendar.DAY_OF_MONTH);



        notiBtn = (Button) view.findViewById(R.id.notificationSetting);

        notiBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NotificationSetting.class);
                startActivity(intent);
            }
        });

        return view;
    }

    public static String getMonth(int month) {
        if(month > 0 && month < 10) {
            return "0" + String.valueOf(month);
        }else {
            return String.valueOf(month);
        }
    }
    public static String getDay(int day) {
        if(day > 0 && day < 10) {
            return "0" + String.valueOf(day);
        }else {
            return String.valueOf(day);
        }
    }

    public void setExistOnlyDatestpAdaper() {
        adapter = new ListItemAdapter();
        for(int i=0; i<9; i++) {

            Month = getMonth(mMonth);
            Day = getDay(mDay);
            adapter.addItem(new ListItem(mYear + "-" + Month + "-" + Day));
            mDay = Integer.parseInt(Day)-1;
            if(mDay == 0) {
                if(mMonth == 2 || mMonth == 4 || mMonth == 6 || mMonth == 8 || mMonth == 9 || mMonth == 11) {
                    mDay = 31;
                    mMonth -= 1;
                } else if(mMonth == 5 || mMonth == 7 || mMonth == 10 || mMonth == 12) {
                    mDay = 30;
                    mMonth -= 1;
                } else if (mMonth == 1) {
                    mDay = 31;
                    mYear -= 1;
                    mMonth = 12;
                } else if(mMonth == 3) {
                    if(mYear % 4 == 0) {
                        mDay = 29;
                        mMonth -= 1;
                    } else {
                        mDay = 28;
                        mMonth -= 1;
                    }
                }

            }
        }
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReference();
        StorageReference pathReference = storageReference.child("image_store");
        if(pathReference == null) {
            Log.i("LoadActivity_0", "null");
        } else {
            StorageReference listRef = storageReference.child("image_store");
            listRef.listAll()
                    .addOnSuccessListener(new OnSuccessListener<ListResult>() {
                        @Override
                        public void onSuccess(ListResult listResult) {
                            List<String> dates = listResult.getItems().stream().map(StorageReference::getName)
                                    .filter(name -> name.contains(createAccountItem.C_name))
                                    .collect(Collectors.toList());

                            adapter.setFileContainList(dates);
                            listView.setAdapter(adapter);
                            textView=view.findViewById(R.id.textView9);
                            if(createAccountItem.C_name!=null){
                                textView.setText(createAccountItem.C_name+" 어린이");
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Uh-oh, an error occurred!
                            Log.i("LoadActivity_3", "failure");
                        }
                    });
        }

    }
}