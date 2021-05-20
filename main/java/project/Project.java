package project;

import card.*;
import Files.*;

import java.util.*;

public class Project {
    public static void main(String args[]) {
        ArrayList<Card> creditcard;
        Format identifyfile;


        String filename = args[0];

        identifyfile = Input.inputFile(filename);

        if (identifyfile != null) {
            creditcard = identifyfile.readFile("/Users/helipatel/Desktop/project/" + filename);
            System.out.println("File successfully open.....!!!!");
            if (identifyfile.writeFile(creditcard, "/Users/helipatel/Desktop/project/")) {
                System.out.println("File Successfully created.....!!!");
            }
        } else {
            System.out.println("File not found");
        }
        }

}

