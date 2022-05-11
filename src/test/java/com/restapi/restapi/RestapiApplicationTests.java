package com.restapi.restapi;

import com.restapi.restapi.Entity.Product;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@SpringBootTest
class RestapiApplicationTests {

	@Value("${productrestapi.services.url}")
	private String baseURL;

	@Test
	public void testGetProduct() {
		System.out.println(baseURL);
		RestTemplate restTemplate =new RestTemplate();
		Product product = restTemplate.getForObject(baseURL+"4",Product.class);
		assertNotNull(product);
		assertEquals("piseth",product.getName());

	}
	@Test
	public void TestCreateProduct()
	{
		RestTemplate restTemplate= new RestTemplate();
		Product product = new Product();
		product.setName("samsung mobile");
		product.setDescription("It good");
		product.setPrice(900);
		Product newProduct = restTemplate.postForObject(baseURL,product,Product.class);
		assertNotNull(newProduct);
		assertNotNull(newProduct.getId());
		assertEquals("samsung mobile", newProduct.getName());

	}
	@Test
	public void TestUpdateProduct(){
		RestTemplate restTemplate= new RestTemplate();
		Product product = restTemplate.getForObject(baseURL+"1",Product.class);
		product.setPrice(14000);
		restTemplate.put(baseURL,product);
	}

}
