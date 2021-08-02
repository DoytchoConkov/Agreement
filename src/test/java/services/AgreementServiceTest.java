package services;

import classes.Agreement;
import classes.Product;
import org.junit.jupiter.api.Test;
import services.AgreementService;
import services.impl.AgreementServiceImpl;

import java.io.FileNotFoundException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AgreementServiceTest {

    @Test
    void testAgreementAPI() throws FileNotFoundException {
        Agreement agreement = new Agreement("Doytcho");
        Product product = new Product("Pizza",BigDecimal.valueOf(10.30));
        Product subProduct1 = new Product("dough",BigDecimal.valueOf(2.56));
        Product subProduct2 = new Product("tomato puree",BigDecimal.valueOf(1.06));
        Product subProduct3 = new Product("cheese",BigDecimal.valueOf(1.22));
        product.addProduct(subProduct1);
        product.addProduct(subProduct2);
        product.addProduct(subProduct3);
        agreement.addProduct(product);

        AgreementService agreementService = new AgreementServiceImpl();

        String path = agreementService.saveAgreement(agreement);
        Agreement newAgreement = agreementService.getAgreement(path);

        assertEquals(agreement.getName(), newAgreement.getName());
        assertEquals(agreement.getSignedBy(), newAgreement.getSignedBy());
        assertEquals(agreement.getProducts().size(), newAgreement.getProducts().size());
        assertEquals(newAgreement, newAgreement.getProducts().get(0).getParentObject());

    }
}