package com.plug.mod3class1;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.plug.mod3class1.database.SentenciaSQL;
import com.plug.mod3class1.entidades.Ubicaciones;

import java.util.ArrayList;

public class DetalleActivity extends AppCompatActivity {
    private MapFragment mapFragment;
    Ubicaciones ubicacion=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        //Fragment manager manejda a toso los fragmentos, y luego lo llamamos con el id;
        mapFragment=(MapFragment)getFragmentManager().findFragmentById(R.id.map);
        //Inicializar el mapa, para poder configurarlo
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {
                //android:drawable/ic_geo o oor

                googleMap.getUiSettings().setMyLocationButtonEnabled(true);
                googleMap.setMyLocationEnabled(true);

                final ArrayList<LatLng> posiciones=new ArrayList<LatLng>();
                for(Ubicaciones item: SentenciaSQL.obtener()){
                    posiciones.add(new LatLng(item.getLatitud(),item.getLongitud()));

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
                    googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                        @Override
                        public boolean onMarkerClick(Marker marker) {
                            String title=marker.getTitle();
                            String description=marker.getSnippet();
                            Toast.makeText(DetalleActivity.this, "Good Job! " +"Title: "+title+" Descripcion: "+description, Toast.LENGTH_SHORT).show();
                            /*final Dialog dialog=new Dialog(DetalleActivity.this);
                            //ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithResourceId(R.drawable.carnet_oskar2).build();
                            dialog.setContentView(R.layout.dialogo_banda_detalle);
                            //ivImagen=(SimpleDraweeView)dialog.findViewById(R.id.ivCarnet);
                            final TextView etdescripcion=(TextView) dialog.findViewById(R.id.etDescripcion);
                            final TextView etNombreBanda=(TextView) dialog.findViewById(R.id.etNombreBanda);
                            final Button btnIrPerfil=(Button)dialog.findViewById(R.id.btnIrPerfil);
                            final Button btnCancelar=(Button)dialog.findViewById(R.id.btnCancelar);
                            dialog.getWindow().setLayout(700,1000); //Primero es ancho, el segundo es el alto
                            etNombreBanda.setText(title);
                            etdescripcion.setText(description);
                            //ivImagen.setImageURI(Uri.parse("res:/"+R.drawable.carnet_oskar2));
                            //ivImagen.setImageURI(imageRequest.getSourceUri());
                            //Toast.makeText(this, "Entramos otra vez", Toast.LENGTH_SHORT).show();
                            btnIrPerfil.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            });
                            btnCancelar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialog.dismiss();
                                }
                            });*/
                            //dialog.show();


                            return false;
                        }
                    });

                }

                //Agregar una linea en el mapa
                googleMap.addPolyline(
                        new PolylineOptions()
                        .addAll(posiciones)
                        .color(Color.GREEN)
                        .width(5)
                );
                //Creamos un poligono y pintamos el contenido
                googleMap.addPolygon(
                        new PolygonOptions()
                        .addAll(posiciones)
                        .fillColor(getResources().getColor(R.color.colorPrimaryDark))
                );
                /*
                //Para agregarle informacion que no estan en los parametros que le dimos
                googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                    @Override
                    public View getInfoWindow(Marker marker) {

                        return null;
                    }

                    @Override
                    public View getInfoContents(Marker marker) {
                        return null;
                    }
                });*/

                googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(final Marker marker) {
                        for (Ubicaciones item:SentenciaSQL.obtener()){
                            if (item.getTitulo().equals(marker.getTitle())
                                    && item.getDescripcion().equals(marker.getSnippet())){
                                ubicacion=item;
                                break;
                            }
                        }
                        Toast.makeText(DetalleActivity.this, "Accion", Toast.LENGTH_SHORT).show();
                        Dialog dialog=new Dialog(DetalleActivity.this);
                        //dialog.getWindow().setLayout(getWindow().getWindowManager().getDefaultDisplay().getWidth(),getWindow().getWindowManager().getDefaultDisplay().getHeight()/2);
                        dialog.getWindow().setLayout(400,300);
                        dialog.setContentView(R.layout.item_editar);
                        final EditText etTitulo=(EditText)dialog.findViewById(R.id.etTitulo);
                        final EditText etDescripcion=(EditText)dialog.findViewById(R.id.etDescripcion);
                        Button btnEditar=(Button)dialog.findViewById(R.id.btnEditar);
                        Button btnEliminar=(Button)dialog.findViewById(R.id.btnEliminar);



                        etTitulo.setText(marker.getTitle());
                        etDescripcion.setText(marker.getSnippet());
                        btnEditar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Ubicaciones ubicaciones=new Ubicaciones();
                                ubicaciones.setTitulo(etTitulo.getText().toString());
                                ubicaciones.setDescripcion(etDescripcion.getText().toString());
                                ubicaciones.setId(ubicacion.getId());
                                ubicaciones.setLatitud(ubicacion.getLatitud());
                                ubicaciones.setLongitud(ubicacion.getLongitud());
                                ubicaciones.setGenero(ubicacion.getGenero());
                                SentenciaSQL.registrar(ubicaciones);
                                //Limpia el mapa, y vuelve al for
                                googleMap.clear();


                            }
                        });

                        btnEliminar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                SentenciaSQL.eliminar(ubicacion.getId());
                                marker.remove();

                            }
                        });

                        /*
                        marker.remove
                        //para borrar del mapa
                        marker.remove();
                        //
                        */


                        dialog.show();


                        /*
                        Intent intent=new Intent(DetalleActivity.this,MapaDetalleActivity.class);
                        startActivity(intent);
                        * */

                        /*Mi dialogo customizado
                        String title=marker.getTitle();
                        String description=marker.getSnippet();
                        final Dialog dialog=new Dialog(DetalleActivity.this);
                            //ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithResourceId(R.drawable.carnet_oskar2).build();
                            dialog.setContentView(R.layout.dialogo_banda_detalle);
                            //ivImagen=(SimpleDraweeView)dialog.findViewById(R.id.ivCarnet);
                            final TextView etdescripcion=(TextView) dialog.findViewById(R.id.etDescripcion);
                            final TextView etNombreBanda=(TextView) dialog.findViewById(R.id.etNombreBanda);
                            final Button btnIrPerfil=(Button)dialog.findViewById(R.id.btnIrPerfil);
                            final Button btnCancelar=(Button)dialog.findViewById(R.id.btnCancelar);
                            dialog.getWindow().setLayout(400,300); //Primero es ancho, el segundo es el alto
                            etNombreBanda.setText(title);
                            etdescripcion.setText(description);
                            //ivImagen.setImageURI(Uri.parse("res:/"+R.drawable.carnet_oskar2));
                            //ivImagen.setImageURI(imageRequest.getSourceUri());
                            //Toast.makeText(this, "Entramos otra vez", Toast.LENGTH_SHORT).show();
                            btnIrPerfil.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            });
                            btnCancelar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialog.dismiss();
                                }
                            });
                        dialog.show();*/

                    }
                });

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }


}
