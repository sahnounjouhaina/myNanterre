package miage.parisnanterre.fr.mynanterre.implem;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.*;

/**
 * Created by Sankar Vijay on 17/02/2019.
 */
public class FetchDataA extends AsyncTask<Void, Void, Void> {
    String data = "";
    String dataParsed = "";
    String dataParsed2 = "";
    String dataParsed3 = "";

    String singleParsed = "";
    String singleParsed2 = "";
    String singleParsed3 = "";


    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://api-ratp.pierre-grimaud.fr/v3/schedules/rers/A/nanterre+universite/A");

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }

            JSONObject jo = new JSONObject(data);

            for (int i = 0; i <= data.length(); i++) {
                JSONArray arr = (JSONArray) jo.getJSONObject("result").getJSONArray("schedules");

                singleParsed = arr.getJSONObject(i).get("message") + "\n";

                dataParsed = dataParsed + singleParsed;

                singleParsed2 = arr.getJSONObject(i).get("code") + "\n";
                dataParsed2 = dataParsed2 + singleParsed2;

                singleParsed3 = arr.getJSONObject(i).get("destination") + "\n";
                dataParsed3 = dataParsed3 + singleParsed3;

            }


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

        Train.direction.setText("vers Saint-Germain-en-Laye");
        Train.code.setText(this.dataParsed2);
        Train.heureT.setText(this.dataParsed);
        Train.destination.setText(this.dataParsed3);

    }
}