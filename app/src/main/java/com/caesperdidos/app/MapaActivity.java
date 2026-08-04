package com.caesperdidos.app;

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

        btnConfirmar = findViewById(R.id.btnConfirmar);

        btnConfirmar.setOnClickListener(v -> {

            Intent intent = new Intent();

            intent.putExtra("latitude", latitude);
            intent.putExtra("longitude", longitude);

            setResult(RESULT_OK, intent);

            finish();
        });

        map.getZoomController().setVisibility(
                CustomZoomButtonsController.Visibility.ALWAYS
        );
        IMapController controller = map.getController();

        controller.setZoom(15.0);

        GeoPoint portoAlegre = new GeoPoint(-30.0346, -51.2177);

        controller.setCenter(portoAlegre);


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
                marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

                marker.setTitle(
                        String.format(
                                "Latitude: %.6f\nLongitude: %.6f",
                                p.getLatitude(),
                                p.getLongitude()
                        )
                );

                map.getOverlays().add(marker);

                marker.showInfoWindow();

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


}