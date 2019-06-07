/*
 * 
 * Klasa przechowująca wszystkie informacje dotyczące obiektów w symulacji.
 * Zawiera podstawowe metody, takie jak dodawanie obiektów(aktyw, tynków inwestorów) oraz zwracanie list zawierających dane
 */
package projekt;

import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.Collections;
import java.util.Random;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import projekt.model.Akcja;
import projekt.model.Aktywa;
import projekt.model.Fundusz;
import projekt.model.Gielda;
import projekt.model.Indeks;
import projekt.model.Inwestor;
import projekt.model.InwestorIndywidualny;
import projekt.model.Rynek;
import projekt.model.RynekSurowcow;
import projekt.model.RynekWalut;
import projekt.model.Surowiec;
import projekt.model.Symulator;
import projekt.model.Waluta;

/**
 *
 * @author Vieja
 */ 

public class Ekonomia {
    
     private ObservableList<Rynek> listaRynek = FXCollections.observableArrayList();
     private ObservableList<Gielda> listaGielda = FXCollections.observableArrayList();
     private ObservableList<Aktywa> listaAktywa = FXCollections.observableArrayList();
     private ObservableList<Akcja> listaAkcja = FXCollections.observableArrayList();
     private ObservableList<Akcja> listaSpolki = FXCollections.observableArrayList();
     private ObservableList<Akcja> listaSpolki2 = FXCollections.observableArrayList();
     private ObservableList<Indeks> listaIndeks = FXCollections.observableArrayList();
     private ObservableList<Akcja> listaIndeks_Spolka = FXCollections.observableArrayList();
     private ObservableList<Inwestor> listaInwestor = FXCollections.observableArrayList();
     private ObservableList<Fundusz> listaFundusz = FXCollections.observableArrayList();
     private ObservableList<Aktywa> listaAktywInwestora = FXCollections.observableArrayList();
     private ObservableList<Fundusz> listaJednostekInwestora = FXCollections.observableArrayList();
     private ArrayList<Thread> threadInwestorzyInd = new ArrayList<Thread>();
     private ArrayList<Thread> threadAkcjaInd = new ArrayList<Thread>();
     private ObservableList<String> listaXWykres = FXCollections.observableArrayList();
     Main main;
     Symulator symulator;
     
     public Ekonomia(Main main) {
        this.main = main;
     }
     
     public void zacznij() {
        
         symulator = new Symulator(this);
         new Thread(symulator).start();
         
         for(int i=0;i<5;i++) {
             dodajRynek(i);  
         }
         for(int i=0;i<10;i++) {  
             dodajAktywo("giełda papierów wartościowych");
             dodajAktywo("giełda papierów wartościowych");
             dodajAktywo("rynek surowców");
             dodajAktywo("rynek walut");
         }
         stworzIndeks(null);
         stworzIndeks(5);
         dodajInwestor(0);
     }

