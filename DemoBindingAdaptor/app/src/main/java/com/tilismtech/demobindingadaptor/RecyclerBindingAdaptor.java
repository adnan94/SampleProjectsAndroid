package com.tilismtech.demobindingadaptor;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.widget.ImageView;

public class RecyclerBindingAdaptor implements android.databinding.DataBindingComponent {
    private Context activity;

    public RecyclerBindingAdaptor(Context activity) {
        DataBindingUtil.setDefaultComponent(this);
        this.activity = activity;
    }

//    @BindingAdapter("app:newsImage")
    public void loadImage(ImageView view, String imgPath) {
//        if (activity instanceof ChannelParentActivity) {
//            if (!((ChannelParentActivity) activity).getAllImageUrls().contains(imgPath)) {
//                ((ChannelParentActivity) activity).getAllImageUrls().add(imgPath);
//            }
//        }
//        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(imgPath))
//                .setResizeOptions(new ResizeOptions(AndroidUtilities.dp(95), AndroidUtilities.dp(95)))
//                .build();
//        setImageController(view, request);
    }

}
