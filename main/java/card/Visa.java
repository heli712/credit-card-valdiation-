package card ;
import  java.util.Date;

public class Visa extends Card {
    public Visa(String creditcardNumber, Date expirationDate, String name, String cardType, boolean validation){
        super(creditcardNumber, expirationDate, name, cardType, validation);
    }
}
