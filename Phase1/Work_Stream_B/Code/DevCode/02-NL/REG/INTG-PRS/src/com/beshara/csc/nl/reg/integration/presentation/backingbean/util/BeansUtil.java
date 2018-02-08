package com.beshara.csc.nl.reg.integration.presentation.backingbean.util;

import com.beshara.csc.sharedutils.business.util.querybuilder.ArabicAlphabetConstants;

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

import java.util.regex.Pattern;

import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

import javax.servlet.ServletContext;


public final class BeansUtil {
    public static final String TEMP_FILE_PREFIX = "regImage";
    public static final String TEMP_FILE_SUFFIX = "temp";
    public static final String EXTENSION_DELIMITER = ".";
    public static final String UPLOADED_IMAGE_BINDING_KEY = 
        "uploaded_image_binding_key";

    public static String AR_LOCALE = "ar";

    private static Map<String, String> imageTypesToExtensions;

    private static final Map<String, ResourceBundle> bundles = 
        new HashMap<String, ResourceBundle>();

    private static String locale = AR_LOCALE;

    static {
        initImageTypesToExtensions();
    }

    private BeansUtil() {
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

    public static String saveRegImage(String tempImagePath, 
                                      String relativeDirectory, 
                                      String outFileName) throws IOException {
        String fileExtension = getFileExtension(tempImagePath);
        String relativeFileName = 
            relativeDirectory + outFileName + fileExtension;
        String targetFilePath = getWebApplicationRootPath() + relativeFileName;
        copyFile(new File(tempImagePath), new File(targetFilePath));
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

    public static String mapImageTypeToExtension(String contentType) {
        return imageTypesToExtensions.get(contentType);
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

    private static void initImageTypesToExtensions() {
        imageTypesToExtensions = new HashMap<String, String>();
        imageTypesToExtensions.put("image/jpeg", "jpg");
        imageTypesToExtensions.put("image/pjpeg", "jpg");
        imageTypesToExtensions.put("image/gif", "gif");
        imageTypesToExtensions.put("image/x-png", "png");

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
    
    public static Pattern getSimilerCharRegexPattern(String searchedString) {
        String[] tokens = searchedString.split("[\\s%]+");
        String pattern = ".*";
        String temp = null;
        for (String token: tokens) {
            if (!token.equals("")) {
                temp = new String(token);
                String checkStartWith = temp.substring(0, 1);
                String checkEndWith = 
                    temp.substring(temp.length() - 1, temp.length());
                if ((checkStartWith.equals(ArabicAlphabetConstants.AlephWithOutHamza + 
                                           "") || 
                     checkStartWith.equals(ArabicAlphabetConstants.AlephWithUpperHamza + 
                                           "") || 
                     checkStartWith.equals(ArabicAlphabetConstants.AlephWithLowerHamza + 
                                           "") || 
                     checkStartWith.equals(ArabicAlphabetConstants.AlephWithMadda + 
                                           "")) && temp.length() >= 1) {
                    String aleph = 
                        "[" + ArabicAlphabetConstants.AlephWithOutHamza + "" + 
                        ArabicAlphabetConstants.AlephWithUpperHamza + "" + 
                        ArabicAlphabetConstants.AlephWithLowerHamza + "" + 
                        ArabicAlphabetConstants.AlephWithMadda + "" + "]";
                    temp = temp.substring(1);
                    temp = aleph + temp;
                }
                if ((checkEndWith.equals(ArabicAlphabetConstants.Haa + "") || 
                     checkEndWith.equals(ArabicAlphabetConstants.TaaMarbota + 
                                         "")) && temp.length() >= 1) {
                    String aleph = 
                        "[" + ArabicAlphabetConstants.Haa + "" + ArabicAlphabetConstants.TaaMarbota + 
                        "" + "]";
                    temp = temp.substring(0, temp.length() - 1);
                    temp = temp + aleph;
                }
                if ((checkEndWith.equals(ArabicAlphabetConstants.Yaa + "") || 
                     checkEndWith.equals(ArabicAlphabetConstants.AlephMaksora + 
                                         "")) && temp.length() >= 1) {
                    String aleph = 
                        "[" + ArabicAlphabetConstants.Yaa + "" + ArabicAlphabetConstants.AlephMaksora + 
                        "" + "]";
                    temp = temp.substring(0, temp.length() - 1);
                    temp = temp + aleph;
                }
                pattern = pattern + temp + ".*";

                if (searchedString.startsWith("%") && 
                    !searchedString.endsWith("%")) {
                    pattern = pattern.substring(0, pattern.length() - 2);
                } else {
                    if (!searchedString.startsWith("%") && 
                        searchedString.endsWith("%")) {
                        pattern = pattern.substring(2);
                    }
                }
            }
        }


        Pattern searchStringPatten = Pattern.compile(pattern);
        return searchStringPatten;
    } 
    
}
