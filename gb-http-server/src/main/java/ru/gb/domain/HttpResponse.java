package ru.gb.domain;

import ru.gb.ResponseSerializer;

import java.io.Reader;

public class HttpResponse implements ResponseSerializer {

    private int statusCode;

    private Reader body;

    public int getStatusCode() {
        return statusCode;
    }

    public Reader getBody() {
        return body;
    }

    private HttpResponse() {
    }

    private HttpResponse(int statusCode, Reader body) {
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

    public static Builder createBuilder() {
        return new Builder();
    }

    public static class Builder {

        private final HttpResponse httpResponse;

        private Builder() {
            this.httpResponse = new HttpResponse();
        }

        public Builder withStatusCode(int statusCode) {
            this.httpResponse.statusCode = statusCode;
            return this;
        }

        public Builder withBody(Reader body) {
            this.httpResponse.body = body;
            return this;
        }

        public HttpResponse build() {
            return this.httpResponse;
        }
    }
}
