package com.example.jaco.splashzang.BD;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by jaco on 14/07/16.
 */
public class RecordeService {

    private BDOpenHelper bdOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    public RecordeService (BDOpenHelper pbdOpenHelper){
        bdOpenHelper = pbdOpenHelper;
    }

    public void open(){
        sqLiteDatabase = bdOpenHelper.getWritableDatabase();
    }

    public Long getRecorde(){
        Cursor cursor = sqLiteDatabase.query(Recorde.TABLE_NAME,
                new String[]{Recorde.KEY_ID + ",MAX(" + Recorde.KEY_RECORDE + ") as" + Recorde.KEY_RECORDE},
                null, null, null, null, null);
        cursor.moveToFirst();
        Long retorno = cursor.getLong(cursor.getColumnIndex(Recorde.KEY_RECORDE));
        cursor.close();

        return retorno;
    }

    public void novoRecorde(Long recorde){
        ContentValues values = new ContentValues();
        values.put(Recorde.KEY_RECORDE, recorde);
        sqLiteDatabase.update(Recorde.TABLE_NAME, values, "_id = ?", new String[]{Long.toString(1)});
    }

}
