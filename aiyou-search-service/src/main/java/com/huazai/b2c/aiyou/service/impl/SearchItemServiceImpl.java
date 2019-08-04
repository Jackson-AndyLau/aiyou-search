package com.huazai.b2c.aiyou.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
				solrServer.add(doc);
			}
			// 将 SolrInputDocument 集合添加到索引库中
			// solrServer.add(sDocuments);

			// 提交请求
			solrServer.commit();
		} catch (SolrServerException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return AiyouResultData.ok();
	}

}
