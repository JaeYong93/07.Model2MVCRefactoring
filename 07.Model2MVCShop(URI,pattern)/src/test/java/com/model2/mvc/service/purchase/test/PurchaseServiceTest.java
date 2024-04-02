package com.model2.mvc.service.purchase.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseDao;
import com.model2.mvc.service.purchase.PurchaseService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/context-aspect.xml", 
									"classpath:config/context-common.xml",
									"classpath:config/context-mybatis.xml",
									"classpath:config/context-transaction.xml"})
public class PurchaseServiceTest {

	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	
	@Autowired
	@Qualifier("purchaseDaoImpl")
	private PurchaseDao purchaseDao;

	//@Test
	public void testAddpurchase() throws Exception {
	
		User user = new User();
		Product product = new Product();
		Purchase purchase = new Purchase();
		
		user.setUserId("user12");
		product.setProdNo(10017);
		purchase.setPurchaseProd(product);
		purchase.setBuyer(user);
		
		purchaseService.addPurchase(purchase);
		
		System.out.println(purchase);
	}
	
	//@Test
	public void testGetPurchase() throws Exception {
	
		Purchase purchase = purchaseService.getPurchase(10060);
		Assert.assertEquals("user12", purchase.getBuyer().getUserId());
		
		System.out.println(purchase);
	
	}
	
	//@Test
	public void testGetPurchasByProd() throws Exception {
		
		Purchase purchase = purchaseService.getPurchase(10060);
		Assert.assertEquals("user12", purchase.getBuyer().getUserId());
		
		System.out.println(purchase);
	}
	
	@Test
	public void testGetPurchaseList() throws Exception {
	
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map = purchaseService.getPurchaseList(search, "user12");
		
		List<Object> list = (List<Object>)map.get("list");
		Assert.assertEquals(3, list.size());
		
		System.out.println(list);
		
		Integer totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
	}
	
	//@Test
	public void testUpdatePurchase() throws Exception {
		
		Purchase purchase = new Purchase();
		purchase.setReceiverName("SCOTT");
		purchase.setPaymentOption("1");
		
		purchaseService.updatePurchase(purchase);
		
		Purchase updatePurchase = purchaseService.getPurchase(purchase.getTranNo());
		
		Assert.assertEquals("SCOTT", purchase.getReceiverName());
	
	}

	//@Test
	public void testUpdateTranCode() throws Exception {
		
		Purchase purchase = new Purchase();
		
		purchase.setTranNo(10060);
		purchase.setTranCode("2");
		
		purchaseService.updateTranCode(purchase);
		
		Purchase updatePurchase = purchaseService.getPurchase(purchase.getTranNo());
		
		Assert.assertEquals("2", updatePurchase.getTranCode().trim());
	}
	
}
