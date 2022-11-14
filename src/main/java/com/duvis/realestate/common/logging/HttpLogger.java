package com.duvis.realestate.common.logging;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class HttpLogger {
	private static final int LOG_MAX_LENGTH = 1024 * 1000 * 10;

	public static String getRequestLog(long id, String tag, HttpRequest request, byte[] body) throws IOException {
		return getRequestLog(id, tag, request.getMethod().name(),  request.getURI().toString()
				, request.getHeaders(), new ByteArrayInputStream(body));
	}
	
	public static String getRequestLog(long id, String tag, String method, String uri
			, HttpHeaders headers, InputStream requestBody) throws IOException {
    	StringBuilder requestLog = new StringBuilder("Request on ").append(Thread.currentThread().getName()).append("\n");
        requestLog.append(id).append(tag).append(method).append(" ").append(uri).append("\n");
        headers.entrySet().stream().forEach(e -> {
        	requestLog.append(id).append(tag).append(e.getKey()).append(": ").append(e.getValue())
    			.append("\n");
        });
        requestLog.append(id).append(tag);
        if (requestBody != null) {
	        requestLog.append(createLimitedBodyLog(headers.getContentType(), requestBody));
        }
        return requestLog.toString();
    }
 
	public static String getResponseLog(long id, String tag, ClientHttpResponse response) throws IOException {
		return getResponseLog(id, tag, response.getRawStatusCode(), response.getHeaders(), response.getBody());
	}
	
    public static String getResponseLog(long id, String tag, int status
    		, HttpHeaders headers, InputStream responseBody) throws IOException {
    	StringBuilder responseLog = new StringBuilder("Response on ").append(Thread.currentThread().getName()).append("\n");
    	responseLog.append(id).append(tag).append(status).append(" ").append("\n");
        headers.entrySet().stream().forEach(e -> {
        	responseLog.append(id).append(tag).append(e.getKey()).append(": ").append(e.getValue())
        		.append("\n");
        });
        responseLog.append(id).append(tag);
        if (responseBody != null) {
			responseLog.append(createLimitedBodyLog(headers.getContentType(), responseBody));
        }
        return responseLog.toString();
    }
    
    private static String createLimitedBodyLog(MediaType contentsType, InputStream body) throws IOException {
    	int logMax = LOG_MAX_LENGTH;
    	int len = 2048 * 10;
    	byte[] buf = new byte[len];
    	int read = 0;
    	long total = 0;
    	StringBuilder bodyBuilder = new StringBuilder(); 
    	if (contentsType == MediaType.APPLICATION_OCTET_STREAM) {
    		return "...stream_log_skipped...";
    	}
    	BufferedInputStream bis = new BufferedInputStream(body);
    	while ((read = bis.read(buf, 0, len)) != -1) {
    		total += read;
    		if (total > logMax) {
    			bodyBuilder.append("...log_skipped...");
    			break;
    		} else {
    			bodyBuilder.append(new String(buf, 0, read));
    		}
    	}
    	return bodyBuilder.toString();
    }
}
