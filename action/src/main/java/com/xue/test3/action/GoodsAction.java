package com.xue.test3.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xue.com.pojo.MyGoods;
import com.xue.test3.service.GoodsInterface;
@Controller
@RequestMapping("/goods")
public class GoodsAction {
	@Autowired
	private GoodsInterface goodsInterface;
	
	@RequestMapping("/getGoods")
//	@ResponseBody
	public String getGoods(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView model=new ModelAndView();
		List<MyGoods> list=goodsInterface.getGoods();
		List<Map<String, Object>> li=new ArrayList<Map<String, Object>>();
		for(MyGoods goods : list){
			Map<String, Object> m=new HashMap<String, Object>();
			m.put("sku", goods.getGoodsCode());
			m.put("name", goods.getNAME());
			m.put("brand", goods.getBrand());
			li.add(m);
		}
		model.addObject("goodsList");
		request.setAttribute("goodsList", li);
		return "jsp/mygoods";
	}	
}
