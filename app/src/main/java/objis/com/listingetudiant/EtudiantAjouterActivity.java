package objis.com.listingetudiant;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

import objis.com.database.Model.EtudiantDAO;
import objis.com.database.Model.Etudiant;

/**
 * Created by yeo-sglo on 08/01/17.
 */

public class EtudiantAjouterActivity extends AppCompatActivity{

    public static final String ETUD_ID = "objis.com.database.Model.Etudiant.EtudiantAjouterActivity";

    private Etudiant mEtudiant;
    private TextView mNomTextView;
    private TextView mPrenomTextView;
    private TextView mLieuNaissTextView;
    private TextView mFiliereTextView;

    private Button mAddButton;

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context, EtudiantAjouterActivity.class);
        return intent;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     setContentView(R.layout.ajout_etudiant);
        mEtudiant  = new Etudiant();

        mNomTextView = (EditText) findViewById(R.id.add_nom);

        mPrenomTextView = (TextView) findViewById(R.id.add_prenom);

        mLieuNaissTextView= (EditText) findViewById(R.id.add_lieu);

        mFiliereTextView = (EditText) findViewById(R.id.add_filiere);

        mAddButton = (Button) findViewById(R.id.btn_add);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEtudiant.setNom(mNomTextView.getText().toString());
                mEtudiant.setPrenom(mPrenomTextView.getText().toString());
                mEtudiant.setDateNaiss(new Date());
                mEtudiant.setLieuNaiss(mLieuNaissTextView.getText().toString());
                mEtudiant.setFiliere(mFiliereTextView.getText().toString());
                EtudiantDAO etudiantDAO = new EtudiantDAO(getApplicationContext());
                etudiantDAO.ajouter(mEtudiant);
                finish();
            }
        });

    }
}
