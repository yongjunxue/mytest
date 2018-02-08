package com.xue.test3.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xue.com.pojo.MyGoods;
import com.xue.util.Page;

public interface GoodsInterface {

	List<MyGoods> getGoods();

	Page<HashMap<String, Object>> getGoodsByPage(Page<HashMap<String, Object>> page,Map<String, Object> query);

}
