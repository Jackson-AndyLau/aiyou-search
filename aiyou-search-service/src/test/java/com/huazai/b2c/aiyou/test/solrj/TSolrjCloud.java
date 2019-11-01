package com.huazai.b2c.aiyou.test.solrj;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * 
 * @author HuaZai
 * @contact who.seek.me@java98k.vip
 *                                 <ul>
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
//		CloudSolrServer solrServer = new CloudSolrServer(SOLR_CLUSTER_URL);
		CloudSolrClient solrClient = new CloudSolrClient(SOLR_CLUSTER_URL);
		// 2、设置 DefaultCollection 属性
		solrClient.setDefaultCollection("aiyou_core");
		// 3、创建 SolrInputDocument 对象
		SolrInputDocument inputDocument = new SolrInputDocument();
		// 4、向文档中添加域
		inputDocument.addField("id", "1001");
		inputDocument.addField("item_title", "测试数据");
		inputDocument.addField("item_price", "1919");
		inputDocument.addField("item_desc", "这是一条测试数据");
		// 5、将文档写入索引库
		solrClient.add(inputDocument);
		// 6、提交请求
		solrClient.commit();
	}

}
