package com.github.quinnjn.braintree.launchmodes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.braintreepayments.api.BraintreeFragment;
import com.braintreepayments.api.PayPal;
import com.braintreepayments.api.exceptions.InvalidArgumentException;
import com.braintreepayments.api.interfaces.BraintreeErrorListener;
import com.braintreepayments.api.interfaces.PaymentMethodNonceCreatedListener;
import com.braintreepayments.api.models.PaymentMethodNonce;

public class PaymentsActivity extends AppCompatActivity implements PaymentMethodNonceCreatedListener,
        BraintreeErrorListener {

    private BraintreeFragment mBraintreeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        try {
            // Setup Braintree
            mBraintreeFragment = BraintreeFragment.newInstance(this, "sandbox_tmxhyf7d_dcpspy2brwdjr3qn");
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        }

        // Request PayPal
        PayPal.authorizeAccount(mBraintreeFragment);
    }

    @Override
    public void onPaymentMethodNonceCreated(PaymentMethodNonce paymentMethodNonce) {
        // Got a PayPal Nonce!
        Toast.makeText(this, "Paid with " + paymentMethodNonce.getDescription(), Toast.LENGTH_SHORT)
                .show();

        // Send it back to the parent Activity
        setResult(RESULT_OK, new Intent()
                .putExtra("nonce", paymentMethodNonce.getNonce()));
    }

    @Override
    public void onError(Exception error) {
        throw new RuntimeException(error);
    }
}
