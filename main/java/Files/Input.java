package Files;



public class Input {
    public static Format inputFile(String fileType) {
        if(fileType.contains("csv")) {
            return new CsvFile() ;
        }
        else if(fileType.contains("json")) {
            return new JsonFile();
        }
        else if(fileType.contains("xml")) {
            return new XmlFile();
        }
        else {
            return null;
        }
    }
}
