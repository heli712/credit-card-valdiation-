package Files;

import card.*;
import java.util.*;

public abstract class Format {
    public abstract ArrayList<Card> readFile(String path) ;
    public abstract boolean writeFile(ArrayList<Card> creditcard, String input_path);
   
}
