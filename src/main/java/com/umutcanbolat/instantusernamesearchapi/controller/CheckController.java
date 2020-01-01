package com.umutcanbolat.instantusernamesearchapi.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.umutcanbolat.instantusernamesearchapi.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.umutcanbolat.instantusernamesearchapi.model.ServiceModel;
import com.umutcanbolat.instantusernamesearchapi.model.ServiceResponseModel;
import com.umutcanbolat.instantusernamesearchapi.model.SiteModel;

@RestController
@RequestMapping("/check")
public class CheckController {
  @Autowired private CheckService checkService;

  @RequestMapping("/{service}/{username}")
  @Cacheable("availabilities")
  public ServiceResponseModel searchUsername(
      @PathVariable String service, @PathVariable String username) {
    return checkService.check(service, username);
  }
}
