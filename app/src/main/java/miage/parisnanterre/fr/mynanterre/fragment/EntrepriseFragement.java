package miage.parisnanterre.fr.mynanterre.fragment;


import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import miage.parisnanterre.fr.mynanterre.R;
import miage.parisnanterre.fr.mynanterre.adapter.EntrepriseAdapter;
import miage.parisnanterre.fr.mynanterre.implem.Entreprise;




public class EntrepriseFragement  extends Fragment{

    // à modifier en fonction de votre localhost
    private static final String url = "jdbc:mysql://10.0.2.2:8889/my_nanterre";
    private static final String user = "root";
    private static final String psw = "root";
    private static Connection conn;
    private List<Entreprise> liste = new ArrayList<>();
    private EntrepriseAdapter enAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.liste_entreprise, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = getView().findViewById(R.id.recycler_view);
        enAdapter = new EntrepriseAdapter(liste);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(enAdapter);

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            Toast.makeText(getContext(), "Problème au niveau du driver", Toast.LENGTH_SHORT).show();
        }
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        prepareEntrepriseData();
    }

    private void prepareEntrepriseData() {

        try {

            conn = DriverManager.getConnection(url, user, psw);
            String sqliD = "SELECT * FROM entreprise ";
            Statement st = conn.createStatement();
            ResultSet rst = st.executeQuery(sqliD);

            while (rst.next()) {
                String logo = rst.getString("image_entreprise");
                String nom = rst.getString("nom_entreprise");

                Entreprise entreprise = new Entreprise(logo, nom);
                liste.add(entreprise);


            }
            enAdapter.notifyDataSetChanged();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        enAdapter.notifyDataSetChanged();
    }
}