package com.srikanth.brewery.web.controllers;

import java.util.List;

public class LombokSingularDemo1Test {
  public static void main(String[] args) {
    
    LombokSingularDemo1 lsd1 = LombokSingularDemo1.builder()
        .id(Long.valueOf(1))
        .name("peter")
        .names(List.of("Mark", "Gerhard"))
        .build();
    
    System.out.println(lsd1);
    
    LombokSingularDemo1 lsd2 = lsd1.builder().clearNames().build();
    System.out.println(lsd1);
    System.out.println(lsd2);
  }
}