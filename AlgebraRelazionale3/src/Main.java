import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        CSVLoader loader1 = new CSVLoader("src/city.csv");
        CSVLoader loader2 = new CSVLoader("src/country.csv");
        CSVLoader loader3 = new CSVLoader("src/countryLanguage.csv");

        Relation r1 = loader1.loadCSVinRelation();
        Relation r2 = loader2.loadCSVinRelation();
        Relation r3 = loader3.loadCSVinRelation();

        System.out.println("1. trova tutte le nazioni Europee: ");
        trovaNazioniPerContinente(r2, "Europe");

        System.out.println("2. trova tutte le città della Francia: ");
        listaCittaPerNazione(r1, "FRA");

        System.out.println("2. trova il nome delle nazioni che hanno una popolazione compresa tra 100 milioni e 200 milioni di abitanti: ");
        nomeNazioneconRangeAbitanti(r2, 100000000, 200000000);
    }

    public static void trovaNazioniPerContinente(Relation r, String continente){
        System.out.println(r.selection("Continent",continente));
    }

    public static void listaCittaPerNazione(Relation r, String codiceNazione){
        ArrayList<String> cittaPerNazione = new ArrayList<>();
        cittaPerNazione.add("Name");
        cittaPerNazione.add("CountryCode");
        System.out.println(r.projection(cittaPerNazione).selection("CountryCode", codiceNazione).toString());
    }

    public static void nomeNazioneconRangeAbitanti(Relation r, int num1, int num2){
        ArrayList<String> cittaPerNazione = new ArrayList<>();
        cittaPerNazione.add("Name");
        Relation ris = new Relation();
        ris = r.selection("Population", "> " + num1).selection("Population", "< " + num2).projection(cittaPerNazione);


        System.out.println(ris.toString());
    }




}
