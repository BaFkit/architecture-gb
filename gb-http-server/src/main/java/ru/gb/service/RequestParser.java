package ru.gb.service;

import ru.gb.domain.HttpRequest;

import java.util.Deque;

public interface RequestParser {

    HttpRequest parse(Deque<String> rawRequest);
}
