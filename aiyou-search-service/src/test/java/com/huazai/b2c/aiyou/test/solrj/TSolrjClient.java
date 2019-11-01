package com.huazai.b2c.aiyou.test.solrj;

import java.io.IOException;
import java.util.HashMap;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.MapSolrParams;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author HuaZai
 * @contact who.seek.me@java98k.vip
 *          <ul>
 * @description 测试solrj客户端
 *              </ul>
 * @className TSolrjClient
 * @package com.huazai.b2c.aiyou.test.solrj
 * @createdTime 2017年06月14日
 *
 * @version V1.0.0
 */
public class TSolrjClient
{
//	public static final String SOLR_URL = "http://192.168.182.128:8080/solr/aiyou_core";
	public static final String SOLR_URL = "http://192.168.159.129:6060/solr/aiyou_core";

	/**
	 * 
	 * @author HuaZai
	 * @contact who.seek.me@java98k.vip
	 * @title addDocument
	 *        <ul>
	 * @description 添加文档
	 *              </ul>
	 * @createdTime 2017年06月15日
	 * @return void
	 *
	 * @version : V1.0.0
	 */
	@Test
	public void addDocument()
	{
		// 通过HttpSolrServer一个SolrServer对象
//		SolrServer solrServer = new HttpSolrServer(SOLR_URL);
		SolrClient solrServer = new HttpSolrClient(SOLR_URL);

		// 创建SolrInputDocument文档流对象
		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("id", "1001");
		doc.addField("item_title", "测试数据");
		doc.addField("item_price", "1919");
		doc.addField("item_desc", "这是一条测试数据");

		try
		{
			// 添加文档
			solrServer.add(doc);
			// 提交文档
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
		} finally
		{
			// 关闭连接
			solrServer.shutdown();
		}
	}

	/**
	 * 
	 * @author HuaZai
	 * @contact who.seek.me@java98k.vip
	 * @title deleteDocumentById
	 *        <ul>
	 * @description 根据文档ID删除文档
	 *              </ul>
	 * @createdTime 2017年06月15日
	 * @throws SolrServerException
	 * @throws IOException
	 * @return void
	 *
	 * @version : V1.0.0
	 */
	@Test
	public void deleteDocumentById()
	{
		// 通过HttpSolrServer一个SolrServer对象
//		SolrServer solrServer = new HttpSolrServer(SOLR_URL);
		SolrClient solrServer = new HttpSolrClient(SOLR_URL);

		try
		{
			// 删除指定ID的文档
			solrServer.deleteById("1001");
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
		} finally
		{
			// 关闭连接
			solrServer.shutdown();
		}
	}

	/**
	 * 
	 * @author HuaZai
	 * @contact who.seek.me@java98k.vip
	 * @title deleteDocumentByQuery
	 *        <ul>
	 * @description 查询删除
	 *              </ul>
	 * @createdTime 2017年06月15日
	 * @return void
	 *
	 * @version : V1.0.0
	 */
	@Test
	public void deleteDocumentByQuery()
	{
		// 通过HttpSolrServer一个SolrServer对象
//		SolrServer solrServer = new HttpSolrServer(SOLR_URL);
		SolrClient solrServer = new HttpSolrClient(SOLR_URL);

		try
		{
			// 删除指定ID的文档
			solrServer.deleteByQuery("id:1001");
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
		} finally
		{
			// 关闭连接
			solrServer.shutdown();
		}
	}

	/**
	 * 
	 * @author HuaZai
	 * @contact who.seek.me@java98k.vip
	 * @title queryDocumentById
	 *        <ul>
	 * @description 根据文档ID查询文档
	 *              </ul>
	 * @createdTime 2017年06月15日
	 * @return void
	 *
	 * @version : V1.0.0
	 */
	@Test
	public void queryDocumentById()
	{
		// 通过HttpSolrClient.Builder一个SolrClient对象
//		SolrServer solrServer = new HttpSolrServer(SOLR_URL);
		SolrClient solrServer = new HttpSolrClient(SOLR_URL);

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
	
	@Test
	public void test() throws SolrServerException {
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
//		HttpSolrServer solrServer = applicationContext.getBean(HttpSolrServer.class);
		HttpSolrClient solrServer = applicationContext.getBean(HttpSolrClient.class);
		SolrQuery solrQuery = new SolrQuery("*:*");
		solrQuery.addField("id");
		QueryResponse response = solrServer.query(solrQuery);
		SolrDocumentList list = response.getResults();
		System.out.println(list.size());
	}

}
