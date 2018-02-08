package com.beshara.csc.gn.inf.integration.presentation.shared;


import com.beshara.base.client.ClientFactoryUtil;
import com.beshara.csc.flm.flm.business.client.INDriveClient;
import com.beshara.csc.flm.flm.business.dto.NDriveDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.util.fileupload.DownloadServlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

import javax.servlet.ServletContext;


public final class IntegrationBeansUtil {
    public static final String TEMP_FILE_PREFIX = "copyImage";
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

    private IntegrationBeansUtil() {
    }

    public static String saveToTempFile(InputStream inputStream, String targetExtension) throws IOException {
        File tempFile = File.createTempFile(TEMP_FILE_PREFIX, EXTENSION_DELIMITER + targetExtension);
        saveStream(inputStream, new FileOutputStream(tempFile));
        return tempFile.getPath();
    }

    public static void copyFile(File in, File out) throws IOException {
        saveStream(new FileInputStream(in), new FileOutputStream(out));
    }

    public static void copyFile(String inPath, String outPath) throws IOException {
        copyFile(new File(inPath), new File(outPath));
    }

    public static String saveEmpImage(String tempImagePath, String relativeDirectory,
                                      String outFileName) throws IOException {
        String fileExtension = getFileExtension(tempImagePath);
        String webAppRootPath = getWebApplicationRootPath();
        String relativeFileName = relativeDirectory + "/" + outFileName + fileExtension;
        String targetFilePath = webAppRootPath + relativeFileName;
        String targetDirectoyry = webAppRootPath + relativeDirectory;
        File targetFile = new File(targetDirectoyry);
        targetFile.mkdirs();
        copyFile(new File(tempImagePath), new File(targetFilePath));
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
        String extension = absoluteFilePath.substring(index + 1, absoluteFilePath.length());
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
        //return getRealPath("/");
        /**** by saad ****/
        INDriveClient client = ClientFactoryUtil.getInstance(INDriveClient.class);
        List dtos = new ArrayList<NDriveDTO>();
        String path = "";
        try {
            dtos = client.getActive();
            path = ((NDriveDTO)dtos.get(0)).getNpath();
        } catch (DataBaseException e) {
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }
        ServletContext c = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
        String ModuleName = c.getContextPath();
        if (!path.equals(""))
            path = path + ModuleName + "/";
        return path;
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
        imageTypesToExtensions.put("image/bmp", "bmp");
        imageTypesToExtensions.put("image/png", "png");
    }

    private static void initContentTypeByExtensions() {
        extensionsToContentTypes = new HashMap<String, String>();
        extensionsToContentTypes.put("jpg", "image/jpeg");
        extensionsToContentTypes.put("jpg", "image/pjpeg");
        extensionsToContentTypes.put("bmp", "image/bmp");
        extensionsToContentTypes.put("png", "image/png");
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