     ArrayList<String> nazwyRynkow = new ArrayList<String>(
        asList("Wall Street", "Córdoba", "La Plata","Baker Street","Po prostu rynek","I kolejny rynek","Najlepszy rynek","Townsville","Twin Peaks","Kaczogród",
                "Asgard Stock Exchange","Dziwny Pan ze Stocku","Kamil Stock","Best Financial Market","Giełda pod Kamieniem","Alibaba Exchange","Talib Market",
                "Giełda Najwyższego Axela","Jądro Ciemności","Ryneczek Lidla","Bikini Bottom Stock","Korynt Market","Ibiza Financial State","Magiczna Giełda Rolnicza",
                "Stock Stock Stock","Giełda Sknerusa McKwacza","Stockholm","Gorilla Financial Market"));
     ArrayList<String> nazwyKrajow = new ArrayList<String>(
        asList("Polska","Niemcy","Stany Zjednoczone","Argentyna","Kenia","Happy Land","Coruscant","Aargau","Mordor","Vietnam","Aksja","Axlo Land","Deep Space",
                "Arabia Saudyjska"));
     ArrayList<String> nazwyImion = new ArrayList<String>(
        asList("Axel","Artur","Jaskier","Zoltan","Dijkstra","Mateusz","Hiob","Janusz","Alibaba","Salamander","Alexander","Olek","Bolek","Krecik","Kraken","Morru",
                "Aaron","Abarca","Adin","Agen","Dack","Daimon","Dan","Darth","Data","Dexter","Syrus","Hal","Harry","Steven","Legio","Quark","Jack","Mark","Morn",
                "James","Q","Ace","Phoenix","Thor","Rick","Morty","Odyn","Bogdan","Jahwe","Jezus","Janusz","Obi-Wan","Anakin","Yoda","Korneliusz","Kazimierz"));
     ArrayList<String> nazwyNazwisk = new ArrayList<String>(
        asList("Kolonoskopia","Scorsese","Sobieski","Belfort","Maximus","Kiełbasa","Bigos","Tesla","Golec","Darwin","Yagami","Tatsumi","Khan","Bond","Welington",
                "Steel","Andromeda","Rinedick","Ser","Belfast","Vegas","Waszyngton","Mickiewicz","Barlog","Foley","Dzikor","Kaczyński","Legoń","Boruc","Lewandowski",
                "Łokietek","Śmiały","Ryder","Shephard","Kenobi","Skywalker","Zielonogórski","Kubica","Stachurski","Milowicz","Duda","Kubacki","Wajda","Niemen","Szpak",
                "Szmidt","Kovalic","Pitt","Velerad","Farrel","Cruise","DiCaprio","Biliński","Cezar","Kopiński","Czapla","Homer","Kupidyn","Baggins","Wayne","Kent","Gaben"));
     ArrayList<String> nazwySurowcow = new ArrayList<String>(
        asList("unobotanium","złoto","srebro","antymateria","drewno","deski","kamień","diament","księżycowy pył","fisstech","złoty proszek","wódka Temerska żytnia",
                "kryształy do mieczy świetlnych","stal","węgiel","ropa","żelazo","metanol"));
     ArrayList<String> nazwyWalut = new ArrayList<String>(
        asList("złoty polski","dolar","korona","yen","euro","muszelka","denar","taka","manat","gulden","dukat","kanarek","królik","szyling"));
     ArrayList<String> nazwySpolek = new ArrayList<String>(
        asList("PKO","Biedronka","Lidl","Galaktyczne Imperium","Mann Co.","Trade Federation","PKO BP","SolForce","Gong Temple","BZWBK","Cadabra","BlueBalls Boutique",
                "Fu King Restaurant","Durex","Raben","Spółka ZUO","BudomREX","H&M","Fidelio","BotLand","Neurotica","TinderTown","Sphinx","McDonalds","Kentucky Fried Chicken",
                "Columbia Pictures","Paramount Studios","Disney","Google","Apple","Microsoft","Fedex","Razer","Roccat","Facebook","CD Project","SAGA","Creative Assembly",
                "Ubisoft","EA","DICE","Life Extension","Multi National United","Resources Development Administration","Buy N Large","McDowell's","Oscorp","Rekall","Nakatomi Trading Co.",
                "Tyrell Corporation","InGen","Starfleet","Stark Industries","Wonka's","Omni Consumer Products","Monsters Inc.","Pizza Planet","Weyland-Yutani","Wayne Enterprises","Ghostbusters"));
     ArrayList<String> nazwyUlic = new ArrayList<String>(
        asList("Oliwkowa","Sowice","Świerkowa","Poznańska","Włodarzewska","Metrowa","Strzelecka","Wrocławska","Jana Olbrachta","Aleje Jerozolimskie","Owocowa","Kwacza",
                "Wiejska","Szwajcarska","Spartańska","Triumfalna","Marka Antoniusza","Berengardzka","Plecakowa","Warszawska","Chop Chop Square"));
     ArrayList<String> nazwyMiast = new ArrayList<String>(
        asList("Chrząszczyżewoszyce","Luboszów","Sielska Woda","Kleszcze","Nowe Warpno","Nowy Narkotyk","Działoszyce","Wyśmierzyce","Poznań","Warszawa","Wrocław","Waszyngton",
                "Nowy Jork","Londyn","Paryż","Berlin","Praga","Moskwa","Strajków Mały","Inowrocław","Nowa Wieś","Al Riyadh"));
     ArrayList<String> nazwyIndeks = new ArrayList<String>(
        asList("Tadawul","Merval","Bovespa","Latibex","CNX Nifty","Topix","KOSPI","Affaesvarlden","STI","RTS","TASE","FTSE","BET","PSI","SENSEI"));
     ArrayList<String> nazwyFundusz = new ArrayList<String>(
        asList("Stratton Oakmont","Avenue Fund","Aktivist","Alior Fundusz","Allianz","Arka Platinum","BGŻ BNP Paribas","Credit Agricole","Eques Creditum","InValue","Investor Gold",
                "MetLife","Millenium","Murapol","Neutral","Open Finance Absolute","Optimum","Superfund","Trigon Profit","UniAkcje","Venture"));
     ArrayList<String> nazwyJednostek = new ArrayList<String>(
        asList("uncja","tona","baryłka","funt","galon","kilogram","buszel","korzec","cetnar","hangar"));
     
