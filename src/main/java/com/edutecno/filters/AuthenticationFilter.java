package com.edutecno.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Inicialización del filtro
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        boolean isLoggedIn = (session != null && session.getAttribute("usuario") != null);
        boolean isLoginPage = httpRequest.getRequestURI().endsWith("login.jsp");
        boolean isLoginAction = httpRequest.getRequestURI().endsWith("login");
        boolean isRegistroPage = httpRequest.getRequestURI().endsWith("registro.jsp");
        boolean isRegistroAction = httpRequest.getRequestURI().endsWith("registro");

        if (isLoggedIn || isLoginPage || isLoginAction || isRegistroPage || isRegistroAction) {
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
        }
    }

    @Override
    public void destroy() {
        // Limpieza del filtro
    }
}