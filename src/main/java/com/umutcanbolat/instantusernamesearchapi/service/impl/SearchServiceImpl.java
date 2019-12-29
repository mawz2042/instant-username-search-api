package com.umutcanbolat.instantusernamesearchapi.service.impl;

import com.umutcanbolat.instantusernamesearchapi.model.SearchCountResponseModel;
import com.umutcanbolat.instantusernamesearchapi.model.SearchModel;
import com.umutcanbolat.instantusernamesearchapi.dao.SearchDAO;
import com.umutcanbolat.instantusernamesearchapi.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SearchServiceImpl implements SearchService {
  @Autowired SearchDAO searchDAO;

  @Override
  public void addSearch(SearchModel search) {
    // set current time
    search.setDate(new Date().getTime() / 1000);
    searchDAO.save(search);
  }

  @Override
  public SearchCountResponseModel getTotalCount() {
    SearchCountResponseModel res = new SearchCountResponseModel();
    res.setCount(searchDAO.count());

    return res;
  }
}
