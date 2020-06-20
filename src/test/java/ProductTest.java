import com.DistribuidoraJD.model.Product;
import com.DistribuidoraJD.model.exception.LackOfStockException;

import org.junit.Assert;
import org.junit.Test;

public class ProductTest {
    @Test
    public void aNewProductHasZeroStock(){
        Product product = new Product();

        Assert.assertEquals(product.getStock(),new Integer(0));
    }
    @Test
    public void givenANewProductWhenIAdd30ToStockThenTheNewProductHas30Stock(){
        Product product = new Product();
        product.addStock(30);

        Assert.assertEquals(product.getStock(),new Integer(30));
    }
    @Test
    public void given30StockProductWhenISubstract10ToStockThenTheNewProductHas20Stock(){
        Product product = new Product();
        product.addStock(30);
        product.substractStock(10);

        Assert.assertEquals(product.getStock(),new Integer(20));
    }
    @Test(expected = LackOfStockException.class)
    public void given10StockProductWhenISubstract20ToStockThenLackOfStockExceptionIsRaised(){
        Product product = new Product();
        product.addStock(10);
        product.substractStock(20);

    }
}