package com.b.simple.design.business.customer;

import java.math.BigDecimal;
import java.util.List;

import com.b.simple.design.business.exception.DifferentCurrenciesException;
import com.b.simple.design.model.customer.Amount;
import com.b.simple.design.model.customer.AmountImpl;
import com.b.simple.design.model.customer.Currency;
import com.b.simple.design.model.customer.Product;

public class CustomerBOImpl implements CustomerBO {

    public static final BigDecimal ZERO = BigDecimal.ZERO;
    public static final Currency EURO = Currency.EURO;

    @Override
    public Amount getCustomerProductsSum(List<Product> products)
            throws DifferentCurrenciesException {

        if (products.isEmpty())
            return new AmountImpl(ZERO, EURO);

        Currency getFirstProductCurrency = products.get(0).getAmount().getCurrency();

        if (!validateCurrencies(products, getFirstProductCurrency)) {
            throw new DifferentCurrenciesException();
        }

        BigDecimal amount = sum(products);

        return new AmountImpl(amount, getFirstProductCurrency);
    }

    private static BigDecimal sum(List<Product> products) {
        BigDecimal amount = BigDecimal.ZERO;
        for (Product product : products) {
            amount = amount.add(product.getAmount().getValue());
        }
        return amount;
    }


    private static Boolean validateCurrencies(List<Product> products, Currency getFirstProductCurrency) throws DifferentCurrenciesException {
        for (Product product : products) {
            if (product.getAmount().getCurrency() != getFirstProductCurrency) {
                return false;
            }
        }
        return true;
    }
}