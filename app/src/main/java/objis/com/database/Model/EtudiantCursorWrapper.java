package objis.com.database.Model;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;

import objis.com.database.Model.Etudiant;
import objis.com.database.Model.MyDBSchema;

/**
 * Created by yeo-sglo on 26/12/16.
 */

public class EtudiantCursorWrapper extends CursorWrapper {

    public EtudiantCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Etudiant getEtudiant(){
         String mId       =getString(getColumnIndex(MyDBSchema.EtudiantTable.MyColumns.id));
         String nom       =getString(getColumnIndex(MyDBSchema.EtudiantTable.MyColumns.nom));
         String prenom    =getString(getColumnIndex(MyDBSchema.EtudiantTable.MyColumns.prenom));
         Long dateNaiss   =getLong(getColumnIndex(MyDBSchema.EtudiantTable.MyColumns.dateNaiss));
         String lieuNaiss =getString(getColumnIndex(MyDBSchema.EtudiantTable.MyColumns.lieuNaiss));
         String filiere   =getString(getColumnIndex(MyDBSchema.EtudiantTable.MyColumns.filiere));

        Etudiant etudiant = new Etudiant();
        etudiant.setId(Long.valueOf(mId));
        etudiant.setNom(nom);
        etudiant.setPrenom(prenom);
        etudiant.setDateNaiss(new Date(dateNaiss));
        etudiant.setLieuNaiss(lieuNaiss);
        etudiant.setFiliere(filiere);

        return etudiant;

    }

}
