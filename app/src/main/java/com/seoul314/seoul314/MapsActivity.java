package com.seoul314.seoul314;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.seoul314.seoul314.relaySeoul.RelayFragment;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    // GPS 관련
    private LocationListener locationListener;
    private LocationManager locationManager;
    private String provider;
    private Context context;
    // 바텀시트 관련
    private FrameLayout actionLayout;
    private BottomSheetBehavior bottomSheetBehavior;
    private View bottomview;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Log.i("test1","클릭");


            switch (item.getItemId()) {

                case R.id.navigation_home:
                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    SoloFragment friendFragment = new SoloFragment();
                    transaction.replace(R.id.container_main, friendFragment);
                    transaction.commit();
                    if(bottomSheetBehavior.getState() == bottomSheetBehavior.STATE_COLLAPSED){
                        bottomSheetBehavior.setState(bottomSheetBehavior.STATE_EXPANDED);
                    }else if(bottomSheetBehavior.getState() == bottomSheetBehavior.STATE_EXPANDED){
                        bottomSheetBehavior.setState(bottomSheetBehavior.STATE_COLLAPSED);
                    }

                    return true;

                case R.id.navigation_dashboard:
                    FragmentManager manager1 = getSupportFragmentManager();
                    FragmentTransaction transaction1 = manager1.beginTransaction();
                    WithFragment withFragment = new WithFragment();
                    transaction1.replace(R.id.container_main, withFragment);
                    transaction1.commit();
                    if(bottomSheetBehavior.getState() == bottomSheetBehavior.STATE_COLLAPSED){
                        bottomSheetBehavior.setState(bottomSheetBehavior.STATE_EXPANDED);
                    }else if(bottomSheetBehavior.getState() == bottomSheetBehavior.STATE_EXPANDED){
                        bottomSheetBehavior.setState(bottomSheetBehavior.STATE_COLLAPSED);
                    }

                    return true;

                case R.id.navigation_notifications:
                    FragmentManager manager2 = getSupportFragmentManager();
                    FragmentTransaction transaction2 = manager2.beginTransaction();
                    RelayFragment relayFragment = new RelayFragment();
                    transaction2.replace(R.id.container_main, relayFragment);
                    transaction2.commit();
                    if(bottomSheetBehavior.getState() == bottomSheetBehavior.STATE_COLLAPSED){
                        bottomSheetBehavior.setState(bottomSheetBehavior.STATE_EXPANDED);
                    }else if(bottomSheetBehavior.getState() == bottomSheetBehavior.STATE_EXPANDED){
                        bottomSheetBehavior.setState(bottomSheetBehavior.STATE_COLLAPSED);
                    }

                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        context = getApplicationContext();
        getLoacationManager();
        // 바텀시트 관련
        final BottomNavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        bottomview = findViewById(R.id.bottom_sheet);
        actionLayout = findViewById(R.id.action_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomview);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);


        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    actionLayout.setVisibility(View.VISIBLE);
                    if(UtilDaoImple.getInstance().getCreateRoom() != null){
                        UtilDaoImple.getInstance().getCreateRoom().setVisibility(View.GONE);
                    }


                }
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    actionLayout.setVisibility(View.GONE);
                    if(UtilDaoImple.getInstance().getCreateRoom() != null){
                        UtilDaoImple.getInstance().getCreateRoom().setVisibility(View.VISIBLE);
                    }
                }

                if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                    actionLayout.setVisibility(View.VISIBLE);
                    if(UtilDaoImple.getInstance().getCreateRoom() != null){
                        UtilDaoImple.getInstance().getCreateRoom().setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        SoloFragment soloFragment = new SoloFragment();
        transaction.replace(R.id.container_main, soloFragment);
        transaction.commit();




    }


    @SuppressLint("MissingPermission")
    public void getLoacationManager(){
        if(locationManager == null){
            locationManager = (LocationManager) this.getSystemService(context.LOCATION_SERVICE);

            // 최적 gps 하드웨어 검색
            Criteria c = new Criteria();
            provider = locationManager.getBestProvider(c, true);
            // 사용가능한 장치가 없다면 모든 장치에서 검색
            if (provider == null || !locationManager.isProviderEnabled(provider)) {
                List<String> hardWare = locationManager.getAllProviders();
                for (int a = 0; a < hardWare.size(); a++) {
                    String gpsHardware = hardWare.get(a);
                    if (locationManager.isProviderEnabled(gpsHardware)) {
                        provider = gpsHardware;
                        break;
                    }
                }
            }
            locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) { // 내 위치 뜸~!

                    Log.i("test1","Location : " + location.getLatitude() + ", " + location.getLongitude());


                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            };
            locationManager.requestLocationUpdates(provider, 0, 100, locationListener);

        }


    }


}

