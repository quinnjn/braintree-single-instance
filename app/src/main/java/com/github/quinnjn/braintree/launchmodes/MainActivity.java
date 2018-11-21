package com.github.quinnjn.braintree.launchmodes;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /*
     * This Activity's launch mode is set to singleInstance
     * That means that launched Activities will not sit on the same Activity stack as this one.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startPayment(View view) {
        // Calling our Payment Activity for results.
        startActivityForResult(new Intent(this, PaymentsActivity.class), 100);
    }


    /*
     * We want to use this onActivityResult to get data back from the payments activity.
     * Although, since it is singleInstance we will never hear back from our child Activities.
     *
     * We can communicate in other ways besides onActivityResult but we cannot trigger results in
     * the same way and the data may be outdated.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data == null) {
            return;
        }

        String nonce = data.getStringExtra("nonce");

        if (nonce == null) {
            return;
        }

        Toast.makeText(this, "Main Activity " + nonce, Toast.LENGTH_SHORT)
                .show();
    }
}
