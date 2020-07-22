package com.bhhan.testutils.http;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by hbh5274@gmail.com on 2020-07-22
 * Github : http://github.com/bhhan5274
 */
public class ApplicationHttpUtils {

    private final String baseUrl;

    public ApplicationHttpUtils(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String post(final String context, final String body){
        try {
            final HttpResponse response = Request.Post(baseUrl + context)
                    .bodyString(body, ContentType.APPLICATION_JSON)
                    .execute()
                    .returnResponse();
            assertIs200(response);
            return EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String get(final String context){
        final HttpResponse response;
        try {
            response = Request.Get(baseUrl + context)
                    .execute()
                    .returnResponse();
            assertIs200(response);
            return EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void assertIs200(final HttpResponse httpResponse){
        assertThat(httpResponse.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    }
}
