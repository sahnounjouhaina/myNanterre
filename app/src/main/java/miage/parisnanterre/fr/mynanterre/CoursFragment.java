package miage.parisnanterre.fr.mynanterre;

/*
 * Created by Sankar Vijay on 18/01/2019.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class CoursFragment extends Fragment {

    public CoursFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.liste_cours, container, false);

    }

}