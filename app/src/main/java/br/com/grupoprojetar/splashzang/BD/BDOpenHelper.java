package br.com.grupoprojetar.splashzang.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "recorde";
    private static final int DATABASE_VERSION = 1;

    private BDOpenHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_RECORDE = "CREATE TABLE " +Recorde.TABLE_NAME+ " ("
                +Recorde.KEY_ID+ " integer primary key, " +Recorde.KEY_RECORDE+
                " long);";

        db.execSQL(CREATE_TABLE_RECORDE);

        String INICIALIZA_TABELA_RECORDE = "INSERT INTO " +Recorde.TABLE_NAME+
                " (" +Recorde.KEY_ID+ ", " +Recorde.KEY_RECORDE+ ")VALUES(1,0);";

        db.execSQL(INICIALIZA_TABELA_RECORDE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
