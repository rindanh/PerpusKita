package com.example.perpuskita;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FocusActivity extends AppCompatActivity {

    private static final String TAG = "SensorsTutorial";
    private Sensor gyroscopeSensor;
    private Sensor lightSensor;
    private SensorManager sensorManager;
    private SensorEventListener gyroscopeSensorListener;
    private SensorEventListener lightSensorListener;
    private float maxValue;
    private TextView lightTextView;
    private Button exitButton;
    private Button lightButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focus);

        sensorManager =
                (SensorManager) getSystemService(SENSOR_SERVICE);
        gyroscopeSensor =
                sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if(gyroscopeSensor == null) {
            Toast.makeText(this, "The device has no gyroscope sensor !", Toast.LENGTH_SHORT).show();
            finish();
        }
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (lightSensor == null) {
            Toast.makeText(this, "The device has no light sensor !", Toast.LENGTH_SHORT).show();
            finish();
        }

        // Create a listener
         gyroscopeSensorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                // More code goes here
                if(sensorEvent.values[0] > 0.5f || sensorEvent.values[0] < -0.5f) { // anticlockwise
                    Toast.makeText(getApplicationContext(), "Jangan main HP!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };

        // max value for light sensor
        maxValue = lightSensor.getMaximumRange();
//        lightTextView = (TextView) findViewById(R.id.showValue);

        lightButton = (Button) findViewById(R.id.buttonLight);
        lightSensorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                final float value = sensorEvent.values[0];
                String vv = Float.toString(value);
                lightButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (value>20) {
                            Toast.makeText(getApplicationContext(), "Cahaya di tempatmu cocok untuk tempat membaca", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "PINDAH! Cahaya di tempatmu tidak cocok untuk tempat membaca", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
//                lightTextView.setText(vv);

                // between 0 and 255
                int newValue = (int) (255f * value / maxValue);
//                getWindow().getDecorView().setBackgroundColor(Color.rgb(newValue, newValue, newValue));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

        exitButton = (Button) findViewById(R.id.buttonExit);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        // Register the listener
        sensorManager.registerListener(gyroscopeSensorListener,
                gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(lightSensorListener, lightSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(gyroscopeSensorListener);
        sensorManager.unregisterListener(lightSensorListener);
    }
}
