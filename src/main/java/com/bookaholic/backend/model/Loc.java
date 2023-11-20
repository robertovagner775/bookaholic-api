package com.bookaholic.backend.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Loc {
  private String location;
  private Long id;
  private Date criacao;
  private String tipocob;
}