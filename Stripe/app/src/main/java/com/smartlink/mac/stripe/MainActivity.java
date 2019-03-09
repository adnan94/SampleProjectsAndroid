package com.smartlink.mac.stripe;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;
import com.stripe.android.view.CardMultilineWidget;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    CardMultilineWidget mCardMultilineWidget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        mCardMultilineWidget = (CardMultilineWidget) findViewById(R.id.card_multiline_widget);

        final Stripe stripe = new Stripe(getApplicationContext());
        stripe.setDefaultPublishableKey("pk_test_v2y1yI9cAQAaHApuAZuwrrXi");
        com.stripe.Stripe.apiKey = "sk_test_AHuYFkY75XoNaa1iDvVMSWYQ";

        findViewById(R.id.checkout_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Card cardToSave = mCardMultilineWidget.getCard();

                if (cardToSave == null) {
                    Log.d("Error Log : ", "Invalid Card Data");
                    return;
                } else {
                    stripe.createToken(

                            new Card(cardToSave.getNumber(), cardToSave.getExpMonth(), cardToSave.getExpYear(), cardToSave.getCVC()),
                            new TokenCallback() {
                                public void onSuccess(Token token) {
                                    // Send token to your own web service
//                MyServer.chargeToken(token);
                                    Log.d("Error Log : ", token.getId().toString());

                                    Map<String, Object> params = new HashMap<>();
                                    params.put("amount", 100);
                                    params.put("currency", "usd");
                                    params.put("description", "Example charge");
                                    params.put("source", token.getId().toString());
                                    try {
                                        Charge charge = Charge.create(params).;

                                    } catch (AuthenticationException e) {
                                        e.printStackTrace();
                                    } catch (InvalidRequestException e) {
                                        e.printStackTrace();
                                    } catch (APIConnectionException e) {
                                        e.printStackTrace();
                                    } catch (CardException e) {
                                        e.printStackTrace();
                                    } catch (APIException e) {
                                        e.printStackTrace();
                                    }
//                            Charge
                                }

                                public void onError(Exception error) {
                                    Toast.makeText(getApplicationContext(),
                                            error.getLocalizedMessage(),
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                    );

                }

            }
        });

    }
}