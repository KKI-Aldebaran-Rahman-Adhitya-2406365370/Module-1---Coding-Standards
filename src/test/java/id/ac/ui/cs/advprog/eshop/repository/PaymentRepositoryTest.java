package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    List<Payment> payments;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
        payments = new ArrayList<>();

        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        Order order = new Order("13652556-012a-4c07-b546-54eb1396d79b", products, 1708560000L, "Safira");

        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment1 = new Payment("pay-1", "VOUCHER_CODE", paymentData, order);
        payments.add(payment1);

        Payment payment2 = new Payment("pay-2", "VOUCHER_CODE", paymentData, order);
        payments.add(payment2);
    }

    @Test
    void testSaveCreate() {
        Payment payment = payments.get(0);
        Payment result = paymentRepository.save(payment);

        Payment findResult = paymentRepository.findById(payments.get(0).getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getStatus(), findResult.getStatus());
    }

    @Test
    void testSaveUpdate() {
        Payment payment = payments.get(0);
        paymentRepository.save(payment);

        Map<String, String> newPaymentData = new HashMap<>();
        newPaymentData.put("voucherCode", "ESHOP1234ABC567X"); // Invalid voucher -> REJECTED
        Payment newPayment = new Payment(payment.getId(), "VOUCHER_CODE", newPaymentData, payment.getOrder());

        Payment result = paymentRepository.save(newPayment);
        Payment findResult = paymentRepository.findById(payment.getId());

        assertEquals(payment.getId(), result.getId());
        assertEquals("REJECTED", findResult.getStatus());
    }

    @Test
    void testFindByIdIfIdFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }
        Payment findResult = paymentRepository.findById(payments.get(1).getId());
        assertEquals(payments.get(1).getId(), findResult.getId());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }
        Payment findResult = paymentRepository.findById("invalid-id");
        assertNull(findResult);
    }

    @Test
    void testGetAllPayments() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }
        List<Payment> paymentList = paymentRepository.getAllPayments();
        assertEquals(2, paymentList.size());
    }
}