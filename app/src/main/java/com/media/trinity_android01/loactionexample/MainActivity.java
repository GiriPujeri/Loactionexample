 package com.media.trinity_android01.loactionexample;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

 public class MainActivity extends AppCompatActivity {
    TextView addressTV,latLongTV;
     Button addressButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addressTV = (TextView) findViewById(R.id.textView);
        latLongTV = (TextView) findViewById(R.id.textView2);

        addressButton = (Button) findViewById(R.id.button);
        addressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                EditText editText = (EditText) findViewById(R.id.editText);
                String address = editText.getText().toString();

                GeocodingLocation locationAddress = new GeocodingLocation();
                locationAddress.getAddressFromLocation(address,
                        getApplicationContext(), new GeocoderHandler());

            }
        });
    }


    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }
            latLongTV.setText(locationAddress);

            String uri = String.format(Locale.ENGLISH, "geo:%f,%f",15.3949053,75.0786244);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            MainActivity.this.startActivity(intent);
        }
    }
}

