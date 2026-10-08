import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Relation city = new CSVLoader("src/city.csv").loadCSVinRelation();
        Relation country = new CSVLoader("src/country.csv").loadCSVinRelation();
        Relation language = new CSVLoader("src/countrylanguage.csv").loadCSVinRelation();

        System.out.println("1. Nazioni europee");
        trovaNazioniPerContinente(country, "Europe");

        System.out.println("2. Citta della Francia");
        listaCittaPerNazione(city, "FRA");

        System.out.println("3. Nazioni con popolazione tra 100M e 200M");
        popolazioneInRange(country, 100000000, 200000000);

        System.out.println("4. Stati del Sud America con capitale");
        capitaliDelleNazioni(country.selection("Continent", "South America"), city);

        System.out.println("5. Nazioni asiatiche con popolazione maggiore del Giappone");
        Relation asia = country.selection("Continent", "Asia");
        nazioniPiuPopoloseDi(asia, country, "Japan");

        System.out.println("6. Italia: citta piu e meno popolosa");
        Relation italia = city.selection("CountryCode", "ITA");

        System.out.println(maxPopolazione(italia, "Population"));
        System.out.println(minPopolazione(italia, "Population"));

        System.out.println("7. Inglese ma non Francese");
        nazioniCheParlanoLinguaMaNonAltra(country, language, "English", "French");

    }



    public static void trovaNazioniPerContinente(Relation r, String continente) {
        System.out.println(r.selection("Continent", continente));
    }



    public static void listaCittaPerNazione(Relation r, String codiceNazione) {

        ArrayList<String> campi = new ArrayList<>();

        campi.add("Name");
        campi.add("CountryCode");

        System.out.println(r.selection("CountryCode", codiceNazione).projection(campi));

    }



    public static void popolazioneInRange(Relation r, int min, int max) {
        ArrayList<String> campi = new ArrayList<>();
        campi.add("Name");
        System.out.println(r.selectionBetween("Population", min, max).projection(campi));

    }



    public static void capitaliDelleNazioni(Relation country, Relation city) {
        Relation city2 = city.renomination("ID", "Capital");
        Relation join = country.join(city2, new String[]{"Capital", "Capital"});
        System.out.println(join);

    }



    public static void nazioniPiuPopoloseDi(
            Relation insiemeNazioni,
            Relation tutteLeNazioni,
            String nomeNazione) {

        Relation nazione = tutteLeNazioni.selection("Name", nomeNazione);

        int indice = tutteLeNazioni.getHeader().indexOf("Population");
        int popolazione = Integer.parseInt(nazione.getRows().get(0).getValue(indice));
        System.out.println(insiemeNazioni.selectionGreaterThan("Population", popolazione));

    }
    public static Row maxPopolazione(Relation r, String attributo) {
        return r.max(attributo);
    }



    public static Row minPopolazione(Relation r, String attributo) {
        return r.min(attributo);
    }



    public static void nazioniCheParlanoLinguaMaNonAltra(Relation country, Relation language, String lingua1, String lingua2) {
        Relation l1 = language.selection("Language", lingua1);
        Relation l2 = language.selection("Language", lingua2);

        ArrayList<String> campi = new ArrayList<>();
        campi.add("CountryCode");

        l1 = l1.projection(campi);
        l2 = l2.projection(campi);

        Relation diff = l1.difference(l2);

        diff = diff.renomination("CountryCode", "Code");

        Relation join = diff.join(country,new String[]{"Code", "Code"});

        ArrayList<String> risultato = new ArrayList<>();
        risultato.add("Name");

        System.out.println(join.projection(risultato));

    }

}