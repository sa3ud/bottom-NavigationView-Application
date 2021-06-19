package com.s3bqour.bottomnavigationviewapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.s3bqour.bottomnavigationviewapplication.fragments.DashBoardFragment;
import com.s3bqour.bottomnavigationviewapplication.fragments.HomeFragment;
import com.s3bqour.bottomnavigationviewapplication.fragments.MyAccountFragment;
import com.s3bqour.bottomnavigationviewapplication.fragments.ServiceFragment;
import com.s3bqour.bottomnavigationviewapplication.fragments.WalletFragment;

public class MainActivity extends AppCompatActivity  implements  BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    private static final int TIME_DELAY = 2000;
    private static long back_pressed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {

            getSupportFragmentManager().beginTransaction().replace(R.id.fl_host,
                    new HomeFragment())
                    .commit();

            bottomNavigationView.setSelectedItemId(R.id.nbv_home);

        }

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {

            case R.id.nbv_home:
                changeFragmentToHome(new HomeFragment(), HomeFragment.class.getSimpleName());
                return true;
            case R.id.nbv_service:
                changeFragmentToHome(new ServiceFragment(), ServiceFragment.class.getSimpleName());
                return true;
            case R.id.nbv_wallet:
                changeFragmentToHome(new WalletFragment(), WalletFragment.class.getSimpleName());
                return true;
            case R.id.nbv_dashboard:
                changeFragmentToHome(new DashBoardFragment(), DashBoardFragment.class.getSimpleName());
                return true;
            case R.id.nbv_my_account:
                changeFragmentToHome(new MyAccountFragment(), MyAccountFragment.class.getSimpleName());
                return true;
        }
        return false;
    }



    public void changeFragmentToHome(Fragment fragmentToDisplay, String tag) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fl_host, fragmentToDisplay, tag);
        if (fm.findFragmentByTag(tag) == null) {
            ft.addToBackStack(tag);
        }
        for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }

        ft.commit();
    }


    private Fragment getCurrentFragment() {

        return getSupportFragmentManager().findFragmentById(R.id.fl_host);

    }



    @Override
    public void onBackPressed() {

        bottomNavigationView.setSelectedItemId(R.id.nbv_home);


        if (getCurrentFragment() instanceof HomeFragment) {

            if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
                super.onBackPressed();
            } else {
                Toast.makeText(getBaseContext(), "Press once again to exit!",
                        Toast.LENGTH_SHORT).show();
            }
            back_pressed = System.currentTimeMillis();

        } else {

            super.onBackPressed();
        }
    }


}