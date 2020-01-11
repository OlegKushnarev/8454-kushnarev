package ru.cft.focusstart.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Operation {
    void getResult(HttpServletRequest req, HttpServletResponse resp) throws IOException;
}