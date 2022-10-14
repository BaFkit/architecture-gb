package ru.gb.service;

import ru.gb.domain.HttpResponse;

class ResponseSerializerImpl implements ResponseSerializer{

    @Override
    public String serialize(HttpResponse httpResponse) {
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 " + httpResponse.getStatusCode().getStatus() + " " + httpResponse.getStatusCode().getName() + "\n");
        httpResponse.getHeaders().forEach((header, value) -> {
            sb.append(header + ": " + value + "\n");
        });
        sb.append("\n");
        sb.append(httpResponse.getBody());
        return sb.toString();
    }
}
