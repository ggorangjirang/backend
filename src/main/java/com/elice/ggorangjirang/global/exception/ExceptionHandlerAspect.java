package com.elice.ggorangjirang.global.exception;

import com.elice.ggorangjirang.discord.DiscordWebhook;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionHandlerAspect {

  @AfterThrowing(pointcut = "execution(* com.elice.ggorangjirang..service..*(..))", throwing = "exception")
  public void handleException(Exception exception) {
    String errorMessage = "Exception occurred: " + exception.getMessage();
    DiscordWebhook.sendErrorMessage(errorMessage);
  }
}
