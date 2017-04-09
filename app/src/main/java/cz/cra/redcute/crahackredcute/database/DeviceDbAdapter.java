package cz.cra.redcute.crahackredcute.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import java.util.ArrayList;

import cz.cra.redcute.crahackredcute.model.Device;

/**
 * Created by obrusvit on 15.3.17.
 */

public class DeviceDbAdapter {
    private static final String DATABASE_NAME = "devices.db";
    private static final int DATABASE_VERSION = 1;

    public static final String DEVICE_TABLE = "device";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_DEVEUID = "deveuid";
    public static final String COLUMN_NAME = "name";


    private String[] allColumns = {COLUMN_ID, COLUMN_DEVEUID, COLUMN_NAME};

    public static final String CREATE_TABLE_BOX = "create table " + DEVICE_TABLE + " ( "
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_DEVEUID + " text not null, "
            + COLUMN_NAME + " text not null); ";

    private SQLiteDatabase sqlDB;
    private Context context;

    private BoxesDbHelper boxesDbHelper;

    public DeviceDbAdapter(Context ctx){
        context = ctx;
    }

    public DeviceDbAdapter open() throws android.database.SQLException{
        boxesDbHelper = new BoxesDbHelper(context);
        sqlDB = boxesDbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        boxesDbHelper.close();
    }

    public Device createDevice(String deveui, String name){
        ContentValues values = new ContentValues();
        values.put(COLUMN_DEVEUID, deveui);
        values.put(COLUMN_NAME, name);
        //values.put(COLUMN_DATE, Calendar.getInstance().getTimeInMillis() + "");

        long insertId = sqlDB.insert(DEVICE_TABLE, null, values);

        Cursor cursor = sqlDB.query(DEVICE_TABLE, allColumns, COLUMN_ID + " = "  + insertId,
                null, null, null, null);
        cursor.moveToFirst();

        Device newDevice = cursorToDevice(cursor);
        cursor.close();

        return newDevice;
    }

    public long deleteBox(long idToDelete){
        return sqlDB.delete(DEVICE_TABLE, COLUMN_ID + " = " + idToDelete, null);
    }

    /**
     *
     * @return rows affected by this update. Generally will be 1.
     */
    /*public long updateBox(long idToUpdate, String newIsFull){

        ContentValues values = new ContentValues();

        values.put(COLUMN_ISFULL, newIsFull);

        return sqlDB.update(DEVICE_TABLE, values, COLUMN_ID + " = " + idToUpdate, null);
    }*/

    public ArrayList<Device> getAllBoxes(){
        ArrayList<Device> boxes = new ArrayList<>();

        //grab all of the information in our database for the notes in it
        Cursor cursor = sqlDB.query(DEVICE_TABLE, allColumns, null, null, null, null, null);

        for(cursor.moveToLast(); !cursor.isBeforeFirst(); cursor.moveToPrevious()){
            Device device = cursorToDevice(cursor);
            boxes.add(device);
        }

        cursor.close();
        return boxes;
    }

    private Device cursorToDevice(Cursor cursor) {
        Device newDevice = new Device(cursor.getString(1), cursor.getString(2), cursor.getLong(0));
        return newDevice;
    }

    private static class BoxesDbHelper extends SQLiteOpenHelper {

        BoxesDbHelper(Context cxt){
            super(cxt, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_BOX);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(BoxesDbHelper.class.getName(), "Upgrading db from version " + oldVersion
                    + " to " +newVersion + ", which will destroy old data");
            db.execSQL("DROP TABLE IF EXISTS " + DEVICE_TABLE);
            onCreate(db);
        }
    }
}
