package card;
import  java.util.Date;

public abstract  class Card {
    private String creditcardNumber;
    private Date expirationDate;
    private String name;
    private String cardType;
    private boolean validation;

    protected Card(String creditcardNumber, Date expirationDate, String name, String cardType, boolean validation) {
        this.creditcardNumber = creditcardNumber;
        this.expirationDate = expirationDate;
        this.name = name;
        this.cardType = cardType;
        this.validation = validation;
    }

    public String getNumber(){
        return this.creditcardNumber;
    }
    public Date getDate(){
        return this.expirationDate;
    }
    public String getname() {
        return this.name;
    }
    public String getcardType() {
        return this.cardType;
    }
    public boolean getValidation() {
        return this.validation;
    }
}
