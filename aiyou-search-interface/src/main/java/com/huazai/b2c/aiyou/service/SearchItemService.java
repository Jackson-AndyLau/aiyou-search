package com.huazai.b2c.aiyou.service;

import com.huazai.b2c.aiyou.repo.AiyouResultData;

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
}
