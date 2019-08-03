package com.huazai.b2c.aiyou.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.huazai.b2c.aiyou.dto.SearchItemDto;
import com.huazai.b2c.aiyou.mapper.SearchItemMapper;
import com.huazai.b2c.aiyou.repo.AiyouResultData;
import com.huazai.b2c.aiyou.service.SearchItemService;

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
	private SolrClient solrClient;

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
				SolrInputDocument document = new SolrInputDocument();
				document.addField("id", searchItemDto.getId());
				document.addField("item_title", searchItemDto.getTitle());
				document.addField("item_sell_point", searchItemDto.getSell_point());
				document.addField("item_price", searchItemDto.getPrice());
				document.addField("item_image", searchItemDto.getImage());
				document.addField("item_category_name", searchItemDto.getCategory_name());
				document.addField("item_desc", searchItemDto.getItem_desc());
				sDocuments.add(document);
			}
			// 将 SolrInputDocument 集合添加到索引库中
			solrClient.add(sDocuments);
			// 提交请求
			solrClient.commit();
		} catch (SolrServerException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				solrClient.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return AiyouResultData.ok();
	}

}
