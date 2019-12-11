package com.revolutionary.fresco;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Uri uri = Uri.parse("https://source.unsplash.com/random");
        SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.my_image_view);
//        draweeView.setImageURI(uri);
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .build();
        draweeView.setController(controller);
        draweeView.getHierarchy().setPlaceholderImage(R.mipmap.placeholder_nav_header);
        draweeView.getHierarchy().setRoundingParams(new RoundingParams().setRoundAsCircle(true));


    }
}
