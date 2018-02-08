package com.xue.test3.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xue.com.pojo.MyGoods;
import com.xue.test3.service.GoodsInterface;
import com.xue.util.Page;
import com.xue.util.ParamObject;
import com.xue.util.ResultConstant;
@Controller
@RequestMapping("/goods")
public class GoodsAction extends BaseController{
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
	
	@RequestMapping("/getGoodsByPage")
	@ResponseBody
	public ParamObject getGoodsByPage(@RequestParam(value="param",required=false)String param) throws Exception {
//		ParamObject paramObject=new ParamObject();
		ParamObject paramObject = converObjectByJson(param, ParamObject.class);
		Page<HashMap<String, Object>> page = buildPage(paramObject.getQuery());
//		Page<HashMap<String, Object>> page=new Page<HashMap<String,Object>>(100);
		page=goodsInterface.getGoodsByPage(page,paramObject.getQuery());
		List<HashMap<String, Object>> li=page.getResult();
		paramObject.setGrid(page.getResult());
		paramObject.setTotal(page.getTotalCount());
		paramObject.buildStatus(ResultConstant.Code.OK, "获取数据成功！");
		return paramObject;
	}	
}
