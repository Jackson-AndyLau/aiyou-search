package com.huazai.aiyou.search.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.huazai.aiyou.common.dto.SearchItemDto;
import com.huazai.aiyou.common.response.AiyouResultData;
import com.huazai.aiyou.common.response.SearchResultData;
import com.huazai.aiyou.search.dao.SearchItemDao;
import com.huazai.aiyou.search.mapper.SearchItemMapper;

/**
 * 
 * @author HuaZai
 * @contact who.seek.me@java98k.vip
 *          <ul>
 * @description 商品搜索DAO实现类
 *              </ul>
 * @className SearchItemDaoImpl
 * @package com.huazai.b2c.aiyou.dao.impl
 * @createdTime 2017年06月15日
 *
 * @version V1.0.0
 */
@Repository
public class SearchItemDaoImpl implements SearchItemDao
{

	@Autowired
	private SolrServer solrServer;

	@Autowired
	private SearchItemMapper searchItemMapper;

	@Override
	public SearchResultData search(SolrQuery solrQuery)
	{
		try
		{
			// 根据SolrQuery对象查询索引库
			QueryResponse response = solrServer.query(solrQuery);
			// 获取查询的文档
			SolrDocumentList sDocumentList = response.getResults();
			// 封装商品列表数据
			List<SearchItemDto> sItemDtos = new ArrayList<>();
			for (SolrDocument solrDocument : sDocumentList)
			{
				SearchItemDto itemDto = new SearchItemDto();
				itemDto.setId(Long.valueOf(String.valueOf(solrDocument.get("id"))));
				// 取标题高亮显示
				Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
				List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
				String itemTitle = "";
				// 有高亮取高亮，无高亮取普通
				if (list != null && list.size() > 0)
				{
					itemTitle = list.get(0);
				} else
				{
					itemTitle = String.valueOf(solrDocument.get("item_title"));
				}
				itemDto.setTitle(itemTitle);
				itemDto.setSell_point(String.valueOf(solrDocument.get("item_sell_point")));
				itemDto.setPrice(Long.valueOf(String.valueOf(solrDocument.get("item_price"))));
				itemDto.setImage(String.valueOf(solrDocument.get("item_image")));
				itemDto.setCategory_name(String.valueOf(solrDocument.get("item_category_name")));
				itemDto.setItem_desc(String.valueOf(solrDocument.get("item_desc")));
				sItemDtos.add(itemDto);
			}
			// 封装返回数据
			SearchResultData resultData = new SearchResultData();
			resultData.setItemList(sItemDtos);
			resultData.setRecordCount(sItemDtos.size());

			return resultData;
		} catch (SolrServerException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public AiyouResultData updateTbItemById(Long itemId)
	{
		// 通过商品ID获取商品信息
		SearchItemDto searchItemDto = searchItemMapper.getTbItemById(itemId);
		// 创建对象
		SolrInputDocument doc = new SolrInputDocument();
		try
		{
			// 添加文档域
			doc.addField("id", searchItemDto.getId());
			doc.addField("item_title", searchItemDto.getTitle());
			doc.addField("item_sell_point", searchItemDto.getSell_point());
			doc.addField("item_price", searchItemDto.getPrice());
			doc.addField("item_image", searchItemDto.getImage());
			doc.addField("item_category_name", searchItemDto.getCategory_name());
			doc.addField("item_desc", searchItemDto.getItem_desc());
			// 将文档添加到索引库
			solrServer.add(doc);
			// 提交事务
			solrServer.commit();
		} catch (SolrServerException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return AiyouResultData.ok();
	}

}
