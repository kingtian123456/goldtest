package com.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.dao.MessageMapper;

import com.entity.Message;
import util.Page;

/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年7月3日 上午9:56:24
* 类说明
*/
@Service
public class MessageService {
	
		@Resource
		MessageMapper ms;
		
		//新增消息
		public int insertSelective(Message record) {
			return ms.insertSelective(record);
		};
		
		//根据消息ID查询消息
	    public Message selectByPrimaryKey(Integer msId) {
	    	return ms.selectByPrimaryKey(msId);
	    };
	    
	    //查询单个文章下的一级回复数
	    public int selectByMessageNewsCount(Map<String,Object> map) {
	    	return ms.selectByMessageNewsCount(map);
	    };
	    
	    //根据消息进行分页
	    public Page<Message> selectgetMessageByInfo(Integer newsId, Integer curPage, Integer pageSize) {
	        if (newsId == null || curPage == null || pageSize == null) {
	            return null;
	        }
	        Map<String,Object> map = new HashMap<>();
	        				   map.put("newsId",newsId);
	        				   map.put("layerId","0");
	        				   
	        int totalRecords = selectByMessageNewsCount(map);
	        
	        System.err.println(totalRecords);
	        
	        Page<Message> page = new Page<>(curPage, pageSize, totalRecords);
	        
	        map.put("before",page.getStartIndex());
	        map.put("after",page.getPageSize());
	        				   
	        List<Message> Messageinfo = ms.selectByMessageNewsOne(map);
	        
	        //查询下一级
	        for (Message message : Messageinfo) {
				List<Message> list = ms.selectByMessageLevel(message.getMsId());
				message.setList(list);
			}
	        
	        page.setPageData(Messageinfo);

	        return page;
	    }
}
