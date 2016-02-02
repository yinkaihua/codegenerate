package com.coding17.easycms.soa.dao.channel.impl;

import org.springframework.stereotype.Repository;

import com.coding17.easycms.soa.base.dao.impl.SuperDAO;
import com.coding17.easycms.soa.dao.channel.TChannelDAO;
import com.coding17.easycms.soa.entity.channel.TChannel;

/**
 * 栏目DAO
 * @Title: TChannelDAOImpl.java 
 * @Package: com.coding17.easycms.soa.dao.channel.impl  
 * @author: yin.kh
 * @date: 2016年1月25日 下午11:14:01
 */
@Repository("tChannelDAO")
public class TChannelDAOImpl extends SuperDAO<TChannel> implements TChannelDAO {

	@Override
	protected String getStatementPrefix() {
		return TChannel.class.getName();
	}

}
