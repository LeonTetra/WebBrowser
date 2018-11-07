package edu.temple.webbrowser;


import android.content.Context;
import android.os.Bundle;
import android.provider.Browser;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class BrowserFragment extends Fragment implements View.OnClickListener{

    private String[] urlRepair = {"http://", "https://", "www."};
    private static int identifier = 0;
    private int id;

    WebFragment currentFragment;
    int current = 0;
    ArrayList<WebFragment> webFragments;
    FragmentManager fm;

    EditText addressBar;
    Button btnGo;

    Button btnNext;
    Button btnBack;

    public BrowserFragment() {
        id = identifier;
    }

    public static BrowserFragment newInstance() {
        BrowserFragment fragment = new BrowserFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        identifier++;
        return fragment;
    }


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_browser, container, false);
        webFragments = new ArrayList<WebFragment>();
        fm = getChildFragmentManager();


        addressBar = v.findViewById(R.id.editUrl);
        btnGo = v.findViewById(R.id.buttonGo);
        current = -1;
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = addressBar.getText().toString();
                if(!url.contains(urlRepair[2]))
                    url = urlRepair[2] + url;
                if(!(url.contains(urlRepair[1])||url.contains(urlRepair[0])))
                    url = urlRepair[1] + url;
                addressBar.setText(url);
                WebFragment tab = WebFragment.newInstance(url);
                currentFragment = tab;
                current++;
                webFragments.add(current, tab);

                fm.beginTransaction().addToBackStack(null).replace(R.id.linlayBrowser, currentFragment).commit();

            }
        });
        btnBack = v.findViewById(R.id.btnLast);
        btnNext = v.findViewById(R.id.btnNext);

        btnBack.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    public String toString(){
        if(addressBar!=null)
            return super.toString() + " | current url= " + addressBar.getText().toString() + " id="+id;
        else
            return super.toString() + " | id="+id;
    }

    @Override
    public void onClick(View v) {
        if(v.equals(btnBack)){
            if(current-1 >=0){
                current--;
                currentFragment = webFragments.get(current);
                addressBar.setText(currentFragment.getUrl());
                fm.beginTransaction().addToBackStack(null).replace(R.id.linlayBrowser, currentFragment).commit();
            }
        }else if(v.equals(btnNext)){
            if(current+1 < webFragments.size()){
                current++;
                currentFragment = webFragments.get(current);
                addressBar.setText(currentFragment.getUrl());
                fm.beginTransaction().addToBackStack(null).replace(R.id.linlayBrowser, currentFragment).commit();
            }
        }
    }
}