    public ObservableList<Rynek> dodajRynek (Integer jaki){
         Random generator = new Random();
         if (jaki == null) jaki=generator.nextInt(5);
         int nazwa = generator.nextInt(nazwyRynkow.size());
         int kraj = generator.nextInt(nazwyKrajow.size());
         int waluta = generator.nextInt(nazwyWalut.size());
         int miasto = generator.nextInt(nazwyMiast.size());
         int adres = generator.nextInt(nazwyUlic.size());
         double marza = round (generator.nextInt(10) + generator.nextDouble());
         if (jaki == 0){
            RynekWalut rynek = new RynekWalut ();
            rynek.setNazwa(nazwyRynkow.get(nazwa));
            rynek.setKraj(nazwyKrajow.get(kraj));
            rynek.setWaluta(nazwyWalut.get(waluta));
            rynek.setMiasto(nazwyMiast.get(miasto));
            rynek.setAdres(nazwyUlic.get(adres));
            rynek.setMarza(marza);
            listaRynek.add(rynek);
         } else if(jaki == 1) {
            RynekSurowcow rynek = new RynekSurowcow ();
            rynek.setNazwa(nazwyRynkow.get(nazwa));
            rynek.setKraj(nazwyKrajow.get(kraj));
            rynek.setWaluta(nazwyWalut.get(waluta));
            rynek.setMiasto(nazwyMiast.get(miasto));
            rynek.setAdres(nazwyUlic.get(adres));
            rynek.setMarza(marza);
            listaRynek.add(rynek);
         } else {
            Gielda rynek = new Gielda ();
            rynek.setNazwa(nazwyRynkow.get(nazwa));
            rynek.setKraj(nazwyKrajow.get(kraj));
            rynek.setWaluta(nazwyWalut.get(waluta));
            rynek.setMiasto(nazwyMiast.get(miasto));
            rynek.setAdres(nazwyUlic.get(adres));
            rynek.setMarza(marza);
            listaRynek.add(rynek);
            listaGielda.add(rynek);
         }
         return listaRynek;
    }
    public ObservableList<Aktywa> dodajAktywo (String aktywo){
         Random generator = new Random();
         int nazwa;
         double kurs = round(generator.nextInt(110)+10 + generator.nextDouble());
         int rynek;
         if (aktywo == "giełda papierów wartościowych"){
            nazwa = generator.nextInt(nazwySpolek.size());
            rynek = generator.nextInt(listaGielda.size());   
            Akcja nowy = new Akcja ();
            nowy.setNazwa(nazwySpolek.get(nazwa));
            nowy.setKurs(kurs);
            nowy.setRynek(listaGielda.get(rynek));
             //System.out.println(this.getDay());
            nowy.setData(Integer.toString(this.getDay()));
            nowy.setKursOtwarcia(kurs);
            nowy.setMin(kurs);
            nowy.setMax(kurs);
            nowy.setZysk(0.0);
            nowy.setPrzychod(0.0);
            nowy.setKapitalWlasny(round(generator.nextInt(50000)+20000 + generator.nextDouble()));
            nowy.setKapitalZakladowy(round(nowy.getKapitalWlasny()+generator.nextInt(50000) + generator.nextDouble()));
            nowy.setWolumen(0);
            nowy.setObroty(round(generator.nextInt(20) + generator.nextDouble()));
            nowy.czyscHistoria();
            listaAktywa.add(nowy);
            listaAkcja.add(nowy);
            threadAkcjaInd.add(new Thread(nowy));
            threadAkcjaInd.get(threadAkcjaInd.size()-1).start();
         } else {
         String typ;
         do{
            rynek = generator.nextInt(listaRynek.size());   
            typ = listaRynek.get(rynek).getTyp();
            } while(typ!=aktywo);
         if(aktywo == "rynek surowców") {
            nazwa = generator.nextInt(nazwySurowcow.size());
            int jednostka = generator.nextInt(nazwyJednostek.size());
            int waluta = generator.nextInt(nazwyWalut.size());
            Surowiec nowy = new Surowiec ();
            nowy.setNazwa(nazwySurowcow.get(nazwa));
            nowy.setRynek(listaRynek.get(rynek));
            nowy.setKurs(kurs);
            nowy.setMin(kurs);
            nowy.setMax(kurs);
            nowy.setJednostka(nazwyJednostek.get(jednostka));
            nowy.setWaluta(nazwyWalut.get(waluta));
            nowy.czyscHistoria();
            listaAktywa.add(nowy);
         } else {
            nazwa = generator.nextInt(nazwyWalut.size());
            Waluta nowy = new Waluta ();
            nowy.setNazwa(nazwyWalut.get(nazwa));
            nowy.setRynek(listaRynek.get(rynek));
            nowy.setKurs(kurs);
            nowy.czyscHistoria();
            listaAktywa.add(nowy);
                }
            }
         return listaAktywa;
        }
    
