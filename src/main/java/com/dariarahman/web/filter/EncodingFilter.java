package com.dariarahman.web.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

// Класс EncodingFilter обеспечивает установку правильной кодировки для запросов, если она не была установлена ранее.
public class EncodingFilter implements Filter {

    private static final Logger log = LogManager.getLogger(EncodingFilter.class);

    private String encoding;

    // Метод инициализации фильтра
    public void init(FilterConfig fConfig) throws ServletException {
        log.debug("Filter initialization starts");

        encoding = fConfig.getInitParameter("encoding");
        log.trace("encoding from config ==> " + encoding);

        log.debug("Filter initialization finished");
    }

    // Метод фильтрации запросов
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        log.debug("EncodingFilter starts");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        log.trace("Request uri --> " + httpRequest.getRequestURI());

        String requestEncoding = request.getCharacterEncoding();
        if (requestEncoding == null) {
            log.trace("Request encoding = null, set encoding --> " + encoding);
            request.setCharacterEncoding(encoding);
        }

        log.debug("Filter finished");
        chain.doFilter(request, response);
    }

    // Метод разрушения фильтра
    public void destroy() {
        log.debug("Filter encoding destroy");
    }
}

