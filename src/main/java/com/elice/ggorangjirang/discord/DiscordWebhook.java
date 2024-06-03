package com.elice.ggorangjirang.discord;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class DiscordWebhook {

  private static final String WEBHOOK_URL = "https://discord.com/api/webhooks/1246005463598895205/j6A3ojRelQCMVcmLYMvCZsI1yhpUImWG9iX8kSplBwMC96Vdzz94iVMpmn2gdhXi6cBp";

  public static void sendErrorMessage(String message) {
    try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
      HttpPost request = new HttpPost(WEBHOOK_URL);
      request.addHeader("Content-Type", "application/json");

      // JSON Payload 생성 및 UTF-8 인코딩 설정
      String jsonPayload = String.format("{\"content\": \"%s\"}", message);
      StringEntity entity = new StringEntity(jsonPayload, StandardCharsets.UTF_8);
      request.setEntity(entity);

      httpClient.execute(request);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

