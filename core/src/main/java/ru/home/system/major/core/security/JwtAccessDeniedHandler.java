package ru.home.system.major.core.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import ru.home.system.artifactory.service.rest.ApiError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.home.system.artifactory.service.rest.RestExceptionHandlerUtil.buildApiError;
import static ru.home.system.artifactory.service.util.JsonUtil.toJson;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

   @Override
   public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
      ApiError apiError = buildApiError(request, accessDeniedException, HttpStatus.FORBIDDEN);
      response.setContentType("application/json");
      response.setStatus(HttpServletResponse.SC_FORBIDDEN);
      response.getOutputStream().println(toJson(apiError));
   }
}
