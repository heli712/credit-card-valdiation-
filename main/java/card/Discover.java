package card ;
import  java.util.Date;

public class Discover extends Card {
    public Discover(String creditcardNumber, Date expirationDate, String name, String cardType, boolean validation){
        super(creditcardNumber, expirationDate, name, cardType, validation);
    }
}
