package com.plug.mod3class1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.plug.mod3class1.database.SentenciaSQL;
import com.plug.mod3class1.entidades.Ubicaciones;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.etTitulo)
    EditText etTitulo;
    @BindView(R.id.etDescripcion)
    EditText etDescripcion;
    @BindView(R.id.etLatitud)
    EditText etLatitud;
    @BindView(R.id.etLongitud)
    EditText etLongitud;
    @BindView(R.id.spGenero)
    Spinner spGenero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnGrabar)
    public void onBtnGrabarClicked() {
        String titulo = etTitulo.getText().toString();
        String descripcion = etDescripcion.getText().toString();
        String latitud = etLatitud.getText().toString();
        String longitud = etLongitud.getText().toString();
        String genero = spGenero.getSelectedItem().toString();

        if (titulo.isEmpty()) {
            etTitulo.setError("El campo es requerido");
            return;
        }
        if (descripcion.isEmpty()) {
            etDescripcion.setError("El campo es requerido");
            return;
        }

        if (latitud.isEmpty()) {
            etLatitud.setError("El campo es requerido");
            return;
        }

        if (longitud.isEmpty()) {
            etLongitud.setError("El campo es requerido");
            return;
        }

        Ubicaciones ubicaciones = new Ubicaciones(
                SentenciaSQL.obtenerId(),
                titulo,
                descripcion,
                Double.parseDouble(latitud),
                Double.parseDouble(longitud),
                genero);
        SentenciaSQL.registrar(ubicaciones);
        Toast.makeText(this, "Se registro", Toast.LENGTH_SHORT).show();
        etTitulo.setText("");
        etDescripcion.setText("");
        etLatitud.setText("");
        etLongitud.setText("");
        //Indicador del cursos
        etTitulo.requestFocus();

    }

    @OnClick(R.id.btnVerMapa)
    public void onBtnVerMapaClicked() {
        Intent intent = new Intent(MainActivity.this, DetalleActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btnLocalizar)
    public void onViewClicked() {
        GPSTracker gpsTracker=new GPSTracker(MainActivity.this);
        if(gpsTracker.canGetLocation()){
            Double latitud=gpsTracker.getLatitude();
            Double longitud=gpsTracker.getLongitude();
            etLongitud.setText(longitud.toString());
            etLatitud.setText(latitud.toString());
        }
        else{
            gpsTracker.showSettingsAlert();
        }
    }
}
