package objis.com.listingetudiant;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yeo-sglo on 07/01/17.
 */

public class EtudiantListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);

        EtudiantListFragment etudiantListFragment = new EtudiantListFragment();

        if(fragment == null){
            fragment = etudiantListFragment;
            fragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
    }
}
