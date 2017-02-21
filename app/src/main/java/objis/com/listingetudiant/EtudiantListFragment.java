package objis.com.listingetudiant;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import objis.com.database.Model.EtudiantDAO;
import objis.com.database.Model.Etudiant;

/**
 * Created by yeo-sglo on 07/01/17.
 */

public class EtudiantListFragment extends Fragment {
    private RecyclerView mEtudiantRecyclerView;
    private EtudiantAdapter mAdapter;
    private EtudiantDAO mEtudiantDAO;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        mEtudiantDAO = new EtudiantDAO(getContext());
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_etudiant, container, false);

        mEtudiantRecyclerView = (RecyclerView) v.findViewById(R.id.etudiant_recycler_view);
        mEtudiantRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        if(mEtudiantDAO.getAll().size() < 1){
            Toast.makeText(getActivity(), "Base de données vide \n Ajouter un nouvel etudiant\n (voir bar de menu)", Toast.LENGTH_LONG).show();
        }

        return v;
    }

    @Override
    public void onResume() {
        updateUI();
        super.onResume();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.my_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_add:
                Intent intent = EtudiantAjouterActivity.newIntent(getContext());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateUI() {
            mAdapter = new EtudiantAdapter(mEtudiantDAO.getAll());
            mEtudiantRecyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
    }


    //==================================== View Holder=================================
    private class EtudiantHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mNomPrenomTextView;
        private TextView mFiliereTextView;

        private Etudiant mEtudiant;


        public void bindEtudiant(Etudiant etudiant){
            mEtudiant = etudiant;
            mNomPrenomTextView.setText(mEtudiant.getNom()+" "+mEtudiant.getPrenom());
            mFiliereTextView.setText(mEtudiant.getFiliere());

        }

        public EtudiantHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mNomPrenomTextView = (TextView) itemView.findViewById(R.id.txt_nom_prenom);
            mFiliereTextView = (TextView) itemView.findViewById(R.id.txt_filiere);
        }

        @Override
        public void onClick(View view) {
            Intent intent1 = EtudiantActivity.newIntent(getActivity(), mEtudiant.getId());
            Intent intent = new Intent(getActivity(), ScrollingActivity.class);
            intent.putExtra(ScrollingActivity.ETUD_ID, mEtudiant.getId());
            startActivity(intent);
        }
    }

    //================================= A D A P T E R =================================
    private class EtudiantAdapter extends RecyclerView.Adapter<EtudiantHolder> {

        private List<Etudiant> mEtudiants;

        public EtudiantAdapter(List<Etudiant> list) {
            mEtudiants = list;
        }

        @Override
        public EtudiantHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_etudiant, parent, false);
            return new EtudiantHolder(view);
        }

        @Override
        public void onBindViewHolder(EtudiantHolder holder, int position) {
            Etudiant etudiant = mEtudiants.get(position);
            holder.bindEtudiant(etudiant);

        }

        @Override
        public int getItemCount() {
            return mEtudiants.size();
        }
    }
}
