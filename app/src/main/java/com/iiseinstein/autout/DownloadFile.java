package com.iiseinstein.autout;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import static android.content.ContentValues.TAG;

public class DownloadFile extends AsyncTask<String, String, String> {

    private String fileName;//Nome del file
    private String folder;

    //@Override
    //protected void onPreExecute() {
    //}
    public static boolean isNetworkConnected(Context c) {
        ConnectivityManager conManager = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conManager.getActiveNetworkInfo();
        return ( netInfo != null && netInfo.isConnected() );//La funzione ritorna un valore bool
    }

    @Override
    protected String doInBackground(String... f_url) {//Funzione che scaricherà il file in background
        int count;
        try {

            URL url = new URL(f_url[0]);//L'url da cui scarcare il file viene parsato in URL
            URLConnection connection = url.openConnection();//Crea una connesione con l'url
            connection.connect();//Usa la connesione creata e si connette


            int lengthOfFile = connection.getContentLength();//Viene letta la lunghezza del file


            InputStream input = new BufferedInputStream(url.openStream(), 8192);


            //Extract file name from URL
            fileName = f_url[0].substring(f_url[0].lastIndexOf('/') + 1, f_url[0].length());


            //External directory path to save file
            folder = Environment.getExternalStorageDirectory() + File.separator + "AutOut/";

            //Create androiddeft folder if it does not exist
            File directory = new File(folder);

            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Output stream to write file
            OutputStream output = new FileOutputStream(folder +"new"+ fileName);

            byte data[] = new byte[1024];

            long total = 0;

            while ((count = input.read(data)) != -1) {
                total += count;
                // publishing the progress....

                Log.d(TAG, "Progress: " + (int) ((total * 100) / lengthOfFile));

                // writing data to file
                output.write(data, 0, count);
            }

            // flushing output
            output.flush();

            // closing streams
            output.close();
            input.close();
            return "Downloaded at: " + folder + fileName;

        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());
        }

        return "Something went wrong";
    }
}