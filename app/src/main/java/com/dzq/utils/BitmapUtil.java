package com.dzq.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by dingzuoqiang on 2017/07/10.
 * Email: 530858106@qq.com
 */

public class BitmapUtil {

    /**
     * 按照一定压缩比 和 质量
     *
     * @param filePath
     * @param width
     * @param height
     * @param quality  图片质量   1-100
     */
    public static Bitmap getSmallBitmap(String filePath, int width, int height, int quality) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        options.inSampleSize = calculateInSampleSize(options, width, height);
        options.inJustDecodeBounds = false;
        Bitmap srcBitmap = BitmapFactory.decodeFile(filePath, options);
        if (srcBitmap == null) {
            return null;
        } else {
            int degree = readPictureDegree(filePath);
            Bitmap dstBitmap = rotateBitmap(srcBitmap, degree);
            if (dstBitmap != srcBitmap) {
                srcBitmap.recycle();
                srcBitmap = null;
            }

            if (quality > 0 && quality <= 100) {
                ByteArrayOutputStream baos = null;

                try {
                    baos = new ByteArrayOutputStream();
                    dstBitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);//这里压缩quality%，把压缩后的数据存放到baos中
                    ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
                    dstBitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
                } finally {
                    try {
                        if (baos != null) {
                            baos.close();
                        }
                    } catch (IOException var15) {
                        var15.printStackTrace();
                    }

                }

                return dstBitmap;
            }
            return dstBitmap;
        }
    }

    /**
     * 按照一定压缩比 和 质量
     *
     * @param filePath
     * @param width
     * @param height
     * @param maxSize  target will be compressed to be smaller than this size.(kb)
     */
    public static Bitmap getSmallBitmap2(String filePath, int width, int height, int maxSize) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        options.inSampleSize = calculateInSampleSize(options, width, height);
        options.inJustDecodeBounds = false;
        Bitmap srcBitmap = BitmapFactory.decodeFile(filePath, options);
        if (srcBitmap == null) {
            return null;
        } else {
            int degree = readPictureDegree(filePath);
            Bitmap dstBitmap = rotateBitmap(srcBitmap, degree);
            if (dstBitmap != srcBitmap) {
                srcBitmap.recycle();
                srcBitmap = null;
            }

            if (dstBitmap != null) {
                int quality = 100;
                ByteArrayOutputStream baos = null;
                try {
                    baos = new ByteArrayOutputStream();
                    dstBitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);//这里压缩quality%，把压缩后的数据存放到baos中
                    // Compress by loop
                    while ((baos.toByteArray().length / 1024 > maxSize) && quality > 0) {
                        // Clean up os
                        baos.reset();
                        // interval 10
                        quality -= 5;
                        dstBitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
                    }
                    ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
                    dstBitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
                } finally {
                    try {
                        if (baos != null) {
                            baos.close();
                        }
                    } catch (IOException var15) {
                        var15.printStackTrace();
                    }
                }
                return dstBitmap;
            }
            return dstBitmap;
        }
    }

    //  4.读取图片旋转角度
    private static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }


    // 5.旋转图片

    private static Bitmap rotateBitmap(Bitmap bitmap, int rotate) {
        if (bitmap == null)
            return null;

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        // Setting post rotate to 90
        Matrix mtx = new Matrix();
        mtx.postRotate(rotate);
        return Bitmap.createBitmap(bitmap, 0, 0, w, h, mtx, true);
    }

    //6.计算压缩比
    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int height = options.outHeight;
        int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            int heightRatio = Math.round((float) height / (float) reqHeight);
            int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio > widthRatio ? widthRatio : heightRatio;
        }

        return inSampleSize;

    }

    /**
     * 图片的缩放方法
     *
     * @param bgimage   ：源图片资源
     * @param newWidth  ：缩放后宽度
     * @param newHeight ：缩放后高度
     * @return
     */
    public static Bitmap zoomImage(Bitmap bgimage, double newWidth,
                                   double newHeight) {
        // 获取这个图片的宽和高
        float width = bgimage.getWidth();
        float height = bgimage.getHeight();
        // 创建操作图片用的matrix对象
        Matrix matrix = new Matrix();
        // 计算宽高缩放率
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 缩放图片动作
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(bgimage, 0, 0, (int) width,
                (int) height, matrix, true);
    }

    /**
     * Get bitmap from specified image path
     *
     * @param imgPath
     * @return
     */
    public static Bitmap getBitmap(String imgPath) {
        // Get bitmap through image path
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = false;
        newOpts.inPurgeable = true;
        newOpts.inInputShareable = true;
        // Do not compress
        newOpts.inSampleSize = 1;
        newOpts.inPreferredConfig = Bitmap.Config.RGB_565;
        return BitmapFactory.decodeFile(imgPath, newOpts);
    }

    /**
     * Store bitmap into specified image path
     *
     * @param bitmap
     * @param outPath
     * @throws FileNotFoundException
     */
    public void storeImage(Bitmap bitmap, String outPath) throws FileNotFoundException {
        FileOutputStream os = new FileOutputStream(outPath);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
    }

    /**
     * Compress by quality,  and generate image to the path specified
     *
     * @param image
     * @param outPath
     * @param maxSize target will be compressed to be smaller than this size.(kb)
     * @throws IOException
     */
    public static void compressAndGenImage(Bitmap image, String outPath, int maxSize) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        // scale
        int options = 100;
        // Store the bitmap into output stream(no compress)
        image.compress(Bitmap.CompressFormat.JPEG, options, os);
        // Compress by loop
        while (os.toByteArray().length / 1024 > maxSize && options > 0) {
            // Clean up os
            os.reset();
            // interval 10
            options -= 10;
            image.compress(Bitmap.CompressFormat.JPEG, options, os);
        }

        // Generate compressed image file
        FileOutputStream fos = new FileOutputStream(outPath);
        fos.write(os.toByteArray());
        fos.flush();
        fos.close();

        // 先判断是否已经回收
        if (image != null && !image.isRecycled()) {
            // 回收并且置为null
            image.recycle();
            image = null;
        }
        System.gc();
    }

    /**
     * Compress by quality,  and generate image to the path specified
     *
     * @param imgPath
     * @param outPath
     * @param maxSize     target will be compressed to be smaller than this size.(kb)
     * @param needsDelete Whether delete original file after compress
     * @throws IOException
     */
    public static void compressAndGenImage(String imgPath, String outPath, int maxSize, boolean needsDelete) throws IOException {
        compressAndGenImage(getBitmap(imgPath), outPath, maxSize);

        // Delete original file
        if (needsDelete) {
            File file = new File(imgPath);
            if (file.exists()) {
                file.delete();
            }
        }
    }

}