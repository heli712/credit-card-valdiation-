package card;

import  java.util.Date;
public class AmericanExpress extends Card {
    public AmericanExpress(String creditcardNumber, Date expirationDate, String name, String cardType, boolean validation){
        super(creditcardNumber, expirationDate, name, cardType, validation);
    }
}

