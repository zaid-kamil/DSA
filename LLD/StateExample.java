class WrongStateException extends Exception{
    public WrongStateException(String message){
        super(message);
    }
}
class ATM{
    ATMState state;
    private int cashInMachine = 2000;
    ATM(){
        state = new NoCardState();
    }

    public int getCashInMachine(){
        return cashInMachine;
    }

    public void setCashInMachine(int cashInMachine) {
        this.cashInMachine = cashInMachine;
    }

    void setState(ATMState state){
        this.state = state;
    }
    void insertCard() throws WrongStateException{
        state.insertCard(this);
    }
    void ejectCard() throws WrongStateException{
        state.ejectCard(this);
    }
    void insertPin(int pinEntered) throws WrongStateException{
        state.insertPin(this,pinEntered);
    }
    void requestCash(int cashToWithdraw) throws WrongStateException{
        state.requestCash(this,cashToWithdraw);
    }
}

interface ATMState{
    void insertCard(ATM atm) throws WrongStateException;
    void ejectCard(ATM atm) throws WrongStateException;
    void insertPin(ATM atm,int pinEntered) throws WrongStateException; 
    void requestCash(ATM atm, int cashToWithdraw) throws WrongStateException;
}

class NoCardState implements ATMState{
    @Override
    public void insertCard(ATM atm) throws WrongStateException{
        System.out.println("Card inserted");
        atm.setState(new HasCardState());
    }
    @Override
    public void ejectCard(ATM atm) throws WrongStateException{
        throw new WrongStateException("No card inserted");
    }
    @Override
    public void insertPin(ATM atm,int pinEntered) throws WrongStateException{
        throw new WrongStateException("No card inserted");
    }
    @Override
    public void requestCash(ATM atm, int cashToWithdraw) throws WrongStateException{
        throw new WrongStateException("No card inserted");
    }
}

class HasCardState implements ATMState{

    @Override
    public void insertCard(ATM atm) throws WrongStateException {
        throw new WrongStateException("Card already inserted");
    }

    @Override
    public void ejectCard(ATM atm) throws WrongStateException {
        System.out.println("Card ejected");
        atm.setState(new NoCardState());
    }

    @Override
    public void insertPin(ATM atm, int pinEntered) throws WrongStateException {
        System.out.println("Pin entered");
        if(pinEntered == 1234){
            atm.setState(new PinValidatedState());
        }else{
            System.out.println("Invalid pin");
            System.out.println("Card ejected");
            atm.setState(new NoCardState());
        }
    }

    @Override
    public void requestCash(ATM atm, int cashToWithdraw) throws WrongStateException {
        throw new WrongStateException("Pin not entered");
    }
}

class PinValidatedState implements ATMState{

    @Override
    public void insertCard(ATM atm) throws WrongStateException {
        throw new WrongStateException("Card already inserted");
    }

    @Override
    public void ejectCard(ATM atm) throws WrongStateException {
        System.out.println("Card ejected");
        atm.setState(new NoCardState());
    }

    @Override
    public void insertPin(ATM atm, int pinEntered) throws WrongStateException {
        throw new WrongStateException("Pin already entered");
    }

    @Override
    public void requestCash(ATM atm, int cashToWithdraw) throws WrongStateException {
        if (cashToWithdraw % 10 != 0) {
            System.out.println("Amount should be multiple of 10");
            return;
        }
        else if(cashToWithdraw > atm.getCashInMachine()){
            System.out.println("Not enough cash in ATM");
            return;
        }
        System.out.println("Cash withdrawn");
        atm.setCashInMachine(atm.getCashInMachine() - cashToWithdraw);
        System.out.println("Card ejected");
        atm.setState(new NoCardState());
    }
}

class StateExample{
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.setCashInMachine(2100);
        try {
            atm.insertCard();
            atm.ejectCard();
            atm.insertCard();
            atm.insertPin(1234);
            atm.requestCash(1000);
            atm.requestCash(100);
            atm.requestCash(100);
        } catch (WrongStateException e) {
            System.out.println(e.getMessage());
        }
    }
}
