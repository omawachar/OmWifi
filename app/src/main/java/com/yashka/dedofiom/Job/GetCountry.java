package com.yashka.dedofiom.Job;

import android.content.Context;
import android.os.AsyncTask;


import com.yashka.dedofiom.BuildConfig;
import com.yashka.dedofiom.Utill.DataBase;
import com.yashka.dedofiom.Utill.ServiceHandler;


import org.json.JSONObject;

import java.io.PrintStream;
import java.util.HashMap;

public class GetCountry extends AsyncTask<String, Void, String> {
    private Context context;
    private DataBase dataBase;
    String versionName = "";

    public GetCountry(Context context2) {
        this.context = context2;
        this.dataBase = new DataBase(context2);
        this.versionName = BuildConfig.VERSION_NAME;
    }

    /* access modifiers changed from: protected */
    public String doInBackground(String... strArr) {
        String makeHttpRequest = new ServiceHandler(this.context).makeHttpRequest("http://ip-api.com/json", 2, new HashMap());
        PrintStream printStream = System.out;
        StringBuilder sb = new StringBuilder();
        sb.append("jojojo ");
        sb.append(makeHttpRequest);
        printStream.println(sb.toString());
        return makeHttpRequest;
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(String str) {
        super.onPostExecute(str);
        PrintStream printStream = System.out;
        StringBuilder sb = new StringBuilder();
        sb.append("skalssdsdajslkjs ");
        sb.append(str);
        printStream.println(sb.toString());
        if (str != null) {
            try {
                String string = new JSONObject(str).getString("country");
                this.dataBase.updateContry(string, this.versionName);
                PrintStream printStream2 = System.out;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("asalaaksasalaslsa ");
                sb2.append(string);
                printStream2.println(sb2.toString());
            } catch (Exception e) {
                PrintStream printStream3 = System.out;
                StringBuilder sb3 = new StringBuilder();
                sb3.append("pfpspps ");
                sb3.append(e);
                printStream3.println(sb3.toString());
            }
        }
    }
}
