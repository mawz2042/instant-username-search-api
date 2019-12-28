package com.umutcanbolat.instantusernamesearchapi.dao;

import com.umutcanbolat.instantusernamesearchapi.model.SearchModel;
import org.springframework.data.repository.CrudRepository;

public interface SearchDAO extends CrudRepository<SearchModel, Integer> {}
