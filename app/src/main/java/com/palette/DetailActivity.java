package com.palette;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

public class DetailActivity extends AppCompatActivity {
    private static final String EXTRA_DATA = "com.palette.DetailActivity.extra.data";
    private ImageView iv_detail;
    private TextView tv_title;

    public static void start(Context context, String data) {
        Intent starter = new Intent(context, DetailActivity.class);
        starter.putExtra(EXTRA_DATA, data);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        iv_detail = (ImageView) findViewById(R.id.iv_detail);
        tv_title = (TextView) findViewById(R.id.tv_title);

        ImagesModel imagesModel = ImagesModel.fromJson(getIntent().getStringExtra(EXTRA_DATA));

        tv_title.setText(imagesModel.getTitle());

        final Palette.PaletteAsyncListener paletteListener = new Palette.PaletteAsyncListener() {
            public void onGenerated(Palette palette) {
                // Get the "vibrant" color swatch based on the bitmap
                Palette.Swatch vibrant = palette.getMutedSwatch();
                if (vibrant != null) {
                    findViewById(R.id.activity_detail).setBackgroundColor(vibrant.getBodyTextColor());
                    tv_title.setTextColor(vibrant.getTitleTextColor());
                }
            }
        };


        Glide.with(this).load(imagesModel.getUrl())
                .asBitmap().centerCrop()
                .into(new BitmapImageViewTarget(iv_detail) {
                    @Override
                    public void onResourceReady(Bitmap drawable, GlideAnimation anim) {
                        super.onResourceReady(drawable, anim);

                        if (drawable != null && !drawable.isRecycled()) {
                            Palette.from(drawable).generate(paletteListener);
                        }
                    }
                });
    }
}

