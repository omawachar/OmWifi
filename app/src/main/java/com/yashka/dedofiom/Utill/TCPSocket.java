package com.yashka.dedofiom.Utill;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.NoRouteToHostException;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class TCPSocket {
    static Context context;
    public static String json;
    Activity activity;
    Dialog dialog;

    /* renamed from: ip */
    public String f111ip;
    boolean isSucces;
    public String port;

    static abstract class Recive implements Runnable {
        DataInputStream dataInputStream;
        Handler handler = new Handler();
        int port;
        ServerSocket serverSocket;
        Socket socket;

        public abstract boolean writeDataOnBd(String str);

        public Recive(int i) {
            this.port = i;
        }

        public void run() {
            String str = "fgfg ";
            try {
                this.serverSocket = new ServerSocket();
                this.serverSocket.setReuseAddress(true);
                this.serverSocket.bind(new InetSocketAddress(this.port));
                this.handler.post(new Runnable() {
                    public void run() {
                        System.out.println("fgfg Waiting for reciver");
                    }
                });
                while (true) {
                    this.socket = this.serverSocket.accept();
                    this.dataInputStream = new DataInputStream(this.socket.getInputStream());
                    byte readByte = this.dataInputStream.readByte();
                    PrintStream printStream = System.out;
                    StringBuilder sb = new StringBuilder();
                    sb.append(str);
                    sb.append(readByte);
                    printStream.println(sb.toString());
                    this.handler.post(new Runnable() {
                        public void run() {
                            Recive.this.writeDataOnBd(TCPSocket.json);
                        }
                    });
                }
            } catch (Exception e) {
                PrintStream printStream2 = System.out;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append(e);
                printStream2.println(sb2.toString());
            }
        }
    }

    class Task extends AsyncTask<String, Void, Boolean> {
        DataOutputStream dataOutputStream;
        PrintWriter printWriter;

        /* renamed from: s */
        Socket f112s;

        Task() {
        }

        /* access modifiers changed from: protected */
        public Boolean doInBackground(String... strArr) {
            TCPSocket tCPSocket = TCPSocket.this;
            tCPSocket.f111ip = strArr[0];
            tCPSocket.port = strArr[1];
            TCPSocket.json = strArr[2];
            try {
                this.f112s = new Socket(tCPSocket.f111ip, Integer.parseInt(TCPSocket.this.port));
                this.dataOutputStream = new DataOutputStream(this.f112s.getOutputStream());
                Thread.sleep(20);
                this.dataOutputStream.writeBytes(TCPSocket.json);
                Thread.sleep(350);
                this.dataOutputStream.flush();
                this.dataOutputStream.close();
                this.f112s.close();
                TCPSocket.this.isSucces = true;
                PrintStream printStream = System.out;
                StringBuilder sb = new StringBuilder();
                sb.append("sdsdsdsdffw ");
                sb.append(TCPSocket.json);
                printStream.println(sb.toString());
            } catch (NoRouteToHostException unused) {
                TCPSocket.this.isSucces = false;
            } catch (ConnectException unused2) {
                TCPSocket.this.isSucces = false;
            } catch (Exception unused3) {
                TCPSocket.this.isSucces = false;
            }
            return Boolean.valueOf(TCPSocket.this.isSucces);
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            super.onPreExecute();
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Boolean bool) {
            super.onPostExecute(bool);
            TCPSocket.this.isSuccess(bool.booleanValue());
        }
    }

    public abstract boolean isSuccess(boolean z);

    public void setActivity(Activity activity2) {
        this.activity = activity2;
    }

    public void setDialog(Dialog dialog2) {
        this.dialog = dialog2;
    }

    public void setContext(Context context2) {
        context = context2;
    }

    public TCPSocket(String str, String str2, String str3) {
        this.f111ip = str;
        this.port = str2;
        json = str3;
    }

    public void send() {
        new Task().execute(new String[]{this.f111ip, this.port, json});
    }
}
