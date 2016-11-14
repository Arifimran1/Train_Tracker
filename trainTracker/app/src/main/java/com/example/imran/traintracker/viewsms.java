package com.example.imran.traintracker;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class viewsms extends Activity {
  /*  String go = "763:Chitra\n" +
                "Khulna_Dhaka\n" +
                 "Left Khulna at 08.30\n" +
               "Next stn:Sharatnagar,1.3 km\n" +
               "Next stop:Bheramara at 1st stn\n" +
              "Delay 02:34"; */
    String go = "";
    TextView txtView;
    String number = "16318";//"+8801825054322"; //16318 //+8801825054322

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewsms);



        IntentFilter filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context arr0, Intent arr1) {
                if(go == "")
                    go = processReceive(arr0,arr1);
                else
                    processReceive(arr0,arr1);
            }
        };
        registerReceiver(receiver, filter);


        Button next1 = (Button) findViewById(R.id.button2);

        next1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(!go.equals("")){
                    Intent myIntent = new Intent(view.getContext(), maps.class);

                    //Create the bundle
                    Bundle bundle = new Bundle();
                    //Add your data to bundle
                    String g02 = getLocatn(go);
                    //go = getLocatn(go); //convert for exact train location

                    Toast.makeText(getBaseContext(),
                            g02,
                            Toast.LENGTH_SHORT).show();

                    bundle.putString("ss",g02);
                    //Add the bundle to the intent

                    myIntent.putExtras(bundle);
                    //Fire that second activity
                    startActivity(myIntent);

                }

                else
                    Toast.makeText(getBaseContext(),
                            "Please wait for sms receive.",
                            Toast.LENGTH_SHORT).show();

            }

        });



       //Bundle bundle = getIntent().getExtras();

        //Extract the data…
        // String stuff = bundle.getString("ss");
        //stuff = getcode(stuff);

        //txtView=(TextView)findViewById(R.id.text3);
        //txtView.setText(stuff );

    }


    BroadcastReceiver receiver = null;



    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
    public String processReceive(Context context, Intent intent){
        //Toast.makeText(context, "CO tin nhan", Toast.LENGTH_LONG).show();
        TextView ibs = (TextView)findViewById(R.id.text3);// add

        Bundle bundle = intent.getExtras();
        Object[] objArr = (Object[])bundle.get("pdus");
        String sms = "";
        String senderNumber = "";
        String smsBody = "";

        for(int i = 0; i < objArr.length;i++){
            SmsMessage smsMsg = SmsMessage.createFromPdu((byte[]) objArr[i]);

            smsBody = smsMsg.getMessageBody();
            senderNumber = smsMsg.getDisplayOriginatingAddress();
            sms += "From: " + senderNumber + "\nContent: " + smsBody + "\n";

        }

        if(senderNumber.equals(number)) {
            ibs.setText(sms);
            return smsBody;
        }
        else
            return "";


    }

    public String getLocatn(String st)
    {
        if(st.indexOf("Next stn:")!=-1){
            int f = st.indexOf("Next stn:");
            int l = st.indexOf(",");l++;
            return "-"+st.substring(f+10, l);
        }
        else if(st.indexOf("Now at:")!=-1){
            int f = st.indexOf("Now at:");
            int l = st.indexOf("Next stop");
            return st.substring(f+8, l-1);
        }
        else
            return "nothing found.";

    }



}
