package com.example.imran.traintracker;




import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class maps extends AppCompatActivity {

    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps);

        Bundle bundle = getIntent().getExtras();
        //Extract the data…
         String stuff = bundle.getString("ss");
        Toast.makeText(getBaseContext(),
                stuff,
                Toast.LENGTH_SHORT).show();

        //txtView=(TextView)findViewById(R.id.text3);
        //txtView.setText(stuff );

        try {
            // Loading map
            initilizeMap();

        } catch (Exception e) {
            e.printStackTrace();
        }

        int a = getin(stuff);

        //double latitude = 23.154047 ;
        //double longitude =89.208373 ;
        double latitude = getLat(a);
        double longitude = getLong(a);


        // create marker
        MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title(near(stuff));

        // adding marker
        googleMap.addMarker(marker);


        /////////////////////
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(latitude, longitude), 13));

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(latitude, longitude ))      // Sets the center of the map to location user
                .zoom(17)                   // Sets the zoom
                .bearing(0)                // Sets the orientation of the camera to east
                .tilt(0)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }

    /**
     * function to load map. If map is not created it will create it for you
     * */
    private void initilizeMap() {
        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.map)).getMap();

            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initilizeMap();
    }

    int getin(String st){
       // for(int i = 0; i < Xyz.length; i++)
          //  if(Xyz[i].st.contains(st))
          //      return i;

        return match(st);

       //return  -1;

    }

    double getLat(int i)
    {
        if(i == -1)
            return 0;
        return Xyz[i].lat;
    }

    double getLong(int i)
    {
        if(i == -1)
            return 0;
        return Xyz[i].lon;
    }

    String near(String st){
        if(st.contains("-"))
          return "near " + st.substring(1,st.length());
        return st;
    }



    int match(String st1){

        int count=0,p,K,L,s=0,max=-11111111;
        int[] arr= new int[20000];
        K=st1.length();
        char[] ch1=st1.toCharArray();
        p= Xyz.length;
        int j=0;

        for( s=0; s<p ; s++)
        {

            L=Xyz[s].st.length();

            char[] ch2=Xyz[s].st.toCharArray();
            int t = (K < L) ? K :  L;

            for(int i = 0 ; i<t ; i++){

                if(ch1[i]==ch2[i]){
                    count++;
                }
            }

            arr[s]=count;
            if(arr[s]>max)

            {
                max=arr[s];

                j = s;

            }
            count=0;
        }

        return j+1;
    }

    xyz Xyz[] = {

            new xyz("Khulna", 22.822207, 89.558669),
            new xyz("-Khulna", 22.854599, 89.535087),

            new xyz("Daulatpur", 22.875537, 89.522706),
            new xyz("-Daulatpur", 23.125239, 89.294032),

            new xyz("Fultola", 22.976880, 89.448334),
            new xyz("-Fultola", 22.951630, 89.473761),

            new xyz("Bejerdanga", 22.987291, 89.437841),
            new xyz("-Bejerdanga", 22.987291, 89.437841),

            new xyz("Noapara",23.032223, 89.396042),
            new xyz("-Noapara", 23.012435, 89.412457),

            new xyz("Chengutia", 23.073725, 89.367052),
            new xyz("-Chengutia", 23.099722, 89.350873),

            new xyz("Singia", 23.121076, 89.347140),
            new xyz("-Singia", 23.125121, 89.318858),

            new xyz("Rupdia", 23.125200, 89.291693),
            new xyz("-Rupdia", 23.134415, 89.248005),

            new xyz("Jessore", 23.154047, 89.208373),
            new xyz("-Jessore", 23.169080, 89.191593),

            new xyz("Jessore Cantonment", 23.187879, 89.184576),
            new xyz("-Jessore Cantonment", 23.203480, 89.173268),

            new xyz("Meherulnagar", 23.216003, 89.164320),
            new xyz("-Meherulnagar", 23.261844, 89.161938),

            new xyz("Barobazar", 23.305206, 89.150738),
            new xyz("-Barobazar",23.348653, 89.137863),

            new xyz("Mubarakganj", 23.399628, 89.125031),
            new xyz("-Mubarakganj", 23.359271, 89.136940),

            new xyz("Shundarpur", 23.415618, 89.084412),
            new xyz("-Shundarpur", 23.412290, 89.102501),

            new xyz(" Kotchandpur", 23.415441, 89.015125),
            new xyz("-Kotchandpur", 23.435543, 88.987294),

            new xyz("Safdarpur", 23.461824, 88.963112),
            new xyz("-Safdarpur", 23.475975, 88.930711),

            new xyz("Ansarbaria", 23.492231, 88.886873),
            new xyz("-Ansarbaria", 23.492546, 88.841275),

            new xyz("Darshana", 23.528141, 88.810698),
            new xyz("-Darshana", 23.586225, 88.814217),

            new xyz("Joyrampur", 23.568092, 88.808960),
            new xyz("-Joyrampur", 23.547734, 88.803166),

            new xyz("Chuadanga", 23.638700, 88.855523),
            new xyz("-Chuadanga", 23.696499, 88.884383),

            new xyz("Mominpur", 23.682450, 88.877860),
            new xyz("-Mominpur", 23.662012, 88.868526),

            new xyz("Munshiganj", 23.715164, 88.893117),
            new xyz("-Munshiganj", 23.701392, 88.886679),

            new xyz("Alamdanga", 23.760577, 88.935710),
            new xyz("-Alamdanga", 23.812845, 88.989354),

            new xyz("Halsha", 23.813650, 88.990084),
            new xyz("-Halsha", 23.813650, 88.990084),

            new xyz("Mirpur", 23.941141, 88.997160),
            new xyz("-Mirpur", 23.981777, 88.994204),

            new xyz("Bheramara ", 24.022562, 88.992171),
            new xyz("-Bheramara ",24.058627, 88.996307),

            new xyz("Pakshi", 24.070710, 89.040416),
            new xyz("-Pakshi", 24.089130, 89.060143),

            new xyz("Ishwardi", 24.129917, 89.063032),
            new xyz("-Ishwardi", 24.173864, 89.198910),

            new xyz("Muladuli", 24.164017, 89.143099),
            new xyz("-Muladuli", 24.162137, 89.112521),

            new xyz("Gafurabad", 24.177798, 89.216613),
            new xyz("-Gafurabad", 24.170908, 89.181851),

            new xyz("Chatmohor", 24.188388, 89.273003),
            new xyz("-Chatmohor", 24.202872, 89.330918),

            new xyz("Guakhara", 24.200406, 89.321541),
            new xyz("-Guakhara", 24.206825, 89.347676),

            new xyz("Bhangura", 24.210466, 89.362611),
            new xyz("-Bhangura", 24.212638, 89.371494),

            new xyz("Boral Bridge", 24.214654, 89.379777),
            new xyz("-Boral Bridge", 24.214654, 89.379777),

            new xyz("Sharatnagar", 24.221131, 89.403294),
            new xyz("-Sharatnagar", 24.231424, 89.427820),

            new xyz("Dilpashar", 24.239152, 89.446338),
            new xyz("-Dilpashar", 24.251615, 89.476122),

            new xyz("Lahiri Mohanpur", 24.260341, 89.497064),
            new xyz("-Lahiri Mohanpur", 24.277965, 89.540001),

            new xyz("Ullapara", 24.289760, 89.569162),
            new xyz("-Ullapara", 24.319543, 89.615425),

            new xyz("Jamtoil", 24.360404, 89.659649),
            new xyz("-Jamtoil", 24.374027, 89.665271),

            new xyz("Shaheed M Monsur Ali ", 24.397694, 89.689153),
            new xyz("-Shaheed M Monsur Ali ", 24.392867, 89.715697),

            new xyz("Bangabandhu Bridge West", 24.395427, 89.731532),
            new xyz("-Bangabandhu Bridge West", 24.399707, 89.782366),

            new xyz("Bangabandhu Bridge east", 24.390327, 89.819444),
            new xyz("-Bangabandhu Bridge east", 24.341011, 89.930209),

            new xyz("Tangail", 24.276322, 89.936281),
            new xyz("-Tangail", 24.232109, 89.985033),

            new xyz("Mohera", 24.169772, 90.030588),
            new xyz("-Mohera", 24.136546, 90.057367),

            new xyz("Mirzapur", 24.105839, 90.100089),
            new xyz("-Mirzapur", 24.072146, 90.163089),

            new xyz("Mouchak", 24.038973, 90.279454),
            new xyz("-Mouchak", 24.035935, 90.352882),

            new xyz("Joydebpur", 23.999342, 90.420195),
            new xyz("-Joydebpur", 23.984738, 90.418521),

            new xyz("Dhirasram", 23.960209, 90.415624),
            new xyz("-Dhirasram", 23.924968, 90.411376),

            new xyz("Tongi", 23.899310, 90.408608),
            new xyz("-Tongi", 23.873962, 90.405926),

            new xyz("Airport", 23.852768, 90.407792),
            new xyz("-Airport", 23.825742, 90.420731),

            new xyz("Dhaka Cantonment", 23.816536, 90.412170),
            new xyz("-Dhaka Cantonment", 23.807093, 90.402471),

            new xyz("Banani", 23.796963, 90.400819),
            new xyz("-Banani", 23.774854, 90.397257),

            new xyz("Tejgaon", 23.759006, 90.394660),
            new xyz("-Tejgaon", 23.750522, 90.405968),

            new xyz("Komlapur", 23.733512, 90.426911),
            new xyz("Khulna", 22.822207, 89.558669),
            new xyz("-Khulna", 22.854599, 89.535087),

            new xyz("Daulatpur", 22.875537, 89.522706),
            new xyz("-Daulatpur", 23.125239, 89.294032),

            new xyz("Fultola", 22.976880, 89.448334),
            new xyz("-Fultola", 22.951630, 89.473761),

            new xyz("Bejerdanga", 22.987291, 89.437841),
            new xyz("-Bejerdanga", 22.987291, 89.437841),

            new xyz("Noapara",23.032223, 89.396042),
            new xyz("-Noapara", 23.012435, 89.412457),

            new xyz("Chengutia", 23.073725, 89.367052),
            new xyz("-Chengutia", 23.099722, 89.350873),

            new xyz("Singia", 23.121076, 89.347140),
            new xyz("-Singia", 23.125121, 89.318858),

            new xyz("Rupdia", 23.125200, 89.291693),
            new xyz("-Rupdia", 23.134415, 89.248005),

            new xyz("Jessore", 23.154047, 89.208373),
            new xyz("-Jessore", 23.169080, 89.191593),

            new xyz("Jessore Cantonment", 23.187879, 89.184576),
            new xyz("-Jessore Cantonment", 23.203480, 89.173268),

            new xyz("Meherulnagar", 23.216003, 89.164320),
            new xyz("-Meherulnagar", 23.261844, 89.161938),

            new xyz("Barobazar", 23.305206, 89.150738),
            new xyz("-Barobazar",23.348653, 89.137863),

            new xyz("Mubarakganj", 23.399628, 89.125031),
            new xyz("-Mubarakganj", 23.359271, 89.136940),

            new xyz("Shundarpur", 23.415618, 89.084412),
            new xyz("-Shundarpur", 23.412290, 89.102501),

            new xyz(" Kotchandpur", 23.415441, 89.015125),
            new xyz("-Kotchandpur", 23.435543, 88.987294),

            new xyz("Safdarpur", 23.461824, 88.963112),
            new xyz("-Safdarpur", 23.475975, 88.930711),

            new xyz("Ansarbaria", 23.492231, 88.886873),
            new xyz("-Ansarbaria", 23.492546, 88.841275),

            new xyz("Darshana", 23.528141, 88.810698),
            new xyz("-Darshana", 23.586225, 88.814217),

            new xyz("Joyrampur", 23.568092, 88.808960),
            new xyz("-Joyrampur", 23.547734, 88.803166),

            new xyz("Chuadanga", 23.638700, 88.855523),
            new xyz("-Chuadanga", 23.696499, 88.884383),

            new xyz("Mominpur", 23.682450, 88.877860),
            new xyz("-Mominpur", 23.662012, 88.868526),

            new xyz("Munshiganj", 23.715164, 88.893117),
            new xyz("-Munshiganj", 23.701392, 88.886679),

            new xyz("Alamdanga", 23.760577, 88.935710),
            new xyz("-Alamdanga", 23.812845, 88.989354),

            new xyz("Halsha", 23.813650, 88.990084),
            new xyz("-Halsha", 23.813650, 88.990084),

            new xyz("Mirpur", 23.941141, 88.997160),
            new xyz("-Mirpur", 23.981777, 88.994204),

            new xyz("Bheramara ", 24.022562, 88.992171),
            new xyz("-Bheramara ",24.058627, 88.996307),

            new xyz("Pakshi", 24.070710, 89.040416),
            new xyz("-Pakshi", 24.089130, 89.060143),

            new xyz("Ishwardi", 24.129917, 89.063032),
            new xyz("-Ishwardi", 24.173864, 89.198910),

            new xyz("Muladuli", 24.164017, 89.143099),
            new xyz("-Muladuli", 24.162137, 89.112521),

            new xyz("Gafurabad", 24.177798, 89.216613),
            new xyz("-Gafurabad", 24.170908, 89.181851),

            new xyz("Chatmohar", 24.188388, 89.273003),
            new xyz("-Chatmohar", 24.202872, 89.330918),

            new xyz("Guakhara", 24.200406, 89.321541),
            new xyz("-Guakhara", 24.206825, 89.347676),

            new xyz("Bhangura", 24.210466, 89.362611),
            new xyz("-Bhangura", 24.212638, 89.371494),

            new xyz("Boral Bridge", 24.214654, 89.379777),
            new xyz("-Boral Bridge", 24.214654, 89.379777),

            new xyz("Sharatnagar", 24.221131, 89.403294),
            new xyz("-Sharatnagar", 24.231424, 89.427820),

            new xyz("Dilpashar", 24.239152, 89.446338),
            new xyz("-Dilpashar", 24.251615, 89.476122),

            new xyz("Lahiri Mohanpur", 24.260341, 89.497064),
            new xyz("-Lahiri Mohanpur", 24.277965, 89.540001),

            new xyz("Ullapara", 24.289760, 89.569162),
            new xyz("-Ullapara", 24.319543, 89.615425),

            new xyz("Jamtoil", 24.360404, 89.659649),
            new xyz("-Jamtoil", 24.374027, 89.665271),

            new xyz("Shaheed M Monsur Ali ", 24.397694, 89.689153),
            new xyz("-Shaheed M Monsur Ali ", 24.392867, 89.715697),

            new xyz("Bangabandhu Bridge West", 24.395427, 89.731532),
            new xyz("-Bangabandhu Bridge West", 24.399707, 89.782366),

            new xyz("Bangabandhu Bridge east", 24.390327, 89.819444),
            new xyz("-Bangabandhu Bridge east", 24.341011, 89.930209),

            new xyz("Tangail", 24.276322, 89.936281),
            new xyz("-Tangail", 24.232109, 89.985033),

            new xyz("Mohera", 24.169772, 90.030588),
            new xyz("-Mohera", 24.136546, 90.057367),

            new xyz("Mirzapur", 24.105839, 90.100089),
            new xyz("-Mirzapur", 24.072146, 90.163089),

            new xyz("Mouchak", 24.038973, 90.279454),
            new xyz("-Mouchak", 24.035935, 90.352882),

            new xyz("Joydebpur", 23.999342, 90.420195),
            new xyz("-Joydebpur", 23.984738, 90.418521),

            new xyz("Dhirasram", 23.960209, 90.415624),
            new xyz("-Dhirasram", 23.924968, 90.411376),

            new xyz("Tongi", 23.899310, 90.408608),
            new xyz("-Tongi", 23.873962, 90.405926),

            new xyz("Airport", 23.852768, 90.407792),
            new xyz("-Airport", 23.825742, 90.420731),

            new xyz("Dhaka Cantonment", 23.816536, 90.412170),
            new xyz("-Dhaka Cantonment", 23.807093, 90.402471),

            new xyz("Banani", 23.796963, 90.400819),
            new xyz("-Banani", 23.774854, 90.397257),

            new xyz("Tejgaon", 23.759006, 90.394660),
            new xyz("-Tejgaon", 23.750522, 90.405968),

            new xyz("Komlapur", 23.733512, 90.426911)

    };

}
