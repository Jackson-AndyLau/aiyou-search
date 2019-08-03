package com.huazai.b2c.aiyou.mapper;

import java.util.List;

import com.huazai.b2c.aiyou.dto.SearchItemDto;

/**
 * 
 * @author HuaZai
 * @contact who.seek.me@java98k.vip
 *          <ul>
 * @description 商品查询mapper接口类
 *              </ul>
 * @className SearchItemMapper
 * @package com.huazai.b2c.aiyou.mapper
 * @createdTime 2017年06月15日
 *
 * @version V1.0.0
 */
public interface SearchItemMapper
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
	public List<SearchItemDto> getSearchItemList();
}
