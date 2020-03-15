package com.yashka.dedofiom.Utill;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class TCPClient {
    public static int SERVERPORT = 80;

    /* renamed from: in */
    private BufferedReader f110in = null;
    private OnMessageReceived mMessageListener = null;
    private boolean mRun = false;
    private PrintWriter out = null;
    public String serverIp = "192.168.4.1";
    private String serverMessage;

    public interface OnMessageReceived {
        void messageReceived(String str);
    }

    public TCPClient(OnMessageReceived onMessageReceived, String str, int i) {
        this.mMessageListener = onMessageReceived;
        this.serverIp = str;
        SERVERPORT = i;
    }

    public void sendMessage(String str) {
        PrintWriter printWriter = this.out;
        if (printWriter != null && !printWriter.checkError()) {
            PrintStream printStream = System.out;
            StringBuilder sb = new StringBuilder();
            sb.append("message: ");
            sb.append(str);
            printStream.println(sb.toString());
            this.out.print(str);
            this.out.flush();
        }
    }

    public void stopClient() {
        this.mRun = false;
    }

    public void run() {
        Socket socket=null;
        String str = "SI: Error";
        String str2 = "TCP SI Error";
        String str3 = "TCP SI Client";
        this.mRun = true;
        try {
            InetAddress byName = InetAddress.getByName(this.serverIp);
            Log.e(str3, "SI: Connecting...");
            socket = new Socket(byName, SERVERPORT);
            try {
                this.out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                Log.e(str3, "SI: Sent.");
                Log.e(str3, "SI: Done.");
                this.f110in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while (this.mRun) {
                    this.serverMessage = this.f110in.readLine();
                    if (!(this.serverMessage == null || this.mMessageListener == null)) {
                        this.mMessageListener.messageReceived(this.serverMessage);
                        StringBuilder sb = new StringBuilder();
                        sb.append("S: Received Message: '");
                        sb.append(this.serverMessage);
                        sb.append("'");
                        Log.e("RESPONSE FROM SERVER", sb.toString());
                    }
                    this.serverMessage = null;
                }
            } catch (Exception e) {
                Log.e(str2, str, e);
                e.printStackTrace();
            }
            socket.close();
        } catch (Exception e2) {
            Log.e(str2, str, e2);
        } catch (Throwable th) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            throw th;
        }
    }
}
