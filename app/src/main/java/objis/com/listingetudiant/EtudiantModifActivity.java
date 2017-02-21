package objis.com.listingetudiant;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

import objis.com.database.Model.EtudiantDAO;
import objis.com.database.Model.Etudiant;


public class EtudiantModifActivity extends AppCompatActivity {

    public static final String ETUD_ID = "objis.com.listingetudiant.EtudiantModifActivity.etud_id";

    public static final String RESULT_STR = "my_str";

    private Etudiant mEtudiant;
    private EditText mNomEditText;
    private EditText mPrenomEditText;
    private EditText mLieuNaissEditText;
    private EditText mFiliereEditText;

    private Button mSaveButton;

    private Long etudId;

    public static Intent newIntent(Context context, Long id) {
        Intent intent = new Intent(context, EtudiantModifActivity.class);
        intent.putExtra(ETUD_ID, id);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modif_etudiant);

        etudId = (Long) getIntent().getSerializableExtra(ETUD_ID);

        EtudiantDAO etudiantDAO = new EtudiantDAO(getApplicationContext());
        for (Etudiant e : etudiantDAO.getAll()) {
            if (e.getId().equals(etudId)){
                mEtudiant = e;
                break;
            }
        }


        mNomEditText = (EditText) findViewById(R.id.edit_nom);
        mNomEditText.setText(mEtudiant.getNom());

        mPrenomEditText = (EditText) findViewById(R.id.edit_prenom);
        mPrenomEditText.setText(mEtudiant.getPrenom());

        mLieuNaissEditText = (EditText) findViewById(R.id.edit_lieu);
        mLieuNaissEditText.setText(mEtudiant.getLieuNaiss());

        mFiliereEditText = (EditText) findViewById(R.id.edit_filiere);
        mFiliereEditText.setText(mEtudiant.getFiliere());


        mSaveButton = (Button) findViewById(R.id.btn_save);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mEtudiant.setNom(mNomEditText.getText().toString());
                mEtudiant.setPrenom(mPrenomEditText.getText().toString());
                mEtudiant.setDateNaiss(new Date());// a voir
                mEtudiant.setLieuNaiss(mLieuNaissEditText.getText().toString());
                mEtudiant.setFiliere(mFiliereEditText.getText().toString());
                EtudiantDAO etudiantDAO = new EtudiantDAO(getApplicationContext());
                etudiantDAO.modifier(mEtudiant);

                Intent intent = new Intent();
                intent.putExtra(RESULT_STR, etudId);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}
