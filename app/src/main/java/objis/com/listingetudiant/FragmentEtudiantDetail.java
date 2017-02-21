package objis.com.listingetudiant;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import objis.com.database.Model.EtudiantDAO;
import objis.com.database.Model.Etudiant;

/**
 * Created by yeo-sglo on 08/01/17.
 */

public class FragmentEtudiantDetail extends Fragment {

    public static final String ETUD_ID = "objis.com.listingetudiant.FragmentEtudiantDetail.etud_id";
    public static final int RESULT_INT = 0;

    private TextView mNomTextView;
    private TextView mPrenomTextView;
    private TextView mLieuNaissTextView;
    private TextView mFiliereTextView;

    private Etudiant mEtudiant;
    private EtudiantDAO mEtudiantDAO;
    private Long id;

    private AlertDialog.Builder mBuilder;

    public static FragmentEtudiantDetail newIntent(Long id){
        Bundle bundle = new Bundle();
        bundle.putSerializable(ETUD_ID, id);

        FragmentEtudiantDetail fragmentEtudiantDetail = new FragmentEtudiantDetail();
        fragmentEtudiantDetail.setArguments(bundle);

        return fragmentEtudiantDetail;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        id = (Long) getArguments().getSerializable(ETUD_ID);
        getEtudiant(id);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details_etudiant, container, false);

        initView(view);

        return view;
    }



    @Override
    public void onStart() {
        Bundle bundle = null;
        onCreate(bundle);
        super.onStart();
    }

    @Override
    public void onResume() {

        super.onResume();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_fragment_afficher, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_delete:

                alertDialogue(this.mBuilder);
                break;
            case R.id.menu_edit:
                Intent intent = EtudiantModifActivity.newIntent(getContext(), id);
                //startActivity(intent);
                startActivityForResult(intent, RESULT_INT);
                break;
            default:
                System.out.println("pas d'action");
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case (RESULT_INT):{
                if(resultCode == Activity.RESULT_OK){
                    Long aLong = data.getLongExtra(EtudiantModifActivity.RESULT_STR, 0);
                    getEtudiant(aLong);
                    initView(this.getView());
                }
            }
        }
    }


    private void getEtudiant(Long id) {
        mEtudiantDAO = new EtudiantDAO(getContext());
        EtudiantDAO etudiantDAO = new EtudiantDAO(getContext());
        for (Etudiant e : etudiantDAO.getAll()) {
            if (e.getId().equals(id)){
                mEtudiant = e;
                break;
            }
        }
    }

    private void initView(View view) {
        mNomTextView = (TextView) view.findViewById(R.id.txt_show_nom);
        mNomTextView.setText(mEtudiant.getNom());

        mPrenomTextView = (TextView) view.findViewById(R.id.txt_show_prenom);
        mPrenomTextView.setText(mEtudiant.getPrenom());

        mLieuNaissTextView = (TextView) view.findViewById(R.id.txt_show_lieu);
        mLieuNaissTextView.setText(mEtudiant.getLieuNaiss());

        mFiliereTextView = (TextView) view.findViewById(R.id.txt_show_filiere);
        mFiliereTextView.setText(mEtudiant.getFiliere());
    }


    private void alertDialogue(AlertDialog.Builder dBuilder) {
        dBuilder = new AlertDialog.Builder(getActivity());
        dBuilder.setTitle("Confirmer");
        dBuilder.setMessage("ce etudiant sera supprimé definitivement de " +
                "votre Base de données");
        dBuilder.setIcon(R.mipmap.ic_intero);

        dBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mEtudiantDAO.delete(id);
                getActivity().finish();
                Toast.makeText(getActivity(), "supprimé", Toast.LENGTH_SHORT).show();
               // return;
            }
        });

        dBuilder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getActivity(), "annulé", Toast.LENGTH_SHORT).show();
               // return;
            }
        });

        dBuilder.create();
        dBuilder.show();
    }
}
