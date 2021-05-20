package creditcard ;
import card.*;
import  java.util.Date;

public class DiscoverCard implements CreditCard{
    private CreditCard identify = null;
    public Card check(String creditcardNumber, Date expirationDate, String name) {
        System.out.println("Discover Card got request");
        String start = creditcardNumber.substring(0,4);
        if(creditcardNumber.length() == 16 && start.equals("6011") ){
            System.out.println("Its a valid Discover Card");
            return new Discover(creditcardNumber, expirationDate, name, "Discover Card", true);
        }
        else {
            if(identify != null){
                return identify.check(creditcardNumber, expirationDate, name);
            }
        }
        return null;
    }
    public void identifyCard(CreditCard next) {
        this.identify = next;
    }
}

