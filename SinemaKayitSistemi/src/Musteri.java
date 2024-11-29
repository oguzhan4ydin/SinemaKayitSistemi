import java.util.ArrayList;
import java.util.List;

public class Musteri extends BaseEntity implements IKayit {
    private String email;
    private static List<Musteri> musteriler = new ArrayList<>();

    public Musteri(int id, String name, String email) {
        super(id, name);
        this.email = email;
    }

    @Override
    public void kayitOl(Musteri musteri) {
        musteriler.add(musteri);
        System.out.println(musteri.getAd() + " adlı müşteri kaydedildi.");
    }

    @Override
    public void kayitlariListele() {
        if (musteriler.isEmpty()) {
            System.out.println("Henüz kayıtlı müşteri yok.");
        } else {
            System.out.println("Kayıtlı Müşteriler:");
            for (Musteri musteri : musteriler) {
                System.out.println("- " + musteri.getAd() + " (" + musteri.getEmail() + ")");
            }
        }
    }

    public String getEmail() {
        return email;
    }

    public String getAd() {
        return getName(); 
    }

    public void setAd(String ad) {
        setName(ad);
    }
    
    @Override
    public void bilgiGoster() {
        System.out.println("Müşteri ID: " + getId() + ", İsim: " + getAd() + ", Email: " + email);
    }
}
