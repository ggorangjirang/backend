package com.elice.ggorangjirang.websocket.dto;

public class DeliveryUpdateMessage {

  private long id;
  private String status;

  // Getters and Setters
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}