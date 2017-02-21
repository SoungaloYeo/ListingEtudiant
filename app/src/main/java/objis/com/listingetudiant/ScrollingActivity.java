package objis.com.listingetudiant;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import objis.com.database.Model.Etudiant;
import objis.com.database.Model.EtudiantDAO;

public class ScrollingActivity extends AppCompatActivity {

    private TextView nom;
    private TextView prenom;
    private TextView lieu;
    private TextView filiere;

    private FloatingActionMenu fam;
    private FloatingActionButton fabDelete;
    private FloatingActionButton fabEdit;
    private FloatingActionButton fabBack;

    private AlertDialog.Builder mBuilder;

    public static final String ETUD_ID = "objis.com.listingetudiant.ScrollingActivity.ETUD_ID";
    private EtudiantDAO mEtudiantDAO;
    private Long etud_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrolling_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        etud_id= getIntent().getLongExtra(ETUD_ID, 0);

        initView(etud_id);

        fabBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        fabDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogue(mBuilder);
            }
        });

        fabEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = EtudiantModifActivity.newIntent(getApplicationContext(), etud_id);
                startActivity(intent);
            }
        });

    }

    private void initView(Long lg) {

        nom = (TextView) findViewById(R.id.txt_show_nom);
        prenom = (TextView) findViewById(R.id.txt_show_prenom);
        lieu = (TextView) findViewById(R.id.txt_show_lieu);
        filiere = (TextView) findViewById(R.id.txt_show_filiere);

        mEtudiantDAO = new EtudiantDAO(this);
        Etudiant etudiant = mEtudiantDAO.getOne(lg);

        nom.setText(etudiant.getNom());
        prenom.setText(etudiant.getPrenom());
        lieu.setText(etudiant.getLieuNaiss());
        filiere.setText(etudiant.getFiliere());

        fam = (FloatingActionMenu) findViewById(R.id.floating_action_menu);
        fabDelete = (FloatingActionButton) findViewById(R.id.floating_btn_delete);
        fabBack = (FloatingActionButton) findViewById(R.id.floating_btn_back);
        fabEdit = (FloatingActionButton) findViewById(R.id.floating_btn_edit);
    }

    private void alertDialogue(AlertDialog.Builder dBuilder) {
        dBuilder = new AlertDialog.Builder(this);
        dBuilder.setTitle("Confirmer");
        dBuilder.setMessage("ce etudiant sera supprimé definitivement de " +
                "votre Base de données");
        dBuilder.setIcon(R.mipmap.ic_intero);

        dBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mEtudiantDAO.delete(etud_id);
                finish();
                Toast.makeText(getApplicationContext(), "supprimé", Toast.LENGTH_SHORT).show();
                // return;
            }
        });

        dBuilder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "annulé", Toast.LENGTH_SHORT).show();
                // return;
            }
        });

        dBuilder.create();
        dBuilder.show();
    }
}
