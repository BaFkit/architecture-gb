package ru.gb.domain;

import java.util.HashMap;
import java.util.Map;

public class HttpResponse {

    private ResponseCode statusCode;

    private Map<String, String> headers;

    private String body;

    private HttpResponse() {
    }

    public ResponseCode getStatusCode() {
        return statusCode;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    public static Builder createBuilder() {
        return new Builder();
    }

    public static class Builder {

        private final HttpResponse httpResponse;

        private Builder() {
            this.httpResponse = new HttpResponse();
            this.httpResponse.headers = new HashMap<>();
        }

        public Builder withStatusCode(ResponseCode statusCode) {
            this.httpResponse.statusCode = statusCode;
            return this;
        }
        public Builder withHeader(String header, String value) {
            this.httpResponse.headers.put(header, value);
            return this;
        }
        public Builder withHeaders(Map<String, String> headers) {
            this.httpResponse.headers.putAll(headers);
            return this;
        }
        public Builder withBody(String body) {
            this.httpResponse.body = body;
            return this;
        }

        public HttpResponse build() {
            if (this.httpResponse.statusCode == null) {
                throw new IllegalArgumentException("Status is obligatory for Response");
            }
            return this.httpResponse;
        }
    }
}
