package miage.parisnanterre.fr.mynanterre.fragment;


import android.content.Intent;
import android.net.Uri;
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
import miage.parisnanterre.fr.mynanterre.adapter.CrousAdapter;

import miage.parisnanterre.fr.mynanterre.adapter.EntrepriseAdapter;
import miage.parisnanterre.fr.mynanterre.implem.Crous;
import miage.parisnanterre.fr.mynanterre.implem.Entreprise;


public class CrousFragment extends Fragment{

    private static final String url = "jdbc:mysql://sql171.main-hosting.eu/u749839367_m1";
    private static final String user = "u749839367_vijay";
    private static final String psw = "9IDCqTm8Lig2";
    private static Connection conn;

    private List<Crous> liste = new ArrayList<>();
    private CrousAdapter cAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.liste_batiments, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = getView().findViewById(R.id.recycler_view);

        cAdapter = new CrousAdapter(liste);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(cAdapter);

        prepareCrousData();
    }

    private void prepareCrousData() {

        try {

            conn = DriverManager.getConnection(url, user, psw);
            String sqliD = "SELECT * FROM Crous ";
            Statement st = conn.createStatement();
            ResultSet rst = st.executeQuery(sqliD);

            while (rst.next()) {
                String batiment = rst.getString("batiment");
                String lieu = rst.getString("lieu");
                int frequentation=rst.getInt("frequentation");

                Crous crous = new Crous(batiment, lieu,frequentation);
                liste.add(crous);


            }


        } catch (SQLException e) {
            e.printStackTrace();
        }




        cAdapter.notifyDataSetChanged();
    }
}
