import com.DistribuidoraJD.model.Client;
import com.DistribuidoraJD.model.ProductCopy;
import com.DistribuidoraJD.model.Sale;
import com.DistribuidoraJD.model.SaleItem;
import com.DistribuidoraJD.model.exception.LackOfStockException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SaleTest {

    private Sale sale;
    private ProductCopy pitusas;
    private ProductCopy donSatur;
    private HashSet<ProductCopy> list;
    private SaleItem saleItem1;

    @Before
    public void setup(){
        list = new HashSet<>();
        sale = new Sale();
        pitusas = new ProductCopy(0,"pitusa", 30d,40d,40,40,40);
        donSatur = new ProductCopy(1,"donSatur", 30d,40d,40,40,40);
        saleItem1 = new SaleItem(pitusas,1);
    }
    @Test
    public void testANewSaleHasZeroProductsAndZeroPrice(){
        Assert.assertEquals(sale.getAmountOfProducts(),0);
        Assert.assertEquals(sale.getTotalPrice(),new Double(0.0d));
    }

    @Test
    public void testWhenIAddANewSaleItemWithOneProductNowTheSaleHasOneProduct(){
        sale.addItem(saleItem1);

        Assert.assertEquals(sale.getAmountOfProducts(),1);
    }

    @Test
    public void testWhenIAddANewSaleItemWith2PitusasThePriceIs60Pesos(){
        SaleItem itemPitusas = new SaleItem(pitusas,2);

        sale.addItem(itemPitusas);

        Assert.assertEquals(sale.getTotalPrice(),new Double(60));
    }

    @Test
    public void testWhenIAddTwoSaleItemWith2PitusasThePriceIs120Pesos(){
        SaleItem itemPitusas = new SaleItem(pitusas,2);

        sale.addItem(itemPitusas);
        sale.addItem(itemPitusas);

        Assert.assertEquals(sale.getTotalPrice(),new Double(120));
    }

    @Test
    public void testWhenTheAmountForDIscountIsReachedTheDiscountIsMade(){
        ProductCopy manaos = new ProductCopy(22,"Manaos",100d,5d,10,6,30);
        SaleItem itemManaos = new SaleItem(manaos,10);

        sale.addItem(itemManaos);

        Assert.assertEquals(sale.getTotalPrice(),new Double(950));
    }

    @Test
    public void testWhenTheAmountForDIscountIsMptReachedTheDiscountIsNotMade(){
        ProductCopy manaos = new ProductCopy(22,"Manaos",100d,5d,10,6,30);
        SaleItem itemManaos = new SaleItem(manaos,9);

        sale.addItem(itemManaos);

        Assert.assertEquals(sale.getTotalPrice(),new Double(900));
    }

    @Test(expected = LackOfStockException.class)
    public void testWhenTryToAddAnItemWithMoreAmountThanStockItRaiseLackOfStockException(){
        ProductCopy manaos = new ProductCopy(22,"Manaos",100d,5d,10,6,2);
        SaleItem itemManaos = new SaleItem(manaos,3);

        sale.addItem(itemManaos);
    }

    @Test
    public void testAfterLackOfStockExceptionTheItemIsNotAdded(){
        ProductCopy manaos = new ProductCopy(22,"Manaos",100d,5d,10,6,2);
        SaleItem itemManaos = new SaleItem(manaos,3);
        try{
            sale.addItem(itemManaos);
        }catch (LackOfStockException e) {}
        Assert.assertEquals(sale.getAmountOfProducts(),0);
    }

    @Test(expected = LackOfStockException.class)
    public void testWhenASaleIsInitializedWithAnInvalidListOfItemsItRaiseException() {
        ProductCopy manaos = new ProductCopy(22,"Manaos",100d,5d,10,6,2);
        SaleItem itemManaos = new SaleItem(manaos,3);
        List<SaleItem> list = new ArrayList<>();
        list.add(itemManaos);

        Sale aNewSale = new Sale(new Client(),list,"");
    }
}
