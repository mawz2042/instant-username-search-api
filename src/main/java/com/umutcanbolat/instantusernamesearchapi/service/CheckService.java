package com.umutcanbolat.instantusernamesearchapi.service;

import com.umutcanbolat.instantusernamesearchapi.model.ServiceModel;
import com.umutcanbolat.instantusernamesearchapi.model.ServiceResponseModel;

import java.util.List;

public interface CheckService {
  ServiceResponseModel check(String service, String username);

  List<ServiceModel> getServices();
}
