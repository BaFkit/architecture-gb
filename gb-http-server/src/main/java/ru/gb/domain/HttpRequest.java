package ru.gb.domain;


import java.util.HashMap;
import java.util.Map;

public class HttpRequest {

    private final String method;

    private final String path;

    private final Map<String, String> headers;

    private final String body;

    private HttpRequest(String method, String path, Map<String, String> headers, String body) {
        this.method = method;
        this.path = path;
        this.headers = headers;
        this.body = body;
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
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

        private String method;

        private String path;

        private final Map<String, String> headers = new HashMap<>();

        private String body;

       private Builder() {
       }

       public Builder withMethod(String method) {
           this.method = method;
           return this;
       }
       public Builder withPath(String path) {
           this.path = path;
           return this;
       }
       public Builder withHeader(String header, String value) {
           System.out.println(header + " --- " + value);
           this.headers.put(header, value);
           return this;
       }
       public Builder withHeaders(Map<String, String> headers) {
           this.headers.putAll(headers);
           return this;
       }
       public Builder withBody(String body) {
           this.body = body;
           return this;
       }

       public HttpRequest build() {
           return new HttpRequest(method, path, headers, body);
       }
    }
}
