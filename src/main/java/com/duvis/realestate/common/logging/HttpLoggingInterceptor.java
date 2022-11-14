package com.duvis.realestate.common.logging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class HttpLoggingInterceptor implements ClientHttpRequestInterceptor {
	private static AtomicLong sId = new AtomicLong(0); 
	
	@Override
	public ClientHttpResponse intercept(org.springframework.http.HttpRequest request, byte[] body,
			ClientHttpRequestExecution execution) throws IOException {
		long id = sId.incrementAndGet();
		if (log.isDebugEnabled()) {
			log.debug(HttpLogger.getRequestLog(id, " >> ", request, body));
		}
		ClientHttpResponse response = execution.execute(request, body);
		if (log.isDebugEnabled()) {
			log.debug(HttpLogger.getResponseLog(id, " << ", response));
		}
		return response;
	}
}
