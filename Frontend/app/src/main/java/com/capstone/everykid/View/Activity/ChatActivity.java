package com.capstone.everykid.View.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.capstone.everykid.Model.CreateAccountItem;
import com.capstone.everykid.Model.G;
import com.capstone.everykid.Model.MessageItem;
import com.capstone.everykid.R;
import com.capstone.everykid.View.Adapter.ChatAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;

public class ChatActivity extends AppCompatActivity {

    EditText et;
    ListView listView;
    TextView p_name;
    CreateAccountItem createAccountItem;
    ArrayList<MessageItem> messageItems=new ArrayList<>();
    ChatAdapter adapter;

    String chat_kkid = Long.toString(createAccountItem.K_kid);

    FirebaseDatabase firebaseDatabase;

    DatabaseReference chatRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


        et=findViewById(R.id.et);
        listView=findViewById(R.id.listview);
        adapter=new ChatAdapter(messageItems,getLayoutInflater());
        listView.setAdapter(adapter);

        firebaseDatabase= FirebaseDatabase.getInstance();
        chatRef= firebaseDatabase.getReference("chat"+chat_kkid);

        chatRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                MessageItem messageItem= dataSnapshot.getValue(MessageItem.class);

                messageItems.add(messageItem);

                adapter.notifyDataSetChanged();
                listView.setSelection(messageItems.size()-1);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void clickSend(View view) {

        String nickName= CreateAccountItem.Name;
        String chatid = CreateAccountItem.Id;
        String message= et.getText().toString();
        String pofileUrl= createAccountItem.porfileUri;

        Calendar calendar= Calendar.getInstance();
        String time=calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE);

        MessageItem messageItem= new MessageItem(nickName, chatid, message, time, pofileUrl);
        chatRef.push().setValue(messageItem);

        et.setText("");

        InputMethodManager imm=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);

    }
}