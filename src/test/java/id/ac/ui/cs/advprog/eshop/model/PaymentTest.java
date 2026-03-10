package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    private Order order;
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        order = new Order("13652556-012a-4c07-b546-54eb1396d79b", products, 1708560000L, "Safira");
        paymentData = new HashMap<>();
    }

    @Test
    void testCreatePaymentNullOrderOrData() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("pay-1", "VOUCHER_CODE", paymentData, null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("pay-1", "VOUCHER_CODE", null, order);
        });
    }

    @Test
    void testCreatePaymentVoucherSuccess() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("pay-1", "VOUCHER_CODE", paymentData, order);

        assertEquals("SUCCESS", payment.getStatus());
        assertEquals("VOUCHER_CODE", payment.getMethod());
        assertEquals("pay-1", payment.getId());
        assertEquals(order, payment.getOrder());
    }

    @Test
    void testCreatePaymentVoucherRejectedNot16Chars() {
        paymentData.put("voucherCode", "ESHOP1234ABC567"); // 15 chars
        Payment payment = new Payment("pay-1", "VOUCHER_CODE", paymentData, order);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentVoucherRejectedNotStartWithEshop() {
        paymentData.put("voucherCode", "CORP1234ABC56789"); // 16 chars, wrong start
        Payment payment = new Payment("pay-1", "VOUCHER_CODE", paymentData, order);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentVoucherRejectedNot8Digits() {
        paymentData.put("voucherCode", "ESHOP1234ABC567X"); // 16 chars, 7 digits
        Payment payment = new Payment("pay-1", "VOUCHER_CODE", paymentData, order);
        assertEquals("REJECTED", payment.getStatus());
    }
}