package com.umutcanbolat.instantusernamesearchapi.service;

import com.umutcanbolat.instantusernamesearchapi.model.SearchCountResponseModel;
import com.umutcanbolat.instantusernamesearchapi.model.SearchModel;

public interface SearchService {
  void addSearch(SearchModel search);

  SearchCountResponseModel getTotalCount();
}
