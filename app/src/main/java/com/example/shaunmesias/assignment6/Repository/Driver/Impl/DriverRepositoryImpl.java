package com.example.shaunmesias.assignment6.Repository.Driver.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.shaunmesias.assignment6.Conf.Databases.Database;
import com.example.shaunmesias.assignment6.Domain.Driver.Driver;
import com.example.shaunmesias.assignment6.Repository.Driver.DriverRepository;
import com.example.shaunmesias.assignment6.Repository.Person.Impl.PersonRepositoryImpl;
import com.example.shaunmesias.assignment6.Repository.Registration.Impl.RegistrationRepositoryImpl;

import java.util.HashSet;
import java.util.Set;


/**
 * Created by Shaun Mesias on 2016/04/20.
 */
public class DriverRepositoryImpl extends SQLiteOpenHelper implements DriverRepository{
    public static final String TABLE_NAME = "Driver";
    private SQLiteDatabase database;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_AREA = "area";
    public static final String COLUMN_CELLULAR = "cellular";
    public static final String COLUMN_DRIVER_DETAILS = "registration";

    public static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
            + COLUMN_NAME + " TEXT NOT NULL , "
            + COLUMN_AREA + " TEXT NOT NULL , "
            + COLUMN_CELLULAR + " TEXT NOT NULL , "
            + COLUMN_DRIVER_DETAILS + " TEXT )";

    public DriverRepositoryImpl(Context context) {
        super(context, Database.DATABASE_NAME, null, Database.DATABASE_VERSION);
    }

    public void open(){
        database = this.getWritableDatabase();
    }

    public void close(){
        this.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DATABASE_CREATE);
        db.execSQL(PersonRepositoryImpl.DATABASE_CREATE);
        db.execSQL(RegistrationRepositoryImpl.DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS" + PersonRepositoryImpl.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS" + RegistrationRepositoryImpl.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public Driver findById(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_AREA, COLUMN_CELLULAR, COLUMN_DRIVER_DETAILS}
                , COLUMN_ID + " =?"
                , new String[]{String.valueOf(id)}
                , null, null, null, null);
        if(cursor.moveToFirst()){
            final Driver driver = new Driver.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .area(cursor.getString(cursor.getColumnIndex(COLUMN_AREA)))
                    .cellNumber(cursor.getString(cursor.getColumnIndex(COLUMN_CELLULAR)))
                    .driverDetails(cursor.getString(cursor.getColumnIndex(COLUMN_DRIVER_DETAILS)))
                    .build();
            return driver;
        }
        else {
            return null;
        }
    }

    @Override
    public Driver save(Driver entity) {
        open();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_AREA, entity.getArea());
        values.put(COLUMN_CELLULAR, entity.getDriverCellNumber());
        values.put(COLUMN_DRIVER_DETAILS, entity.getRegistrationNumber());

        long id = database.insertOrThrow(TABLE_NAME, null, values);
        Driver insertedEntity = new Driver.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Driver update(Driver entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_AREA, entity.getArea());
        values.put(COLUMN_CELLULAR, entity.getDriverCellNumber());
        values.put(COLUMN_DRIVER_DETAILS, entity.getRegistrationNumber());

        database.update(TABLE_NAME, values, COLUMN_ID + " =? ", new String[]{String.valueOf(entity.getId())});

        return entity;
    }

    @Override
    public Driver delete(Driver entity) {
        open();
        database.delete(TABLE_NAME, COLUMN_ID + " =?", new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public int deleteAll() {

        open();
        int rowsDeleted = database.delete(TABLE_NAME,null,null);
        return rowsDeleted;
    }

    @Override
    public Set<Driver> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Driver> Person = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Driver driver = new Driver.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .area(cursor.getString(cursor.getColumnIndex(COLUMN_AREA)))
                        .cellNumber(cursor.getString(cursor.getColumnIndex(COLUMN_CELLULAR)))
                        .driverDetails(cursor.getString(cursor.getColumnIndex(COLUMN_DRIVER_DETAILS)))
                        .build();
                Person.add(driver);
            } while (cursor.moveToNext());
        }
        return Person;
    }
}
