package com.xue.test3.action;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xue.util.Page;
import com.xue.util.ParamObject;
import com.xue.util.RenderUtils;
import com.xue.util.ResultConstant;
import com.xue.util.WtmsResult;

public class BaseController{
	static Logger logger = Logger.getLogger(BaseController.class);
	
	public <T> T converObjectByJson(String s, Class<T> c) {
		ObjectMapper mapper = new ObjectMapper();
		T o = null;
		try {
			o = mapper.readValue(s, c);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return o;
	}
	
	public static void main(String[] args) {
		String param="{\"query\":{\"taskId\":\"GRB123\",\"id\":\"111\",\"pageSize\":\"10\",\"pageNo\":\"1\"}}";
//		param="{'query':{'taskId':'GRB123','id':'111','pageSize':'10','pageNo':'1'}}";
//		param="param:{\"query\": {\"taskId\": \"\",\"startTime\": \"\",\"chuHuoName\": \"\",\"orderTypeId\": -1, \"page\": 1,\"pageSize\": 30 }}";
		ParamObject o=new BaseController().converObjectByJson(param,ParamObject.class);
		System.out.println(o.toString());
	}
	
//	protected long getCurrentUserId() {
//		return getUserDetailsImpl().getUserId();
//	}
//	protected String getSessionId() {
//		return getUserDetailsImpl().getSessionid();
//	}
//
//	protected String getToken() {
//		return getUserDetailsImpl().getToken();
//	}

//	protected UserDetailsImpl getUserDetailsImpl() {
//		return (UserDetailsImpl) SpringSecurityUtils.getCurrentUserDetails();
//	}

//	public ComUser getCurrentUser() {
//		JRedisShardServiceImpl jredis = ApplicationContextProvider.getBean(JRedisShardServiceImpl.class);
//		String sessionid = getUserDetailsImpl().getSessionid();
//		Boolean bgVO = jredis.exists(BaseUtil.toByteArray(sessionid));
//		logger.info("当前用户sessionid："+sessionid);
//		logger.info("当前用户是否存在："+bgVO);
//		if (bgVO == true) {
//			byte[] sessionData = jredis.get(BaseUtil.toByteArray(sessionid));
//			ObjectInputStream ois = null;
//			try {
//				ois = new ObjectInputStream(new ByteArrayInputStream(sessionData));
//				ComUser user = (ComUser) ois.readObject();
//				logger.info("当前用户："+user.getAccount());
//				return user;
//			} catch (IOException e) {
//				e.printStackTrace();
//			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
//			} finally {
//				if (ois != null) {
//					try {
//						ois.close();
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//
//		}
//		logger.info("当前用户：空");
//		return null;
//	}

//	/**
//	 * 返回防止重复提交的Token
//	 * @return
//	 */
//	protected String getCsrfToken(String csrfToken) {
//		JRedisShardServiceImpl jredis = ApplicationContextProvider.getBean(JRedisShardServiceImpl.class);
//		String sessionid = getToken()+"csrfToken"+csrfToken;
//		Boolean bgVO = jredis.exists(BaseUtil.toByteArray(sessionid));
//		if (bgVO == true) {
//			byte[] sessionData = jredis.get(BaseUtil.toByteArray(sessionid));
//			ObjectInputStream ois = null;
//			try {
//				ois = new ObjectInputStream(new ByteArrayInputStream(sessionData));
//				String csrfToke = (String) ois.readObject();
//				return csrfToke;
//			} catch (IOException e) {
//				e.printStackTrace();
//			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
//			} finally {
//				if (ois != null) {
//					try {
//						ois.close();
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//
//		}
//		return null;
//	}
	protected <T> Page<T> buildPage(HttpServletRequest request) {
		Page<T> page = new Page<T>();
		String pageNo = request.getParameter("pageNo");
		if (pageNo == null || pageNo.equals("")) {
			pageNo = "1";
		}
		page.setPageNo(Integer.parseInt(pageNo));
		String pageSize = request.getParameter("pageSize");
		if (StringUtils.isNotBlank(pageSize)) {
			page.setPageSize(Integer.parseInt(pageSize));
		}

		return page;
	}
	protected <T> Page<T> buildPage(Map<String, Object> query) {
		Page<T> page = new Page<T>();
		Object pageNo = query.get("pageNo");
		if (pageNo == null || pageNo.toString().equals("")) {
			pageNo = query.get("page");
			if (pageNo == null || pageNo.toString().equals("")) {
				pageNo = "1";
			}
		}
		page.setPageNo(Integer.parseInt(pageNo.toString()));
		Object pageSize = query.get("pageSize");
		if (pageSize != null && !pageSize.toString().equals("")) {
			page.setPageSize(Integer.parseInt(pageSize.toString()));
		}

		return page;
	}

	/** 基于@ExceptionHandler异常处理 */
//	@ExceptionHandler
//	@ResponseBody
//	public WtmsResult exp(HttpServletRequest request, Exception ex) {
//		WtmsResult result = new WtmsResult();
//		// request.setAttribute("ex", ex);
//		logger.error("系统错误：[" + this.getClass() + "]" + ex);
//		ex.printStackTrace();
//		// 根据不同错误转向不同页面 这里使用json
//		String wtmsException = "com.autozi.common.exceptions.WtmsException";
//		if (ex.getMessage() != null && ex.getMessage().startsWith(wtmsException)) {
//			String errMSG = ex
//					.getMessage()
//					.substring(ex.getMessage().indexOf(wtmsException) + wtmsException.length() + 2,
//							ex.getMessage().indexOf("\r\n")).trim();
//			result.buildStatus(ResultConstant.Code.ERROR, errMSG);
//			return result;
//		} else if (ex instanceof WtmsInterfaceException) {
//			WtmsInterfaceException e = (WtmsInterfaceException) ex;
//			result.buildStatus(e.getCode(), ex.getMessage());
//			return result;
//		} else if (ex instanceof WtmsException) {
//			result.buildStatus(ResultConstant.Code.ERROR, ex.getMessage());
//			return result;
//		} else {
//			result.buildStatus(ResultConstant.Code.ERROR, ResultConstant.Code.ERROR_MSG);
//			return result;
//		}
//	}
	
	
	protected HashMap<String, Object> buildFilter(HttpServletRequest request) {
		if(request.getParameter("param")!=null){
			Map<String, Object> query = converObjectByJson(request.getParameter("param"),ParamObject.class).getQuery();
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.putAll(query);
			return paramMap;
		}
        Map<?, ?> filter = request.getParameterMap();
        HashMap<String, Object> filters = new HashMap<String, Object>();
        filters = (HashMap<String, Object>) copyQueryMap(filter, filters);
//        filters.remove("pageNo"); //移除页码标志
//        filters.remove("pageNum");
//        filters.remove("basePath");
        return filters;
    }
	
    
    /**
     * 取得查询条件
     *
     * @param filter
     * @param queryMap
     */
    public static Map<String, Object> copyQueryMap(Map<?, ?> filter, Map<String, Object> queryMap) {
        for (Map.Entry<?, ?> entry : filter.entrySet()) {
            String[] value = (String[]) entry.getValue();
            if (value != null && !"".equals(value[0]) && !" ".equals(value[0])) {
            	if(value[0].trim().indexOf("\\")>0){
            		queryMap.put(entry.getKey().toString(), value[0].trim());//value[0].trim().replaceAll("\\\\", "").replaceAll("\n", "").replaceAll(" ", ""));
            	}else{
            		queryMap.put(entry.getKey().toString(), value[0].trim());
            	}
            }
        }
        return queryMap;
    }
    
//    public String getExcelFilePath(String excelName){
//    	try {
//			return ApplicationContextProvider.getApplicationContext()
//					.getResource("/WEB-INF/excel_template/"+excelName+".xls")
//					.getFile().getAbsolutePath();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//    	return null;
//    }
	
    /**
     * @Description: 根据HttpServletRequest对象获取MultipartFile
     * @dateTime 2013-10-29下午02:10:48
     */
    public MultipartFile getMultipartFile(HttpServletRequest request) {
    	return getFileSet(request, 1024 * 20000, new String[]{".xls",".xlsx"}).get(0);
    }
    
    /**
     * @Description: 根据HttpServletRequest对象获取MultipartFile集合
     * @dateTime 2013-10-29下午02:10:48
     */
    public List<MultipartFile> getMultipartFileList(HttpServletRequest request) {
    	return getFileSet(request, 1024 * 20000, new String[]{".xls",".xlsx"});
    }
    
    /**
     * @param maxLength    文件最大限制
     * @param allowExtName 不允许上传的文件扩展名
     * @Description: 根据HttpServletRequest对象获取MultipartFile集合
     * @dateTime 2013-10-29下午02:10:48
     */
    public List<MultipartFile> getFileSet(HttpServletRequest request, long maxLength, String[] allowExtName) {
        MultipartHttpServletRequest multipartRequest = null;
        try {
            multipartRequest = (MultipartHttpServletRequest) request;
        } catch (Exception e) {
            return new LinkedList<MultipartFile>();
        }

        List<MultipartFile> files = new LinkedList<MultipartFile>();
        files = multipartRequest.getFiles("attach");
        // 移除不符合条件的
        for (int i = 0; i < files.size(); i++) {
            if (!validateFile(files.get(i), maxLength, allowExtName)) {
                files.remove(files.get(i));
                if (files.size() == 0) {
                    return files;
                }
            }
        }
        return files;
    }
    
    
    
    /**
     * @param maxLength    文件最大限制
     * @param allowExtName 不允许上传的文件扩展名
     * @Description: 验证文件格式，这里主要验证后缀名
     * @user zhiyun.chen
     * @dateTime 2013-10-29下午02:11:48
     */
    private boolean validateFile(MultipartFile file, long maxLength, String[] allowExtName) {
        if (file.getSize() < 0 || file.getSize() > maxLength)
            return false;
        String filename = file.getOriginalFilename();

        // 处理不选择文件点击上传时，也会有MultipartFile对象，在此进行过滤
        if (filename.equals("")) {
            return false;
        }
        String extName = filename.substring(filename.lastIndexOf("."))
                .toLowerCase();
        if (allowExtName == null || allowExtName.length == 0
                || Arrays.binarySearch(allowExtName, extName) != -1) {
            return true;
        } else {
            return false;
        }
    }
    
    //对导入的excel进行格式化
//    public String impExcelFormat(String cell,boolean empty,String msg){
//    	if(empty){
//    		if((cell==null || StringUtils.isBlank(cell.toString()))){
//    			throw new WtmsInterfaceException(msg);
//    		}else{
//    			String str = cell.toString();
//    			if(StringUtils.isNotBlank(str) && StringUtils.contains(str, ".")){
//    	    		String _str = str.substring(str.lastIndexOf("."));
//    	    		if(_str.equals(".0")){
//    	    			return str.substring(0, str.lastIndexOf("."));
//    	    		}
//    	    		return str;
//    	    	}
//    	    	return str;
//    		}
//    	}else{
//    		if((cell==null || StringUtils.isBlank(cell.toString()))){
//    			return "";
//    		}else{
//    			String str = cell.toString();
//    			if(StringUtils.isNotBlank(str) && StringUtils.contains(str, ".")){
//    	    		String _str = str.substring(str.lastIndexOf("."));
//    	    		if(_str.equals(".0")){
//    	    			return str.substring(0, str.lastIndexOf("."));
//    	    		}
//    	    		return str;
//    	    	}
//    	    	return str;
//    		}
//    	}
//    	
//    }
	
    
    
    
    /**
     * RESTFUL用POST方式上传时，获取JSON信息
     * @param request
     * @param response
     * @return
     * @throws java.io.IOException
     * @use JSONObject object = getJSONObject(request, response);
     */
    public JSONObject getJSONObject(ServletRequest request,HttpServletResponse response)throws Exception{
    	
    	return JSONObject.fromObject(buildFilter((HttpServletRequest)request));
    }
    
    /**
     * 用二进制流方式上传时，获取JSON信息
     * @param request
     * @param response
     * @return
     * @throws java.io.IOException
     * @use JSONObject object = getJSONObjectFromStream(request, response);
     */
    public JSONObject getJSONObjectFromStream(ServletRequest request,HttpServletResponse response)throws Exception{
    	
    	String content = null;
        try{
            InputStream is = request.getInputStream();
            StringBuffer sb = new StringBuffer() ;
            InputStreamReader isr = new InputStreamReader(is,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String s = "" ;
            while((s=br.readLine())!=null){
                sb.append(s) ;
            }
            content =sb.toString();
//            System.out.println("&&&&&&&&&&&&"+content);
        } catch (Exception ex){
            if(ex instanceof EOFException){
            }else{
                renderJson(response, WtmsResult.error(ResultConstant.Code.ERROR_MSG));
            }
        }
        JSONObject object = JSONObject.fromObject(content);
        return object;
    }
    /**
	 * 输出JSON到页面
	 */
	protected void renderJson(HttpServletResponse response, Object data) {
		RenderUtils.renderJson(response, data);
	}
}
