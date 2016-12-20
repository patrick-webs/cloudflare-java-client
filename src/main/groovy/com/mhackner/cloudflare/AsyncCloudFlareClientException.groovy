package com.mhackner.cloudflare

import groovyx.net.http.HttpResponseDecorator

public class AsyncCloudFlareClientException extends RuntimeException {

	private HttpResponseDecorator resp
	private String entityContent

	AsyncCloudFlareClientException(HttpResponseDecorator resp, String entityContent) {
		this.resp = resp
		this.entityContent = entityContent
	}

	public HttpResponseDecorator getResp() {
		return resp
	}

	public String getEntityContent() {
		return entityContent
	}


	@Override
	public String toString() {
		return 'AsyncCloudFlareClientException{status=' + resp.getStatusLine() + '}'
	}
}
