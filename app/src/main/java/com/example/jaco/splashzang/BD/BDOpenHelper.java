package com.example.jaco.splashzang.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jaco on 14/07/16.
 */
public class BDOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "recorde";
    public static final int DATABASE_VERSION = 1;

    public BDOpenHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_TABLE_RECORDE = "CREATE TABLE " +Recorde.TABLE_NAME+ " ("
                +Recorde.KEY_ID+ " integer primary key, " +Recorde.KEY_RECORDE+
                " long);";
        sqLiteDatabase.execSQL(CREATE_TABLE_RECORDE);

        String INICIALIZA_TABELA_RECORDE = "INSERT TO " +Recorde.TABLE_NAME+
                " (" +Recorde.KEY_ID+ ", " +Recorde.KEY_RECORDE+ ")VALUES(1,0);";
        sqLiteDatabase.execSQL(INICIALIZA_TABELA_RECORDE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
