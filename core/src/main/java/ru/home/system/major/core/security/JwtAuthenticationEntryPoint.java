package ru.home.system.major.core.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import ru.home.system.artifactory.service.rest.ApiError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static ru.home.system.artifactory.service.rest.RestExceptionHandlerUtil.buildApiError;
import static ru.home.system.artifactory.service.util.JsonUtil.toJson;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

   @Override
   public void commence(HttpServletRequest request,
                        HttpServletResponse response,
                        AuthenticationException authException) throws IOException
   {
      ApiError apiError = buildApiError(request, authException, HttpStatus.UNAUTHORIZED);
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

      PrintWriter out = response.getWriter();
      out.print(toJson(apiError));
   }
}
