package com.iiseinstein.autout;

import android.Manifest;
import android.content.Intent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.view.*;
import android.graphics.Point;
import android.widget.Toast;

import pub.devrel.easypermissions.EasyPermissions;
import java.io.File;

import java.util.ArrayList;
import java.util.List;

import static com.iiseinstein.autout.DownloadFile.isNetworkConnected;


public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {
    ImageView mappa_b;
    ImageView aiuto_b;
    ImageView info_b;
    ImageView impostazioni_b;
    //Funzione che legge il file
    void readFile(File f, List<String> _file){
        //Prima creo la lista di stringhe che conterrà il file
        BufferedReader reader;

       try{
           BufferedReader br = new BufferedReader(new FileReader(f));
           String line;

           while ((line = br.readLine()) != null) {

               _file.add(line);
           }
           br.close();
        } catch(IOException ioe){//In caso di errori
            ioe.printStackTrace();
        }
        Log.d("First Line", _file.get(0));
    }


    //variabili per il download del file
    private static final String TAG = MainActivity.class.getSimpleName();//Nome della classe MainActivity
    private String listurl="https://raw.githubusercontent.com/gruppoautismo/GestioneFile/master/list.lst";//Contiene il link da cui verrà scaricato il file
    private static final int WRITE_REQUEST_CODE = 300;//Codice che serve per chiedere al dispositivo che é un operazione di scrittura
    //Inizializzo una funzione che servirà poi per controllare se il dispositivo é connesso a internet








    public void download(String filename, String link){
        if(isNetworkConnected(this)){

            if (EasyPermissions.hasPermissions(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                //Get the URL entered
                Toast.makeText(this, "Download file necessari...", Toast.LENGTH_LONG);
                new DownloadFile().execute(link);


                File newf = new File(Environment.getExternalStorageDirectory() + "/AutOut/new" +filename);//Oggetto di tipo file per il nuovo file
                Log.e("Newfileex", Boolean.toString(newf.exists()));
                List<String> newfsl = new ArrayList<>();//Array di stringhe che conterrà il file appena scaricato
                File oldf = new File(Environment.getExternalStorageDirectory() + "/AutOut/" + filename);//Oggetto di tipo file per il nuovo file
                List<String> oldfsl = new ArrayList<>();//Array di stringhe che conterrà il file appena scaricato
                Log.e("1a", "1a");
                readFile(newf, newfsl);//Legge il nuovo file
                if (oldf.exists()) {
                    Log.e("1b", "1b");
                    readFile(oldf, oldfsl);
                }
                Log.e("1c", "1c");
                if (oldf.exists()){
                    String tmp = newfsl.get(0);
                    String tmp2 = "";
                    for (int i = 1; i < tmp.length(); i++) {
                        tmp2 += tmp.charAt(i);

                    }
                    float newver = Float.parseFloat(tmp2);
                    tmp = oldfsl.get(0);
                    tmp2 = "";
                    for (int i = 1; i < tmp.length(); i++) {
                        tmp2 += tmp.charAt(i);

                    }
                    float oldver = Float.parseFloat(tmp2);
                    Log.d("-------newver", Float.toString(newver));
                    Log.d("-------oldver", Float.toString(oldver));


                    if (newver > oldver) {
                        oldf.delete();
                        newf.renameTo(oldf);
                    } else {
                        newf.delete();
                    }
                }else{
                    newf.renameTo(oldf);
                }





            } else {
                //If permission is not present request for the same.
                EasyPermissions.requestPermissions(MainActivity.this, getString(R.string.write_file), WRITE_REQUEST_CODE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                EasyPermissions.requestPermissions(MainActivity.this, getString(R.string.write_file), WRITE_REQUEST_CODE, Manifest.permission.READ_EXTERNAL_STORAGE);
                Log.e("Sorry", "Sorry");
            }
        }
    }
    public void checkupdatelist(){
        download("list.lst", listurl);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Write id", Manifest.permission.WRITE_EXTERNAL_STORAGE);

        checkupdatelist();
        /*File f = new File(Environment.getExternalStorageDirectory() + "/AutOut");
        if(f.exists()){
            f= new File(Environment.getExternalStorageDirectory()+"/AutOut/");

        }else{

            //Log.e("Network check", Boolean.toString(isNetworkConnected(this)));
        }*/
        Display display = getWindowManager().getDefaultDisplay();//Oggetto che consiste nel display, servira poi per sistemare tutti gli oggetti nel menu
        Point size = new Point();
        display.getSize(size);

        int width = size.x;
        int height = size.y;

        mappa_b=(ImageView)findViewById(R.id.Mappa);
        mappa_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("button log", "Clicked!");
                apri_trasporti();
            }
        });
        aiuto_b=(ImageView)findViewById(R.id.Aiuto);
        aiuto_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apri_aiuto();
            }
        });
        info_b=(ImageView)findViewById(R.id.Info);
        impostazioni_b=(ImageView)findViewById(R.id.Impostazioni);

        mappa_b.getLayoutParams().height =(height/2)-60;
        mappa_b.getLayoutParams().width = width/2;
        mappa_b.setX(0);
        mappa_b.setY(0);


        aiuto_b.getLayoutParams().height =(height/2)-60;
        aiuto_b.getLayoutParams().width = width/2;
        aiuto_b.setX(width/2);
        aiuto_b.setY(-((height/2) -60));

        info_b.getLayoutParams().height =(height/2)-60;
        info_b.getLayoutParams().width = width/2;
        info_b.setX(0);
        info_b.setY(-((height/2)-60));

        impostazioni_b.getLayoutParams().height =(height/2)-60;
        impostazioni_b.getLayoutParams().width = width/2;
        impostazioni_b.setX(width/2);
        impostazioni_b.setY(2*(-((height/2) -60)));

    }
    void apri_aiuto(){//Funzione necessaria per far partire l'attività in caso cliccato Aiuto
        Intent intent = new Intent(this, aiuto.class);
        startActivity(intent);
    }
    void apri_trasporti(){//Funzione necessaria per far partire l'attività in caso cliccato Aiuto
        Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.muoversi.regione.lombardia.it/planner/"));
        startActivity(browserIntent);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {//Funzione che viene chiamata se l'utente(La prima volta) da i permessi di scrittura all'app
        //Download the file once permission is granted
        new DownloadFile().execute(listurl);//Richiama la funzione per scarivare il file
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {//Funzione che viene chiamata se l'utente rifiuta i permessi di scrittura all'app
        Log.d(TAG, "Permission has been denied");
    }

    /**
     * Async Task to download file from URL
     */

}
