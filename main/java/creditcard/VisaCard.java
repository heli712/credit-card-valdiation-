package creditcard ;
import card.*;
import  java.util.Date;

public class VisaCard implements CreditCard{
    private CreditCard identify = null;
    public Card check(String creditcardNumber, Date expirationDate, String name) {
        System.out.println("Visa Card got request");
        char first = creditcardNumber.charAt(0);
        if((creditcardNumber.length() == 16 || creditcardNumber.length() == 13) && first == '4' ){
            System.out.println("Its a valid Visa Card");
            return new Visa(creditcardNumber, expirationDate, name,"Visa Card", true);
        }
        else {
            if(identify != null){
                return identify.check(creditcardNumber, expirationDate, name);
            }
        }
        System.out.println("Its invalid card");
        return new Visa(creditcardNumber, expirationDate, name, "invalid Card", false);
    }

    public void identifyCard(CreditCard next) {
        this.identify = next;
    }
}

