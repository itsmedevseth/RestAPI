package com.restapi.restapi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.restapi.restapi.Entity.Product;

import com.restapi.restapi.Repo.ProductReposity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest
public class ProductRestApiMvctes {

    private static final String  PRODUCT_URL  = "/productapi/products";

	private static final String CONTEXT_URL = "/productapi";

	private static final int PRODUCT_PRICE = 1000;

	private static final String PRODUCT_DESCRIPTION_STRING = "Very cool";

	private static final String PRODUCT_NAME_STRING = "Dell";

	private static final int PRODUCT_ID = 1;

	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductReposity reposity;



    @Test
    public void testFindAll() throws Exception 
    {
        Product product= buildProduct();
        
        	
      List<Product> products =   Arrays.asList( product);
   when(reposity.findAll()).thenReturn(products);
   ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
   	mockMvc.perform(get(PRODUCT_URL).contextPath(CONTEXT_URL)).andExpect(status().isOk())
            .andExpect(content().json(objectWriter.writeValueAsString(products)));
    }
    @Test
    public void testCreatProduct() throws Exception {
        Product product = buildProduct();

        when(reposity.save(any())).thenReturn(product);
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(PRODUCT_URL).contextPath(CONTEXT_URL).contentType(MediaType.APPLICATION_JSON)
                .content(objectWriter.writeValueAsString(product))).andExpect(status().isOk())
                .andExpect(content().json(objectWriter.writeValueAsString(product)));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        Product product = buildProduct();

        when(reposity.save(any())).thenReturn(product);
        product.setPrice(1200);
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(put(PRODUCT_URL).contextPath(CONTEXT_URL).contentType(MediaType.APPLICATION_JSON)
                .content(objectWriter.writeValueAsString(product))).andExpect(status().isOk())
                .andExpect(content().json(objectWriter.writeValueAsString(product)));
    }
    @Test
    public void deleteProduct() throws Exception {
        doNothing().when(reposity).deleteById(PRODUCT_ID);
        mockMvc.perform(delete(PRODUCT_URL+PRODUCT_ID).contextPath(CONTEXT_URL)).andExpect(status().isOk());

    }


    private Product buildProduct() {
		Product product=new Product();
		product.setId(PRODUCT_ID);
        product.setName(PRODUCT_NAME_STRING);
        product.setDescription(PRODUCT_DESCRIPTION_STRING);
        product.setPrice(PRODUCT_PRICE);
        return product;
	}
  
}
