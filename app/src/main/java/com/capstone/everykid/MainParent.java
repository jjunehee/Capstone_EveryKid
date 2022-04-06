package com.capstone.everykid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import android.view.MenuItem;
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
        navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.chatting:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, chatFragment).commit();
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