package com.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.dao.LabsMapper;
import com.dao.UserMapper;
import com.entity.Labs;

import com.entity.User;
import util.Page;

/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年7月9日 上午10:59:58
* 类说明
*/
@Service
public class LabsService {
	
		@Resource
		LabsMapper lb;
		
		@Resource
		UserMapper us;
		
		//插入序列号
		public int insertSelective(Labs record) {
			return lb.insertSelective(record);
		};
		
		//查询序列号
		public Labs selectByPrimaryKey(Integer labsId) {
			return lb.selectByPrimaryKey(labsId);
		};
		
		//修改序列号
		public int updateByPrimaryKeySelective(Labs record) {
			return lb.updateByPrimaryKeySelective(record);
		};
		
		public Labs selectByPrimaryNumber(String labsNumber) {
			return lb.selectByPrimaryNumber(labsNumber);
		};
		
		//查询序列号，改用户认证
		public boolean  updateLabsUser(String labsNumber,User user) {
			boolean result = false;
			Labs labs = selectByPrimaryNumber(labsNumber);
			if(labs != null && labs.getLabsValidation() != 1 && user.getUser_auth() != 1) {
				user.setUser_auth(1);
				int num = us.updateByPrimaryKeySelective(user);
				if(num > 0) {
					labs.setLabsValidation(1);
					labs.setLabsActtime(new Date());
					labs.setLabsUser(user.getUser_account());
					int miss = updateByPrimaryKeySelective(labs);
					if(miss > 0) {
						result = true;
					}else {
						throw new RuntimeException();
					}
				}
				
			}
			return result;
		}
		
		//根据分类做数据分页
		public Page<Labs> selectgetLabsByInfo(Integer curPage, Integer pageSize) {
			if (curPage == null || pageSize == null) {
		            return null;
		    }
		    int totalRecords = lb.selectByLabsCount();
		    Page<Labs> page = new Page<>(curPage, pageSize, totalRecords);
		    Map<String,Object> map = new HashMap<>();
		        			   map.put("startIndex",page.getStartIndex());
		        			   map.put("pageSize",page.getPageSize());
		        				   
		    List<Labs> userById = lb.selectByPageLabs(map);
		    page.setPageData(userById);

		    return page;
		 }
		
		//根据序列ID删除单个序列号
		public int deleteByPrimaryKey(Integer labsId) {
			return lb.deleteByPrimaryKey(labsId);
		};
}