    public void usunAktywaUsuwanegoRynku(Integer indeks){
        int ilosc = listaAktywa.size();
        Rynek usuwany = listaRynek.get(indeks).getRynek();
        Rynek rynek;
        for (int i=0;i<ilosc;i++) {
            rynek = listaAktywa.get(i).getRynek();
            if (rynek == usuwany) {
                System.out.println("Usuwam aktywo z rynku " + listaAktywa.get(i).getNazwaRynek());
                listaAktywa.remove(i);
                i--;
                ilosc--; 
            }
        }
        for (int i=0;i<ilosc;i++) {
            rynek = listaAkcja.get(i).getRynek();
            if (rynek == usuwany) {
                System.out.println("Usuwam akcje z rynku " + listaAkcja.get(i).getNazwaRynek());
                listaAkcja.remove(i);
                i--;
                ilosc--; 
            }
        }        
        ilosc = listaAkcja.size();
        
        if (listaRynek.get(indeks).getTyp() == "giełda papierów wartościowych")
        {
            Gielda gielda = (Gielda) listaRynek.get(indeks).getRynek();
            Gielda rynek2;
            ilosc = listaGielda.size();
            for (int i = 0; i < ilosc; i++) {
                rynek2 = (Gielda) listaGielda.get(i).getRynek();
                if (rynek2 == gielda) listaGielda.remove(i);
            }
        }
    }
        
