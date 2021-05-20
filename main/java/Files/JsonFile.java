package Files;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import card.*;
import creditcard.*;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import com.github.cliftonlabs.json_simple.JsonArray;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;


public class JsonFile extends Format {
    public ArrayList<Card> readFile(String path) {
        AmercianExpressCard americanEx = new AmercianExpressCard();
        DiscoverCard discover = new DiscoverCard();
        MasterCard master = new MasterCard();
        VisaCard visa = new VisaCard();

        americanEx.identifyCard(discover);
        discover.identifyCard(master);
        master.identifyCard(visa);

        SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yy");
        ArrayList<Card> creditcards = new ArrayList<>();
        System.out.println("Json file");
        BufferedReader br = null;
        try {
            br = Files.newBufferedReader(Paths.get(path));
            JsonArray jsonParser = (JsonArray) Jsoner.deserialize(br);

            jsonParser.forEach(cc -> {
                JsonObject cardJsonObject = (JsonObject) cc;
                String expirationDate = (String) cardJsonObject.get("ExpirationDate");
                Date date = null;
                try {
                    date = dateFormat.parse(expirationDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String nameOfCardHolder = (String) cardJsonObject.get("NameOfCardholder");
                String cardNumber = ((BigDecimal) cardJsonObject.get("CardNumber")).toString();

                creditcards.add(americanEx.check(cardNumber, date, nameOfCardHolder));
            });
            br.close();
            return creditcards;
        } catch ( IOException | JsonException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean writeFile(ArrayList<Card> creditcards, String input_path) {
        System.out.println("Writing to json File....!!");

        BufferedWriter reader = null;
        try {
            reader = Files.newBufferedWriter(Paths.get(input_path + "output.json"));
            JsonArray cardsOutput = new JsonArray();
            for (Card card : creditcards) {
                JsonObject finalCard = new JsonObject();
                finalCard.put("CardNumber", card.getNumber());
                finalCard.put("Type", card.getcardType());
                cardsOutput.add(finalCard);
            }
            Jsoner.serialize(cardsOutput, reader);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

}
