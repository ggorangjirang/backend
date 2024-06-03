package com.elice.ggorangjirang.discord;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DiscordWebhook {

  @Value("${discord.webhook.url}")
  private String webhookUrl;

  private static final String CONTENT_TYPE_HEADER = "Content-Type";
  private static final String CONTENT_TYPE_JSON = "application/json";
  private static final String JSON_PAYLOAD_TEMPLATE = "{\"content\": \"%s\"}";

  public void sendErrorMessage(String message) {
    try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
      HttpPost request = new HttpPost(webhookUrl);
      request.addHeader(CONTENT_TYPE_HEADER, CONTENT_TYPE_JSON);

      // JSON Payload 생성 및 UTF-8 인코딩 설정
      String jsonPayload = String.format(JSON_PAYLOAD_TEMPLATE, message);
      StringEntity entity = new StringEntity(jsonPayload, StandardCharsets.UTF_8);
      request.setEntity(entity);

      httpClient.execute(request);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
