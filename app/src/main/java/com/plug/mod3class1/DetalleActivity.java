package com.plug.mod3class1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.plug.mod3class1.database.SentenciaSQL;
import com.plug.mod3class1.entidades.Ubicaciones;

public class DetalleActivity extends AppCompatActivity {
    private MapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        //Fragment manager manejda a toso los fragmentos, y luego lo llamamos con el id;
        mapFragment=(MapFragment)getFragmentManager().findFragmentById(R.id.map);
        //Inicializar el mapa, para poder configurarlo
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                for(Ubicaciones item: SentenciaSQL.obtener()){

                    if(item.getGenero().equals("Rock")){
                        googleMap.addMarker(
                                new MarkerOptions()
                                        //Titulo
                                        .title(item.getTitulo())
                                        //Descripcion
                                        .snippet(item.getDescripcion())
                                        //Ubicacion o posicion
                                        .position(new LatLng(item.getLatitud(),item.getLongitud()))
                                        //Imagen a mostrar en el punto
                                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
                                //.icon(BitmapDescriptorFactory.fromResource(R.drawable.rojo))
                        );
                    }
                    else if(item.getGenero().equals("Salsa")){
                        googleMap.addMarker(
                                new MarkerOptions()
                                        //Titulo
                                        .title(item.getTitulo())
                                        //Descripcion
                                        .snippet(item.getDescripcion())
                                        //Ubicacion o posicion
                                        .position(new LatLng(item.getLatitud(),item.getLongitud()))
                                        //Imagen a mostrar en el punto
                                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
                                //.icon(BitmapDescriptorFactory.fromResource(R.drawable.rojo))
                        );
                    }
                    else if(item.getGenero().equals("Pop")){
                        googleMap.addMarker(
                                new MarkerOptions()
                                        //Titulo
                                        .title(item.getTitulo())
                                        //Descripcion
                                        .snippet(item.getDescripcion())
                                        //Ubicacion o posicion
                                        .position(new LatLng(item.getLatitud(),item.getLongitud()))
                                        //Imagen a mostrar en el punto
                                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
                                //.icon(BitmapDescriptorFactory.fromResource(R.drawable.rojo))
                        );
                    }
                    else if(item.getGenero().equals("Reggae")){
                        googleMap.addMarker(
                                new MarkerOptions()
                                        //Titulo
                                        .title(item.getTitulo())
                                        //Descripcion
                                        .snippet(item.getDescripcion())
                                        //Ubicacion o posicion
                                        .position(new LatLng(item.getLatitud(),item.getLongitud()))
                                        //Imagen a mostrar en el punto
                                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                                //.icon(BitmapDescriptorFactory.fromResource(R.drawable.rojo))
                        );
                    }
                    else if(item.getGenero().equals("Cumbia")){
                        googleMap.addMarker(
                                new MarkerOptions()
                                        //Titulo
                                        .title(item.getTitulo())
                                        //Descripcion
                                        .snippet(item.getDescripcion())
                                        //Ubicacion o posicion
                                        .position(new LatLng(item.getLatitud(),item.getLongitud()))
                                        //Imagen a mostrar en el punto
                                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))
                                //.icon(BitmapDescriptorFactory.fromResource(R.drawable.rojo))
                        );
                    }
                    //El zoom maximo es de 15, hacer zoom ubicar el punto en el mapa
                    //Point file kilometer radio
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                            new LatLng(item.getLatitud(),item.getLongitud()),
                            15
                    ));

                }
            }
        });
    }
}
