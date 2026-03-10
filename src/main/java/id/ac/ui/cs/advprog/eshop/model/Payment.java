package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;

import java.util.Map;

@Getter
public class Payment {
    private String id;
    private String method;
    private String status;
    private Map<String, String> paymentData;
    private Order order;

    public Payment(String id, String method, Map<String, String> paymentData, Order order) {
        if (order == null || paymentData == null) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.method = method;
        this.paymentData = paymentData;
        this.order = order;

        if ("VOUCHER_CODE".equals(method)) {
            this.status = validateVoucherCode(paymentData.get("voucherCode")) ? "SUCCESS" : "REJECTED";
        } else {
            this.status = "REJECTED";
        }
    }

    private boolean validateVoucherCode(String voucher) {
        if (voucher == null || voucher.length() != 16 || !voucher.startsWith("ESHOP")) {
            return false;
        }

        int numCount = 0;
        for (char c : voucher.toCharArray()) {
            if (Character.isDigit(c)) {
                numCount++;
            }
        }
        boolean isNumCount8 = (numCount == 8);
        return isNumCount8;
    }

}