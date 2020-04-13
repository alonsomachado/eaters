package com.example.eaters.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.eaters.Fragments.Cart_Fragment;
import com.example.eaters.Fragments.Favorites_Fragment;
import com.example.eaters.Fragments.Main_Fragment;
import com.example.eaters.Fragments.Profile_Fragment;
import com.example.eaters.Fragments.Search_Fragment;
import com.example.eaters.R;

public class MainActivity extends AppCompatActivity {

    private TextView mToolbarTitle;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull final MenuItem item) {

            final BottomNavigationView bottomNavigationView;
            bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);

            Fragment selectedFragment = null;
            final FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_fragment_right, R.anim.exit_fragment_right, R.anim.enter_fragment_right, R.anim.exit_fragment_right);
            transaction.addToBackStack(null);

            switch (item.getItemId()) {

                case R.id.navigation_home:
                    selectedFragment = new Main_Fragment();
                    transaction.replace(R.id.fragment_container, selectedFragment, getString(R.string.menuhome));
                    transaction.commit();
                    break;


                case R.id.navigation_search:
                    selectedFragment = new Search_Fragment();

                    transaction.replace(R.id.fragment_container, selectedFragment, getString(R.string.menubuscar)).commit();
                    break;

                case R.id.favourites:
                    selectedFragment = new Favorites_Fragment();

                    transaction.replace(R.id.fragment_container, selectedFragment, getString(R.string.menufavoritos)).commit();
                    return true;

                case R.id.cart:
                    selectedFragment = new Cart_Fragment();

                    transaction.replace(R.id.fragment_container, selectedFragment, getString(R.string.menucarrinho)).commit();
                    break;

                case R.id.navigation_profile:
                    selectedFragment = new Profile_Fragment();

                    transaction.replace(R.id.fragment_container, selectedFragment, getString(R.string.menuperfil)).commit();
                    break;


            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    selectedFragment).commit();

            return true;
        }
    };

    /*private void doFragmentTransaction(Fragment fragment, String tag, boolean addToBackStack, String message) {

        final FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putString("key","value");
        fragment.setArguments(bundle);

        transaction.replace(R.id.fragment_container, fragment, tag);
        if(addToBackStack){  transaction.addToBackStack(tag);}
        transaction.commit();
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Main_Fragment()).commit();
    }


}
