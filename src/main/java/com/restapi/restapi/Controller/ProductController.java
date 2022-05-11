package com.restapi.restapi.Controller;


import com.restapi.restapi.Entity.Product;
import com.restapi.restapi.Repo.ProductReposity;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductReposity reposity;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

 //view list
    @ApiOperation(value="Retrives all the product", notes = "A list of product",
                        response = Product.class,
                        responseContainer = "List",
                        produces = "Application/json")
    @RequestMapping(value = "/products",method = RequestMethod.GET)
    public List<Product> getProduct(){
        
        return reposity.findAll();
    }
//get by id
    @RequestMapping(value = "/products/{id}",method = RequestMethod.GET)
    public Product getProduct(@PathVariable("id")int id)

    {
        LOGGER.info("Finding product by ID" + id);

            return reposity.findById(id).get();
    }
    //Create
    @RequestMapping(value = "/products",method = RequestMethod.POST)
    public Product createProduct(@RequestBody Product product){
        return reposity.save(product);
    }
    //Update
    @RequestMapping(value = "/products",method = RequestMethod.PUT)
    public Product updateProduct(@RequestBody Product product){
        return reposity.save(product);
    }
  //delete
    @RequestMapping(value = "/products/{id}",method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable("id")int id)
    {
       reposity.deleteById(id);

    }


}
