package com.elice.ggorangjirang.deliveries.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class DeliveriesController {

 /* @PostMapping("/deliveries")
  public DeliveriesDto AddDeliveries(){

    return "deliveries";
  }

  @GetMapping("/deliveries/{id}")
  public DeliveriesDto viewDeliveries(@PathVariable long id){

    return "deliveries";
  }

  @PatchMapping("admin/deliveries/{id}/status")
  public DeliveriesStatusDto updateDeliveriesStatus(@PathVariable long id){

    return "deliveries";
  }
  */
}
