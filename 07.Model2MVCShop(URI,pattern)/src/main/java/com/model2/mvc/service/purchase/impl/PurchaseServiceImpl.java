package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.PurchaseDao;
import com.model2.mvc.service.domain.Purchase;

@Service("purchaseServiceImpl")
public class PurchaseServiceImpl implements PurchaseService {

	@Autowired
	@Qualifier("purchaseDaoImpl")
	private PurchaseDao purchaseDao;

	public void setPurchaseDao(PurchaseDao purchaseDao) {
		this.purchaseDao = purchaseDao;		
	}
	
	public PurchaseServiceImpl() {
		System.out.println(this.getClass());
	}

	@Override
	public void addPurchase(Purchase purchase) throws Exception {
		purchaseDao.addPurchase(purchase);
	}

	@Override
	public Purchase getPurchase(int tranNo) throws Exception {
		return purchaseDao.getPurchase(tranNo);
	}

	@Override
	public Purchase getPurchaseByProd(int prodNo) throws Exception {
		return purchaseDao.getPurchaseByProd(prodNo);
	}

	@Override
	public Map<String, Object> getPurchaseList(Search search, String userId) throws Exception {
		List<Object> list = purchaseDao.getPurchaseList(search, userId);
		int totalCount = purchaseDao.getTotalCount(search);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}

	@Override
	public Map<String, Object> getSaleList(Search search) throws Exception {
		List<Object> list = purchaseDao.getSaleList(search);
		int totalCount = purchaseDao.getTotalCount(search);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}

	@Override
	public void updatePurchase(Purchase purchase) throws Exception {
		purchaseDao.updatePurchase(purchase);
	}

	@Override
	public void updateTranCode(Purchase purchase) throws Exception {
		purchaseDao.updateTranCode(purchase);
	}

}
