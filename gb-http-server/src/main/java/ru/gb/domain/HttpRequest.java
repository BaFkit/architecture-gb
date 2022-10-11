package ru.gb.domain;

import ru.gb.RequestParser;

import java.util.List;

public class HttpRequest implements RequestParser {

    private String method;

    private String path;


    public HttpRequest(String method, String path) {
        this.method = method;
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public HttpRequest parse(List<String> rawRequest) {
        String[] parts = rawRequest.get(0).split(" ");
        return new HttpRequest(parts[0], parts[1]);
    }
}
