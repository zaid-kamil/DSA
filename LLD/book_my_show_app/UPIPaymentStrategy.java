package book_my_show_app;

public class UPIPaymentStrategy implements PaymentStrategy {
    @Override
    public void makePayment(double amount) {    
        System.out.println("Payment done using UPI: " + amount);
    }
    
}
