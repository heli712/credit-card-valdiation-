package creditcard ;

import card.*;
import  java.util.Date;

public class AmercianExpressCard implements CreditCard{

    private CreditCard next = null;
    public Card check(String creditcardNumber, Date expirationDate, String name) {
        System.out.println("American Express Card got request");
        char first = creditcardNumber.charAt(0);
        char second = creditcardNumber.charAt(1);
        if(creditcardNumber.length() == 15 && first == '3' &&(second == '4' || second == '7')){
            System.out.println("Its a valid American Express Card");
            return new AmericanExpress(creditcardNumber, expirationDate, name, "AmericanExpress Card", true);
        }
        else {
            if(next != null){
                return next.check(creditcardNumber, expirationDate, name);
            }
        }
        return null;
    }
    public void identifyCard(CreditCard next) {
        this.next = next;
    }
}

