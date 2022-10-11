package ru.gb.domain;

import ru.gb.RequestParser;

import java.util.List;

public class HttpRequest implements RequestParser {

    private String method;

    private String path;

    private HttpRequest() {
    }

    private HttpRequest(String method, String path) {
        this.method = method;
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    @Override
    public HttpRequest parse(List<String> rawRequest) {
        String[] parts = rawRequest.get(0).split(" ");
        return new HttpRequest(parts[0], parts[1]);
    }

    public static Builder createBuilder() {
        return new Builder();
    }

    public static class Builder {

       private final HttpRequest httpRequest;

       private Builder() {
           this.httpRequest = new HttpRequest();
       }

       public Builder withMethod(String method) {
           this.httpRequest.method = method;
           return this;
       }
       public Builder withPath(String path) {
           this.httpRequest.path = path;
           return this;
       }

       public HttpRequest build() {
           return this.httpRequest;
       }
    }
}
