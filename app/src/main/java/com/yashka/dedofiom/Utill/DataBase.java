package com.yashka.dedofiom.Utill;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.yashka.dedofiom.Model.AdsControll;
import com.yashka.dedofiom.Model.BlockObject;
import com.yashka.dedofiom.Model.TempHumi;
import com.yashka.dedofiom.Model.TimmerM;
import com.yashka.dedofiom.Model.ToolObject;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DataBase extends SQLiteOpenHelper {
    private static final String ACKNOWLEDGED = "acknowledged";
    private static final String APP_VERSION = "app_version";
    private static final String BLOCK_NAME = "block_name";
    private static final String COLOR = "color";
    private static final String CONNECTION_TYPE = "connection_type";
    private static final String CONNECT_FIRST = "connect_first";
    private static final String CONTRY = "contry";
    private static final String DATABASE_NAME = "db_wifi_tcp";
    private static final int DATABASE_VERSION = 7;
    private static final String DEVICE_TYPE = "device_type";
    private static final String Friday = "Friday";
    private static final String GROUP = "groups";

    /* renamed from: ID */
    private static final String f108ID = "id";
    private static final String IMAGE = "image";
    private static final String INSTALL_DATE = "install_date";
    private static final String IS_ENABLE = "is_enable";
    private static final String IS_REFUND = "is_refund";
    private static final String KEY_COUNT = "key_count";
    private static final String LAST_SHOW_DATE_TIME = "last_show_date_time";
    private static final String Monday = "Monday";
    private static final String OFF_TIME = "off_time";
    private static final String ON_TIME = "on_time";
    private static final String PURCHASE_TOKEN = "purchase_token";
    private static final String SHOW_ADS = "show_ads";
    private static final String SI_UNIT = "si_unit";
    private static final String STATUS = "status";
    private static final String Saturday = "Saturday";
    private static final String Sunday = "Sunday";
    private static final String TABLE_ADD_CTRL = "add_ctrl";
    private static final String TABLE_BLOCK = "table_block";
    private static final String TABLE_COLOR = "table_color";
    private static final String TABLE_CONNECTION_MODE = "connection_mode";
    private static final String TABLE_CONNECT_FIRST = "table_connect_first";
    private static final String TABLE_IMAGE = "table_image";
    private static final String TABLE_TEMP_HUMIDITY = "table_temp_humi";
    private static final String TABLE_TIMER = "table_timer";
    private static final String TABLE_TOOLS = "table_tools";
    private static final String TEMP_HUMI = "temp_humi";
    private static final String TEMP_PORT = "temp_port";
    private static final String THING_SPEAK_API = "things_peak_api_key";
    private static final String THING_SPEAK_CHANEL_ID = "things_chanel_id";
    private static final String THING_SPEAK_FIELD_NUMBER = "things_peak_field_number";
    private static final String TIMER_NAME = "timer_name";
    private static final String TOOL_CURRENT_STATE = "tool_current_state";
    private static final String TOOL_ID = "tool_id";
    private static final String TOOL_IMAGE = "tool_image";
    private static final String TOOL_IP = "tool_ip";
    private static final String TOOL_NAME = "tool_name";
    private static final String TOOL_OFF_CODE = "tool_off_code";
    private static final String TOOL_ON_CODE = "tool_on_code";
    private static final String TOOL_PORT = "tool_port";
    private static final String Thursday = "Thursday";
    private static final String Tuesday = "Tuesday";
    private static final String UNIT_ID = "unit_id";
    private static final String Wednesday = "Wednesday";
    private String CREATE_TABLE_BLOCK = "CREATE TABLE IF NOT EXISTS table_block(id INTEGER PRIMARY KEY AUTOINCREMENT, image TEXT DEFAULT '',tool_id INTEGER DEFAULT 0, block_name TEXT DEFAULT '',tool_ip TEXT DEFAULT '',tool_port TEXT)";
    private String CREATE_TABLE_COLOR = "CREATE TABLE IF NOT EXISTS table_color(id INTEGER PRIMARY KEY AUTOINCREMENT, color INTEGER DEFAULT 0)";
    private String CREATE_TABLE_CONNECTION_MODE = "CREATE TABLE IF NOT EXISTS connection_mode(id INTEGER PRIMARY KEY UNIQUE, connection_type TEXT DEFAULT '', status INTEGER DEFAULT 0 )";
    private String CREATE_TABLE_CONNECT_FIRST = "CREATE TABLE IF NOT EXISTS table_connect_first(id INTEGER PRIMARY KEY UNIQUE, connect_first INTEGER DEFAULT 0)";
    private String CREATE_TABLE_FULL_SCREEN_ADD_CTRL = "CREATE TABLE IF NOT EXISTS add_ctrl(id INTEGER PRIMARY KEY UNIQUE, last_show_date_time TEXT DEFAULT '', show_ads TEXT DEFAULT '0', is_refund TEXT DEFAULT '0', purchase_token TEXT DEFAULT '',install_date TEXT DEFAULT '',contry TEXT DEFAULT '',key_count TEXT DEFAULT '0' )";
    private String CREATE_TABLE_IMAGE = "CREATE TABLE IF NOT EXISTS table_image(id INTEGER PRIMARY KEY UNIQUE, image TEXT DEFAULT '',groups INTEGER DEFAULT 0, color INTEGER DEFAULT 0)";
    private String CREATE_TABLE_TEMP_HUMI = "CREATE TABLE IF NOT EXISTS table_temp_humi(id INTEGER PRIMARY KEY UNIQUE, temp_port INTEGER DEFAULT 0, temp_humi TEXT DEFAULT '')";
    private String CREATE_TABLE_TIMER = "CREATE TABLE IF NOT EXISTS table_timer(id INTEGER PRIMARY KEY UNIQUE, on_time TEXT DEFAULT '', off_time TEXT DEFAULT '', timer_name TEXT DEFAULT '', is_enable INTEGER DEFAULT 0, Sunday TEXT DEFAULT '', Monday TEXT DEFAULT '', Tuesday TEXT DEFAULT '', Wednesday TEXT DEFAULT '', Thursday TEXT DEFAULT '', Friday TEXT DEFAULT '', Saturday INTEGER DEFAULT 0 )";
    private String CREATE_TOOL_TABLE = "CREATE TABLE IF NOT EXISTS table_tools(id INTEGER PRIMARY KEY AUTOINCREMENT, unit_id TEXT,tool_id TEXT,tool_name TEXT,tool_image TEXT DEFAULT '' ,tool_on_code TEXT,tool_off_code TEXT,tool_current_state INTEGER,tool_ip TEXT ,tool_port TEXT)";

    public void InsertDataViaSocket(Context context, int i) {
    }

    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, 7);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(this.CREATE_TOOL_TABLE);
        sQLiteDatabase.execSQL(this.CREATE_TABLE_COLOR);
        sQLiteDatabase.execSQL(this.CREATE_TABLE_IMAGE);
        sQLiteDatabase.execSQL(this.CREATE_TABLE_BLOCK);
        sQLiteDatabase.execSQL(this.CREATE_TABLE_TEMP_HUMI);
        sQLiteDatabase.execSQL(this.CREATE_TABLE_CONNECT_FIRST);
        sQLiteDatabase.execSQL(this.CREATE_TABLE_TIMER);
        sQLiteDatabase.execSQL(this.CREATE_TABLE_CONNECTION_MODE);
        sQLiteDatabase.execSQL("ALTER TABLE table_tools ADD COLUMN things_peak_api_key TEXT DEFAULT ''");
        sQLiteDatabase.execSQL("ALTER TABLE table_tools ADD COLUMN things_chanel_id TEXT DEFAULT ''");
        sQLiteDatabase.execSQL("ALTER TABLE table_tools ADD COLUMN things_peak_field_number TEXT DEFAULT ''");
        sQLiteDatabase.execSQL("ALTER TABLE table_tools ADD COLUMN device_type TEXT DEFAULT '1'");
        sQLiteDatabase.execSQL("ALTER TABLE table_tools ADD COLUMN si_unit TEXT DEFAULT ''");
        sQLiteDatabase.execSQL(this.CREATE_TABLE_FULL_SCREEN_ADD_CTRL);
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO add_ctrl (last_show_date_time,show_ads,key_count,install_date) VALUES ('now',");
        sb.append(Utility.SHOW_ADS);
        sb.append(",0,'");
        sb.append(format);
        sb.append("')");
        sQLiteDatabase.execSQL(sb.toString());
        sQLiteDatabase.execSQL("ALTER TABLE add_ctrl ADD COLUMN app_version TEXT DEFAULT ''");
        sQLiteDatabase.execSQL("ALTER TABLE add_ctrl ADD COLUMN acknowledged TEXT DEFAULT 'false'");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < 3) {
            sQLiteDatabase.execSQL(this.CREATE_TABLE_CONNECT_FIRST);
            sQLiteDatabase.execSQL("ALTER TABLE table_block ADD COLUMN tool_ip TEXT DEFAULT ''");
            sQLiteDatabase.execSQL("ALTER TABLE table_block ADD COLUMN tool_port TEXT DEFAULT ''");
        } else if (i < 4) {
            sQLiteDatabase.execSQL(this.CREATE_TABLE_TIMER);
        }
        if (i < 5) {
            sQLiteDatabase.execSQL(this.CREATE_TABLE_CONNECTION_MODE);
            sQLiteDatabase.execSQL("ALTER TABLE table_tools ADD COLUMN things_peak_api_key TEXT DEFAULT ''");
            sQLiteDatabase.execSQL("ALTER TABLE table_tools ADD COLUMN things_chanel_id TEXT DEFAULT ''");
            sQLiteDatabase.execSQL("ALTER TABLE table_tools ADD COLUMN things_peak_field_number TEXT DEFAULT ''");
            sQLiteDatabase.execSQL("ALTER TABLE table_tools ADD COLUMN device_type TEXT DEFAULT '1'");
            sQLiteDatabase.execSQL("ALTER TABLE table_tools ADD COLUMN si_unit TEXT DEFAULT ''");
        }
        if (i < 6) {
            sQLiteDatabase.execSQL(this.CREATE_TABLE_FULL_SCREEN_ADD_CTRL);
            String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO add_ctrl (last_show_date_time,show_ads,key_count,install_date) VALUES ('now',");
            sb.append(Utility.SHOW_ADS);
            sb.append(",0,'");
            sb.append(format);
            sb.append("')");
            sQLiteDatabase.execSQL(sb.toString());
        }
        if (i < 7) {
            sQLiteDatabase.execSQL("ALTER TABLE add_ctrl ADD COLUMN app_version TEXT DEFAULT ''");
            sQLiteDatabase.execSQL("ALTER TABLE add_ctrl ADD COLUMN acknowledged TEXT DEFAULT 'false'");
        }
    }

    public void insertConnectionTypeData(int i, int i2) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CONNECTION_TYPE, Integer.valueOf(i));
        contentValues.put("status", Integer.valueOf(i2));
        writableDatabase.insert(TABLE_CONNECTION_MODE, null, contentValues);
        writableDatabase.close();
    }

    public void updateConnectionType(int i, int i2) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String str = "status";
        contentValues.put(str, Integer.valueOf(i2));
        StringBuilder sb = new StringBuilder();
        sb.append("connection_type = ");
        sb.append(i);
        String sb2 = sb.toString();
        String str2 = TABLE_CONNECTION_MODE;
        writableDatabase.update(str2, contentValues, sb2, null);
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put(str, Integer.valueOf(0));
        StringBuilder sb3 = new StringBuilder();
        sb3.append("connection_type != ");
        sb3.append(i);
        writableDatabase.update(str2, contentValues2, sb3.toString(), null);
        writableDatabase.close();
    }

    public int getConnectionType() {
        int i = Utility.LOCAL;
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor rawQuery = readableDatabase.rawQuery("SELECT connection_type FROM connection_mode WHERE status =  1", null);
        if (rawQuery.getCount() > 0) {
            rawQuery.moveToFirst();
            i = rawQuery.getInt(rawQuery.getColumnIndex(CONNECTION_TYPE));
        }
        rawQuery.close();
        readableDatabase.close();
        return i;
    }

    public boolean insertToolData(ToolObject toolObject) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UNIT_ID, Integer.valueOf(toolObject.unitId));
        contentValues.put(TOOL_ID, toolObject.toolID);
        contentValues.put(TOOL_NAME, toolObject.name);
        contentValues.put(TOOL_IMAGE, toolObject.toolImage);
        contentValues.put(TOOL_ON_CODE, toolObject.toolOnCode);
        contentValues.put(TOOL_OFF_CODE, toolObject.toolOffCode);
        contentValues.put(TOOL_CURRENT_STATE, Integer.valueOf(toolObject.toolState));
        contentValues.put(TOOL_IP, toolObject.ip);
        contentValues.put(TOOL_PORT, toolObject.port);
        contentValues.put(THING_SPEAK_API, toolObject.thingSpeakApiKey);
        contentValues.put(THING_SPEAK_CHANEL_ID, toolObject.thingSpeakChanelId);
        contentValues.put(THING_SPEAK_FIELD_NUMBER, toolObject.thingSpeakFieldNumber);
        contentValues.put(DEVICE_TYPE, Integer.valueOf(toolObject.deviceType));
        contentValues.put(SI_UNIT, toolObject.siUnit);
        writableDatabase.insert(TABLE_TOOLS, null, contentValues);
        writableDatabase.close();
        return true;
    }

    public boolean updateToolData(ToolObject toolObject) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UNIT_ID, Integer.valueOf(toolObject.unitId));
        contentValues.put(TOOL_NAME, toolObject.name);
        contentValues.put(TOOL_IMAGE, toolObject.toolImage);
        contentValues.put(TOOL_ON_CODE, toolObject.toolOnCode);
        contentValues.put(TOOL_OFF_CODE, toolObject.toolOffCode);
        contentValues.put(TOOL_CURRENT_STATE, Integer.valueOf(toolObject.toolState));
        contentValues.put(TOOL_IP, toolObject.ip);
        contentValues.put(TOOL_PORT, toolObject.port);
        contentValues.put(THING_SPEAK_API, toolObject.thingSpeakApiKey);
        contentValues.put(THING_SPEAK_CHANEL_ID, toolObject.thingSpeakChanelId);
        contentValues.put(THING_SPEAK_FIELD_NUMBER, toolObject.thingSpeakFieldNumber);
        contentValues.put(DEVICE_TYPE, Integer.valueOf(toolObject.deviceType));
        contentValues.put(SI_UNIT, toolObject.siUnit);
        StringBuilder sb = new StringBuilder();
        sb.append("id = ");
        sb.append(toolObject.toolID);
        writableDatabase.update(TABLE_TOOLS, contentValues, sb.toString(), null);
        writableDatabase.close();
        return true;
    }

    public boolean insertImage(int i, String str, int i2, int i3) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(f108ID, Integer.valueOf(i));
        contentValues.put(IMAGE, str);
        contentValues.put(GROUP, Integer.valueOf(i2));
        contentValues.put(COLOR, Integer.valueOf(i3));
        writableDatabase.replace(TABLE_IMAGE, null, contentValues);
        writableDatabase.close();
        return true;
    }

    public List<ToolObject> getSpecificToolUnit(String str) {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM table_tools WHERE unit_id =  '");
        sb.append(str);
        sb.append("'");
        String sb2 = sb.toString();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor rawQuery = readableDatabase.rawQuery(sb2, null);
        if (rawQuery.getCount() > 0) {
            rawQuery.moveToFirst();
            do {
                ToolObject toolObject = new ToolObject();
                toolObject.setToolName(rawQuery.getString(rawQuery.getColumnIndex(TOOL_NAME)));
                toolObject.setToolImage(rawQuery.getString(rawQuery.getColumnIndex(TOOL_IMAGE)));
                toolObject.setToolID(rawQuery.getString(rawQuery.getColumnIndex(TOOL_ID)));
                arrayList.add(toolObject);
            } while (rawQuery.moveToNext());
        }
        rawQuery.close();
        readableDatabase.close();
        return arrayList;
    }

    public void updateToolDetials(String str, String str2, String str3, String str4) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE table_tools SET tool_name = '");
        sb.append(str2);
        sb.append("' , ");
        sb.append(TOOL_ON_CODE);
        String str5 = " = '";
        sb.append(str5);
        sb.append(str3);
        sb.append("', ");
        sb.append(TOOL_OFF_CODE);
        sb.append(str5);
        sb.append(str4);
        sb.append("' WHERE ");
        sb.append(TOOL_ID);
        sb.append(str5);
        sb.append(str);
        sb.append("'");
        writableDatabase.execSQL(sb.toString());
    }

    public ToolObject getToolDataForEdit(String str) {
        ToolObject toolObject = new ToolObject();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM table_tools WHERE tool_id = '");
        sb.append(str);
        sb.append("'");
        String sb2 = sb.toString();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor rawQuery = readableDatabase.rawQuery(sb2, null);
        if (rawQuery.getCount() > 0) {
            rawQuery.moveToFirst();
            toolObject.setToolName(rawQuery.getString(rawQuery.getColumnIndex(TOOL_NAME)));
            toolObject.setToolOnCode(rawQuery.getString(rawQuery.getColumnIndex(TOOL_ON_CODE)));
            toolObject.setToolOffCode(rawQuery.getString(rawQuery.getColumnIndex(TOOL_OFF_CODE)));
        }
        rawQuery.close();
        readableDatabase.close();
        return toolObject;
    }

    public String getOnOffCode(String str, int i) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        String str2 = "'";
        String str3 = "";
        if (i == 1) {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT tool_off_code FROM table_tools WHERE tool_id = '");
            sb.append(str);
            sb.append(str2);
            Cursor rawQuery = readableDatabase.rawQuery(sb.toString(), null);
            if (rawQuery.getCount() > 0) {
                rawQuery.moveToFirst();
                str3 = rawQuery.getString(rawQuery.getColumnIndex(TOOL_OFF_CODE));
            }
            rawQuery.close();
            readableDatabase.close();
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("SELECT tool_on_code FROM table_tools WHERE tool_id = '");
            sb2.append(str);
            sb2.append(str2);
            Cursor rawQuery2 = readableDatabase.rawQuery(sb2.toString(), null);
            if (rawQuery2.getCount() > 0) {
                rawQuery2.moveToFirst();
                str3 = rawQuery2.getString(rawQuery2.getColumnIndex(TOOL_ON_CODE));
            }
            rawQuery2.close();
            readableDatabase.close();
        }
        return str3;
    }

    public void changeSate(String str, int i) {
        String str2 = "  ";
        String str3 = "'";
        String str4 = " = '";
        String str5 = f108ID;
        String str6 = "'  WHERE ";
        String str7 = "UPDATE table_tools SET tool_current_state = '";
        if (i == 0) {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            StringBuilder sb = new StringBuilder();
            sb.append(str7);
            sb.append(1);
            sb.append(str6);
            sb.append(str5);
            sb.append(str4);
            sb.append(str);
            sb.append(str3);
            writableDatabase.execSQL(sb.toString());
            PrintStream printStream = System.out;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("LLL if ");
            sb2.append(1);
            sb2.append(str2);
            sb2.append(str);
            printStream.println(sb2.toString());
            return;
        }
        SQLiteDatabase writableDatabase2 = getWritableDatabase();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(str7);
        sb3.append(0);
        sb3.append(str6);
        sb3.append(str5);
        sb3.append(str4);
        sb3.append(str);
        sb3.append(str3);
        writableDatabase2.execSQL(sb3.toString());
        PrintStream printStream2 = System.out;
        StringBuilder sb4 = new StringBuilder();
        sb4.append("LLL else ");
        sb4.append(0);
        sb4.append(str2);
        sb4.append(str);
        printStream2.println(sb4.toString());
    }

    public int getToolState(String str) {
        int i;
        SQLiteDatabase readableDatabase = getReadableDatabase();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT tool_current_state FROM table_tools WHERE tool_id = '");
        sb.append(str);
        sb.append("'");
        Cursor rawQuery = readableDatabase.rawQuery(sb.toString(), null);
        if (rawQuery.getCount() > 0) {
            rawQuery.moveToFirst();
            i = rawQuery.getInt(rawQuery.getColumnIndex(TOOL_CURRENT_STATE));
        } else {
            i = 10;
        }
        rawQuery.close();
        readableDatabase.close();
        return i;
    }

    public int getToolImage(String str) {
        int i;
        SQLiteDatabase readableDatabase = getReadableDatabase();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT tool_image FROM table_tools WHERE tool_id = '");
        sb.append(str);
        sb.append("'");
        Cursor rawQuery = readableDatabase.rawQuery(sb.toString(), null);
        if (rawQuery.getCount() > 0) {
            rawQuery.moveToFirst();
            i = rawQuery.getInt(rawQuery.getColumnIndex(TOOL_IMAGE));
        } else {
            i = 0;
        }
        rawQuery.close();
        readableDatabase.close();
        return i;
    }

    public List<ToolObject> getToolInBlock(int i) {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM table_tools WHERE unit_id = '");
        sb.append(i);
        sb.append("'");
        String sb2 = sb.toString();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor rawQuery = readableDatabase.rawQuery(sb2, null);
        if (rawQuery.getCount() > 0) {
            rawQuery.moveToFirst();
            do {
                ToolObject toolObject = new ToolObject();
                toolObject.id = rawQuery.getInt(rawQuery.getColumnIndex(f108ID));
                toolObject.setToolName(rawQuery.getString(rawQuery.getColumnIndex(TOOL_NAME)));
                toolObject.setToolImage(rawQuery.getString(rawQuery.getColumnIndex(TOOL_IMAGE)));
                toolObject.toolState = rawQuery.getInt(rawQuery.getColumnIndex(TOOL_CURRENT_STATE));
                toolObject.ip = rawQuery.getString(rawQuery.getColumnIndex(TOOL_IP));
                toolObject.toolOffCode = rawQuery.getString(rawQuery.getColumnIndex(TOOL_ON_CODE));
                toolObject.toolOnCode = rawQuery.getString(rawQuery.getColumnIndex(TOOL_OFF_CODE));
                toolObject.port = rawQuery.getString(rawQuery.getColumnIndex(TOOL_PORT));
                toolObject.thingSpeakChanelId = rawQuery.getString(rawQuery.getColumnIndex(THING_SPEAK_CHANEL_ID));
                toolObject.thingSpeakFieldNumber = rawQuery.getString(rawQuery.getColumnIndex(THING_SPEAK_FIELD_NUMBER));
                toolObject.thingSpeakApiKey = rawQuery.getString(rawQuery.getColumnIndex(THING_SPEAK_API));
                toolObject.deviceType = rawQuery.getInt(rawQuery.getColumnIndex(DEVICE_TYPE));
                toolObject.siUnit = rawQuery.getString(rawQuery.getColumnIndex(SI_UNIT));
                arrayList.add(toolObject);
            } while (rawQuery.moveToNext());
        }
        rawQuery.close();
        readableDatabase.close();
        return arrayList;
    }

    public List<ToolObject> getToolInBlock(int i, int i2) {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM table_tools WHERE unit_id = '");
        sb.append(i);
        sb.append("' AND ");
        String str = DEVICE_TYPE;
        sb.append(str);
        sb.append(" = '");
        sb.append(i2);
        sb.append("'");
        String sb2 = sb.toString();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor rawQuery = readableDatabase.rawQuery(sb2, null);
        if (rawQuery.getCount() > 0) {
            rawQuery.moveToFirst();
            do {
                ToolObject toolObject = new ToolObject();
                toolObject.id = rawQuery.getInt(rawQuery.getColumnIndex(f108ID));
                toolObject.setToolName(rawQuery.getString(rawQuery.getColumnIndex(TOOL_NAME)));
                toolObject.setToolImage(rawQuery.getString(rawQuery.getColumnIndex(TOOL_IMAGE)));
                toolObject.toolState = rawQuery.getInt(rawQuery.getColumnIndex(TOOL_CURRENT_STATE));
                toolObject.ip = rawQuery.getString(rawQuery.getColumnIndex(TOOL_IP));
                toolObject.toolOffCode = rawQuery.getString(rawQuery.getColumnIndex(TOOL_ON_CODE));
                toolObject.toolOnCode = rawQuery.getString(rawQuery.getColumnIndex(TOOL_OFF_CODE));
                toolObject.port = rawQuery.getString(rawQuery.getColumnIndex(TOOL_PORT));
                toolObject.thingSpeakChanelId = rawQuery.getString(rawQuery.getColumnIndex(THING_SPEAK_CHANEL_ID));
                toolObject.thingSpeakFieldNumber = rawQuery.getString(rawQuery.getColumnIndex(THING_SPEAK_FIELD_NUMBER));
                toolObject.thingSpeakApiKey = rawQuery.getString(rawQuery.getColumnIndex(THING_SPEAK_API));
                toolObject.deviceType = rawQuery.getInt(rawQuery.getColumnIndex(str));
                toolObject.siUnit = rawQuery.getString(rawQuery.getColumnIndex(SI_UNIT));
                arrayList.add(toolObject);
            } while (rawQuery.moveToNext());
        }
        rawQuery.close();
        readableDatabase.close();
        return arrayList;
    }

    public String getToolUnit(String str) {
        String str2;
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT unit_id FROM table_tools WHERE tool_id = '");
        sb.append(str);
        sb.append("'");
        String sb2 = sb.toString();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor rawQuery = readableDatabase.rawQuery(sb2, null);
        if (rawQuery.getCount() > 0) {
            rawQuery.moveToFirst();
            str2 = rawQuery.getString(rawQuery.getColumnIndex(UNIT_ID));
        } else {
            str2 = "";
        }
        rawQuery.close();
        readableDatabase.close();
        return str2;
    }

    public void insertBlock(String str, String str2, String str3, String str4) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BLOCK_NAME, str);
        contentValues.put(IMAGE, str2);
        contentValues.put(TOOL_IP, str3);
        contentValues.put(TOOL_PORT, str4);
        writableDatabase.insert(TABLE_BLOCK, null, contentValues);
    }

    public void insertBlock(String str, String str2) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BLOCK_NAME, str);
        contentValues.put(IMAGE, str2);
        writableDatabase.insert(TABLE_BLOCK, null, contentValues);
    }

    public List<BlockObject> getAllBlock() {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor rawQuery = readableDatabase.rawQuery("SELECT * FROM table_block", null);
        if (rawQuery.getCount() > 0) {
            rawQuery.moveToFirst();
            do {
                BlockObject blockObject = new BlockObject();
                blockObject.id = rawQuery.getInt(rawQuery.getColumnIndex(f108ID));
                blockObject.name = rawQuery.getString(rawQuery.getColumnIndex(BLOCK_NAME));
                blockObject.image = rawQuery.getString(rawQuery.getColumnIndex(IMAGE));
                blockObject.ip = rawQuery.getString(rawQuery.getColumnIndex(TOOL_IP));
                blockObject.port = rawQuery.getInt(rawQuery.getColumnIndex(TOOL_PORT));
                arrayList.add(blockObject);
            } while (rawQuery.moveToNext());
        }else{

        }
        rawQuery.close();
        readableDatabase.close();
        return arrayList;
    }

    public List<String> getImages(int i) {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM table_image WHERE groups = '");
        sb.append(i);
        sb.append("'");
        String sb2 = sb.toString();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor rawQuery = readableDatabase.rawQuery(sb2, null);
        if (rawQuery.getCount() > 0) {
            rawQuery.moveToFirst();
            do {
                arrayList.add(rawQuery.getString(rawQuery.getColumnIndex(IMAGE)));
            } while (rawQuery.moveToNext());
        }
        rawQuery.close();
        readableDatabase.close();
        return arrayList;
    }

    public void deleteBlock(int i) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        SQLiteDatabase writableDatabase2 = getWritableDatabase();
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM table_block WHERE id = '");
        sb.append(i);
        String str = "'";
        sb.append(str);
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append("DELETE FROM table_tools WHERE unit_id = '");
        sb3.append(i);
        sb3.append(str);
        String sb4 = sb3.toString();
        writableDatabase.execSQL(sb2);
        writableDatabase2.execSQL(sb4);
        writableDatabase.close();
        writableDatabase2.close();
    }

    public boolean updateToolData(int i, int i2, String str, String str2, String str3, String str4) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UNIT_ID, Integer.valueOf(i));
        contentValues.put(TOOL_NAME, str);
        contentValues.put(TOOL_IMAGE, str2);
        contentValues.put(TOOL_ON_CODE, str3);
        contentValues.put(TOOL_OFF_CODE, str4);
        StringBuilder sb = new StringBuilder();
        sb.append("id = ");
        sb.append(i2);
        writableDatabase.update(TABLE_TOOLS, contentValues, sb.toString(), null);
        writableDatabase.close();
        return true;
    }

    public void updateSensorsValues(int i, int i2, String str) {
        PrintStream printStream = System.out;
        StringBuilder sb = new StringBuilder();
        sb.append("laksjkalksas ");
        sb.append(i);
        String str2 = " ";
        sb.append(str2);
        sb.append(i2);
        sb.append(str2);
        sb.append(str);
        printStream.println(sb.toString());
        SQLiteDatabase writableDatabase = getWritableDatabase();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("UPDATE table_tools SET tool_current_state = '");
        sb2.append(str);
        sb2.append("'  WHERE ");
        sb2.append(UNIT_ID);
        String str3 = " = '";
        sb2.append(str3);
        sb2.append(i);
        String str4 = "' AND ";
        sb2.append(str4);
        sb2.append(DEVICE_TYPE);
        sb2.append(" =  '");
        sb2.append(Utility.SENSOR);
        sb2.append(str4);
        sb2.append(THING_SPEAK_FIELD_NUMBER);
        sb2.append(str3);
        sb2.append(i2);
        sb2.append("'");
        writableDatabase.execSQL(sb2.toString());
        writableDatabase.close();
    }

    public List<Integer> getSensorFieldNumberList(int i) {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT things_peak_field_number FROM table_tools WHERE unit_id =  '");
        sb.append(i);
        sb.append("' AND ");
        sb.append(DEVICE_TYPE);
        sb.append(" = '");
        sb.append(Utility.SENSOR);
        sb.append("'");
        String sb2 = sb.toString();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor rawQuery = readableDatabase.rawQuery(sb2, null);
        if (rawQuery.getCount() > 0) {
            rawQuery.moveToFirst();
            do {
                arrayList.add(Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndex(THING_SPEAK_FIELD_NUMBER))));
            } while (rawQuery.moveToNext());
        }
        rawQuery.close();
        readableDatabase.close();
        return arrayList;
    }

    public void updateSensorValue(String str, String str2) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE table_tools SET tool_current_state = '");
        sb.append(str2);
        sb.append("'  WHERE ");
        sb.append(f108ID);
        sb.append(" = '");
        sb.append(str);
        sb.append("'");
        writableDatabase.execSQL(sb.toString());
        PrintStream printStream = System.out;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("LLL ifkk ");
        sb2.append(str2);
        sb2.append("  ");
        sb2.append(str);
        printStream.println(sb2.toString());
    }

    public void updateBlock(int i, String str, String str2, String str3, String str4) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BLOCK_NAME, str);
        contentValues.put(IMAGE, str2);
        contentValues.put(TOOL_PORT, str4);
        contentValues.put(TOOL_IP, str3);
        StringBuilder sb = new StringBuilder();
        sb.append("id = ");
        sb.append(i);
        writableDatabase.update(TABLE_BLOCK, contentValues, sb.toString(), null);
        writableDatabase.close();
    }

    public void updateBlock(int i, String str, String str2) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BLOCK_NAME, str);
        contentValues.put(IMAGE, str2);
        StringBuilder sb = new StringBuilder();
        sb.append("id = ");
        sb.append(i);
        writableDatabase.update(TABLE_BLOCK, contentValues, sb.toString(), null);
        writableDatabase.close();
    }

    public BlockObject getBlockByid(int i) {
        BlockObject blockObject = new BlockObject();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM table_block WHERE id = '");
        sb.append(i);
        sb.append("'");
        String sb2 = sb.toString();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor rawQuery = readableDatabase.rawQuery(sb2, null);
        if (rawQuery.getCount() > 0) {
            rawQuery.moveToFirst();
            do {
                blockObject.name = rawQuery.getString(rawQuery.getColumnIndex(BLOCK_NAME));
                blockObject.image = rawQuery.getString(rawQuery.getColumnIndex(IMAGE));
                blockObject.ip = rawQuery.getString(rawQuery.getColumnIndex(TOOL_IP));
                blockObject.port = rawQuery.getInt(rawQuery.getColumnIndex(TOOL_PORT));
            } while (rawQuery.moveToNext());
        }
        rawQuery.close();
        readableDatabase.close();
        return blockObject;
    }

    public ToolObject getToolByid(int i) {
        ToolObject toolObject = new ToolObject();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM table_tools WHERE id = '");
        sb.append(i);
        sb.append("'");
        String sb2 = sb.toString();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor rawQuery = readableDatabase.rawQuery(sb2, null);
        if (rawQuery.getCount() > 0) {
            rawQuery.moveToFirst();
            do {
                toolObject.toolID = rawQuery.getString(rawQuery.getColumnIndex(f108ID));
                toolObject.name = rawQuery.getString(rawQuery.getColumnIndex(TOOL_NAME));
                toolObject.toolImage = rawQuery.getString(rawQuery.getColumnIndex(TOOL_IMAGE));
                toolObject.ip = rawQuery.getString(rawQuery.getColumnIndex(TOOL_IP));
                toolObject.port = rawQuery.getString(rawQuery.getColumnIndex(TOOL_PORT));
                toolObject.toolOffCode = rawQuery.getString(rawQuery.getColumnIndex(TOOL_OFF_CODE));
                toolObject.toolOnCode = rawQuery.getString(rawQuery.getColumnIndex(TOOL_ON_CODE));
                toolObject.toolState = rawQuery.getInt(rawQuery.getColumnIndex(TOOL_CURRENT_STATE));
                toolObject.thingSpeakChanelId = rawQuery.getString(rawQuery.getColumnIndex(THING_SPEAK_CHANEL_ID));
                toolObject.thingSpeakFieldNumber = rawQuery.getString(rawQuery.getColumnIndex(THING_SPEAK_FIELD_NUMBER));
                toolObject.thingSpeakApiKey = rawQuery.getString(rawQuery.getColumnIndex(THING_SPEAK_API));
                toolObject.deviceType = rawQuery.getInt(rawQuery.getColumnIndex(DEVICE_TYPE));
                toolObject.siUnit = rawQuery.getString(rawQuery.getColumnIndex(SI_UNIT));
            } while (rawQuery.moveToNext());
        }
        rawQuery.close();
        readableDatabase.close();
        return toolObject;
    }

    public ToolObject getToolByDeviceType(int i, int i2) {
        ToolObject toolObject = new ToolObject();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM table_tools WHERE device_type = '");
        sb.append(i);
        sb.append("' AND ");
        String str = UNIT_ID;
        sb.append(str);
        sb.append(" = '");
        sb.append(i2);
        sb.append("'");
        String sb2 = sb.toString();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor rawQuery = readableDatabase.rawQuery(sb2, null);
        if (rawQuery.getCount() > 0) {
            rawQuery.moveToFirst();
            do {
                toolObject.unitId = rawQuery.getInt(rawQuery.getColumnIndex(str));
                toolObject.toolID = rawQuery.getString(rawQuery.getColumnIndex(f108ID));
                toolObject.name = rawQuery.getString(rawQuery.getColumnIndex(TOOL_NAME));
                toolObject.toolImage = rawQuery.getString(rawQuery.getColumnIndex(TOOL_IMAGE));
                toolObject.ip = rawQuery.getString(rawQuery.getColumnIndex(TOOL_IP));
                toolObject.port = rawQuery.getString(rawQuery.getColumnIndex(TOOL_PORT));
                toolObject.toolOffCode = rawQuery.getString(rawQuery.getColumnIndex(TOOL_OFF_CODE));
                toolObject.toolOnCode = rawQuery.getString(rawQuery.getColumnIndex(TOOL_ON_CODE));
                toolObject.toolState = rawQuery.getInt(rawQuery.getColumnIndex(TOOL_CURRENT_STATE));
                toolObject.thingSpeakChanelId = rawQuery.getString(rawQuery.getColumnIndex(THING_SPEAK_CHANEL_ID));
                toolObject.thingSpeakFieldNumber = rawQuery.getString(rawQuery.getColumnIndex(THING_SPEAK_FIELD_NUMBER));
                toolObject.thingSpeakApiKey = rawQuery.getString(rawQuery.getColumnIndex(THING_SPEAK_API));
                toolObject.deviceType = rawQuery.getInt(rawQuery.getColumnIndex(DEVICE_TYPE));
                toolObject.siUnit = rawQuery.getString(rawQuery.getColumnIndex(SI_UNIT));
            } while (rawQuery.moveToNext());
        }
        rawQuery.close();
        readableDatabase.close();
        return toolObject;
    }

    public void deleteTool(int i) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM table_tools WHERE id = '");
        sb.append(i);
        sb.append("'");
        writableDatabase.execSQL(sb.toString());
        writableDatabase.close();
    }

    public void insertTempHumi(String str, String str2) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(f108ID, Integer.valueOf(1));
        if (!str.equals("")) {
            contentValues.put(TEMP_PORT, str);
        }
        contentValues.put(TEMP_HUMI, str2);
        writableDatabase.replace(TABLE_TEMP_HUMIDITY, null, contentValues);
        writableDatabase.close();
    }

    public TempHumi getTemphumi() {
        TempHumi tempHumi = new TempHumi();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor rawQuery = readableDatabase.rawQuery("SELECT * FROM table_temp_humi WHERE id = 1", null);
        if (rawQuery.getCount() > 0) {
            rawQuery.moveToFirst();
            do {
                tempHumi.port = rawQuery.getInt(rawQuery.getColumnIndex(TEMP_PORT));
                tempHumi.temphumi = rawQuery.getString(rawQuery.getColumnIndex(TEMP_HUMI));
            } while (rawQuery.moveToNext());
        }
        rawQuery.close();
        readableDatabase.close();
        return tempHumi;
    }

    public int getAllDeviceCount() {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor rawQuery = readableDatabase.rawQuery("SELECT * FROM table_tools", null);
        int count = rawQuery.getCount();
        rawQuery.close();
        readableDatabase.close();
        return count;
    }

    public int getAllDeviceCount(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM table_tools WHERE device_type = '");
        sb.append(i);
        sb.append("'");
        String sb2 = sb.toString();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor rawQuery = readableDatabase.rawQuery(sb2, null);
        int count = rawQuery.getCount();
        rawQuery.close();
        readableDatabase.close();
        return count;
    }

    public int getAllRunningDeviceCount(int i, int i2) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM table_tools WHERE tool_current_state = '");
        sb.append(i);
        sb.append("' AND ");
        sb.append(DEVICE_TYPE);
        sb.append(" = '");
        sb.append(i2);
        sb.append("'");
        String sb2 = sb.toString();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor rawQuery = readableDatabase.rawQuery(sb2, null);
        int count = rawQuery.getCount();
        rawQuery.close();
        readableDatabase.close();
        return count;
    }

    public int getAllDeviceCountInBlock(int i, int i2) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM table_tools WHERE unit_id = '");
        sb.append(i);
        sb.append("' AND ");
        sb.append(DEVICE_TYPE);
        sb.append(" = '");
        sb.append(i2);
        sb.append("'");
        String sb2 = sb.toString();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor rawQuery = readableDatabase.rawQuery(sb2, null);
        int count = rawQuery.getCount();
        rawQuery.close();
        readableDatabase.close();
        return count;
    }

    public int getAllRunningDeviceCountInBlock(int i, int i2, int i3) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM table_tools WHERE unit_id = '");
        sb.append(i);
        String str = "' AND ";
        sb.append(str);
        sb.append(TOOL_CURRENT_STATE);
        String str2 = " = '";
        sb.append(str2);
        sb.append(i2);
        sb.append(str);
        sb.append(DEVICE_TYPE);
        sb.append(str2);
        sb.append(i3);
        sb.append("'");
        String sb2 = sb.toString();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor rawQuery = readableDatabase.rawQuery(sb2, null);
        int count = rawQuery.getCount();
        rawQuery.close();
        readableDatabase.close();
        return count;
    }

    public void insertConnectFirst(int i, int i2) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(f108ID, Integer.valueOf(i));
        contentValues.put(CONNECT_FIRST, Integer.valueOf(i2));
        writableDatabase.replace(TABLE_CONNECT_FIRST, null, contentValues);
        writableDatabase.close();
    }

    public int getConnectFirst() {
        int i;
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor rawQuery = readableDatabase.rawQuery("SELECT connect_first FROM table_connect_first WHERE id = 1", null);
        if (rawQuery.getCount() > 0) {
            rawQuery.moveToFirst();
            do {
                i = rawQuery.getInt(rawQuery.getColumnIndex(CONNECT_FIRST));
            } while (rawQuery.moveToNext());
        } else {
            i = 0;
        }
        rawQuery.close();
        readableDatabase.close();
        return i;
    }

    public void copyIpPortBlockTOTemp() {
        ArrayList arrayList = new ArrayList();
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor rawQuery = readableDatabase.rawQuery("SELECT id,tool_ip,tool_port FROM table_block", null);
        int count = rawQuery.getCount();
        String str = TOOL_PORT;
        String str2 = TOOL_IP;
        if (count > 0) {
            rawQuery.moveToFirst();
            do {
                ToolObject toolObject = new ToolObject();
                toolObject.id = rawQuery.getInt(rawQuery.getColumnIndex(f108ID));
                toolObject.ip = rawQuery.getString(rawQuery.getColumnIndex(str2));
                toolObject.port = rawQuery.getString(rawQuery.getColumnIndex(str));
                arrayList.add(toolObject);
            } while (rawQuery.moveToNext());
        }
        for (int i = 0; i < arrayList.size(); i++) {
            String str3 = "";
            if (!((ToolObject) arrayList.get(i)).ip.equals(str3) || !((ToolObject) arrayList.get(i)).port.equals(str3) || ((ToolObject) arrayList.get(i)).ip != null || ((ToolObject) arrayList.get(i)).port != null) {
                contentValues.put(str2, ((ToolObject) arrayList.get(i)).ip);
                contentValues.put(str, ((ToolObject) arrayList.get(i)).port);
                StringBuilder sb = new StringBuilder();
                sb.append("unit_id = ");
                sb.append(((ToolObject) arrayList.get(i)).id);
                readableDatabase.update(TABLE_TOOLS, contentValues, sb.toString(), null);
            }
        }
        rawQuery.close();
        readableDatabase.close();
    }

    public ToolObject getIpPortFromBucket(int i) {
        ToolObject toolObject = new ToolObject();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT id,tool_ip,tool_port FROM table_block WHERE id = '");
        sb.append(i);
        sb.append("'");
        String sb2 = sb.toString();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor rawQuery = readableDatabase.rawQuery(sb2, null);
        if (rawQuery.getCount() > 0) {
            rawQuery.moveToFirst();
            do {
                toolObject.ip = rawQuery.getString(rawQuery.getColumnIndex(TOOL_IP));
                toolObject.port = rawQuery.getString(rawQuery.getColumnIndex(TOOL_PORT));
            } while (rawQuery.moveToNext());
        }
        rawQuery.close();
        readableDatabase.close();
        return toolObject;
    }

    public long insertTimer(String str, String str2, String str3, int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ON_TIME, str);
        contentValues.put(OFF_TIME, str2);
        contentValues.put(TIMER_NAME, str3);
        contentValues.put(Sunday, Integer.valueOf(i));
        contentValues.put(Monday, Integer.valueOf(i2));
        contentValues.put(Tuesday, Integer.valueOf(i3));
        contentValues.put(Wednesday, Integer.valueOf(i4));
        contentValues.put(Thursday, Integer.valueOf(i5));
        contentValues.put(Friday, Integer.valueOf(i6));
        contentValues.put(Saturday, Integer.valueOf(i7));
        long replace = writableDatabase.replace(TABLE_TIMER, null, contentValues);
        writableDatabase.close();
        return replace;
    }

    public List<TimmerM> getAllTimmer() {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor rawQuery = readableDatabase.rawQuery("SELECT * FROM table_timer", null);
        if (rawQuery.getCount() > 0) {
            rawQuery.moveToFirst();
            do {
                TimmerM timmerM = new TimmerM();
                timmerM.f90id = rawQuery.getInt(rawQuery.getColumnIndex(f108ID));
                timmerM.name = rawQuery.getString(rawQuery.getColumnIndex(TIMER_NAME));
                timmerM.onTime = rawQuery.getString(rawQuery.getColumnIndex(ON_TIME));
                timmerM.offTime = rawQuery.getString(rawQuery.getColumnIndex(OFF_TIME));
                timmerM.sunday = rawQuery.getInt(rawQuery.getColumnIndex(Sunday));
                timmerM.monday = rawQuery.getInt(rawQuery.getColumnIndex(Monday));
                timmerM.tuesday = rawQuery.getInt(rawQuery.getColumnIndex(Tuesday));
                timmerM.wendesday = rawQuery.getInt(rawQuery.getColumnIndex(Wednesday));
                timmerM.friday = rawQuery.getInt(rawQuery.getColumnIndex(Friday));
                timmerM.saturday = rawQuery.getInt(rawQuery.getColumnIndex(Saturday));
                timmerM.thursday = rawQuery.getInt(rawQuery.getColumnIndex(Thursday));
                arrayList.add(timmerM);
            } while (rawQuery.moveToNext());
        }
        rawQuery.close();
        readableDatabase.close();
        return arrayList;
    }

    public TimmerM getTimmer(int i) {
        TimmerM timmerM = new TimmerM();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM table_timer WHERE id = '");
        sb.append(i);
        sb.append("'");
        String sb2 = sb.toString();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor rawQuery = readableDatabase.rawQuery(sb2, null);
        if (rawQuery.getCount() > 0) {
            rawQuery.moveToFirst();
            do {
                timmerM.f90id = rawQuery.getInt(rawQuery.getColumnIndex(f108ID));
                timmerM.name = rawQuery.getString(rawQuery.getColumnIndex(TIMER_NAME));
                timmerM.onTime = rawQuery.getString(rawQuery.getColumnIndex(ON_TIME));
                timmerM.offTime = rawQuery.getString(rawQuery.getColumnIndex(OFF_TIME));
                timmerM.sunday = rawQuery.getInt(rawQuery.getColumnIndex(Sunday));
                timmerM.monday = rawQuery.getInt(rawQuery.getColumnIndex(Monday));
                timmerM.tuesday = rawQuery.getInt(rawQuery.getColumnIndex(Tuesday));
                timmerM.wendesday = rawQuery.getInt(rawQuery.getColumnIndex(Wednesday));
                timmerM.friday = rawQuery.getInt(rawQuery.getColumnIndex(Friday));
                timmerM.saturday = rawQuery.getInt(rawQuery.getColumnIndex(Saturday));
                timmerM.thursday = rawQuery.getInt(rawQuery.getColumnIndex(Thursday));
            } while (rawQuery.moveToNext());
        }
        rawQuery.close();
        readableDatabase.close();
        return timmerM;
    }

    public void updateOntime(int i, String str) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ON_TIME, str);
        StringBuilder sb = new StringBuilder();
        sb.append("id = ");
        sb.append(i);
        writableDatabase.update(TABLE_TIMER, contentValues, sb.toString(), null);
        writableDatabase.close();
    }

    public void updateOfftime(int i, String str) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(OFF_TIME, str);
        StringBuilder sb = new StringBuilder();
        sb.append("id = ");
        sb.append(i);
        writableDatabase.update(TABLE_TIMER, contentValues, sb.toString(), null);
        writableDatabase.close();
    }

    public void updateDay(int i, String str) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(str);
        sb.append(" FROM ");
        String str2 = TABLE_TIMER;
        sb.append(str2);
        sb.append(" WHERE ");
        sb.append(f108ID);
        sb.append(" = '");
        sb.append(i);
        sb.append("'");
        Cursor rawQuery = writableDatabase.rawQuery(sb.toString(), null);
        ContentValues contentValues = new ContentValues();
        if (rawQuery.getCount() > 0) {
            rawQuery.moveToFirst();
            if (rawQuery.getInt(rawQuery.getColumnIndex(str)) == 0) {
                contentValues.put(str, Integer.valueOf(1));
            } else {
                contentValues.put(str, Integer.valueOf(0));
            }
        }
        rawQuery.close();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("id = ");
        sb2.append(i);
        writableDatabase.update(str2, contentValues, sb2.toString(), null);
        writableDatabase.close();
    }

    public int getDayValue(int i, String str) {
        int i2;
        SQLiteDatabase writableDatabase = getWritableDatabase();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(str);
        sb.append(" FROM ");
        sb.append(TABLE_TIMER);
        sb.append(" WHERE ");
        sb.append(f108ID);
        sb.append(" = '");
        sb.append(i);
        sb.append("'");
        Cursor rawQuery = writableDatabase.rawQuery(sb.toString(), null);
        if (rawQuery.getCount() > 0) {
            rawQuery.moveToFirst();
            i2 = rawQuery.getInt(rawQuery.getColumnIndex(str));
        } else {
            i2 = 0;
        }
        rawQuery.close();
        writableDatabase.close();
        return i2;
    }

    public boolean deleteTimmmer(int i) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        StringBuilder sb = new StringBuilder();
        sb.append("id = '");
        sb.append(i);
        sb.append("'");
        boolean z = writableDatabase.delete(TABLE_TIMER, sb.toString(), null) > 0;
        writableDatabase.close();
        return z;
    }

    public void updateIsEnable(int i) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT is_enable FROM table_timer WHERE id = '");
        sb.append(i);
        sb.append("'");
        Cursor rawQuery = writableDatabase.rawQuery(sb.toString(), null);
        ContentValues contentValues = new ContentValues();
        if (rawQuery.getCount() > 0) {
            rawQuery.moveToFirst();
            String str = IS_ENABLE;
            if (rawQuery.getInt(rawQuery.getColumnIndex(str)) == 0) {
                contentValues.put(str, Integer.valueOf(1));
            } else {
                contentValues.put(str, Integer.valueOf(0));
            }
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("id = ");
        sb2.append(i);
        writableDatabase.update(TABLE_TIMER, contentValues, sb2.toString(), null);
        writableDatabase.close();
    }

    public int getIsEnable(int i) {
        int i2;
        SQLiteDatabase writableDatabase = getWritableDatabase();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT is_enable FROM table_timer WHERE id = '");
        sb.append(i);
        sb.append("'");
        Cursor rawQuery = writableDatabase.rawQuery(sb.toString(), null);
        if (rawQuery.getCount() > 0) {
            rawQuery.moveToFirst();
            i2 = rawQuery.getInt(rawQuery.getColumnIndex(IS_ENABLE));
        } else {
            i2 = 0;
        }
        rawQuery.close();
        writableDatabase.close();
        return i2;
    }

    public String getOnTime(int i) {
        String str;
        SQLiteDatabase writableDatabase = getWritableDatabase();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT on_time FROM table_timer WHERE id = '");
        sb.append(i);
        sb.append("' AND ");
        sb.append(IS_ENABLE);
        sb.append(" = 1");
        Cursor rawQuery = writableDatabase.rawQuery(sb.toString(), null);
        if (rawQuery.getCount() > 0) {
            rawQuery.moveToFirst();
            str = rawQuery.getString(rawQuery.getColumnIndex(ON_TIME));
        } else {
            str = "";
        }
        rawQuery.close();
        writableDatabase.close();
        return str;
    }

    public String getOffTime(int i) {
        String str;
        SQLiteDatabase writableDatabase = getWritableDatabase();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT off_time FROM table_timer WHERE id = '");
        sb.append(i);
        sb.append("' AND ");
        sb.append(IS_ENABLE);
        sb.append(" = 1");
        Cursor rawQuery = writableDatabase.rawQuery(sb.toString(), null);
        if (rawQuery.getCount() > 0) {
            rawQuery.moveToFirst();
            str = rawQuery.getString(rawQuery.getColumnIndex(OFF_TIME));
        } else {
            str = "";
        }
        rawQuery.close();
        writableDatabase.close();
        return str;
    }

    public void updatePuchaseToken(String str, boolean z) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PURCHASE_TOKEN, str);
        StringBuilder sb = new StringBuilder();
        sb.append(z);
        sb.append("");
        contentValues.put(ACKNOWLEDGED, sb.toString());
        writableDatabase.update(TABLE_ADD_CTRL, contentValues, "id = 1", null);
        writableDatabase.close();
    }

    public void updateContry(String str, String str2) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CONTRY, str);
        contentValues.put(APP_VERSION, str2);
        writableDatabase.update(TABLE_ADD_CTRL, contentValues, "id = 1", null);
        writableDatabase.close();
    }

    public void updateIsRefund(int i) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(IS_REFUND, Integer.valueOf(i));
        writableDatabase.update(TABLE_ADD_CTRL, contentValues, "id = 1", null);
        writableDatabase.close();
    }

    public int updateKeyCount() {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery("SELECT key_count FROM add_ctrl WHERE id = 1", null);
        int count = rawQuery.getCount();
        int i = 0;
        Integer valueOf = Integer.valueOf(0);
        String str = KEY_COUNT;
        if (count > 0) {
            rawQuery.moveToFirst();
            i = rawQuery.getInt(rawQuery.getColumnIndex(str));
        }
        rawQuery.close();
        ContentValues contentValues = new ContentValues();
        int count2 = rawQuery.getCount();
        String str2 = TABLE_ADD_CTRL;
        if (count2 == 0) {
            contentValues.put(f108ID, Integer.valueOf(1));
            contentValues.put(str, valueOf);
            writableDatabase.insert(str2, null, contentValues);
        } else {
            if (Utility.ADD_KEY_COUNT <= i) {
                contentValues.put(str, valueOf);
            } else {
                contentValues.put(str, Integer.valueOf(i + 1));
            }
            writableDatabase.update(str2, contentValues, "id = 1", null);
        }
        writableDatabase.close();
        return i;
    }

    public int BolockAds(int i) {
        int i2;
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery("SELECT key_count FROM add_ctrl WHERE id = 1", null);
        String str = "ajaksajaksja 1 ";
        System.out.println(str);
        if (rawQuery.getCount() > 0) {
            rawQuery.moveToFirst();
            i2 = rawQuery.getInt(rawQuery.getColumnIndex(KEY_COUNT));
        } else {
            i2 = 0;
        }
        rawQuery.close();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SHOW_ADS, Integer.valueOf(i));
        if (i == Utility.HIDE_ADS_TEMPORY) {
            contentValues.put(LAST_SHOW_DATE_TIME, format);
        }
        System.out.println(str);
        int count = rawQuery.getCount();
        String str2 = TABLE_ADD_CTRL;
        if (count == 0) {
            contentValues.put(f108ID, Integer.valueOf(1));
            writableDatabase.insert(str2, null, contentValues);
            System.out.println("ajaksajaksja 2 ");
        } else {
            writableDatabase.update(str2, contentValues, "id = 1", null);
            System.out.println("ajaksajaksja 3 ");
        }
        writableDatabase.close();
        return i2;
    }

    public boolean showAddAgain() {
        boolean z;
        SQLiteDatabase writableDatabase = getWritableDatabase();
        String str = "SELECT * FROM add_ctrl WHERE datetime(last_show_date_time) < datetime('now','-6 hour','localtime') ";
        Cursor rawQuery = writableDatabase.rawQuery(str, null);
        PrintStream printStream = System.out;
        StringBuilder sb = new StringBuilder();
        sb.append("alaksallak 1");
        sb.append(str);
        printStream.println(sb.toString());
        if (rawQuery.getCount() > 0) {
            rawQuery.moveToFirst();
            System.out.println("alaksallak 2");
            String str2 = SHOW_ADS;
            if (rawQuery.getInt(rawQuery.getColumnIndex(str2)) != Utility.HIDE_ADS_PERMENETLY || rawQuery.getInt(rawQuery.getColumnIndex(IS_REFUND)) == Utility.REFUND) {
                System.out.println("alaksallak 3");
                z = true;
                ContentValues contentValues = new ContentValues();
                contentValues.put(str2, Integer.valueOf(Utility.SHOW_ADS));
                writableDatabase.update(TABLE_ADD_CTRL, contentValues, "id = 1", null);
                rawQuery.close();
                writableDatabase.close();
                return z;
            }
        }
        z = false;
        rawQuery.close();
        writableDatabase.close();
        return z;
    }

    public int isShowAdd() {
        int i;
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery("SELECT * FROM add_ctrl", null);
        if (rawQuery.getCount() > 0) {
            rawQuery.moveToFirst();
            i = rawQuery.getInt(rawQuery.getColumnIndex(SHOW_ADS));
        } else {
            i = Utility.SHOW_ADS;
        }
        rawQuery.close();
        writableDatabase.close();
        PrintStream printStream = System.out;
        StringBuilder sb = new StringBuilder();
        sb.append("lakslakslajska ");
        sb.append(i);
        printStream.println(sb.toString());
        return i;
    }

    public AdsControll getAdsControlData() {
        AdsControll adsControll = new AdsControll();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery("SELECT * FROM add_ctrl", null);
        if (rawQuery.getCount() > 0) {
            rawQuery.moveToFirst();
            adsControll.installDate = rawQuery.getString(rawQuery.getColumnIndex(INSTALL_DATE));
            adsControll.purchaseToken = rawQuery.getString(rawQuery.getColumnIndex(PURCHASE_TOKEN));
            adsControll.country = rawQuery.getString(rawQuery.getColumnIndex(CONTRY));
            adsControll.isRefund = rawQuery.getInt(rawQuery.getColumnIndex(IS_REFUND));
            adsControll.adState = rawQuery.getInt(rawQuery.getColumnIndex(SHOW_ADS));
            adsControll.keyCount = rawQuery.getInt(rawQuery.getColumnIndex(KEY_COUNT));
            adsControll.appVersion = rawQuery.getString(rawQuery.getColumnIndex(APP_VERSION));
            adsControll.acknowledged = rawQuery.getString(rawQuery.getColumnIndex(ACKNOWLEDGED));
        }
        rawQuery.close();
        writableDatabase.close();
        return adsControll;
    }
}
