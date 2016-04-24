package com.example.shaunmesias.assignment6.Repository.Registration.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.shaunmesias.assignment6.Conf.Databases.Database;
import com.example.shaunmesias.assignment6.Domain.Driver.Driver;
import com.example.shaunmesias.assignment6.Domain.Registration.Registration;
import com.example.shaunmesias.assignment6.Factories.RegistrationImpl.RegistrationFactoryImpl;
import com.example.shaunmesias.assignment6.Repository.Driver.Impl.DriverRepositoryImpl;
import com.example.shaunmesias.assignment6.Repository.Person.Impl.PersonRepositoryImpl;
import com.example.shaunmesias.assignment6.Repository.Registration.RegistrationRepository;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Shaun Mesias on 2016/04/24.
 */
public class RegistrationRepositoryImpl extends SQLiteOpenHelper implements RegistrationRepository{
    public static final String TABLE_NAME = "Register";
    private SQLiteDatabase database;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_EMAIL = "email";

    public static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
            + COLUMN_USERNAME + " TEXT NOT NULL , "
            + COLUMN_PASSWORD + " TEXT NOT NULL , "
            + COLUMN_EMAIL + " TEXT NOT NULL )";

    public RegistrationRepositoryImpl(Context context) {
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
        db.execSQL(DriverRepositoryImpl.DATABASE_CREATE);
        db.execSQL(PersonRepositoryImpl.DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS" + PersonRepositoryImpl.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS" + DriverRepositoryImpl.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS" + RegistrationRepositoryImpl.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public Registration findById(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_ID, COLUMN_USERNAME, COLUMN_PASSWORD, COLUMN_EMAIL}
                , COLUMN_ID + " =?"
                , new String[]{String.valueOf(id)}
                , null, null, null, null);
        if(cursor.moveToFirst()){

            final Registration r = new Registration.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .username(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)))
                    .password(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)))
                    .email(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)))
                    .build();
            return r;
        }
        else {
            return null;
        }
    }

    @Override
    public Registration save(Registration entity) {

            open();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, entity.getUsername());
        values.put(COLUMN_PASSWORD, entity.getPassword());
        values.put(COLUMN_EMAIL, entity.getEmail());

        long id = database.insertOrThrow(TABLE_NAME, null, values);
        Registration insertedEntity = new Registration.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();

        return insertedEntity;
    }

    @Override
    public Registration update(Registration entity) {

            open();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_USERNAME, entity.getUsername());
        values.put(COLUMN_PASSWORD, entity.getPassword());
        values.put(COLUMN_EMAIL, entity.getEmail());

        database.update(TABLE_NAME, values, COLUMN_ID + " =? ", new String[]{String.valueOf(entity.getId())});

        return entity;
    }

    @Override
    public Registration delete(Registration entity) {

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
    public Set<Registration> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Registration> Person = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Registration r = new Registration.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .username(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)))
                        .password(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)))
                        .email(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)))
                        .build();
                Person.add(r);
            } while (cursor.moveToNext());
        }
        return Person;
    }
}
