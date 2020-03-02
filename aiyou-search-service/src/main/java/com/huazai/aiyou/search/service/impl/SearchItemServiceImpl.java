package com.huazai.aiyou.search.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huazai.aiyou.common.dto.SearchItemDto;
import com.huazai.aiyou.common.response.AiyouResultData;
import com.huazai.aiyou.common.response.SearchResultData;
import com.huazai.aiyou.search.dao.SearchItemDao;
import com.huazai.aiyou.search.mapper.SearchItemMapper;
import com.huazai.aiyou.search.service.SearchItemService;

/**
 * 
 * @author HuaZai
 * @contact who.seek.me@java98k.vip
 *          <ul>
 * @description SearchItem Service实现类
 *              </ul>
 * @className SearchItemServiceImpl
 * @package com.huazai.b2c.aiyou.service.impl
 * @createdTime 2017年06月15日
 *
 * @version V1.0.0
 */
@Service
public class SearchItemServiceImpl implements SearchItemService
{

	@Autowired
	private SearchItemMapper searchItemMapper;

	@Autowired
	private SearchItemDao searchItemDao;

	@Autowired
	private SolrServer solrServer;

	@Override
	public AiyouResultData importItemAll()
	{
		try
		{
			// 查询商品数据集合
			List<SearchItemDto> itemList = searchItemMapper.getSearchItemList();
			// 创建 SolrInputDocument 集合对象
			List<SolrInputDocument> sDocuments = new ArrayList<>();
			for (SearchItemDto searchItemDto : itemList)
			{
				SolrInputDocument doc = new SolrInputDocument();
				doc.addField("id", searchItemDto.getId());
				doc.addField("item_title", searchItemDto.getTitle());
				doc.addField("item_sell_point", searchItemDto.getSell_point());
				doc.addField("item_price", searchItemDto.getPrice());
				doc.addField("item_image", searchItemDto.getImage());
				doc.addField("item_category_name", searchItemDto.getCategory_name());
				doc.addField("item_desc", searchItemDto.getItem_desc());
				sDocuments.add(doc);
			}
			// 将 SolrInputDocument 集合添加到索引库中
			solrServer.add(sDocuments);

			// 提交请求
			solrServer.commit();
		} catch (SolrServerException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return AiyouResultData.ok();
	}

	@Override
	public SearchResultData searchItemByPage(String query, Integer pageNum, Integer pageRow)
	{
		try
		{
			// 创建查询对象
			SolrQuery solrQuery = new SolrQuery();
			// 设置查询条件
			solrQuery.setQuery(query);
			// 设置分页条件
			solrQuery.setStart((pageNum - 1) * pageRow);
			solrQuery.setRows(pageRow);
			// 指定默认搜索域
			solrQuery.set("df", "item_title");
			// 设置高亮
			solrQuery.setHighlight(true);
			solrQuery.addHighlightField("item_title");
			solrQuery.setHighlightSimplePre("<em style=\"color:red\">");
			solrQuery.setHighlightSimplePost("</em>");
			// 执行查询
			SearchResultData resultData = searchItemDao.search(solrQuery);
			// 设置总页数
			long recordCount = resultData.getRecordCount();
			long pageCount = recordCount / pageRow;
			if (recordCount % pageRow > 0)
			{
				pageCount++;
			}
			resultData.setPageCount(pageCount);
			return resultData;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public AiyouResultData updateTbItemById(Long itemId)
	{
		AiyouResultData resultData = searchItemDao.updateTbItemById(itemId);
		return resultData;
	}

}
