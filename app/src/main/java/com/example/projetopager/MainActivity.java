package com.example.projetopager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tab;
    ViewPager2 pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)//Checando a permissão da camera
                != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)//Checando a permissão da galeria
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE},0); // qual activity vai receber as permissões, array list
        }
        tab = findViewById(R.id.tab);
        pager = findViewById(R.id.pager);
        FragmentManager fm = getSupportFragmentManager();
        Adaptador adaptador = new Adaptador(fm, getLifecycle());
        pager.setAdapter(adaptador);
        tab.addTab(tab.newTab().setText("Camêra"));
        tab.addTab(tab.newTab().setText("Jogo Random"));
        tab.addTab(tab.newTab().setText("CalculaHora"));
        tab.addTab(tab.newTab().setText("Time"));
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {


            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tab.selectTab(tab.getTabAt(position));
            }
        });
    }
}