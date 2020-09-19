package image;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.webkit.MimeTypeMap;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ImageUtil {
    private static int REQ_WIDTH = 256;
    private static int REQ_HEIGHT = 256;

    public static String getExtensionFromUri(Context context, Uri imageUri) {
        return MimeTypeMap.getSingleton()
                .getExtensionFromMimeType(context.getContentResolver().getType(imageUri));
    }

    private static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static byte[] convertToBytes(Context context, Uri uri) throws IOException {
        String ext = getExtensionFromUri(context, uri);
        InputStream input = context.getContentResolver().openInputStream(uri);
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(input, null, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, REQ_WIDTH, REQ_HEIGHT);

        if (input != null) input.close();
        input = context.getContentResolver().openInputStream(uri);
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeStream(input, null, options);
        if (bitmap != null) {
            if (ext.equalsIgnoreCase("jpg")
                    || ext.equalsIgnoreCase("jpeg"))
                bitmap.compress(Bitmap.CompressFormat.JPEG, 75, byteOut);

            else bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteOut);
        } else {
            Log.d("SYNCING", "convertToPngBytes: null");
        }
        byte[] data = byteOut.toByteArray();

        if (input != null) input.close();
        byteOut.flush();
        byteOut.close();

        return data;
    }

    public static File convertToFile(Context context, Uri uri, String fileName) throws IOException {
        String ext = getExtensionFromUri(context, uri);
        InputStream input = context.getContentResolver().openInputStream(uri);
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(input, null, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, REQ_WIDTH, REQ_HEIGHT);

        if (input != null) input.close();
        input = context.getContentResolver().openInputStream(uri);
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeStream(input, null, options);
        if (bitmap != null) {
            if (ext.equalsIgnoreCase("jpg")
                    || ext.equalsIgnoreCase("jpeg"))
                bitmap.compress(Bitmap.CompressFormat.JPEG, 75, byteOut);

            else bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteOut);
        } else {
            Log.d("SYNCING", "convertToPngBytes: null");
        }
        byte[] data = byteOut.toByteArray();

        //create a file to write bitmap data
        Log.d("file name =>",fileName+"."+ext);
        File file = new File(context.getCacheDir(), fileName);
                file.createNewFile();
//        file.setWritable(true);
        Log.d("after create fileName=>",file.getName());
        // Initialize a pointer
        // in file using OutputStream
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        // Starts writing the bytes in it
        Log.d("writing","before writing to file");
        fileOutputStream.write(data);
        Log.d("writing","after writing to file");

        fileOutputStream.flush();
        fileOutputStream.close();


        return file;
    }

    public static MultipartBody.Part toMultiPartFile(String name, File imageFile) {
        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), imageFile);

        return MultipartBody.Part.createFormData(name,
                imageFile.getName(), // filename, this is optional
                reqFile);
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            Log.e("src",src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.e("Bitmap","returned");
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception",e.getMessage());
            return null;
        }
    }

}
