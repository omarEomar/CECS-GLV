package com.beshara.jsfbase.csc.util.fileupload;


import com.beshara.base.config.ConfigManager;
import com.beshara.jsfbase.csc.util.FlmHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;


public class DownloadServlet extends HttpServlet {

    @SuppressWarnings("compatibility:2067671441795590522")
    private static final long serialVersionUID = 1L;

    public final static String CONTENT_TYPE_PARAM = "ct";
    public final static String ABS_FILE_PATH_PARAM = "af";
    public final static String REL_FILE_PATH_PARAM = "rf";

    private static String uploadsDir;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public static String getUploadsDir() {
        if (uploadsDir == null) {
            ConfigManager cfg = ConfigManager.getInstance();
            uploadsDir = cfg.getParam("glb:uploadsdir", true);
        }
        return uploadsDir;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (FlmHelper.hasFileDownloadRequest(request)) {
            FlmHelper.downloadFile(request, response);
        } else {
            String contentType = StringUtils.trimToNull(request.getParameter(CONTENT_TYPE_PARAM));
            if (contentType != null) {
                response.setContentType(contentType);
            }

            String absFilePath = StringUtils.trimToNull(request.getParameter(ABS_FILE_PATH_PARAM));
            String relFilePath = StringUtils.trimToNull(request.getParameter(REL_FILE_PATH_PARAM));

            FileInputStream fis = null;
            ServletOutputStream sos = null;
            try {
                if (absFilePath != null) {
                    fis = new FileInputStream(new File(absFilePath));
                } else {
                    fis = new FileInputStream(new File(getUploadsDir(), relFilePath));
                }
                sos = response.getOutputStream();
                byte[] buffer = new byte[1024];
                int count = 0;
                while ((count = fis.read(buffer)) != -1) {
                    sos.write(buffer, 0, count);
                }
            } finally {
                if (fis != null) {
                    fis.close();
                }
                if (sos != null) {
                    sos.close();
                }
            }
        }
    }
}
