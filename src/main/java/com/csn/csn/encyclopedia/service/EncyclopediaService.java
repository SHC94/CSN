package com.csn.csn.encyclopedia.service;

import com.csn.csn.Item.entity.DictionaryItem;
import com.csn.csn.main.vo.SearchParam;

import java.util.Dictionary;
import java.util.List;

public interface EncyclopediaService {

    List<DictionaryItem> selectDictionaryList();

    List<DictionaryItem> selectSearchDict(SearchParam searchParam) throws Exception;

}//end interface()
