package ru.gb.domain;

import ru.gb.ResponseSerializer;

public class HttpResponse implements ResponseSerializer {

    private int statusCode;

    private String body;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public HttpResponse(int statusCode, String body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    @Override
    public String serialize(HttpResponse httpResponse) {

        if(httpResponse.getStatusCode() == 404) {
            return  "HTTP/1.1 404 NOT_FOUND\n" +
                    "Content-Type: text/html; charset=utf-8\n" +
                    "\n";
        }
        if(httpResponse.getStatusCode() == 200) {
            return "HTTP/1.1 200 OK\n" +
                    "Content-Type: text/html; charset=utf-8\n" +
                    "\n";
        }
        return null;
    }
}
