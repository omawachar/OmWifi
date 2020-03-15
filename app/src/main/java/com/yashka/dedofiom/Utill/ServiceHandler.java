package com.yashka.dedofiom.Utill;

import android.content.Context;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;

public class ServiceHandler {
    public static final int GET = 1;
    public static final int POST = 2;
    String charset = "UTF-8";
    HttpURLConnection conn;
    Context context;
    boolean isSSLConnection;
    String paramsString;
    int readTimeOut = 10;
    String respond = null;
    int respondCode = 0;
    StringBuilder result;
    StringBuilder sbParams;
    HttpsURLConnection sslConn;
    int timeOut = 20;
    URL urlObj;

    /* renamed from: wr */
    DataOutputStream f109wr;

    public ServiceHandler(Context context2) {
        this.context = context2;
    }

    public String makeHttpRequest(String str, int i, HashMap<String, String> hashMap) {
        String str2 = "";
        this.respond = str2;
        if (str.contains("https:")) {
            this.isSSLConnection = true;
        } else {
            this.isSSLConnection = false;
        }
        try {
            this.sbParams = new StringBuilder();
            int i2 = 0;
            for (String str3 : hashMap.keySet()) {
                if (i2 != 0) {
                    this.sbParams.append("&");
                }
                String str4 = hashMap.get(str3) != null ? (String) hashMap.get(str3) : str2;
                StringBuilder sb = this.sbParams;
                sb.append(str3);
                sb.append("=");
                sb.append(URLEncoder.encode(str4, this.charset));
                i2++;
            }
            String str5 = "GET";
            String str6 = "POST";
            String str7 = "?";
            String str8 = "Accept-Charset";
            if (this.isSSLConnection) {
                if (i == 2) {
                    this.urlObj = new URL(str);
                    this.sslConn = (HttpsURLConnection) this.urlObj.openConnection();
                    this.sslConn.setDoOutput(true);
                    this.sslConn.setRequestMethod(str6);
                    this.sslConn.setRequestProperty(str8, this.charset);
                    int i3 = this.timeOut - this.readTimeOut;
                    this.sslConn.setReadTimeout(this.readTimeOut * 1000);
                    this.sslConn.setConnectTimeout(i3 * 1000);
                    this.sslConn.setHostnameVerifier(new HostnameVerifier() {
                        public boolean verify(String str, SSLSession sSLSession) {
                            return true;
                        }
                    });
                    this.sslConn.setSSLSocketFactory((SSLSocketFactory) SSLSocketFactory.getDefault());
                    try {
                        this.sslConn.connect();
                    } catch (AssertionError e2) {
                        e2.printStackTrace();
                    }
                    this.paramsString = this.sbParams.toString();
                    this.f109wr = new DataOutputStream(this.sslConn.getOutputStream());
                    this.f109wr.writeBytes(this.paramsString);
                    this.f109wr.flush();
                    this.f109wr.close();
                } else if (i == 1) {
                    if (this.sbParams.length() != 0) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(str);
                        sb2.append(str7);
                        sb2.append(this.sbParams.toString());
                        str = sb2.toString();
                    }
                    this.urlObj = new URL(str);
                    this.sslConn = (HttpsURLConnection) this.urlObj.openConnection();
                    this.sslConn.setDoOutput(false);
                    this.sslConn.setRequestMethod(str5);
                    this.sslConn.setRequestProperty(str8, this.charset);
                    this.sslConn.setConnectTimeout(this.timeOut * 1000);
                    this.sslConn.connect();
                    PrintStream printStream = System.out;
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("hhkkhhkk ");
                    sb3.append(str);
                    printStream.println(sb3.toString());
                }
                this.conn = this.sslConn;
            } else if (i == 2) {
                this.urlObj = new URL(str);
                this.conn = (HttpURLConnection) this.urlObj.openConnection();
                this.conn.setDoOutput(true);
                this.conn.setRequestMethod(str6);
                this.conn.setRequestProperty(str8, this.charset);
                int i4 = this.timeOut - this.readTimeOut;
                this.conn.setReadTimeout(this.readTimeOut * 1000);
                this.conn.setConnectTimeout(i4 * 1000);
                this.conn.connect();
                this.paramsString = this.sbParams.toString();
                this.f109wr = new DataOutputStream(this.conn.getOutputStream());
                this.f109wr.writeBytes(this.paramsString);
                this.f109wr.flush();
                this.f109wr.close();
            } else if (i == 1) {
                if (this.sbParams.length() != 0) {
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append(str);
                    sb4.append(str7);
                    sb4.append(this.sbParams.toString());
                    str = sb4.toString();
                }
                this.urlObj = new URL(str);
                this.conn = (HttpURLConnection) this.urlObj.openConnection();
                this.conn.setDoOutput(false);
                this.conn.setRequestMethod(str5);
                this.conn.setRequestProperty(str8, this.charset);
                this.conn.setConnectTimeout(this.timeOut * 1000);
                this.conn.connect();
            }
            this.respondCode = this.conn.getResponseCode();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(this.conn.getInputStream())));
            this.result = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                this.result.append(readLine);
            }
            StringBuilder sb5 = new StringBuilder();
            sb5.append("result : ");
            sb5.append(this.result.toString());
            Log.d("JSON Parser", sb5.toString());
            this.respond = this.result.toString();
            this.conn.disconnect();
        } catch (SSLHandshakeException unused) {
            makeHttpRequest(str.replace("https", "http"), i, hashMap);
        } catch (Exception e3) {
            e3.printStackTrace();
            PrintStream printStream2 = System.out;
            StringBuilder sb6 = new StringBuilder();
            sb6.append("ttttttt ssl: ");
            sb6.append(e3.getMessage());
            printStream2.println(sb6.toString());
        }
        return this.respond;
    }

    public int getRespondCode() {
        return this.respondCode;
    }

    public int getTimeOut() {
        return this.timeOut;
    }

    public void setTimeOut(int i) {
        this.timeOut = i;
    }

    public int getReadTimeOut() {
        return this.readTimeOut;
    }

    public void setReadTimeOut(int i) {
        this.readTimeOut = i;
    }
}
