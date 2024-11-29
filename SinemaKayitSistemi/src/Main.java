import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Film> filmler = new ArrayList<>();
        List<Salon> salonlar = new ArrayList<>();
        List<Musteri> musteriler = new ArrayList<>();

        // 5 adet salon otomatik ekleniyor
        for (int i = 1; i <= 5; i++) {
            Salon salon = new Salon(i, "Salon " + i, null);
            salonlar.add(salon);
        }

        while (true) {
            // menu
            System.out.println("\n--- Sinema Müşteri Kayıt Sistemi ---");
            System.out.println("1- Yeni Film Tanımla ve Salona Ata");
            System.out.println("2- Yeni Müşteri Ekle");
            System.out.println("3- Filmleri Listele");
            System.out.println("4- Salonları Listele ve Detay Görüntüle");
            System.out.println("5- Çıkış");
            System.out.print("Seçiminizi yapın: ");
            
            int secim = 0;
            boolean valid = false;
            while (!valid) {
                try {
                    secim = Integer.parseInt(scanner.nextLine());
                    valid = true; 
                } catch (NumberFormatException e) {
                    System.out.println("Geçersiz değer. Lütfen bir sayı girin.");
                    System.out.print("Seçiminizi yapın: ");
                }
            }

            switch (secim) {
                case 1:
                    System.out.print("Film adı: ");
                    String filmAdi = scanner.nextLine();
                    System.out.print("Film süresi (dk): ");
                    int sure = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Film türü: ");
                    String filmTuru = scanner.nextLine();

                    Film yeniFilm = new Film(filmAdi, sure, filmTuru);
                    filmler.add(yeniFilm);
                    System.out.println("Film başarıyla eklendi: " + yeniFilm.getAd());

                    System.out.println("Bu filmi hangi salona atamak istiyorsunuz? (Numara seçin)");
                    for (Salon salon : salonlar) {
                        if (salon.getFilm() == null) {
                            System.out.println("Salon No: " + salon.getId());
                        }
                    }

                    int salonSecim = 0;
                    valid = false;
                    while (!valid) {
                        try {
                            salonSecim = Integer.parseInt(scanner.nextLine());
                            valid = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Geçersiz değer. Lütfen bir sayı girin.");
                        }
                    }

                    Salon secilenSalon = null;
                    for (Salon salon : salonlar) {
                        if (salon.getId() == salonSecim && salon.getFilm() == null) {
                            secilenSalon = salon;
                            break;
                        }
                    }

                    if (secilenSalon == null) {
                        System.out.println("Geçersiz veya zaten dolu bir salon seçtiniz.");
                        break;
                    }

                    secilenSalon.setFilm(yeniFilm);
                    yeniFilm.addSalon(secilenSalon);
                    System.out.println("Film " + secilenSalon.getId() + " numaralı salona başarıyla atandı.");
                    break;

                case 2:
                    System.out.print("Müşteri adı: ");
                    String musteriAdi = scanner.nextLine();
                    System.out.print("Müşteri email: ");
                    String musteriEmail = scanner.nextLine();

                    System.out.println("Hangi salona kayıt olacak? (Numara seçin)");
                    for (Salon salon : salonlar) {
                        if (salon.getFilm() != null) {
                            System.out.println("Salon No: " + salon.getId());
                        }
                    }

                    int musteriSalonSecim = 0;
                    valid = false;
                    while (!valid) {
                        try {
                            musteriSalonSecim = Integer.parseInt(scanner.nextLine());
                            valid = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Geçersiz değer. Lütfen bir sayı girin.");
                        }
                    }

                    Salon musteriSecilenSalon = null;
                    for (Salon salon : salonlar) {
                        if (salon.getId() == musteriSalonSecim && salon.getFilm() != null) {
                            musteriSecilenSalon = salon;
                            break;
                        }
                    }

                    if (musteriSecilenSalon == null) {
                        System.out.println("Geçersiz bir salon seçtiniz.");
                        break;
                    }

                    Musteri yeniMusteri = new Musteri(musteriler.size() + 1, musteriAdi, musteriEmail);
                    musteriler.add(yeniMusteri);
                    musteriSecilenSalon.addMusteri(yeniMusteri);

                    System.out.println("Müşteri başarıyla " + musteriSecilenSalon.getId() + " numaralı salona eklendi.");
                    break;

                case 3:
                    if (filmler.isEmpty()) {
                        System.out.println("Henüz eklenmiş bir film yok.");
                    } else {
                        System.out.println("Sistemdeki Filmler:");
                        for (Film film : filmler) {
                            film.bilgiGoster();
                        }
                    }
                    break;

                case 4:
                    System.out.println("Mevcut Salonlar:");
                    for (Salon salon : salonlar) {
                        System.out.println("Salon No: " + salon.getId() +
                                (salon.getFilm() != null ? ", Film: " + salon.getFilm().getAd() : ", Film: Yok"));
                    }

                    System.out.print("Kayıtlı müşterileri seçmek için salon numarası seçin: ");
                    int salonNumarasi = 0;
                    valid = false;
                    while (!valid) {
                        try {
                            salonNumarasi = Integer.parseInt(scanner.nextLine());
                            valid = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Geçersiz değer. Lütfen bir sayı girin.");
                        }
                    }

                    Salon bulunanSalon = null;
                    for (Salon salon : salonlar) {
                        if (salon.getId() == salonNumarasi) {
                            bulunanSalon = salon;
                            break;
                        }
                    }

                    if (bulunanSalon == null) {
                        System.out.println("Böyle bir salon yok.");
                    } else {
                        System.out.println("Salon No: " + bulunanSalon.getId());
                        if (bulunanSalon.getFilm() != null) {
                            System.out.println("Oynatılan Film: " + bulunanSalon.getFilm().getAd());
                            System.out.println("Bu filme gidecek müşteriler:");
                            for (Musteri musteri : bulunanSalon.getMusteriler()) {
                                System.out.println("- " + musteri.getAd() + " (" + musteri.getEmail() + ")");
                            }
                        } else {
                            System.out.println("Bu salonda film oynatılmıyor.");
                        }
                    }
                    break;

                case 5:
                    System.out.println("Sistemden çıkılıyor...");
                    return;

                default:
                    System.out.println("Geçersiz seçim. Tekrar deneyin.");
            }
        }
    }
}
