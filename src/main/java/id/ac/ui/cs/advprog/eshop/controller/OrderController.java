package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.service.OrderService;
import id.ac.ui.cs.advprog.eshop.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/create")
    public String createOrderPage() {
        return "createOrder";
    }

    @GetMapping("/history")
    public String historyPage() {
        return "orderHistoryForm";
    }

    @PostMapping("/history")
    public String historyPost(@RequestParam String author, Model model) {
        model.addAttribute("orders", orderService.findAllByAuthor(author));
        return "orderHistoryList";
    }

    @GetMapping("/pay/{orderId}")
    public String payPage(@PathVariable String orderId, Model model) {
        model.addAttribute("order", orderService.findById(orderId));
        return "orderPay";
    }

    @PostMapping("/pay/{orderId}")
    public String payPost(@PathVariable String orderId, @RequestParam String method, @RequestParam Map<String, String> paymentData, Model model) {
        Order order = orderService.findById(orderId);
        Payment payment = paymentService.addPayment(order, method, paymentData);
        model.addAttribute("paymentId", payment.getId());
        return "paymentIdResult";
    }
}