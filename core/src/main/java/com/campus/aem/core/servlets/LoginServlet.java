package com.campus.aem.core.servlets;

import com.campus.aem.core.service.EmailService;
import com.campus.aem.core.service.LoginService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class,
        property = {
                Constants.SERVICE_DESCRIPTION + "=LoginServlet",
                "sling.servlet.paths=" + "/bin/loginForm",
                "sling.servlet.methods=" + HttpConstants.METHOD_GET,
                "sling.servlet.methods=" + HttpConstants.METHOD_POST,
                "sling.servlet.extensions=" + "json",
        })
public class LoginServlet extends SlingAllMethodsServlet {

    private static final Logger LOG = LoggerFactory.getLogger(LoginServlet.class);

    @Reference
    private LoginService loginService;

    @Reference
    private EmailService emailService;

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("username");
        String password = request.getParameter("password");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (email == null || password == null ||
                email.isEmpty() || password.isEmpty()) {

            response.setStatus(SlingHttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(
                    "{\"status\":\"error\",\"message\":\"Email or password missing\"}"
            );
            return;
        }

        boolean authenticated = loginService.authenticate(email,password);
        if (authenticated){
            emailService.sendLoginSuccessMail(email);
            LOG.info("User '{}' authenticated successfully", email);

            response.setStatus(SlingHttpServletResponse.SC_OK);
            response.getWriter().write("{\"status\":\"success\"}");
        }else{
            LOG.warn("Invalid login attempt for user '{}'", email);

            response.setStatus(SlingHttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(
                    "{\"status\":\"error\",\"message\":\"Invalid credentials\"}"
            );
        }
    }

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (username != null && password != null && !username.isEmpty() && !password.isEmpty()) {
            response.setStatus(SlingHttpServletResponse.SC_OK);
            response.getWriter().write(
                    "{\"status\":\"success\",\"message\":\"Username and password received\"}"
            );
        } else {
            response.setStatus(SlingHttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(
                    "{\"status\":\"error\",\"message\":\"Username or password is missing\"}"
            );
        }
    }
}
