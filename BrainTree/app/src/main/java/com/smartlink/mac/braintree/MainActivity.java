package com.smartlink.mac.braintree;

import android.app.Activity;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.ClientTokenRequest;
import com.braintreegateway.Environment;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;
import com.braintreepayments.api.dropin.DropInActivity;
import com.braintreepayments.api.dropin.DropInRequest;
import com.braintreepayments.api.dropin.DropInResult;
import com.braintreepayments.api.models.BraintreeApiConfiguration;

import java.math.BigDecimal;


public class MainActivity extends AppCompatActivity {

    BraintreeGateway gateway = new BraintreeGateway(
            Environment.SANDBOX,
            "jjryh3nmy5gpq2z9",
            "qvp8v2dhybf7jzxq",
            "001a5973d9a5f9faea4ffeff1a793192"
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClientTokenRequest clientTokenRequest = new ClientTokenRequest();
                String clientToken = gateway.clientToken().generate(clientTokenRequest);


                DropInRequest dropInRequest = new DropInRequest()
                .clientToken(clientToken);
                startActivityForResult(dropInRequest.getIntent(MainActivity.this), 0);
                }

        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {
                DropInResult result = data.getParcelableExtra(DropInResult.EXTRA_DROP_IN_RESULT);
                // use the result to update your UI and send the payment method nonce to your server
                TransactionRequest request = new TransactionRequest()
                        .amount(new BigDecimal("10.00"))
                        .paymentMethodNonce(result.getPaymentMethodNonce().getNonce())
                        .options()
                        .submitForSettlement(true)
                        .done();

                Result<Transaction> resultt = gateway.transaction().sale(request);
                if (resultt.isSuccess()) {
                    // See result.getTarget() for details
                } else {
                    // Handle errors
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                // the user canceled
            } else {
                // handle errors here, an exception may be available in
                Exception error = (Exception) data.getSerializableExtra(DropInActivity.EXTRA_ERROR);
            }
        }
    }
}
