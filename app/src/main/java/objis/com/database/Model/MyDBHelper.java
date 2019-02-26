package objis.com.database.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yeo-sglo on 25/12/16.
 */

public class  MyDBHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "etablissement.db";

    public MyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDB) {

        sqLiteDB.execSQL("CREATE TABLE "+ MyDBSchema.EtudiantTable.NAME+" ("+  MyDBSchema.EtudiantTable.MyColumns.id+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                MyDBSchema.EtudiantTable.MyColumns.nom+" TEXT,"+
                MyDBSchema.EtudiantTable.MyColumns.prenom+" TEXT,"+
                MyDBSchema.EtudiantTable.MyColumns.dateNaiss+" TEXT,"+
                MyDBSchema.EtudiantTable.MyColumns.lieuNaiss+" TEXT,"+
                MyDBSchema.EtudiantTable.MyColumns.filiere+" TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDB, int i, int i1) {
        sqLiteDB.execSQL("DROP TABLE IF EXISTS "+MyDBSchema.EtudiantTable.NAME);
        onCreate(sqLiteDB);
    }
}
