package objis.com.database.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeo-sglo on 25/12/16.
 */

public class EtudiantDAO{
    private static EtudiantDAO sEtudiantDAO;

    private Context mContext;
    private SQLiteDatabase mDB;


    public EtudiantDAO(Context context) {
        mContext = context.getApplicationContext();
        mDB = new MyDBHelper(mContext).getWritableDatabase();
    }

    public Long ajouter(Etudiant etudiant) {
        ContentValues values = getContentValues(etudiant);
        return mDB.insert(MyDBSchema.EtudiantTable.NAME, null, values);
    }

    public int modifier(Etudiant etudiant) {
        ContentValues values = getContentValues(etudiant);
        return mDB.update(MyDBSchema.EtudiantTable.NAME, values,
                MyDBSchema.EtudiantTable.MyColumns.id + " = ?",
                new String[]{String.valueOf(etudiant.getId())});
    }

    public void delete(Long keyId) {
        mDB.delete(MyDBSchema.EtudiantTable.NAME,
                MyDBSchema.EtudiantTable.MyColumns.id + " = ?",
                new String[]{String.valueOf(keyId)});
    }


    public List<Etudiant> getAll() {
        List<Etudiant> myListes = new ArrayList<>();

        EtudiantCursorWrapper cursorWrapper = etudiantCursorWrapper(null, null);

        try {
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()) {
                myListes.add(cursorWrapper.getEtudiant());
                cursorWrapper.moveToNext();
            }
        } finally {
            cursorWrapper.close();
        }

        return myListes;
    }

    public Etudiant getOne(Long id) {
        EtudiantCursorWrapper cursorWrapper = etudiantCursorWrapper(
                MyDBSchema.EtudiantTable.MyColumns.id + " = ?",
                new String[]{id.toString()}
        );

        try {
            if (cursorWrapper.getCount() == 0) {
                return null;
            }
            cursorWrapper.moveToFirst();

            return cursorWrapper.getEtudiant();

        } finally {
            cursorWrapper.close();
        }
    }


    public int countEtudiant() {
        List<Etudiant> etudiants = new ArrayList<>();
        EtudiantCursorWrapper cursorWrapper = etudiantCursorWrapper(null, null);
        return cursorWrapper.getCount();
    }


    //cette methode nous permetra de creer notre objet "ContentValues" une seul foi
    private static ContentValues getContentValues(Etudiant etudiant) {
        ContentValues values = new ContentValues();
        //values.put(MyDBSchema.EtudiantTable.MyColumns.id, etudiant.getId());
        values.put(MyDBSchema.EtudiantTable.MyColumns.nom, etudiant.getNom());
        values.put(MyDBSchema.EtudiantTable.MyColumns.prenom, etudiant.getPrenom());
        values.put(MyDBSchema.EtudiantTable.MyColumns.dateNaiss, etudiant.getDateNaiss().getTime());
        values.put(MyDBSchema.EtudiantTable.MyColumns.lieuNaiss, etudiant.getLieuNaiss());
        values.put(MyDBSchema.EtudiantTable.MyColumns.filiere, etudiant.getFiliere());
        return values;
    }


    public EtudiantCursorWrapper etudiantCursorWrapper(String whereClaure, String[] whereArgs) {
        Cursor cursor = mDB.query(MyDBSchema.EtudiantTable.NAME,
                        null,
                        whereClaure,
                        whereArgs,
                        null,       //groupBy
                        null,       //having
                        null       //orderBy
        );
        return new EtudiantCursorWrapper(cursor);
    }

}
