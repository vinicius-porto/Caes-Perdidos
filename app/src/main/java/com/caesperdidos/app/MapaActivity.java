package com.caesperdidos.app;

import android.location.Address;
import android.location.Geocoder;
import android.widget.Toast;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import android.content.Intent;
import android.os.Bundle;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.views.MapView;
import org.osmdroid.views.CustomZoomButtonsController;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.MapEventsOverlay;
import org.osmdroid.views.overlay.Marker;
import com.google.android.material.button.MaterialButton;
import org.osmdroid.api.IMapController;
public class MapaActivity extends AppCompatActivity {
    private MapView map;
    private Marker marker;
    private String endereco = "";
    private double latitude= 0;
    private double longitude = 0;
    private MaterialButton btnConfirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mapa);
        map = findViewById(R.id.map);
        map.setMultiTouchControls(true);
        map.setTilesScaledToDpi(true);
        map.getController().setZoom(17.0);

        btnConfirmar = findViewById(R.id.btnConfirmar);

        btnConfirmar.setOnClickListener(v -> {

            Intent intent = new Intent();

            intent.putExtra("latitude", latitude);
            intent.putExtra("longitude", longitude);
            intent.putExtra("endereco", endereco);
            setResult(RESULT_OK, intent);

            finish();
        });

        map.getZoomController().setVisibility(
                CustomZoomButtonsController.Visibility.ALWAYS
        );
        IMapController controller = map.getController();

        controller.setZoom(15.0);

        double latitudeRecebida =
                getIntent().getDoubleExtra("latitude", 0);

        double longitudeRecebida =
                getIntent().getDoubleExtra("longitude", 0);

        if (latitudeRecebida != 0 && longitudeRecebida != 0) {

            latitude = latitudeRecebida;
            longitude = longitudeRecebida;

            GeoPoint localizacaoAtual =
                    new GeoPoint(latitude, longitude);

            controller.setCenter(localizacaoAtual);

        } else {

            GeoPoint portoAlegre =
                    new GeoPoint(-30.0346, -51.2177);

            controller.setCenter(portoAlegre);
        }

        if (latitude != 0 && longitude != 0) {

            GeoPoint localizacaoAtual =
                    new GeoPoint(latitude, longitude);

            marker = new Marker(map);

            marker.setPosition(localizacaoAtual);

            marker.setAnchor(
                    Marker.ANCHOR_CENTER,
                    Marker.ANCHOR_BOTTOM
            );

            marker.setTitle(
                    String.format(
                            "Latitude: %.6f\nLongitude: %.6f",
                            latitude,
                            longitude
                    )
            );

            map.getOverlays().add(marker);
        }


        MapEventsReceiver receiver = new MapEventsReceiver() {
            @Override
            public boolean singleTapConfirmedHelper(GeoPoint p) {

                if (marker != null) {
                    map.getOverlays().remove(marker);
                }

                latitude = p.getLatitude();
                longitude = p.getLongitude();

                marker = new Marker(map);

                marker.setPosition(p);

                marker.setAnchor(
                        Marker.ANCHOR_CENTER,
                        Marker.ANCHOR_BOTTOM
                );

                map.getOverlays().add(marker);

                buscarEndereco(
                        latitude,
                        longitude
                );

                map.invalidate();

                return true;
            }

            @Override
            public boolean longPressHelper(GeoPoint p) {
                return false;
            }
        };

        map.getOverlays().add(new MapEventsOverlay(receiver));



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }


    private void buscarEndereco(double latitude, double longitude) {

        Geocoder geocoder = new Geocoder(
                this,
                Locale.getDefault()
        );

        try {

            List<Address> enderecos = geocoder.getFromLocation(
                    latitude,
                    longitude,
                    1
            );

            if (enderecos != null && !enderecos.isEmpty()) {

                Address address = enderecos.get(0);

                endereco = address.getAddressLine(0);

                if (marker != null) {

                    marker.setTitle(endereco);
                    marker.showInfoWindow();

                }

                map.invalidate();

            } else {

                endereco = "Endereço não encontrado";

                Toast.makeText(
                        this,
                        endereco,
                        Toast.LENGTH_SHORT
                ).show();
            }

        } catch (IOException e) {

            endereco = "Não foi possível obter o endereço";

            Toast.makeText(
                    this,
                    endereco,
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

}