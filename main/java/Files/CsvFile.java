package Files;

import card.*;
import creditcard.*;
import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import  java.util.Date;
import java.text.ParseException;
import java.util.ArrayList;
import  java.text.NumberFormat;
import java.util.Scanner;



public class CsvFile extends Format {
    public ArrayList<Card> readFile(String path) {
        AmercianExpressCard americanEx = new AmercianExpressCard();
        DiscoverCard discover = new DiscoverCard();
        MasterCard master = new MasterCard();
        VisaCard visa = new VisaCard();

        americanEx.identifyCard(discover);
        discover.identifyCard(master);
        master.identifyCard(visa);

        ArrayList<Card> creditCards = new ArrayList<>();
        NumberFormat nf = NumberFormat.getNumberInstance();
        System.out.println("Reading from csv file...");
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yy");

        ArrayList<String> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(path));) {
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                records.add(data);
            }
            for (int i = 1; i < records.size(); i++) {
                System.out.println(records.get(i));
                String[] info = records.get(i).split("\t");
                double number = nf.parse(info[0]).doubleValue();
                String cardnumber = String.format("%.0f", number);
                Date date = dateFormat.parse(info[1]);
                String name = info[2];

                creditCards.add(americanEx.check(cardnumber, date, name));

            }
            // System.out.print(records);
        } catch (FileNotFoundException | ParseException e) {
            e.printStackTrace();
        }
        return creditCards;
    }

    public boolean writeFile(ArrayList<Card> creditcard, String input_path) {
        System.out.println("Writing in CSV file.....!!!");
        File csvFile = new File(input_path + "output.csv");
        try (PrintWriter pwrite = new PrintWriter(csvFile)) {
            StringBuilder sb = new StringBuilder();
            sb.append("CardNumber");
            sb.append(",");
            sb.append("Type");
            sb.append(",");
            sb.append("ErrorMessage");
            sb.append("\n");

            for (Card cc : creditcard) {
                sb.append(cc.getNumber());
                sb.append(",");
                sb.append(cc.getcardType());
                if (!cc.getValidation()) {
                    sb.append(",");
                    sb.append("ERROR:Invalid CreditCard Number....");
                }
                sb.append("\n");
            }
            pwrite.write(sb.toString());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

}
