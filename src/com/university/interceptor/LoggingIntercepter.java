package com.university.interceptor;

import javax.servlet.http.*;
import org.springframework.web.servlet.HandlerInterceptor;
import java.io.*;
import java.time.LocalDateTime;

public class LoggingInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String uri = request.getRequestURI();
        String logMsg = LocalDateTime.now() + " - " + uri;
        FileWriter fw = new FileWriter("logs.txt", true);
        fw.write(logMsg + "\n");
        fw.close();
        return true;
    }
}
