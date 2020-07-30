import com.DistribuidoraJD.model.ProductC;
import com.DistribuidoraJD.model.exception.LackOfStockException;

import org.junit.Assert;
import org.junit.Test;

public class ProductTest {
    @Test
    public void aNewProductHasZeroStock(){
        ProductC productC = new ProductC();

        Assert.assertEquals(productC.getStock(),new Integer(0));
    }
    @Test
    public void givenANewProductWhenIAdd30ToStockThenTheNewProductHas30Stock(){
        ProductC productC = new ProductC();
        productC.addStock(30);

        Assert.assertEquals(productC.getStock(),new Integer(30));
    }
    @Test
    public void given30StockProductWhenISubstract10ToStockThenTheNewProductHas20Stock(){
        ProductC productC = new ProductC();
        productC.addStock(30);
        productC.substractStock(10);

        Assert.assertEquals(productC.getStock(),new Integer(20));
    }
    @Test(expected = LackOfStockException.class)
    public void given10StockProductWhenISubstract20ToStockThenLackOfStockExceptionIsRaised(){
        ProductC productC = new ProductC();
        productC.addStock(10);
        productC.substractStock(20);

    }
}