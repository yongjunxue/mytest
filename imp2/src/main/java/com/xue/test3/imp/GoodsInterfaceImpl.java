package com.xue.test3.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xue.com.pojo.MyGoods;
import com.xue.test3.dao.MyGoodsDao;
import com.xue.test3.service.GoodsInterface;
import com.xue.util.Page;

@Service
public class GoodsInterfaceImpl implements GoodsInterface {
	@Autowired
	private MyGoodsDao goodsDao;
	@Override
	public List<MyGoods> getGoods() {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("sku", "33edd");
		List<MyGoods> list=goodsDao.findListForMap(map);
		return list;
	}
	@Override
	public Page<HashMap<String, Object>> getGoodsByPage(Page<HashMap<String, Object>> page , Map<String, Object> query) {
		page=goodsDao.getGoodsByPage(page,query);
		return page;
	}

}
