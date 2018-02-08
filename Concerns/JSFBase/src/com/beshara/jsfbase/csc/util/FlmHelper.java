package com.beshara.jsfbase.csc.util;


import com.beshara.common.web.context.WebContext;
import com.beshara.csc.flm.flm.business.FileManager;
import com.beshara.csc.flm.flm.business.dto.FileDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;


public class FlmHelper {

    private FlmHelper() {
        super();
    }

    public static String getFileURL(String fileId) {
        String contextPath = WebContext.get().getRequest().getContextPath();
        StringBuilder sb = new StringBuilder(contextPath);
        sb.append("/downloadservlet?");
        sb.append(FileManager.FILE_ID_PARAM).append("=").append(fileId);
        return sb.toString();
    }

    public static boolean hasFileDownloadRequest(HttpServletRequest request) {
        return getFileId(request) != null;
    }

    public static String getFileId(HttpServletRequest request) {
        return StringUtils.trimToNull(request.getParameter(FileManager.FILE_ID_PARAM));
    }

    public static void downloadFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fileId = getFileId(request);
        FileManager fileManager = FileManager.getInstance();
        FileDTO file;
        InputStream is = null;
        OutputStream os = null;
        try {
            file = fileManager.getFile(fileId);
            response.setContentType(file.getContentType());
            is = fileManager.getInputStream(file);
            os = response.getOutputStream();
            fileManager.copy(is, os);
        } catch (DataBaseException e) {
            ;
        } catch (SharedApplicationException e) {
            ;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e) {
                    ;
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (Exception e) {
                    ;
                }
            }
        }
    }
}
