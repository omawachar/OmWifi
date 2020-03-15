package com.yashka.dedofiom.Job;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.Settings.Secure;

import com.yashka.dedofiom.API;
import com.yashka.dedofiom.Model.AdsControll;
import com.yashka.dedofiom.Utill.DataBase;
import com.yashka.dedofiom.Utill.ServiceHandler;

import org.json.JSONObject;

import java.io.PrintStream;
import java.util.HashMap;

public class PurchesControll extends AsyncTask<String, Void, String> {
    private Context context;
    private DataBase dataBase;

    public PurchesControll(Context context2) {
        this.context = context2;
        this.dataBase = new DataBase(context2);
    }

    /* access modifiers changed from: protected */
    public String doInBackground(String... strArr) {
        String str = "android_id";
        String string = Secure.getString(this.context.getContentResolver(), str);
        AdsControll adsControlData = this.dataBase.getAdsControlData();
        HashMap hashMap = new HashMap();
        hashMap.put(str, string);
        hashMap.put("install_date", adsControlData.installDate);
        String str2 = "";
        hashMap.put("order_id", str2);
        hashMap.put("app_version", adsControlData.appVersion);
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append(adsControlData.keyCount);
        hashMap.put("key_count", sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str2);
        sb2.append(adsControlData.adState);
        hashMap.put("add_status", sb2.toString());
        hashMap.put("acknowledged", adsControlData.acknowledged);
        String str3 = "purchase_token";
        if (adsControlData.purchaseToken.equals(str2)) {
            hashMap.put(str3, string);
        } else {
            hashMap.put(str3, adsControlData.purchaseToken);
        }
        hashMap.put("country", adsControlData.country);
        StringBuilder sb3 = new StringBuilder();
        sb3.append(Build.BRAND);
        sb3.append(" ");
        sb3.append(Build.MODEL);
        hashMap.put("device_model", sb3.toString());
        return new ServiceHandler(this.context).makeHttpRequest(API.purchesControl, 2, hashMap);
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(String str) {
        super.onPostExecute(str);
        PrintStream printStream = System.out;
        StringBuilder sb = new StringBuilder();
        sb.append("asasdsad ");
        sb.append(str);
        printStream.println(sb.toString());
        if (str != null) {
            try {
                this.dataBase.updateIsRefund(new JSONObject(str).getInt("is_refund"));
            } catch (Exception e) {
                PrintStream printStream2 = System.out;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("pfpspps ");
                sb2.append(e);
                printStream2.println(sb2.toString());
            }
        }
        PrintStream printStream3 = System.out;
        StringBuilder sb3 = new StringBuilder();
        sb3.append("skalsajslkjs ");
        sb3.append(str);
        String str2 = " ";
        sb3.append(str2);
        sb3.append(Build.MANUFACTURER);
        sb3.append(str2);
        sb3.append(Build.BRAND);
        sb3.append(str2);
        sb3.append(Build.MODEL);
        printStream3.println(sb3.toString());
    }
}
