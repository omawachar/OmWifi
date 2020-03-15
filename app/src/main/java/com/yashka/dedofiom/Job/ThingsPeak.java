package com.yashka.dedofiom.Job;

import android.content.Context;
import android.os.AsyncTask;

import com.yashka.dedofiom.API;
import com.yashka.dedofiom.Model.ToolObject;
import com.yashka.dedofiom.Utill.DataBase;
import com.yashka.dedofiom.Utill.ServiceHandler;
import com.yashka.dedofiom.Utill.Utility;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import es.dmoral.toasty.Toasty;


public abstract class ThingsPeak extends AsyncTask<String, Void, String> {
    private int API_TYPE;
    private Context context;
    private DataBase dataBase;
    private String fieldNumber;
    private List<Integer> fieldNumberList;
    private boolean isAuto;
    private List<String> sensorValueList = new ArrayList();
    private int state;
    private ToolObject toolObject;

    public abstract void changeIndicaterStatus(String str, boolean z);

    public abstract void onPost(boolean z);

    public ThingsPeak(Context context2, int i, ToolObject toolObject2, int i2, boolean z) {
        this.context = context2;
        this.API_TYPE = i;
        this.toolObject = toolObject2;
        this.state = i2;
        this.isAuto = z;
        this.dataBase = new DataBase(context2);
    }

    public ThingsPeak(Context context2, int i, ToolObject toolObject2, boolean z) {
        this.context = context2;
        this.API_TYPE = i;
        this.toolObject = toolObject2;
        this.isAuto = z;
        this.dataBase = new DataBase(context2);
        this.fieldNumberList = this.dataBase.getSensorFieldNumberList(toolObject2.unitId);
        for (int i2 = 0; i2 < this.fieldNumberList.size(); i2++) {
            PrintStream printStream = System.out;
            StringBuilder sb = new StringBuilder();
            sb.append("alskalsas ");
            sb.append(this.fieldNumberList.get(i2));
            printStream.println(sb.toString());
        }
    }

    /* access modifiers changed from: protected */
    public String doInBackground(String... strArr) {
        String str;
        HashMap hashMap = new HashMap();
        StringBuilder sb = new StringBuilder();
        sb.append("field");
        sb.append(this.toolObject.thingSpeakFieldNumber);
        this.fieldNumber = sb.toString();
        hashMap.put("api_key", this.toolObject.thingSpeakApiKey);
        if (this.API_TYPE == Utility.WRITE_FEED) {
            if (this.state == 1) {
                hashMap.put(this.fieldNumber, "0");
            } else {
                hashMap.put(this.fieldNumber, "1");
            }
            str = API.writeFeed;
        } else {
            String str2 = "https://api.thingspeak.com/channels/";
            String str3 = "results";
            if (this.API_TYPE == Utility.READ_FEILD) {
                hashMap.put(str3, "200");
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str2);
                sb2.append(this.toolObject.thingSpeakChanelId);
                sb2.append(API.readField2);
                sb2.append(this.toolObject.thingSpeakFieldNumber);
                sb2.append(API.readField3);
                str = sb2.toString();
            } else if (this.API_TYPE == Utility.READ_FEED) {
                hashMap.put(str3, "100");
                StringBuilder sb3 = new StringBuilder();
                sb3.append(str2);
                sb3.append(this.toolObject.thingSpeakChanelId);
                sb3.append(API.readFeed2);
                str = sb3.toString();
            } else {
                str = "";
            }
        }
        PrintStream printStream = System.out;
        StringBuilder sb4 = new StringBuilder();
        sb4.append("alalal ");
        sb4.append(str);
        printStream.println(sb4.toString());
        String makeHttpRequest = new ServiceHandler(this.context).makeHttpRequest(str, 1, hashMap);
        PrintStream printStream2 = System.out;
        StringBuilder sb5 = new StringBuilder();
        sb5.append("jojojo ");
        sb5.append(makeHttpRequest);
        printStream2.println(sb5.toString());
        return makeHttpRequest;
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(String str) {
        super.onPostExecute(str);
        System.out.println("ddddggggjjjj ProfileDownLoad onPostExecute ");
        if (str != null) {
            String str2 = "You are using free ThingSpeak license. Currently, a single channel can only be updated once every 15 seconds.";
            String str3 = "Connection failed with Server.Please check ThingSpeak API Key , Channel ID and Field number";
            String str4 = "0";
            String str5 = "";
            if (this.API_TYPE != Utility.WRITE_FEED) {
                String str6 = "null";
                String str7 = "feeds";
                String str8 = "pfpspps ";
                if (this.API_TYPE == Utility.READ_FEILD) {
                    try {
                        JSONArray jSONArray = new JSONObject(str).getJSONArray(str7);
                        int length = jSONArray.length() - 1;
                        while (true) {
                            if (length < 0) {
                                break;
                            }
                            String string = jSONArray.getJSONObject(length).getString(this.fieldNumber);
                            PrintStream printStream = System.out;
                            StringBuilder sb = new StringBuilder();
                            sb.append(str8);
                            sb.append(string);
                            printStream.println(sb.toString());
                            if (!string.equals(str6)) {
                                changeIndicaterStatus(string, false);
                                break;
                            }
                            length--;
                        }
                        System.out.println(str8);
                    } catch (Exception e) {
                        PrintStream printStream2 = System.out;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(str8);
                        sb2.append(e);
                        printStream2.println(sb2.toString());
                    }
                    if (str.equals(str5)) {
                        Toasty.error(this.context, (CharSequence) str3, 1).show();
                    } else if (str.equals(str4)) {
                        Toasty.error(this.context, (CharSequence) str2, 1).show();
                    }
                } else if (this.API_TYPE == Utility.READ_FEED) {
                    try {
                        JSONArray jSONArray2 = new JSONObject(str).getJSONArray(str7);
                        for (int i = 0; i < jSONArray2.length(); i++) {
                            JSONObject jSONObject = jSONArray2.getJSONObject(i);
                            for (int i2 = 0; i2 < this.fieldNumberList.size(); i2++) {
                                StringBuilder sb3 = new StringBuilder();
                                sb3.append("field");
                                sb3.append(this.fieldNumberList.get(i2));
                                String string2 = jSONObject.getString(sb3.toString());
                                if (!string2.equals(str6)) {
                                    this.dataBase.updateSensorsValues(this.toolObject.unitId, ((Integer) this.fieldNumberList.get(i2)).intValue(), string2);
                                }
                            }
                        }
                        System.out.println(str8);
                        changeIndicaterStatus(str5, true);
                    } catch (Exception e2) {
                        PrintStream printStream3 = System.out;
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append(str8);
                        sb4.append(e2);
                        printStream3.println(sb4.toString());
                    }
                }
            } else if (str.equals(str5)) {
                Toasty.error(this.context, (CharSequence) str3, 1).show();
            } else if (str.equals(str4)) {
                Toasty.error(this.context, (CharSequence) str2, 1).show();
            } else {
                changeIndicaterStatus(str5, false);
            }
        }
        onPost(this.isAuto);
    }
}
