package com.srikanth.brewery.web.controllers;

import lombok.*;

import java.util.List;

@Builder(builderClassName = "Builder")
@Getter
@ToString
public class LombokSingularDemo1 {
  private Long id;
  @Singular
  private List<String> names;

}