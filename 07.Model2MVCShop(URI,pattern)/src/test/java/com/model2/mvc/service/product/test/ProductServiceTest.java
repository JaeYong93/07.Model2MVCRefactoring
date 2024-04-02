package com.model2.mvc.service.product.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/context-aspect.xml", 
									"classpath:config/context-common.xml",
									"classpath:config/context-mybatis.xml",
									"classpath:config/context-transaction.xml"})
public class ProductServiceTest {

	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	@Test
	public void testAddProduct() throws Exception {
		
		Product product = new Product();

		product.setProdName("갈치");
		product.setProdDetail("제주은갈치");
		product.setPrice(10000);
		product.setFileName("갈치사진");
		product.setManuDate("20240301");
		
		productService.addProduct(product);
	
		System.out.println(product);
		
		Assert.assertEquals("갈치", product.getProdName());
		Assert.assertEquals("제주은갈치", product.getProdDetail());
		Assert.assertEquals(10000, product.getPrice());
		Assert.assertEquals("갈치사진", product.getFileName());
		Assert.assertEquals("20240301", product.getManuDate());		
	}

	//@Test
	public void testGetProduct() throws Exception {
		
		Product product = new Product();
		
		product = productService.getProduct(10033);
		
		System.out.println(product);
		
		Assert.assertEquals(10033, product.getProdNo());		
		Assert.assertEquals("갈치", product.getProdName());
		Assert.assertEquals("제주은갈치", product.getProdDetail());
		Assert.assertEquals(10000, product.getPrice());
		Assert.assertEquals("갈치사진", product.getFileName());
		Assert.assertEquals("20240301", product.getManuDate());
		
		Assert.assertNotNull(productService.getProduct(10012));
		Assert.assertNotNull(productService.getProduct(10016));
	}
	
	//@Test
	public void testUpdateProduct() throws Exception {
	
		Product product = productService.getProduct(10040);
		Assert.assertNotNull(product);
		
		Assert.assertEquals("갈치", product.getProdName());
		Assert.assertEquals("제주은갈치", product.getProdDetail());
		Assert.assertEquals("20240301", product.getManuDate());
		Assert.assertEquals(10000, product.getPrice());
		Assert.assertEquals("갈치사진", product.getFileName());
		
		product.setProdName("고등어");
		product.setProdDetail("노르웨이고등어");
		product.setManuDate("20240228");
		product.setPrice(8000);
		product.setFileName("고등어사진");
		
		productService.updateProduct(product);
		
		product = productService.getProduct(10040);
		Assert.assertNotNull(product);
		
		System.out.println(product);
		
		Assert.assertEquals("고등어", product.getProdName());
		Assert.assertEquals("노르웨이고등어", product.getProdDetail());
		Assert.assertEquals("20240228", product.getManuDate());
		Assert.assertEquals(8000, product.getPrice());
		Assert.assertEquals("고등어사진", product.getFileName());		
	}
	
	//@Test
	public void testGetProductListAll() throws Exception {

		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		Map<String, Object> map = productService.getProductList(search);
		
		List<Object> list = (List<Object>)map.get("list");
		Assert.assertEquals(3, list.size());
		
		System.out.println(list);
		
		Integer totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
		
		System.out.println("====================");
		
		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("0");
		search.setSearchKeyword("");
		map = productService.getProductList(search);
		
		list = (List<Object>)map.get("list");
		Assert.assertEquals(3, list.size());
		
		System.out.println(list);
		
		totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
	}
	
	//@Test
	public void testGetProductListByProdNo() throws Exception {

		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("0");
		search.setSearchKeyword("10033");
		Map<String, Object> map = productService.getProductList(search);
		
		List<Object> list = (List<Object>)map.get("list");
		Assert.assertEquals(1, list.size());
		
		System.out.println(list);
		
		Integer totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
		
		System.out.println("====================");
		
		search.setSearchCondition("0");
		search.setSearchKeyword(""+System.currentTimeMillis());
		map = productService.getProductList(search);
		
		list = (List<Object>)map.get("list");
		Assert.assertEquals(0, list.size());
		
		System.out.println(list);
		
		totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
	}
	
	//@Test
	public void testGetProductListByProdName() throws Exception {

		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("1");
		search.setSearchKeyword("갈치");		
		Map<String, Object> map = productService.getProductList(search);
		
		List<Object> list = (List<Object>)map.get("list");
		Assert.assertEquals(1, list.size());
		
		System.out.println(list);
		
		Integer totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
		
		System.out.println("====================");
		
		search.setSearchCondition("1");
		search.setSearchKeyword(""+System.currentTimeMillis());
		map = productService.getProductList(search);
		
		list = (List<Object>)map.get("list");
		Assert.assertEquals(0, list.size());
		
		System.out.println(list);
		
		totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
	}
	
	//@Test
	public void testGetProductListByPrice() throws Exception {

		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("2");
		search.setSearchKeyword("10000");		
		Map<String, Object> map = productService.getProductList(search);
		
		List<Object> list = (List<Object>)map.get("list");
		Assert.assertEquals(3, list.size());
		
		System.out.println(list);
		
		Integer totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
		
		System.out.println("====================");
		
		search.setSearchCondition("2");
		search.setSearchKeyword(""+System.currentTimeMillis());
		map = productService.getProductList(search);
		
		list = (List<Object>)map.get("list");
		Assert.assertEquals(0, list.size());
		
		System.out.println(list);
		
		totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
	}	
}//end of class