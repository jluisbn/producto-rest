package com.gapsi.product;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gapsi.product.model.ProductModel;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductsRestApplicationTests {

	private ProductModel pm = new ProductModel();
	
	@Test
	public void testAddProduct() throws SQLException 
	{
		int res = pm.addProduct("nombre", "descripcion", "20", "modelo", "1");
		assertTrue(res > 0);
	}
	
	@Test
	public void testUpdateProduct() throws SQLException 
	{
		int res = pm.updateProduct(1, "descripcion", "modelo");
		assertTrue(res > 0);
	}

}
