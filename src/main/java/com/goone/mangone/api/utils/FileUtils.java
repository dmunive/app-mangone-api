package com.goone.mangone.api.utils;

import com.goone.mangone.api.exception.SaveImageException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.Date;
import java.util.UUID;

public class FileUtils {

    private static final String EXTENSION_IMAGE = ".png";

    public static String saveImage(String base64, String server, String path, String field, String message) throws SaveImageException {
        String date = DateUtils.formatDatebyPatternYYYYMMDDHyphen(new Date());
        String nameImage;
        File file = new File(Paths.get(server).toString() + Paths.get(path).toString() + File.separator + date);
        boolean toSave = true;
        if (!file.exists()) {
            toSave = file.mkdir();
        }
        if (toSave) {
            nameImage = Paths.get(path).toString() + File.separator + date + File.separator + generateRamdomFileName();
            Base64 decoder = new Base64();
            byte[] imageBytes = decoder.decode(base64);
            try {
                FileOutputStream osf = new FileOutputStream(new File(Paths.get(server).toString() + nameImage));
                osf.write(imageBytes);
                osf.flush();
            } catch (Exception e) {
                throw new SaveImageException(field, message);
            }
        } else {
            throw new SaveImageException(field, message);
        }
        return nameImage;
    }

    private static String generateRamdomFileName() {
        return UUID.randomUUID().toString().replace("-", "") + EXTENSION_IMAGE;
    }

    public static String setPathImage(String path, String image){
        String pathImage = "";
        if(!StringUtils.isEmpty(path) && !StringUtils.isEmpty(path)){
            pathImage = path + image;
        }
        DateUtils.formatDatebyPatternYYYYMMDDHyphen(new Date());
        return  pathImage;
    }

}
