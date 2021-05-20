package creditcard ;
import card.*;
import  java.util.Date;

public interface CreditCard {

    public Card check(String creditcardNumber, Date expirationDate, String name);

    public void identifyCard(CreditCard next);

}
