package creditcard ;
import card.*;
import  java.util.Date;

public class MasterCard implements CreditCard{

    private CreditCard identify = null;
    public Card check(String creditcardNumber, Date expirationDate, String name) {
        System.out.println("Master Card got reques");
        char first = creditcardNumber.charAt(0);
        int second = Character.getNumericValue(creditcardNumber.charAt(1));
        if(creditcardNumber.length() == 16 && first == '5' &&(second <= 5 && second >= 1)){
            System.out.println("Its a valid Master Card");
            return new Master(creditcardNumber, expirationDate, name, "Master Card", true);
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
