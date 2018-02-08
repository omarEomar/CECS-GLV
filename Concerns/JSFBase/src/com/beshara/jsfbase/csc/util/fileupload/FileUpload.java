package com.beshara.jsfbase.csc.util.fileupload;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

import javax.servlet.ServletContext;


public final class FileUpload {
    public static final String TEMP_FILE_PREFIX = "File";
    public static final String TEMP_FILE_SUFFIX = "temp";
    public static final String EXTENSION_DELIMITER = ".";
    public static final String UPLOADED_IMAGE_BINDING_KEY = 
        "uploaded_image_binding_key";
    public static String AR_LOCALE = "ar";
    private static Map<String, String> fileTypesToExtensions;
    private static final Map<String, ResourceBundle> bundles = 
        new HashMap<String, ResourceBundle>();
    private static String locale = AR_LOCALE;

    static {
        initFileTypesToExtensions();
    }

    private FileUpload() {
    }

    public static String saveToTempFile(InputStream inputStream, 
                                        String targetExtension) throws IOException {
        File tempFile = 
            File.createTempFile(TEMP_FILE_PREFIX, EXTENSION_DELIMITER + 
                                targetExtension);
        saveStream(inputStream, new FileOutputStream(tempFile));
        return tempFile.getPath();
    }

    public static void copyFile(File in, File out) throws IOException {
        saveStream(new FileInputStream(in), new FileOutputStream(out));
    }

    public static void copyFile(String inPath, 
                                String outPath) throws IOException {
        copyFile(new File(inPath), new File(outPath));
    }


    /**
     * Purpose: copy file from temp dir into any location under server
     * Creation/Modification History :
     * 1.1 - Developer Name: Khalid Mohie
     * 1.2 - Date:  today
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: overloaded method
     *                    
     */
    public static String saveFile(String tempFilePath, 
                                  String relativeDirectory, 
                                  String outFileName) throws IOException {
        String fileExtension = getFileExtension(tempFilePath);
        String relativeFileName = 
            relativeDirectory + outFileName + fileExtension;
        String targetFilePath = getWebApplicationRootPath() + relativeFileName;
        System.out.println(targetFilePath);
        File dir = new File(getWebApplicationRootPath() + relativeDirectory);
        dir.mkdirs();
        File f = new File(dir, outFileName + fileExtension);
        f.createNewFile();
        copyFile(new File(tempFilePath), f);
        //        new File(targetFilePath).mkdirs();
        //        f = new File(targetFilePath);
        //        saveStream(new File(tempFilePath), new FileOutputStream(f));
        return relativeFileName;
    }

    /**
     * Purpose: make new file from stream and put it into any location under server
     * Creation/Modification History :
     * 1.1 - Developer Name: Khalid Mohie
     * 1.2 - Date:  today
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: overloaded method
     *                     
     */
    public static String saveFile(InputStream inputStream, 
                                  String relativeDirectory, 
                                  String outFileName) throws IOException {
        String relativeFileName = relativeDirectory + outFileName;
        String targetFilePath = getWebApplicationRootPath() + relativeFileName;
        System.out.println(targetFilePath);
        //copyFile(new File(tempImagePath), new File(targetFilePath));
        saveStream(inputStream, new FileOutputStream(targetFilePath));
        return relativeFileName;
    }

    public static void saveStream(InputStream inputStream, 
                                  OutputStream outputStream) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(outputStream);
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        try {
            byte[] buffer = new byte[1024];
            int count = 0;
            while ((count = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, count);
            }
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
    }

    public static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(EXTENSION_DELIMITER));
    }

    public static String mapFileTypeToExtension(String contentType) {
        return fileTypesToExtensions.get(contentType);
    }

    public static String getWebApplicationRootPath() {
        return getRealPath("/");
    }

    public static String getRealPath(String dir) {
        return (((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext())).getRealPath(dir);
    }

    public static Object getValue(String bindingString) {
        return createValueBinding(bindingString).getValue(FacesContext.getCurrentInstance());
    }

    public static void setValue(String bindingString, Object object) {
        createValueBinding(bindingString).setValue(FacesContext.getCurrentInstance(), 
                                                   object);
    }

    public static ValueBinding createValueBinding(String bindingString) {
        return FacesContext.getCurrentInstance().getApplication().createValueBinding("#{" + 
                                                                                     bindingString + 
                                                                                     "}");
    }

    private static void initFileTypesToExtensions() {
        fileTypesToExtensions = new HashMap<String, String>();
        fileTypesToExtensions.put("image/jpeg", "jpg");
        fileTypesToExtensions.put("image/pjpeg", "jpg");
        fileTypesToExtensions.put("image/gif", "gif");
        fileTypesToExtensions.put("image/x-png", "png");

    }

    public static String getResource(String bundleKey, String resourceKey) {
        try {
            return getBundle0(bundleKey).getString(resourceKey);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    private static ResourceBundle getBundle0(String bundleKey) {

        ResourceBundle bundle = bundles.get(bundleKey);
        if (bundle != null) {
            return bundle;
        }

        bundle = ResourceBundle.getBundle(bundleKey, new Locale(locale));
        bundles.put(bundleKey, bundle);

        return bundle;
    }
}
