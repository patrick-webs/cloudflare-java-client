package com.mhackner.cloudflare

import org.apache.http.util.EntityUtils

import groovyx.net.http.AsyncHTTPBuilder
import groovyx.net.http.HttpResponseDecorator

class AsyncCloudFlareHTTPBuilder extends AsyncHTTPBuilder {
	AsyncCloudFlareHTTPBuilder(Map<String, ?> args) throws URISyntaxException {
		super(args)
	}

	/**
	 * This failure handler enables client code to access the response body.
	 * It isn't needed for the {@link CloudFlareClient} because that uses the groovy RESTClient which
	 * has the feature built in.
	 *
	 * @param resp response
	 * @throws AsyncCloudFlareClientException with the response and entity content
	 */
	@Override
	protected void defaultFailureHandler(HttpResponseDecorator resp) {
		try {
			String entityContent = EntityUtils.toString(resp.getEntity())
			throw new AsyncCloudFlareClientException(resp, entityContent)
		} catch (IOException e) {
			throw new IllegalStateException("Unable to extract response entity", e)
		}
	}


}
