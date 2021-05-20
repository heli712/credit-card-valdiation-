package card ;
import  java.util.Date;

public class Master extends Card {
    public Master(String creditcardNumber, Date expirationDate, String name, String cardType, boolean validation){
        super(creditcardNumber, expirationDate, name, cardType, validation);
    }
}
