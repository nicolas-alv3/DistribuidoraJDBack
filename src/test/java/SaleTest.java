import com.DistribuidoraJD.model.Product;
import com.DistribuidoraJD.model.Sale;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SaleTest {

    Sale sale;
    private Product pitusas;
    private Product donSatur;

    @Before
    public void setup(){
        sale = new Sale();
        pitusas = new Product(0,"pitusa", 30d,40d,40,40,40);
        donSatur = new Product(1,"donSatur", 30d,40d,40,40,40);
    }
    @Test
    public void testANewSaleHasZeroProductsAndZeroPrice(){
        Assert.assertEquals(sale.getProducts().size(),0);
        Assert.assertEquals(sale.getTotalPrice(),new Double(0.0d));
    }

    @Test
    public void testWhenIAddANewListWithOneProductToTheSaleThenTheSaleHasOneProduct(){
        List<Product> list = new ArrayList<>();
        list.add(pitusas);
        sale.addProducts(list);

        Assert.assertEquals(sale.getProducts().size(),1);
    }

    @Test
    public void testWhenIAddANewListWithOneProductOf30PesosToTheSaleThenTheSaleTotalPriceIs30Pesos(){
        List<Product> list = new ArrayList<>();
        list.add(pitusas);
        sale.addProducts(list);

        Assert.assertEquals(sale.getTotalPrice(),new Double(30));
    }

    @Test
    public void testWhenIAddANewListWithTwoProductOf30PesosToTheSaleThenTheSaleTotalPriceIs60Pesos(){
        List<Product> list = new ArrayList<>();
        list.add(pitusas);
        list.add(donSatur);
        sale.addProducts(list);

        Assert.assertEquals(sale.getTotalPrice(),new Double(60));
    }


}