        public ObservableList<Akcja> getSpolki(Gielda gielda,Integer liczba) {
            if (liczba == 1) {
            listaSpolki.clear();
            int ilosc = listaAktywa.size();
            Gielda sprawdzaj;
            String typ;
            for (int i=0;i<ilosc;i++) {
                typ = listaAktywa.get(i).getTyp();
                if (typ == "akcja") {
                sprawdzaj = (Gielda) listaAktywa.get(i).getRynek();
                if (sprawdzaj == gielda) {
                        listaSpolki.add( (Akcja) listaAktywa.get(i));
                    }
                }
            }
            return listaSpolki;
            } else {
            listaSpolki2.clear();
            int ilosc = listaAktywa.size();
            Gielda sprawdzaj;
            String typ;
            for (int i=0;i<ilosc;i++) {
                typ = listaAktywa.get(i).getTyp();
                if (typ == "akcja") {
                sprawdzaj = (Gielda) listaAktywa.get(i).getRynek();
                if (sprawdzaj == gielda) {
                        listaSpolki2.add( (Akcja) listaAktywa.get(i));
                    }
                }
            }
            return listaSpolki2; 
            }    
        }
        
         public ObservableList<Akcja> getSpolkiIndeksu(Indeks indeks) {
             listaIndeks_Spolka.clear();
             int liczba = indeks.getListaSpolek().size();
             Akcja spolka;
             for(int i=0;i<liczba;i++) {
                 spolka = indeks.getSpolka(i);
                 listaIndeks_Spolka.add(spolka);
             }
             return listaIndeks_Spolka;
         }
         
         public ObservableList<Aktywa> getListaAktywInwestora(Inwestor inwestor) {
             listaAktywInwestora.clear();
             int liczba = inwestor.getlistaAktywa().size();
             for (int i=0;i<liczba;i++) {
                 listaAktywInwestora.add(i,inwestor.getlistaAktywa().get(i));
             }
             return listaAktywInwestora;
         }
         
