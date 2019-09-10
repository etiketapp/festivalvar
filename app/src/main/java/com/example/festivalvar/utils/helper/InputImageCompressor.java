package com.example.festivalvar.utils.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.Toast;

public class InputImageCompressor extends Activity {

    public static void compressInputImage(Intent data, Context context, ImageView newIV)
    {
        Bitmap bitmap;
        Uri inputImageData = data.getData();
        try
        {
            Bitmap bitmapInputImage = MediaStore.Images.Media.getBitmap(context.getContentResolver(), inputImageData);
            if (bitmapInputImage.getWidth() > 2048 && bitmapInputImage.getHeight() > 2048)
            {
                bitmap = Bitmap.createScaledBitmap(bitmapInputImage, 1024, 1280, true);
                newIV.setImageBitmap(bitmap);
            }
            else if (bitmapInputImage.getWidth() > 2048 && bitmapInputImage.getHeight() < 2048)
            {
                bitmap = Bitmap.createScaledBitmap(bitmapInputImage, 1920, 1200, true);
                newIV.setImageBitmap(bitmap);
            }
            else if (bitmapInputImage.getWidth() < 2048 && bitmapInputImage.getHeight() > 2048)
            {
                bitmap = Bitmap.createScaledBitmap(bitmapInputImage, 1024, 1280, true);
                newIV.setImageBitmap(bitmap);
            }
            else if (bitmapInputImage.getWidth() < 2048 && bitmapInputImage.getHeight() < 2048)
            {
                bitmap = Bitmap.createScaledBitmap(bitmapInputImage, bitmapInputImage.getWidth(), bitmapInputImage.getHeight(), true);
                newIV.setImageBitmap(bitmap);
            }
        } catch (Exception e)
        {
            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}