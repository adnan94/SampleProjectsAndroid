package com.revolutionary.demoencriptdecript;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {

    TextView encryptedText, text;
    Button encrypt, decrypt;
    String outputStr;
    SecretKeySpec key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        encryptedText = (TextView) findViewById(R.id.textEncrypted);
        text = (TextView) findViewById(R.id.text);
        encrypt = (Button) findViewById(R.id.encrypt);
        decrypt = (Button) findViewById(R.id.decrypt);


        try {
            key = generateKey(text.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        encrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                try {
//                    outputStr = encrypt(text.getText().toString());
//                    encryptedText.setText(outputStr);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                String decrypted = "";

                ///////////second way/////////
                try {
                    outputStr = AESUtils.encrypt(text.getText().toString());
                    encryptedText.setText(outputStr);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        decrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                try {
//                    outputStr = decrypt(outputStr);
//                    encryptedText.setText(outputStr);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }

///////////one way/////////
                String decrypted = "";
                try {
                    decrypted = AESUtils.decrypt(outputStr);
                    encryptedText.setText(decrypted);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private String encrypt(String text) throws Exception {


        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(text.getBytes());
        String encryptedValue = Base64.encodeToString(encVal, android.util.Base64.DEFAULT);
        return encryptedValue;
    }


    public String decrypt(String cipher) throws Exception {
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decodeval = Base64.decode(cipher, Base64.DEFAULT);
        byte[] decval = c.doFinal(decodeval);
        String decryptedValue = new String(decval);
        return decryptedValue;
    }


    private SecretKeySpec generateKey(String text) throws Exception {

        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = text.getBytes("UTF-8");
        digest.update(bytes, 0, bytes.length);
        byte[] key = digest.digest();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        return secretKeySpec;
    }
}
