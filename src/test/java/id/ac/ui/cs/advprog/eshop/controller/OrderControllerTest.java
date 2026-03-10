package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.service.OrderService;
import id.ac.ui.cs.advprog.eshop.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = OrderController.class)
class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @MockBean
    private PaymentService paymentService;

    @Test
    void testCreateOrderPage() throws Exception {
        mockMvc.perform(get("/order/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("createOrder"));
    }

    @Test
    void testHistoryPage() throws Exception {
        mockMvc.perform(get("/order/history"))
                .andExpect(status().isOk())
                .andExpect(view().name("orderHistoryForm"));
    }

    @Test
    void testHistoryPost() throws Exception {
        doReturn(new ArrayList<>()).when(orderService).findAllByAuthor("Safira");
        mockMvc.perform(post("/order/history").param("author", "Safira"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("orders"))
                .andExpect(view().name("orderHistoryList"));
    }

    @Test
    void testPayPage() throws Exception {
        Order mockOrder = new Order("ord-1", new ArrayList<>(), 123L, "Safira");
        doReturn(mockOrder).when(orderService).findById("ord-1");

        mockMvc.perform(get("/order/pay/ord-1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("order"))
                .andExpect(view().name("orderPay"));
    }

    @Test
    void testPayPost() throws Exception {
        Order mockOrder = new Order("ord-1", new ArrayList<>(), 123L, "Safira");
        Payment mockPayment = new Payment("pay-1", "VOUCHER_CODE", new HashMap<>(), mockOrder);

        doReturn(mockOrder).when(orderService).findById("ord-1");
        doReturn(mockPayment).when(paymentService).addPayment(eq(mockOrder), eq("VOUCHER_CODE"), any());

        mockMvc.perform(post("/order/pay/ord-1")
                        .param("method", "VOUCHER_CODE"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("paymentId"))
                .andExpect(view().name("paymentIdResult"));
    }
}