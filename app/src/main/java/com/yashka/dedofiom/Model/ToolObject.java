package com.yashka.dedofiom.Model;

public class ToolObject {
    public int deviceType;

    /* renamed from: id */
    public int id;
    public int image;

    /* renamed from: ip */
    public String ip;
    public String name;
    public String port;
    public String siUnit;
    public String thingSpeakApiKey;
    public String thingSpeakChanelId;
    public String thingSpeakFieldNumber;
    public String thingSpeakStatus;
    public String toolID;
    public String toolImage;
    public String toolOffCode;
    public String toolOnCode;
    public int toolState;
    public int unitId = 0;

    public ToolObject() {
        String str = "";
        this.toolID = str;
        this.name = str;
        this.toolImage = str;
        this.toolOnCode = str;
        this.toolOffCode = str;
        this.ip = str;
        this.port = str;
        this.thingSpeakApiKey = str;
        this.thingSpeakChanelId = str;
        this.thingSpeakFieldNumber = str;
        this.deviceType = 1;
        this.thingSpeakStatus = "0";
        this.siUnit = str;
    }

    public String getToolID() {
        return this.toolID;
    }

    public void setToolID(String str) {
        this.toolID = str;
    }

    public String getToolName() {
        return this.name;
    }

    public void setToolName(String str) {
        this.name = str;
    }

    public String getToolImage() {
        return this.toolImage;
    }

    public void setToolImage(String str) {
        this.toolImage = str;
    }

    public String getToolOnCode() {
        return this.toolOnCode;
    }

    public void setToolOnCode(String str) {
        this.toolOnCode = str;
    }

    public String getToolOffCode() {
        return this.toolOffCode;
    }

    public void setToolOffCode(String str) {
        this.toolOffCode = str;
    }

    public int getToolState() {
        return this.toolState;
    }

    public void setToolState(int i) {
        this.toolState = i;
    }
}
