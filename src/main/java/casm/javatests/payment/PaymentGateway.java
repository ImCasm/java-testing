package casm.javatests.payment;

public interface PaymentGateway {
    PaymentResponse requestPayment(PaymentRequest request);
}
