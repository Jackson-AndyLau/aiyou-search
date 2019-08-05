package com.huazai.b2c.aiyou.dao;

import org.apache.solr.client.solrj.SolrQuery;

import com.huazai.b2c.aiyou.repo.SearchResultData;

/**
 * 
 * @author HuaZai
 * @contact who.seek.me@java98k.vip
 *          <ul>
 * @description 商品搜索DAO接口层
 *              </ul>
 * @className SearchItemDao
 * @package com.huazai.b2c.aiyou.dao
 * @createdTime 2017年06月15日
 *
 * @version V1.0.0
 */
public interface SearchItemDao
{
	/**
	 * 
	 * @author HuaZai
	 * @contact who.seek.me@java98k.vip
	 * @title search
	 *        <ul>
	 * @description 搜索商品
	 *              </ul>
	 * @createdTime 2017年06月15日
	 * @param solrQuery
	 * @return
	 * @return SearchResultData
	 *
	 * @version : V1.0.0
	 */
	public SearchResultData search(SolrQuery solrQuery);
}
