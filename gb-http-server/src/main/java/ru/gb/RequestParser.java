package ru.gb;

import ru.gb.domain.HttpRequest;

import java.util.List;

public interface RequestParser {

    HttpRequest parse(List<String> rawRequest);
}
