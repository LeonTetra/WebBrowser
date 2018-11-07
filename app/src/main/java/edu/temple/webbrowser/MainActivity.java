package edu.temple.webbrowser;

import android.provider.Browser;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    FragmentManager fm;
    ViewPager viewPager;
    SwipeAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        fm = getSupportFragmentManager();
        sa = new SwipeAdapter(fm);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(sa);

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_forw:
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                return true;
            case R.id.action_prev:
                viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
                return true;
            default:
                sa.instantiateItem(viewPager, viewPager.getChildCount());
                sa.notifyDataSetChanged();

                Log.d("TEST",sa.browserFragments.toString());
                Log.d("TEST", "current="+viewPager.getChildCount());
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);

                return super.onOptionsItemSelected(item);
        }
    }
}
