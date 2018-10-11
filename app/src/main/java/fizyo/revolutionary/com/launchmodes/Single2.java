package fizyo.revolutionary.com.launchmodes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Single2 extends AppCompatActivity {
    Button button1, button2, button3, button4;

    @Override
    protected void onResume() {
        Toast.makeText(this, "Activity 2 Single Top", Toast.LENGTH_SHORT).show();
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "On Create", Toast.LENGTH_SHORT).show();

        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Single2.this,Single2.class));

            }
        });
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Single2.this,Single3.class));

            }
        });
        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Single2.this,Single3.class));

            }
        });


    }

    @Override
    protected void onNewIntent(Intent intent) {
        Toast.makeText(this, "Activity On New Intent", Toast.LENGTH_SHORT).show();

        super.onNewIntent(intent);
    }
}
