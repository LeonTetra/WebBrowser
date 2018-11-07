package edu.temple.webbrowser;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.LinearLayout;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WebFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WebFragment extends Fragment {
    private static final String ARG_PARAM1 = "url";
    private WebView webView;
    private String pUrl;


    public WebFragment() {
    }

    public String getUrl(){
        return pUrl;
    }
    public static WebFragment newInstance(String url) {
        WebFragment fragment = new WebFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, url);
        Log.d("newInstance", url + " | Web fragment instantiated" );
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pUrl = getArguments().getString(ARG_PARAM1);
        }
        Log.d("onCreate", pUrl + " | Web Fragment created" );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_web, container, false);
        webView = v.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        Log.d("Container", ""+container.toString());
        Log.d("onCreateView", pUrl + " | Web Fragment View created" );
        webView.loadUrl(pUrl);
        return v;
    }
    public void onResume(){
        super.onResume();
        Log.d("RESUME", "Resumed");
        webView.loadUrl(pUrl);
    }
    public void onPause(){
        super.onPause();
        Log.d("PAUSE", "Pause");
        webView.loadUrl(pUrl);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
