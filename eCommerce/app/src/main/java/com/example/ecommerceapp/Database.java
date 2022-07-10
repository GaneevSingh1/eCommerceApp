package com.example.ecommerceapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.ecommerceapp.models.Item;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String COLUMN_ITEM_NAME = "ITEM_NAME";
    public static final String COLUMN_ITEM_PRICE = "ITEM_PRICE";
    public static final String COLUMN_ID = "ID";

    public Database(@Nullable Context context) {
        super(context, "item.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + CUSTOMER_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_ITEM_NAME + " TEXT, " + COLUMN_ITEM_PRICE + " INT)";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addItem(Item itemModel){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ITEM_NAME, itemModel.getName());
        cv.put(COLUMN_ITEM_PRICE, itemModel.getSellPrice());

        long insetResult = db.insert(CUSTOMER_TABLE, null, cv);
        if (insetResult == -1){
            return false;
        }else {
            return true;
        }
    }

    public ArrayList<Item> getAllItems() {
        ArrayList<Item> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + CUSTOMER_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            do {
                int itemID = cursor.getInt(0);
                String itemName = cursor.getString(1);
                int itemPrice = cursor.getInt(2);

                Item newItem = new Item(itemID, itemName, itemPrice);
                returnList.add(newItem);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return returnList;
    }

    public boolean deleteItem(Item item){
        String queryString = "DELETE FROM " + CUSTOMER_TABLE + " WHERE " + COLUMN_ID + " = " + item.getId();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            return true;
        } else {
            return false;
        }
    }
}
