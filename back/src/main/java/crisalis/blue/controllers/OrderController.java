package crisalis.blue.controllers;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.web.bind.annotation.RequestMapping;

@Entity
@RequestMapping(name = "order")
public class OrderController {

  @Id
  private Long id;

}
