package fizyo.revolutionary.com.retrofit2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityJsonObject extends AppCompatActivity {

    private ListView listView;
    String url = "http://www.blueappsoftware.in/";
    private Button button;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listViewHeroes);
        button = (Button) findViewById(R.id.button);
        button.setText("Array");
        text = (TextView) findViewById(R.id.text);
        text.setVisibility(View.VISIBLE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivityJsonObject.this, MainActivity.class));
                finish();
            }
        });
        buildRetrofit();
    }

    private void buildRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiObject service = retrofit.create(ApiObject.class);
        Call<model> call = service.getModel();
        call.enqueue(new Callback<model>() {
            @Override
            public void onResponse(Call<model> call, Response<model> response) {
                Toast.makeText(MainActivityJsonObject.this, "Got Data", Toast.LENGTH_SHORT).show();

                Log.d(" mainAction", "  rom - " + response.body().getRom().toString());
                Log.d(" mainAction", "  rom - " + response.body().getScreenSize().toString());
                Log.d(" mainAction", "  rom - " + response.body().getBackCamera().toString());
                text.setText(response.body().getRom().toString() + "\n" + response.body().getScreenSize().toString() + "\n" + response.body().getBackCamera().toString());
            }

            @Override
            public void onFailure(Call<model> call, Throwable t) {
                Log.e("mainAction ", "  error " + t.toString());
                Toast.makeText(MainActivityJsonObject.this, "Got Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
