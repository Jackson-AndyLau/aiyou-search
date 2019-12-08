package com.huazai.b2c.aiyou.listener;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;

import com.huazai.b2c.aiyou.service.SearchItemService;

/**
 * 
 * @author HuaZai
 * @contact who.seek.me@java98k.vip
 *          <ul>
 * @description 商品 ActiveMQ 监听类
 *              </ul>
 * @className TbItemChangeListener
 * @package com.huazai.b2c.aiyou.listener
 * @createdTime 2017年06月15日
 *
 * @version V1.0.0
 */
public class TbItemChangeListener implements MessageListener {

	@Autowired
	private SearchItemService searchItemService;

	@Override
	public void onMessage(Message message) {
		try {
			TextMessage textMessage = null;
			Long itemId = null;
			// 获取商品ID
			if (message instanceof TextMessage) {
				textMessage = (TextMessage) message;
				itemId = Long.parseLong(textMessage.getText());
			}
			// 修改商品索引库
			searchItemService.updateTbItemById(itemId);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
