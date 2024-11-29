import java.util.ArrayList;
import java.util.List;

public class Salon extends BaseEntity {
    private List<Musteri> musteriler;
    private Film film;

    public Salon(int id, String name, Film film) {
        super(id, name);
        this.musteriler = new ArrayList<>();
        this.film = film;
    }

    public List<Musteri> getMusteriler() {
        return musteriler;
    }

    public Film getFilm() {
        return film;
    }

    public void addMusteri(Musteri musteri) {
        musteriler.add(musteri);
    }

    public void setFilm(Film film) {
        this.film = film;
    }
    
    @Override
    public void bilgiGoster() {
        System.out.println("Salon ID: " + getId() + ", İsim: " + getName());
        System.out.println("Gösterimdeki Film: " + film.getAd());
        if (!musteriler.isEmpty()) {
            System.out.println("Kayıtlı Müşteriler:");
            for (Musteri musteri : musteriler) {
                System.out.println("- " + musteri.getName());
            }
        }
    }
}
