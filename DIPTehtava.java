
import java.util.Calendar;

public class DIPTehtava {

    private static BusinessLuokka businessLuokkaAutolle = DIPConfigurator.konfiguraatioAutolle();

    private static BusinessLuokka businessLuokkaMoottoripyoralle = DIPConfigurator.konfiguraatioMoottoripyorille();

    public static void main(final String[] args) {
        System.out.println("Järjestelmän autolla on " + "ajettu keskimäärin vuodessa: "
                + businessLuokkaAutolle.laskeKeskimaaraisetKilometritPerVuosi() + " kilometriä");

        System.out.println("Järjestelmän moottoripyörälla on " + "ajettu keskimäärin vuodessa: "
                + businessLuokkaMoottoripyoralle.laskeKeskimaaraisetKilometritPerVuosi() + " kilometriä");
    }
}

/**
 * Tämän konfiguraattoriluokan tehtävä on rakentaa sovelluksen konfiguraatio.
 * Jos sovelluksesta halutaan erilainen konfiguraatio, niin tarvitsee muuttaa
 * vain tätä luokkaa, ei itse sovelluksen luokkia. Sovelluksen määrittelyt
 * voitaisiin myös antaa esimerkiksi XML-tiedostossa, jonka tämä luokka sitten
 * lataisi ja käsittelisi. Esim. Springissä on vastaava luokka.
 */
class DIPConfigurator {

    public static BusinessLuokka konfiguraatioAutolle() {
        final BusinessLuokka businessLuokkaAutolle = new BusinessLuokka();
        // TODO: Konfiguroi luokka tässä järjestelmän Autolla
        Auto auto = new Auto(56000, 2018, true);
        businessLuokkaAutolle.setAjoneuvo(auto);
        return businessLuokkaAutolle;
    }

    public static BusinessLuokka konfiguraatioMoottoripyorille() {
        final BusinessLuokka businessLuokkaMoottoripyoralle = new BusinessLuokka();
        // TODO: Konfiguroi luokka tässä järjestelmän Moottoripyoralla
        Moottoripyora moottoripyora = new Moottoripyora(20500, 2015, false);
        businessLuokkaMoottoripyoralle.setAjoneuvo(moottoripyora);
        return businessLuokkaMoottoripyoralle;
    }
}

/**
 * Ajoneuvo-rajapinta
 */
interface Ajoneuvo {
    public int getAjetutKilometrit();

    public int getVuosimalli();
}

/**
 * Auto-luokka toteuttaa Ajoneuvo-rajapinnan asioita ja lisäksi sillä on
 * pelkästään Auto-tyyppiin liittyviä asioita 1 kpl.
 */
class Auto implements Ajoneuvo {
    private final int km;
    private final int vuosimalli;
    private final boolean onkoFarmari;

    public Auto(final int km, final int vuosimalli, final boolean onkoFarmari) {
        this.km = km;
        this.vuosimalli = vuosimalli;
        this.onkoFarmari = onkoFarmari;
    }

    public int getAjetutKilometrit() {
        return this.km;
    }

    public int getVuosimalli() {
        return this.vuosimalli;
    }

    public boolean onkoFarmari() {
        return this.onkoFarmari;
    }
}

/**
 * Moottoripyora-luokka toteuttaa Ajoneuvo-rajapinnan asioita ja lisäksi sillä
 * on pelkästään Moottoripyora-tyyppiin liittyviä asioita 1 kpl.
 */
class Moottoripyora implements Ajoneuvo {
    private final int km;
    private final int vuosimalli;
    private final boolean onkoKevytMoottoripyora;

    public Moottoripyora(final int km, final int vuosimalli, final boolean onkoKevytMoottoripyora) {
        this.km = km;
        this.vuosimalli = vuosimalli;
        this.onkoKevytMoottoripyora = onkoKevytMoottoripyora;
    }

    public int getAjetutKilometrit() {
        return km;
    }

    public int getVuosimalli() {
        return vuosimalli;
    }

    public boolean onkoKevytMoottoripyora() {
        return this.onkoKevytMoottoripyora;
    }
}

/**
 * Tämä luokka sisältää järjestelmän businesslogiikan, eli oikestaan sen kaikean
 * kiinnostavan järjestelmässä, mitä tuoteomistaja on meille määritellyt.
 */
class BusinessLuokka {
    private Ajoneuvo ajoneuvo;

    public int laskeKeskimaaraisetKilometritPerVuosi() {
        return ajoneuvo.getAjetutKilometrit()
                / ((Calendar.getInstance().get(Calendar.YEAR) - ajoneuvo.getVuosimalli()) + 1);
    }

    public void setAjoneuvo(final Ajoneuvo ajoneuvo) {
        this.ajoneuvo = ajoneuvo;
    }
}