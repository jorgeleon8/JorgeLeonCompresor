package valumesdanielandroidstudio.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.File;
import java.io.IOException;

public class MainGrabacion extends Activity implements OnCompletionListener {

    MediaRecorder record;
    File archivo;
    MediaPlayer play;
    Button G, P, Rp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_grabacion);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_grabacion, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Metodo para grabar

    public void grabar(View v){


        record = new MediaRecorder(); // Definicion de un obejeto de la clase MediaRecorder
        record.setAudioSource(MediaRecorder.AudioSource.MIC); // Se define el microfono como fuente de audio
        record.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP); // Se especifica que el archivo sera almacenado con extencion .3gp
        record.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        File path = new File(Environment.getExternalStorageDirectory().getPath()); // Se crea un archivo temporal en la SD con extencion .3gp
        try{
            archivo =  File.createTempFile("Temporal",".3gp",path);
        } catch (IOException e){

        }

        record.setOutputFile(archivo.getAbsolutePath()); // Se indica donde se guarda la grabacion
        // Se llama al metodo "prepare" y se inicia la grabacion
        try{
            record.prepare();
        }catch (IOException e){

        }
        record.start();
    }

    public void pausa(View v){
        record.stop();
        record.release();
        play = new MediaPlayer();
        play.setOnCompletionListener(this);

    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }
}
