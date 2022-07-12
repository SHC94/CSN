package com.csn.csn.main.service;

import com.csn.csn.Item.entity.NewsItem;
import com.csn.csn.main.entity.Tab;
import com.csn.csn.main.vo.LoginForm;
import com.csn.csn.main.vo.SearchParam;
import com.csn.csn.search.entity.Search;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface MainService {

    public List<Tab> selectTab();

    public boolean membershipFind(LoginForm loginForm, HttpSession session);

    List<Search> selectSearchList(SearchParam searchParam);

    public List<NewsItem> selectNewsList();
}//end interface()
