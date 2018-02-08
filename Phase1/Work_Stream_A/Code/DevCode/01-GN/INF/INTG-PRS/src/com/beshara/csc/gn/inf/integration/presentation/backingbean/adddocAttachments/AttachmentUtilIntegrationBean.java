package com.beshara.csc.gn.inf.integration.presentation.backingbean.adddocAttachments;

import com.beshara.jsfbase.csc.util.fileupload.DownloadServlet;

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

public final class AttachmentUtilIntegrationBean {
    public static final String TEMP_FILE_PREFIX = "orgImage";
    public static final String TEMP_FILE_SUFFIX = "temp";
    public static final String EXTENSION_DELIMITER = ".";
    public static final String UPLOADED_IMAGE_BINDING_KEY = "uploaded_image_binding_key";

    public static String AR_LOCALE = "ar";

    private static Map<String, String> imageTypesToExtensions;
    private static Map<String, String> extensionsToContentTypes;

    private static final Map<String, ResourceBundle> bundles = new HashMap<String, ResourceBundle>();

    private static String locale = AR_LOCALE;

    static {
        initImageTypesToExtensions();
        initContentTypeByExtensions();
    }

    /**
     * index for saving multable files
     * **/
    public static String saveToTempFile(InputStream inputStream, String targetExtension) throws IOException {
        //to solve saving more than one file
        String tempFilePrefix = TEMP_FILE_PREFIX;
        File tempFile = File.createTempFile(tempFilePrefix, EXTENSION_DELIMITER + targetExtension);
        saveStream(inputStream, new FileOutputStream(tempFile));
        System.out.println(tempFile.getName());
        return tempFile.getPath();
    }

    public static void copyFile(File in, File out) throws IOException {
        saveStream(new FileInputStream(in), new FileOutputStream(out));
    }

    public static void copyFile(String inPath, String outPath) throws IOException {
        copyFile(new File(inPath), new File(outPath));
    }

    /**
     * save Image using path & file name
     * */
    public static String saveImage(String tempImagePath, String relativeDirectory,
                                   String outFileName) throws IOException {
        String relativeFileName = relativeDirectory + outFileName;
        File targetFilePath = getUploadPath(relativeFileName);
        System.out.println("targetFilePath" + targetFilePath);
        createDirs(targetFilePath.getParentFile());
        copyFile(new File(tempImagePath), targetFilePath);
        return relativeFileName;
    }

    /**
     * to view attachment
     *
     * */
    public static String getUploadImagePathToOpen(String relativeFilePath) {
        int index = relativeFilePath.lastIndexOf(".");
        String extension = relativeFilePath.substring(index + 1, relativeFilePath.length());
        String contentType = extensionsToContentTypes.get(extension);

        return String.format("/downloadservlet?%s=%s&&%s=%s", DownloadServlet.CONTENT_TYPE_PARAM, contentType,
                             DownloadServlet.REL_FILE_PATH_PARAM, relativeFilePath);
    }

    public static String getTempUploadImagePathToOpen(String absoluteFilePath) {
        int index = absoluteFilePath.lastIndexOf(".");
        String extension = absoluteFilePath.substring(index, absoluteFilePath.length());
        String contentType = extensionsToContentTypes.get(extension);
        return String.format("/downloadservlet?%s=%s&&%s=%s&&%s=%s", DownloadServlet.CONTENT_TYPE_PARAM, contentType,
                             DownloadServlet.REL_FILE_PATH_PARAM, absoluteFilePath,
                             DownloadServlet.ABS_FILE_PATH_PARAM, absoluteFilePath);
    }

    public static void createDirs(File targetPath) {
        while (!targetPath.exists()) {
            createDirs(targetPath.getParentFile());
            targetPath.mkdir();
        }
    }

    public static void saveStream(InputStream inputStream, OutputStream outputStream) throws IOException {
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

    public static File getUploadPath(String relativeModulePath) {
        return new File(String.format("%s\\%s", DownloadServlet.getUploadsDir(), relativeModulePath));
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
        createValueBinding(bindingString).setValue(FacesContext.getCurrentInstance(), object);
    }

    public static ValueBinding createValueBinding(String bindingString) {
        return FacesContext.getCurrentInstance().getApplication().createValueBinding("#{" + bindingString + "}");
    }

    private static void initImageTypesToExtensions() {
        imageTypesToExtensions = new HashMap<String, String>();
        imageTypesToExtensions.put("image/jpeg", "jpg");
        imageTypesToExtensions.put("image/pjpeg", "jpg");
        imageTypesToExtensions.put("image/gif", "gif");
        imageTypesToExtensions.put("image/png", "png");
        imageTypesToExtensions.put("application/pdf", "pdf");
        imageTypesToExtensions.put("application/msword", "doc");
        imageTypesToExtensions.put("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "xlsx");
       imageTypesToExtensions.put("text/plain", "txt");
        imageTypesToExtensions.put("application/vnd.openxmlformats-officedocument.wordprocessingml.document", "docs");
    }

    private static void initContentTypeByExtensions() {
        extensionsToContentTypes = new HashMap<String, String>();
        extensionsToContentTypes.put("jpg", "image/jpeg");
        //  extensionsToContentTypes.put("jpg","image/pjpeg");
        extensionsToContentTypes.put("gif", "image/gif");
        extensionsToContentTypes.put("png", "image/png");
        extensionsToContentTypes.put("pdf", "application/pdf");
        extensionsToContentTypes.put("doc", "application/msword");
        extensionsToContentTypes.put("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        extensionsToContentTypes.put("txt", "text/plain");
        extensionsToContentTypes.put("docs", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
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

