package com.umutcanbolat.instantusernamesearchapi.controller;

import com.umutcanbolat.instantusernamesearchapi.model.ServiceModel;
import com.umutcanbolat.instantusernamesearchapi.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ServicesController {
  @Autowired private CheckService checkService;

  @RequestMapping("/services/getAll")
  @Cacheable("services")
  public List<ServiceModel> getServicesList() {
    return checkService.getServices();
  }
}
