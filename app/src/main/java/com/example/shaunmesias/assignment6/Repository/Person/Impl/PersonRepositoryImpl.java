package com.example.shaunmesias.assignment6.Repository.Person.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.shaunmesias.assignment6.Conf.Databases.Database;
import com.example.shaunmesias.assignment6.Domain.Person.Person;
import com.example.shaunmesias.assignment6.Repository.Driver.Impl.DriverRepositoryImpl;
import com.example.shaunmesias.assignment6.Repository.Person.PersonRepository;
import com.example.shaunmesias.assignment6.Repository.Registration.Impl.RegistrationRepositoryImpl;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Shaun Mesias on 2016/04/19.
 */
public class PersonRepositoryImpl extends SQLiteOpenHelper implements PersonRepository{

    public static final String TABLE_NAME = "Person";
    private SQLiteDatabase database;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_CELLULAR = "cellular";
    public static final String COLUMN_VEHICLE_NAME = "vehicleName";
    public static final String COLUMN_VEHICLE_TYPE = "vehicleType";

    public static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
            + COLUMN_NAME + " TEXT NOT NULL , "
            + COLUMN_LOCATION + " TEXT NOT NULL , "
            + COLUMN_CELLULAR + " TEXT NOT NULL , "
            + COLUMN_VEHICLE_NAME + " TEXT , "
            + COLUMN_VEHICLE_TYPE + " TEXT )";

    public PersonRepositoryImpl(Context context) {
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
        db.execSQL(RegistrationRepositoryImpl.DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS" + DriverRepositoryImpl.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public Person findById(long aLong) {


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_LOCATION, COLUMN_CELLULAR, COLUMN_VEHICLE_NAME, COLUMN_VEHICLE_TYPE}
                , COLUMN_ID + " =?"
                , new String[]{String.valueOf(aLong)}
                , null, null, null, null);
        if(cursor.moveToFirst()){
            final Person person = new Person.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .location(cursor.getString(cursor.getColumnIndex(COLUMN_LOCATION)))
                    .cellNumber(cursor.getString(cursor.getColumnIndex(COLUMN_CELLULAR)))
                    .vehicleDetails(cursor.getString(cursor.getColumnIndex(COLUMN_VEHICLE_TYPE))
                            , cursor.getString(cursor.getColumnIndex(COLUMN_VEHICLE_NAME)))
                    .build();
            return person;
        }
        else {
            return null;
        }
    }

    @Override
    public Person save(Person entity) {

            open();


        ContentValues values = new ContentValues();
        //values.put(Database.COLUMN_ID, );
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_LOCATION, entity.getLocation());
        values.put(COLUMN_CELLULAR, entity.getPersonCellNumber());
        values.put(COLUMN_VEHICLE_NAME, entity.getVehicleName());
        values.put(COLUMN_VEHICLE_TYPE, entity.getType());

        long id = database.insertOrThrow(TABLE_NAME, null, values);
        Person insertedEntity = new Person.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Person update(Person entity) {

            open();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_LOCATION, entity.getLocation());
        values.put(COLUMN_CELLULAR, entity.getPersonCellNumber());
        values.put(COLUMN_VEHICLE_NAME, entity.getVehicleName());
        values.put(COLUMN_VEHICLE_TYPE, entity.getType());

        database.update(TABLE_NAME, values, COLUMN_ID + " =? ", new String[]{String.valueOf(entity.getId())});

        return entity;
    }

    @Override
    public Person delete(Person entity) {

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
    public Set<Person> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Person> Person = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Person person = new Person.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .location(cursor.getString(cursor.getColumnIndex(COLUMN_LOCATION)))
                        .cellNumber(cursor.getString(cursor.getColumnIndex(COLUMN_CELLULAR)))
                        .vehicleDetails(cursor.getString(cursor.getColumnIndex(COLUMN_VEHICLE_TYPE))
                                , cursor.getString(cursor.getColumnIndex(COLUMN_VEHICLE_NAME)))
                        .build();
                Person.add(person);
            } while (cursor.moveToNext());
        }
        return Person;
    }
}
