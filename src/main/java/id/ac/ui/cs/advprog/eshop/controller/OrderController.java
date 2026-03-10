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
        return null;
    }

    @GetMapping("/history")
    public String historyPage() {
        return null;
    }

    @PostMapping("/history")
    public String historyPost(@RequestParam String author, Model model) {
        return null;
    }

    @GetMapping("/pay/{orderId}")
    public String payPage(@PathVariable String orderId, Model model) {
        return null;
    }

    @PostMapping("/pay/{orderId}")
    public String payPost(@PathVariable String orderId, @RequestParam String method, @RequestParam Map<String, String> paymentData, Model model) {
        return null;
    }
}