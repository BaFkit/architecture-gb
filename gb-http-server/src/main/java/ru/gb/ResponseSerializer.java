package ru.gb;

import ru.gb.domain.HttpResponse;

public interface ResponseSerializer {

    String serialize(HttpResponse httpResponse);
}
