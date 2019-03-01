package lk.syscolabs.candidatemanager.util;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class LoggingDispatcherServlet extends DispatcherServlet {
    private final Map<String, String> developers;
    @Autowired
    private Log appLog;


    public LoggingDispatcherServlet() {
        this.developers = new HashMap<>();
        developers.put("10.1.10.63", "\033[1;33misuru\033[0m");
        developers.put("10.1.11.146", "\033[1;33mpiyumi\033[0m");
        developers.put("10.1.10.87", "\033[1;33minzamam\033[0m");
        developers.put("0:0:0:0:0:0:0:1", "\033[1;33mnilanka\033[0m");
        developers.put("10.1.11.27", "\033[1;33mnilanka\033[0m");
    }

    @Override
    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!(request instanceof ContentCachingRequestWrapper)) {
            request = new ContentCachingRequestWrapper(request);
        }
        if (!(response instanceof ContentCachingResponseWrapper)) {
            response = new ContentCachingResponseWrapper(response);
        }

        try {
            super.doDispatch(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            this.appLog.error(e.getStackTrace());
        } finally {
            log(request, response);
            try {
                updateResponse(response);
            } catch (Exception e) {
                e.printStackTrace();
                appLog.error(e.getStackTrace());
            }

        }
    }

    private void log(HttpServletRequest requestToCache, HttpServletResponse responseToCache) {
        LogMessage log = new LogMessage();
        log.setHttpStatus(responseToCache.getStatus());
        log.setHttpMethod(requestToCache.getMethod());
        log.setPath(requestToCache.getRequestURI());
        if (developers.containsKey(requestToCache.getRemoteAddr())) {
            log.setClientIp(developers.get(requestToCache.getRemoteAddr()));
        } else {
            log.setClientIp(requestToCache.getRemoteAddr());
        }
        log.setAuthType(requestToCache.getAuthType());
        if (requestToCache.getRequestURI().contains("api-docs")) {
            log.setResponse("\033[0;35mjson doc of api by SWAGGER\033[0m");
        } else if (requestToCache.getRequestURI().contains("swagger")) {
            log.setResponse("\033[0;35mdoc resource of api by SWAGGER\033[0m");
        } else if (requestToCache.getRequestURI().contains("favicon.ico")) {
            log.setResponse("\033[0;35mfavicon icon by browser\033[0m");
        } else {
            log.setResponse(getResponsePayload(responseToCache));
        }


        log.setPayload(getRequestPayLoad(requestToCache));
        this.appLog.info(log);
    }

    private String getResponsePayload(HttpServletResponse response) {
        ContentCachingResponseWrapper wrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        return getContentFromWrapper(wrapper);
    }

    private String getRequestPayLoad(HttpServletRequest request) {
        ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        return getContentFromWrapper(wrapper);
    }

    private void updateResponse(HttpServletResponse response) throws IOException {
        ContentCachingResponseWrapper responseWrapper =
                WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        responseWrapper.copyBodyToResponse();
    }

    private String getContentFromWrapper(Object wrapper) {
        if (wrapper != null) {
            byte[] buf;
            String encoding;
            try {
                ContentCachingRequestWrapper requestWrapper = (ContentCachingRequestWrapper) wrapper;
                buf = requestWrapper.getContentAsByteArray();
                encoding = requestWrapper.getCharacterEncoding();
            } catch (ClassCastException e) {
                ContentCachingResponseWrapper responseWrapper = (ContentCachingResponseWrapper) wrapper;
                buf = responseWrapper.getContentAsByteArray();
                encoding = responseWrapper.getCharacterEncoding();
            }

            if (buf.length > 0) {
                int length = Math.min(buf.length, 5120);
                try {
                    return new String(buf, 0, length, encoding);
                } catch (UnsupportedEncodingException ex) {
                    // NOOP
                }
            }
        }
        return "[unknown]";
    }

}
