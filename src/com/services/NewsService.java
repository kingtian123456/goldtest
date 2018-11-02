package com.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.MessageMapper;
import com.dao.NewsMapper;
import com.entity.News;
import com.servlert.ToolUtil;

import util.Page;

/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年6月27日 下午4:35:33
* 类说明
*/
@Service
public class NewsService {
	
	@Resource
	NewsMapper news;
	
	@Resource
	MessageMapper ms;
	
	//插入数据
	public int insertSelective(News record) {
		return news.insertSelective(record);
	};
	
	//查询所有的关键字
	public List<String> selectByNewsKey(){
		return news.selectByNewsKey();
	};
	
	//查询博客文章总数
	public int selectByblogCount(Integer sortid) {
		return news.selectByblogCount(sortid);
	};
	
	//根据newsId查询文章信息
	public News selectByPrimaryKey(Integer newsId) {
		return news.selectByPrimaryKey(newsId);
	};
	
	//根据输入的对象，更新内容
	public int updateByPrimaryKeySelective(News record) {
		return news.updateByPrimaryKeySelective(record);
	};
	
	//根据输入的标题查找文章
	public News selectByNewsID(String title) {
		return news.selectByNewsID(title);
	};
	
	//文章点击排行榜
	public List<News> selectByNewsArticle(){
		return news.selectByNewsArticle();
	};
	
	//根据文章ID删除文章和留言
	public boolean deleteNewsMessage(Integer newsId) {
		boolean result = false;
		int num = news.deleteByPrimaryKey(newsId);
		if(num > 0) {//查询该文章是不是  有留言
			int man = ms.selectByMessageNewsIdCount(newsId);
			if(man > 0) {//有留言进行留言删除
				int mun = ms.delectByNewsMage(newsId);
				if(mun > 0) {
					result = true;
				}else {
					System.err.println("事件回滚!!!");
					throw new RuntimeException();
				}
			}else {//没有留言,没有必要进行留言删除
				result = true;
			}
		}
		return result;
	}
	
	//根据分类ID批量删除文章和留言
	public boolean deleteSortIdNewsMessage(Integer sortId) {
		boolean result = false;
		List<News> list = news.selectBySortIdNews(sortId);
		if(list.size() > 0) {//判断该分类下，有没有文章
			//有文章，循环删除文章和留言
			for (News news : list) {
				result = deleteNewsMessage(news.getNewsId());
			}
		}else {//没有文章，没有必要进行删除
			result = true;
		}
		return result;
	}
	
	//分页查询
	public Page<News> selectgetNewsByInfo(Integer sortid, Integer curPage, Integer pageSize) {
        if (sortid == null || curPage == null || pageSize == null) {
            return null;
        }
        int totalRecords = news.selectByblogCount(sortid);
        Page<News> page = new Page<>(curPage, pageSize, totalRecords);
        Map<String,Object> map = new HashMap<>();
        				   map.put("category", sortid);
        				   map.put("before",page.getStartIndex());
        				   map.put("after",page.getPageSize());
        				   
        List<News> userById = news.selectByblogpaging(map);
        //拆分关键字集合
        for (News news : userById) {
			news.setList(ToolUtil.conversionList(news.getNewsKeyword()));
		}
        page.setPageData(userById);

        return page;
    }
}
