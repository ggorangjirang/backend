package com.elice.ggorangjirang.global.exception;

import com.elice.ggorangjirang.discord.DiscordWebhook;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class ExceptionHandlerAspect {

  private final DiscordWebhook discordWebhook;

  @AfterThrowing(pointcut = "execution(* com.elice.ggorangjirang..service..*(..))", throwing = "exception")
  public void handleException(Exception exception) {
    String errorMessage = "Exception occurred: " + exception.getMessage();
    discordWebhook.sendErrorMessage(errorMessage);
  }
}
