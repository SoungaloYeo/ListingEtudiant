package objis.com.listingetudiant;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class EtudiantActivity extends AppCompatActivity {

    private static final String EXTRA_ETUDIANT_ID = "objis.com.listingetudiant.etudiant.id";

    public static Intent newIntent(Context context, Long etudiant_id){
        Intent intent = new Intent(context, EtudiantActivity.class);
        intent.putExtra(EXTRA_ETUDIANT_ID, etudiant_id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);

        if(fragment == null){
            Long id = (Long) getIntent().getSerializableExtra(EXTRA_ETUDIANT_ID);
            fragment = FragmentEtudiantDetail.newIntent(id);
            fragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
    }
}
