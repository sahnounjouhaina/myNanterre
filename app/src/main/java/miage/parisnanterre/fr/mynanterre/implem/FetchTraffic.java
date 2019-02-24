package miage.parisnanterre.fr.mynanterre.implem;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Sankar Vijay on 23/02/2019.
 */
public class FetchTraffic extends AsyncTask<Void, Void, Void> {
    String data = "";
    String dataParsed = "";
    String dataParsed2 = "";
    String singleParsed = "";
    String singleParsed2 = "";


    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://api-ratp.pierre-grimaud.fr/v3/traffic/rers/A");

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }

            JSONObject jo = new JSONObject(data);
            JSONObject jo2 = jo.getJSONObject("result");

            singleParsed = jo2.get("title") + "\n";
            dataParsed = dataParsed + singleParsed;

            singleParsed2 = jo2.get("message") + "\n";
            dataParsed2 = dataParsed2 + singleParsed2;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        GareFavorite.title.setText(this.dataParsed);
        GareFavorite.info.setText(this.dataParsed2);
        Train.title.setText(this.dataParsed);
        Train.info.setText(this.dataParsed2);


    }

}