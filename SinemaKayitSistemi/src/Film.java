import java.util.ArrayList;
import java.util.List;

public class Film {
    private String ad;
    private int sure;
    private String tur;
    private List<Salon> salonlar;

    public Film(String ad, int sure, String tur) {
        this.ad = ad;
        this.sure = sure;
        this.tur = tur;
        this.salonlar = new ArrayList<>();
    }

    public String getAd() {
        return ad;
    }

    public int getSure() {
        return sure;
    }

    public String getTur() {
        return tur;
    }

    public List<Salon> getSalonlar() {
        return salonlar;
    }

    public void addSalon(Salon salon) {
        salonlar.add(salon);
    }

    public void bilgiGoster() {
        System.out.println("Film: " + ad + ", Süre: " + sure + " dk, Tür: " + tur);
        if (!salonlar.isEmpty()) {
            System.out.println("Gösterim Yapılan Salonlar:");
            for (Salon salon : salonlar) {
                System.out.println("- " + salon.getName());
            }
        }
    }
}
