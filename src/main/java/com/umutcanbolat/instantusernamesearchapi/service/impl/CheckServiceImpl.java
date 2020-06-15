package com.umutcanbolat.instantusernamesearchapi.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.umutcanbolat.instantusernamesearchapi.controller.CheckController;
import com.umutcanbolat.instantusernamesearchapi.model.ServiceModel;
import com.umutcanbolat.instantusernamesearchapi.model.ServiceResponseModel;
import com.umutcanbolat.instantusernamesearchapi.model.SiteModel;
import com.umutcanbolat.instantusernamesearchapi.service.CheckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
@Slf4j
public class CheckServiceImpl implements CheckService {
  @Autowired private ResourceLoader resourceLoader;
  private static LinkedHashMap<String, SiteModel> sitesMap;

  static {
    // read sites data from resources
    InputStream in = CheckController.class.getResourceAsStream("/static/sites.json");
    BufferedReader reader = new BufferedReader(new InputStreamReader(in));

    // parse json to model list
    Gson gson = new Gson();
    JsonReader jReader = new JsonReader(reader);
    Type mapType = new TypeToken<LinkedHashMap<String, SiteModel>>() {}.getType();
    sitesMap = gson.fromJson(reader, mapType);
  }

  @Override
  public ServiceResponseModel check(String service, String username) {
    try {
      SiteModel site = sitesMap.get(service.toLowerCase());
      if (site != null) {
        String url = site.getUrl().replace("{}", username);
        HttpResponse<String> response =
            Unirest.get(url)
                .header("Connection", "keep-alive")
                .header("Upgrade-Insecure-Requests", "1")
                .header(
                    "User-Agent",
                    "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36")
                .header(
                    "Accept",
                    "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                .header("Accept-Encoding", "gzip, deflate")
                .header("Accept-Language", "en-US;q=1")
                .asString();
        log.info("checking " + service + " for " + username);
        log.info("status: " + response.getStatus());
        if ("instagram".equalsIgnoreCase(service)) {
          log.info(response.getBody());
        }

        boolean available = false;

        /*
         * Error Types
         * 0: returns HTTP 4xx response when the username is not taken.
         * 1: still returns HTTP 2xx when the username is not taken, so we check the response body for errorMsg.
         * 2: returns HTTP 4xx if the username is not taken or disabled.
         * 	  Since disabled usernames cannot be taken again, we check the response body.
         */
        if (site.getErrorType() == 0) {
          if (response.getStatus() != 200) {
            available = true;
          }
        } else if (site.getErrorType() == 1) {
          if (response.getBody().contains(site.getErrorMsg())) {
            available = true;
          }
        } else if (site.getErrorType() == 2) {
          if (response.getStatus() != 200 && response.getBody().contains(site.getErrorMsg())) {
            available = true;
          }
        }
        return new ServiceResponseModel(site.getService(), url, available);
      }
      // service not found
      return new ServiceResponseModel("Service " + service + " is not supported yet :/");
    } catch (Exception ex) {
      return new ServiceResponseModel(ex.getMessage());
    }
  }

  @Override
  public List<ServiceModel> getServices() {
    try {
      List<ServiceModel> serviceList = new ArrayList<ServiceModel>();

      for (SiteModel site : sitesMap.values()) {
        serviceList.add(
            new ServiceModel(
                site.getService(), "/" + site.getService().toLowerCase() + "/{username}"));
      }
      return serviceList;
    } catch (Exception ex) {
      return null;
    }
  }
}
