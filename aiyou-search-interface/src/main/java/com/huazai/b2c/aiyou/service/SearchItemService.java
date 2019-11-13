package com.huazai.b2c.aiyou.service;

import com.huazai.b2c.aiyou.repo.AiyouResultData;
import com.huazai.b2c.aiyou.repo.SearchResultData;

/**
 * 
 * @author HuaZai
 * @contact who.seek.me@java98k.vip
 *          <ul>
 * @description SearchItem Service层接口
 *              </ul>
 * @className SearchItemService
 * @package com.huazai.b2c.aiyou.service
 * @createdTime 2017年06月15日
 *
 * @version V1.0.0
 */
public interface SearchItemService
{

	/**
	 * 
	 * @author HuaZai
	 * @contact who.seek.me@java98k.vip
	 * @title getSearchItemList
	 *        <ul>
	 * @description 查询商品集合
	 *              </ul>
	 * @createdTime 2017年06月15日
	 * @return
	 * @return List<SearchItemDto>
	 *
	 * @version : V1.0.0
	 */
	public AiyouResultData importItemAll();

	/**
	 * 
	 * @author HuaZai
	 * @contact who.seek.me@java98k.vip
	 * @title search
	 *        <ul>
	 * @description 查询商品分页
	 *              </ul>
	 * @createdTime 2017年06月15日
	 * @param queryString 查询条件
	 * @param pageNum     当前页码
	 * @param pageRow     显示条数
	 * @return
	 * @return SearchResultData
	 *
	 * @version : V1.0.0
	 */
	public SearchResultData searchItemByPage(String query, Integer pageNum, Integer pageRow);

	/**
	 * 
	 * @author HuaZai
	 * @contact who.seek.me@java98k.vip
	 * @title updateTbItemById
	 *        <ul>
	 * @description 通过商品ID修改索引库商品信息
	 *              </ul>
	 * @createdTime 2017年06月17日
	 * @param itemId
	 * @return
	 * @return AiyouResultData
	 *
	 * @version : V1.0.0
	 */
	public AiyouResultData updateTbItemById(Long itemId);
}
