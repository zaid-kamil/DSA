package book_my_show_app;

enum PaymentStatus {
    PENDING,
    COMPLETED,
    DECLINED,
    CANCELLED,
    REFUNDED
}
public class PaymentController {
    PaymentStrategy paymentStrategy;
    PaymentStatus paymentStatus;

    public PaymentController(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
        this.paymentStatus = PaymentStatus.PENDING;
    }

    public void makePayment(double amount) {
        paymentStrategy.makePayment(amount);
        paymentStatus = PaymentStatus.COMPLETED;
    }

    public void cancelPayment() {
        paymentStatus = PaymentStatus.CANCELLED;
    }

    
}
