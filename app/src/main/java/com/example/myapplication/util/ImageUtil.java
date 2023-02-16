package com.example.myapplication.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import kotlin.jvm.internal.Intrinsics;

public class ImageUtil {

    public static File reduceFileImage(File file) {
        Intrinsics.checkNotNullParameter(file, "file");
        Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
        int compressQuality = 100;
        boolean var4 = false;

        int streamLength;
        do {
            ByteArrayOutputStream bmpStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, compressQuality, (OutputStream)bmpStream);
            byte[] bmpPicByteArray = bmpStream.toByteArray();
            streamLength = bmpPicByteArray.length;
            compressQuality -= 5;
        } while(streamLength > 1000000);

        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG, compressQuality, (OutputStream)(new FileOutputStream(file)));
        }catch (Exception e){
            Log.e("ImageUtil", e.getMessage());
        }
        return file;
    }

}
