package com.umutcanbolat.instantusernamesearchapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SiteModel {
  private String service;
  private String url;
  private String urlRegister;
  private int errorType;
  private String errorMsg;
  private String userAgent;
}
