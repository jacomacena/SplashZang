package br.com.grupoprojetar.splashzang.BD;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class RecordeService {
    private BDOpenHelper bdOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    private RecordeService (BDOpenHelper pbdOpenHelper){
        bdOpenHelper = pbdOpenHelper;
    }

    private void open(){
        sqLiteDatabase = bdOpenHelper.getWritableDatabase();
    }

    private Long getRecorde(){
        Cursor cursor = sqLiteDatabase.query(Recorde.TABLE_NAME,
                new String[]{Recorde.KEY_ID + ",MAX(" + Recorde.KEY_RECORDE + ") as" + Recorde.KEY_RECORDE},
                null, null, null, null, null);
        cursor.moveToFirst();
        Long retorno = cursor.getLong(cursor.getColumnIndex(Recorde.KEY_RECORDE));
        cursor.close();

        return retorno;
    }

    private void novoRecorde(Long recorde){
        ContentValues values = new ContentValues();
        values.put(Recorde.KEY_RECORDE, recorde);
        sqLiteDatabase.update(Recorde.TABLE_NAME, values, "_id = ?", new String[]{Long.toString(1)});
    }
}
