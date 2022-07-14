package com.csn.csn.encyclopedia.service;

import com.csn.csn.Item.entity.DictionaryItem;
import com.csn.csn.encyclopedia.vo.PopularSearch;
import com.csn.csn.main.vo.SearchParam;
import com.csn.csn.search.entity.Search;

import java.util.Dictionary;
import java.util.List;

public interface EncyclopediaService {

    List<DictionaryItem> selectDictionaryList();

    List<DictionaryItem> selectSearchDict(SearchParam searchParam) throws Exception;

    void ApiExamDatalabTrend();

    List<PopularSearch> popularSearch();
}//end interface()
