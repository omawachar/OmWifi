package com.yashka.dedofiom.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener;

import com.yashka.dedofiom.R;
import com.yashka.dedofiom.Utill.DataBase;
import com.yashka.dedofiom.Utill.Utility;

import java.io.PrintStream;

public class Help extends Fragment {
    private int connectionType;
    private Context context;
    private DataBase dataBase;
    private String localUrl = "https://codepreviewlb.blogspot.com/2019/05/include-define-sendkey-0-button-to-send.html";
    private SwipeRefreshLayout mySwipeRefreshLayout;
    private String thingsPeakUrl = "https://codepreviewlb.blogspot.com/2019/12/thingspeak-with-dedofi.html";
    private String url = "";
    private WebView webView;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.help, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        this.webView = (WebView) view.findViewById(R.id.webView);
        this.mySwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        this.dataBase = new DataBase(this.context);
        this.connectionType = this.dataBase.getConnectionType();
        if (this.connectionType == Utility.THINGSPEAK) {
            this.url = this.thingsPeakUrl;
        } else {
            this.url = this.localUrl;
        }
        loadHelpView(this.webView, this.mySwipeRefreshLayout);
    }

    public void onAttach(Context context2) {
        super.onAttach(context2);
        this.context = context2;
    }

    public void loadHelpView(final WebView webView2, final SwipeRefreshLayout swipeRefreshLayout) {
        webView2.getSettings().setJavaScriptEnabled(true);
        webView2.getSettings().setBuiltInZoomControls(true);
        webView2.getSettings().setDisplayZoomControls(false);
        webView2.setWebViewClient(new WebViewClient());
        webView2.loadUrl(this.url);
        PrintStream printStream = System.out;
        StringBuilder sb = new StringBuilder();
        sb.append("kkjkdjs ");
        sb.append(this.url);
        printStream.println(sb.toString());
        swipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            public void onRefresh() {
                webView2.reload();
            }
        });
        webView2.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView webView, int i) {
                if (i == 100) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });
    }
}
