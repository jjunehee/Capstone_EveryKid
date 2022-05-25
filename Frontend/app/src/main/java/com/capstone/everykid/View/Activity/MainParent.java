package com.capstone.everykid.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import android.view.MenuItem;

import com.capstone.everykid.R;
import com.google.android.material.navigation.NavigationBarView;
import android.os.Bundle;

public class MainParent extends AppCompatActivity {

    HomeFragment homeFragment;
    ChatFragment chatFragment;
    ListFragment listFragment;
    ProfileFragment profileFragment;
    CommunityFragment communityFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent_main);

        homeFragment=new HomeFragment();
        chatFragment=new ChatFragment();
        listFragment=new ListFragment();
        profileFragment=new ProfileFragment();
        communityFragment=new CommunityFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.containers, homeFragment).commit();

        NavigationBarView navigationBarView = findViewById(R.id.bottom_navigationview);
        navigationBarView.setSelectedItemId(R.id.home);
        navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.chatting:
                        //1) 선생님과 대화가 처음이거나 예전에 종료되었을때
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, chatFragment).commit();
                        //2) 선생님이 아직 대화 종료를 안함 (추가해야됨)
                        return true;
                    case R.id.community:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, communityFragment).commit();
                        return true;
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, homeFragment).commit();
                        return true;
                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, profileFragment).commit();
                        return true;
                    case R.id.list:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, listFragment).commit();
                        return true;
                }
                return false;
            }
        });
    }
}