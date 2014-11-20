package com.cvte.sdk.update.util;

import android.content.Context;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @description: Api for cache operation
 * @author: Saul
 * @date: 14-11-10
 * @version: 1.0
 */
public class CacheUtil {

    public static void clear(Context context) {
        String cachePath = PreferenceUtil.getDownloadPath(context);
        final File cacheFolder = new File(cachePath);
        if (cacheFolder.exists()) {
            final long maxCacheSize = PreferenceUtil.getCacheSize(context);
            final long folderSize = FileUtil.folderSize(cacheFolder);
            if (folderSize > maxCacheSize) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        File[] files = cacheFolder.listFiles();
                        Arrays.sort(files, new Comparator<File>() { // move older file forward
                            @Override
                            public int compare(File lhs, File rhs) {
                                return Long.valueOf(lhs.lastModified()).compareTo(rhs.lastModified());
                            }
                        });
                        long currentSize = folderSize;
                        for (File file : files) {
                            long size = file.length();
                            if (file.delete()) {
                                currentSize -= size;
                            }
                            if (currentSize < maxCacheSize / 2) {
                                break;
                            }
                        }
                    }
                }).start();
            }
        }
    }
}
