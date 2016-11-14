package com.example.imran.traintracker;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;


public class train extends Activity {

    AutoCompleteTextView autocomplete;
    String phone = "16318"; //"+8801825054322";// rahat's number //16318 //+8801825054322



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trainselect);

        autocomplete = (AutoCompleteTextView) findViewById(R.id.auto1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, arr);
        autocomplete.setThreshold(0);
        autocomplete.setAdapter(adapter);


        Button next1 = (Button) findViewById(R.id.button3);

        next1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
               // Intent myIntent = new Intent(view.getContext(), viewsms.class);
               // startActivityForResult(myIntent, 0);


                Intent i = new Intent(view.getContext(), viewsms.class);
                AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.auto1);
                String getrec = textView.getText().toString();
                getrec = getcode(getrec);

                //Create the bundle
                //Bundle bundle = new Bundle();

                //Add your data to bundle
                //bundle.putString("ss", getrec + " " + phone);

                //Add the bundle to the intent
                //i.putExtras(bundle);

                //Fire that second activity
                //startActivity(i);


                if (phone.length()>0 && getrec.length()<8) {
                    sendSMS(phone, getrec); //send sms :)
                    startActivity(i); // goto next page
                    Toast.makeText(getBaseContext(),//know that i give right i/p
                            getrec,
                            Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getBaseContext(),
                            "Please select the right train.",
                            Toast.LENGTH_SHORT).show();

                //


            }

        });

    }



    private String getcode(String stuff) {

        if(stuff.length() < 30)
            return "wrong entry wrong entry";

        int k = stuff.lastIndexOf('(');
        stuff = stuff.substring(k+1, stuff.length()-1);
        return "tr " + stuff;

    }

    private void sendSMS(String phoneNumber, String message)
    {
        //PendingIntent pi = PendingIntent.getActivity(this, 0,
                //new Intent(this, viewsms.class), 0);
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }

    String[] arr = {
            "Subarna Express (Chittagong-Dhaka) (701)",
            "Subarna Express (Dhaka-Chittagong) (702)",
            "Mahanagar Godhuli (Chittagong-Dhaka) (703)",
            "Mahanagar Provati (Dhaka-Chittagong) (704)",
            "Mahanagar Provati (Chittagong-Dhaka) (721)",
            "Mahanagar Godhuli (Dhaka-Chittagong) (722)",
            "Turna (Chittagong-Dhaka) (741)",
            "Turna (Dhaka-Chittagong) (742)",
            "Parabat Express (Dhaka-Sylhet) (709)",
            "Parabat Express (Sylhet-Dhaka) (710)",
            "Joyantika Express (Dhaka-Sylhet) (717)",
            "Joyantika Express (Sylhet-Dhaka) (718)",
            "Upaban Express (Dhaka-Sylhet) (739)",
            "Upaban Express (Sylhet-Dhaka) (740)",
            "Kalani Expresss (Dhaka-Sylhet) (773)",
            "Kalani Expresss (Sylhet-Dhaka) (774)",
            "Ekota Express (Dhaka-Dinagpur) (705)",
            "Ekota Express (Dinagpur-Dhaka) (706)",
            "Drutajan Express (Dhaka-Dinagpur) (757)",
            "Drutajan Express (Dinagpur-Dhaka) (758)",
            "Upakul Express (Noakhali-Dhaka) (711)",
            "Upakul Express (Dhaka-Noakhali) (712)",
            "Lalmoni Express (Dhaka-Lalmonirhat) (751)",
            "Lalmoni Express (Lalmonirhat-Dhaka) (752)",
            "Rangpur Express (Dhaka-Rangpur) (771)",
            "Rangpur Express (Rangpur-Dhaka) (772)",
            "Jamuna Express (Dhaka-B.B Setu Purbo) (745)",
            "Jamuna Express (B.B Setu Purbo-Dhaka) (746)",
            "Egarosindhur Provati (Dhaka-Kishorganj) (737)",
            "Egarosindhur Provati (Kishorganj-Dhaka) (738)",
            "Egarosindhur Godhuli (Dhaka-Kishorganj) (749)",
            "Egarosindhur Godhuli (Kishorganj-Dhaka) (750)",
            "Kishorgonj Express (Dhaka-Kishorganj) (781)",
            "Kishorgonj Express (Kishorganj-Dhaka) (782)",
            "Haor Express (Dhaka-Mohanganj) (777)",
            "Haor Express (Mohanganj-Dhaka) (778)",
            "Aghnibina (Dhaka-Tarakandi) (735)",
            "Aghnibina (Tarakandi-Dhaka) (736)",
            "Sundarban Express (Khulna-Dhaka) (725)",
            "Sundarban Express (Dhaka-Khulna) (726)",
            "Chitra Express (Khulna-Dhaka) (763)",
            "Chitra Express (Dhaka-Khulna) (764)",
            "Silk city Express (Dhaka-Rajshahi) (753)",
            "Silk city Express (Rajshahi-Dhaka) (754)",
            "Padma Express (Dhaka-Rajshahi) (759)",
            "Padma Express (Rajshahi-Dhaka) (760)",
            "Dhumketu Express (Dhaka-Rajshahi) (769)",
            "Dhumketu Express (Rajshahi-Dhaka) (770)",
            "Nilsagar (Dhaka Cantt-Chilahati) (765)",
            "Nilsagar (Chilahat-Dhaka Cantt) (766)",
            "Moitree Express (Dhaka Cantt-Kolkata) (3107)",
            "Moitree Express (Kolkata-Dhaka Cantt) (3108)",
            "Moitree Express (Kolkata-Dhaka Cantt) (3109)",
            "Moitree Express (Dhaka Cantt-Kolkata)  (3110)",
            "Paharika Express (Chittagong-Sylhet) (719)",
            "Paharika Express (Sylhet-Chittagong) (720)",
            "Udayan Express (Chittagong-Sylhet) (723)",
            "Udayan Express (Sylhet-Chittagong) (724)",
            "Meghna Express (Chittagong-Chandpur) (729)",
            "Meghna Express (Chandpur_Chandpur) (730)",
            "Rupsha Express (Khulna-Saidpur) (727)",
            "Rupsha Express (Saidpur-Khulna) (728)",
            "Simanta Express (Khulna-Saidpur) (747)",
            "Simanta Express (Saidpur-Khulna) (748)",
            "Titumir Express (Rajshahi-Chilahati) (733)",
            "Titumir Express (Chilahati-Rajshahi) (734)",
            "Barendra Express (Rajshahi-Chilahati) (731)",
            "Barendra Express (Chilahati-Rajshahi) (732)",
            "Madhumati Express (Goalonda Ghat-Rajshahi) (755)",
            "Madhumati Express (Rajshahi-Goalonda Ghat) (756)",
            "Dolanchapa Express (Santahar-Dinajpur) (767)",
            "Dolanchapa Express (Dinajpur-Santahar) (768)",
            "Karotoa Express (Santahar-Burimari) (713)",
            "Karotoa Express (Burimari-Santahar) (714)"
    };



}
