package com.huazai.b2c.aiyou.test.solrj;

import java.io.IOException;
import java.util.HashMap;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.MapSolrParams;
import org.junit.Test;

/**
 * 
 * @author HuaZai
 * @contact who.seek.me@java98k.vip
 *          <ul>
 * @description 测试solr集群
 *              </ul>
 * @className TSolrjCluster
 * @package com.huazai.b2c.aiyou.test.solrj
 * @createdTime 2019年10月31日 下午5:41:37
 *
 * @version V1.0.0
 */
public class TSolrjCloud {

	public static final String SOLR_CLUSTER_URL = "192.168.159.129:2001,192.168.159.129:2002,192.168.159.129:2003";

	/**
	 * 
	 * @author HuaZai
	 * @contact who.seek.me@java98k.vip
	 * @title TSolrClusterAddDocument
	 *        <ul>
	 * @description 测试新增文档
	 *              </ul>
	 * @createdTime 2019年10月31日 下午7:31:18
	 * @throws SolrServerException
	 * @throws IOException
	 * @return void
	 *
	 * @version : V1.0.0
	 */
	@Test
	public void tSolrCloudAddDoc() throws SolrServerException, IOException {
		// 1、创建SolrServer对象，使用CloudSolrServer子类，构造方法的参数是zookeeper的地址
		CloudSolrServer solrServer = new CloudSolrServer(SOLR_CLUSTER_URL);
//		CloudSolrClient solrServer = new CloudSolrClient(SOLR_CLUSTER_URL);
		// 2、设置 DefaultCollection 属性
		solrServer.setDefaultCollection("aiyou_core");
		// 3、创建 SolrInputDocument 对象
		SolrInputDocument inputDocument = new SolrInputDocument();
		// 4、向文档中添加域
		inputDocument.addField("id", "1001");
		inputDocument.addField("item_title", "测试数据");
		inputDocument.addField("item_price", "1919");
		inputDocument.addField("item_desc", "这是一条测试数据");
		// 5、将文档写入索引库
		solrServer.add(inputDocument);
		// 6、提交请求
		solrServer.commit();
	}

	@Test
	public void tSolrCloudQuaryDoc() {
		// 1、创建SolrServer对象，使用CloudSolrServer子类，构造方法的参数是zookeeper的地址
		CloudSolrServer solrServer = new CloudSolrServer(SOLR_CLUSTER_URL);
//		CloudSolrClient solrServer = new CloudSolrClient(SOLR_CLUSTER_URL);
		// 指定查询的集合
		solrServer.setDefaultCollection("aiyou_core");

		// 设置查询参数
		// 第一种查询方式（）：MapSolrParams来实现
		HashMap<String, String> map = new HashMap<>();
		// 设置查询参数
		map.put("q", "id:1001");
		// 创建MapSolrParams查询对象
		MapSolrParams solrParams = new MapSolrParams(map);

		// 第二种查询方式：SolrParams 有一个 SolrQuery 子类来实现
		SolrQuery solrQuery = new SolrQuery("id:1001");
		// 指定需要回显的内容
		solrQuery.addField("id");
		solrQuery.addField("item_title");
		solrQuery.addField("item_price");
		
		try
		{
			// 根据参数查询指定文档
			QueryResponse query = solrServer.query(solrParams);
			// 提交查询请求
			solrServer.commit();
			// 输出查询内容
			SolrDocumentList list = query.getResults();
			for (SolrDocument document : list)
			{
				System.err.println("商品ID：" + document.get("id"));
				System.err.println("商品标题：" + document.get("item_title"));
				System.err.println("商品价格：" + document.get("item_price"));
			}
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
			// 关闭连接
			solrServer.shutdown();
		}

	}

}