         public ObservableList<Fundusz> getListaJednostekInwestora(Inwestor inwestor) {
             listaJednostekInwestora.clear();
             int liczba = inwestor.getlistaJednostek().size();
             for (int i=0;i<liczba;i++) {
                 listaJednostekInwestora.add(i,inwestor.getlistaJednostek().get(i));
             }
             return listaJednostekInwestora;
         }
        
      
        public ObservableList<Indeks> stworzIndeks(Integer jaki) {
            Random generator = new Random();
            int los = generator.nextInt(nazwyIndeks.size());
            Gielda gielda;
            int los2;
            int k = 0;
            if (jaki == null) {
            do {
                los2 = generator.nextInt(listaGielda.size());
                gielda = listaGielda.get(los2);
                getSpolki(gielda,2);
                k++;
               } while (k < 25 && listaSpolki2.size() < 3);
                Indeks indeks = new Indeks();
            // INDEKS STWORZONY, TERAZ TRZEBA WYLOSOWAC SPOLKI
            do {
                los2 = generator.nextInt(listaSpolki2.size());
            } while ( los2 < 2 ); // INDEKS MA CO NAJMNIEJ DWIE SPÓŁKI
            boolean test = false;
            System.out.println(los2);
            for (int i=0;i<listaSpolki2.size();i++) {
                System.out.println(listaSpolki2.get(i).getNazwa());
            }
            Akcja losowa;
            for (int i=0;i<los2;i++) {
                do {
                    int los3 = generator.nextInt(listaSpolki2.size());
                    System.out.println("los3: "+los3);
                    losowa = listaSpolki2.get(los3);
                    System.out.println(losowa.getNazwa());
                    test = indeks.sprawdzCzyNowa(losowa);
                } while (test == false);
                indeks.addListaSpolek(losowa);
            }
            indeks.setNazwa(nazwyIndeks.get(los));
            indeks.setGielda(gielda);
            indeks.setTyp("los");
            indeks.setWig(0);
            listaIndeks.add(indeks);
            return listaIndeks;
            } else {
            do {
                los2 = generator.nextInt(listaGielda.size());
                gielda = listaGielda.get(los2);
                getSpolki(gielda,2);
                k++;
               } while (k < 25 && listaSpolki2.size() < jaki+1);
            if (k!=25) {
            ArrayList<Double> temp = new ArrayList<Double>();
            for (int i=0;i<listaSpolki2.size();i++) {
                temp.add(listaSpolki2.get(i).getObroty());
            }
            
            Collections.sort(temp);
            Indeks indeks = new Indeks();
            for (int i=0;i<jaki;i++) {
                for (int j=0;j<listaSpolki2.size();j++) {
                    if (listaSpolki2.get(j).getObroty() == temp.get(i))
                    indeks.addListaSpolek(listaSpolki2.get(j));
                }
            }      
            indeks.setNazwa(nazwyIndeks.get(los));
            indeks.setGielda(gielda);
            indeks.setTyp("WIG");
            indeks.setWig(jaki);
            listaIndeks.add(indeks);
            }
            return listaIndeks;
            }
        }
        // MUSI ZWRACAĆ OBSERVABLE LIST ŻEBY BUTTON ZADZIAŁAŁ
        public ObservableList<Inwestor> dodajInwestor(Integer jaki) {
            Random generator = new Random();
            int los;
            if (jaki == 0) {
                InwestorIndywidualny nowy = new InwestorIndywidualny();
                los = generator.nextInt(nazwyImion.size());
                nowy.setImie(nazwyImion.get(los));
                los = generator.nextInt(nazwyNazwisk.size());
                nowy.setNazwa(nazwyNazwisk.get(los));
                
                double liczba = round (generator.nextInt(50000)+1000 + generator.nextDouble());
                nowy.setBudzet(liczba);
                
                int pesel=0;
                for (int i=1;i<1000000000;i*=10)
                {
                    los = generator.nextInt(10);
                    pesel+=los*i;
                }
                nowy.setPesel(pesel);
                listaInwestor.add(nowy);
                threadInwestorzyInd.add(new Thread(nowy));
                threadInwestorzyInd.get(threadInwestorzyInd.size()-1).start();
                
                return listaInwestor;
                
            } else {
                Fundusz nowy = new Fundusz();
                los = generator.nextInt(nazwyImion.size());
                nowy.setImie(nazwyImion.get(los));
                los = generator.nextInt(nazwyNazwisk.size());
                nowy.setNazwisko(nazwyNazwisk.get(los));
                los = generator.nextInt(nazwyFundusz.size());
                nowy.setNazwa(nazwyFundusz.get(los));
                
                double liczba = round (generator.nextInt(50000)+1000 + generator.nextDouble());
                nowy.setBudzet(liczba);
                
                listaInwestor.add(nowy);
                listaFundusz.add(nowy);
                threadInwestorzyInd.add(new Thread(nowy));
                threadInwestorzyInd.get(threadInwestorzyInd.size()-1).start();
                
                return listaInwestor;
            }
        }
        
    public Integer getDay () {
        int aktualny = symulator.getSimulationDay();
        return aktualny;
    }       
        
    public static double round(double f)
   {  double temp = (double)(f*(Math.pow(10,2)));
          temp = (Math.round(temp));
          temp = temp/(int)(Math.pow(10,2));
           System.out.println("temp: "+temp);
          return temp;

   }
     
     public ObservableList<Rynek> getlistaRynek() {
        return listaRynek;
    }
     public ObservableList<Gielda> getlistaGielda() {
        return listaGielda;
    }
     public ObservableList<Aktywa> getlistaAktywa() {
        return listaAktywa;
     }
     public ObservableList<Akcja> getlistaAkcja() {
        return listaAkcja;
     }
     public ObservableList<Akcja> getlistaSpolki() {
        return listaSpolki;
     }
     public ObservableList<Indeks> getlistaIndeks() {
        return listaIndeks;
     }
     public ObservableList<Akcja> getlistaIndeks_Spolka() {
        return listaIndeks_Spolka;
     }
     public ObservableList<Inwestor> getlistaInwestor() {
        return listaInwestor;
     }
     public ObservableList<Fundusz> getlistaFundusz() {
        return listaFundusz;
     }
     public ObservableList<Aktywa> getlistaAktywInwestora() {
        return listaAktywInwestora;
     }
     public ObservableList<Fundusz> getlistaJednostekInwestora() {
        return listaJednostekInwestora;
     }  
}