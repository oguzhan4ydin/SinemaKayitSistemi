import java.io.*;
import java.util.Scanner;

public class SinemaKayitSistemi {

    static final int MAX_FILM = 10;
    static final int MAX_MUSTERI = 20;

    static String[] filmAdlari = new String[MAX_FILM];
    static int[] filmSureleri = new int[MAX_FILM];
    static String[] filmTurleri = new String[MAX_FILM];
    static int filmSayisi = 0;

    static String[] musteriAdlari = new String[MAX_MUSTERI];
    static String[] musteriEmailleri = new String[MAX_MUSTERI];
    static int musteriSayisi = 0;

    static boolean[][] biletler = new boolean[MAX_MUSTERI][MAX_FILM];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        dosyadanYukle(); // Başlangıçta kayıtları oku
        int secim;

        do {
            System.out.println("\n--- Sinema Müşteri Kayıt Sistemi ---");
            System.out.println("1. Film Ekle");
            System.out.println("2. Müşteri Ekle");
            System.out.println("3. Bilet Kaydı");
            System.out.println("4. Kayıtları Listele");
            System.out.println("5. Film Sil");
            System.out.println("6. Müşteri Sil");
            System.out.println("7. Film Ara");
            System.out.println("8. Müşteri Ara");
            System.out.println("9. Verileri Şimdi Kaydet"); // Yeni seçenek
            System.out.println("0. Çıkış");
            System.out.print("Seçiminiz: ");
            secim = scanner.nextInt();
            scanner.nextLine();

            switch (secim) {
                case 1 -> filmEkle(scanner);
                case 2 -> musteriEkle(scanner);
                case 3 -> biletKaydi(scanner);
                case 4 -> kayitlariListele();
                case 5 -> filmSil(scanner);
                case 6 -> musteriSil(scanner);
                case 7 -> filmAra(scanner);
                case 8 -> musteriAra(scanner);
                case 9 -> {  // Yeni seçenek için case eklenmiş
                    dosyayaKaydet();
                    System.out.println("Veriler başarıyla kaydedildi.");
                }
                case 0 -> {
                    dosyayaKaydet();
                    System.out.println("Veriler kaydedildi. Çıkılıyor...");
                }
                default -> System.out.println("Geçersiz seçim.");
            }

        } while (secim != 0);

        scanner.close();
    }

    static void filmEkle(Scanner scanner) {
        if (filmSayisi >= MAX_FILM) {
            System.out.println("Maksimum film sayısına ulaşıldı.");
            return;
        }

        System.out.print("Film adı: ");
        filmAdlari[filmSayisi] = scanner.nextLine();

        System.out.print("Film süresi (dk): ");
        filmSureleri[filmSayisi] = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Film türü: ");
        filmTurleri[filmSayisi] = scanner.nextLine();

        filmSayisi++;
        System.out.println("Film eklendi.");
    }

    static void musteriEkle(Scanner scanner) {
        if (musteriSayisi >= MAX_MUSTERI) {
            System.out.println("Maksimum müşteri sayısına ulaşıldı.");
            return;
        }

        System.out.print("Müşteri adı: ");
        musteriAdlari[musteriSayisi] = scanner.nextLine();

        System.out.print("Email: ");
        musteriEmailleri[musteriSayisi] = scanner.nextLine();

        musteriSayisi++;
        System.out.println("Müşteri eklendi.");
    }

    static void biletKaydi(Scanner scanner) {
        if (musteriSayisi == 0 || filmSayisi == 0) {
            System.out.println("Önce müşteri ve film eklemelisiniz.");
            return;
        }

        System.out.println("Müşteri seçin:");
        for (int i = 0; i < musteriSayisi; i++) {
            System.out.println(i + " - " + musteriAdlari[i]);
        }
        int musteriIndex = scanner.nextInt();

        System.out.println("Film seçin:");
        for (int i = 0; i < filmSayisi; i++) {
            System.out.println(i + " - " + filmAdlari[i]);
        }
        int filmIndex = scanner.nextInt();

        if (musteriIndex < musteriSayisi && filmIndex < filmSayisi) {
            biletler[musteriIndex][filmIndex] = true;
            System.out.println("Bilet kaydı yapıldı.");
        } else {
            System.out.println("Geçersiz müşteri veya film seçimi.");
        }
    }

    static void kayitlariListele() {
        for (int i = 0; i < musteriSayisi; i++) {
            System.out.println("\nMüşteri: " + musteriAdlari[i] + " (" + musteriEmailleri[i] + ")");
            System.out.print("Filmler: ");
            boolean filmVar = false;
            for (int j = 0; j < filmSayisi; j++) {
                if (biletler[i][j]) {
                    System.out.print(filmAdlari[j] + ", ");
                    filmVar = true;
                }
            }
            if (!filmVar) System.out.print("Yok");
            System.out.println();
        }
    }

    static void filmSil(Scanner scanner) {
        System.out.println("Silinecek filmi seçin:");
        for (int i = 0; i < filmSayisi; i++) {
            System.out.println(i + " - " + filmAdlari[i]);
        }
        int index = scanner.nextInt();

        if (index < 0 || index >= filmSayisi) {
            System.out.println("Geçersiz seçim.");
            return;
        }

        for (int i = index; i < filmSayisi - 1; i++) {
            filmAdlari[i] = filmAdlari[i + 1];
            filmSureleri[i] = filmSureleri[i + 1];
            filmTurleri[i] = filmTurleri[i + 1];
        }
        for (int i = 0; i < musteriSayisi; i++) {
            for (int j = index; j < filmSayisi - 1; j++) {
                biletler[i][j] = biletler[i][j + 1];
            }
        }
        filmSayisi--;
        System.out.println("Film silindi.");
    }

    static void musteriSil(Scanner scanner) {
        System.out.println("Silinecek müşteriyi seçin:");
        for (int i = 0; i < musteriSayisi; i++) {
            System.out.println(i + " - " + musteriAdlari[i]);
        }
        int index = scanner.nextInt();

        if (index < 0 || index >= musteriSayisi) {
            System.out.println("Geçersiz seçim.");
            return;
        }

        for (int i = index; i < musteriSayisi - 1; i++) {
            musteriAdlari[i] = musteriAdlari[i + 1];
            musteriEmailleri[i] = musteriEmailleri[i + 1];
            biletler[i] = biletler[i + 1];
        }
        musteriSayisi--;
        System.out.println("Müşteri silindi.");
    }

    static void filmAra(Scanner scanner) {
        System.out.print("Aranacak film adı: ");
        String arama = scanner.nextLine().toLowerCase();
        boolean bulundu = false;
        for (int i = 0; i < filmSayisi; i++) {
            if (filmAdlari[i].toLowerCase().contains(arama)) {
                System.out.println(i + " - " + filmAdlari[i] + " (" + filmTurleri[i] + ", " + filmSureleri[i] + " dk)");
                bulundu = true;
            }
        }
        if (!bulundu) {
            System.out.println("Eşleşen film bulunamadı.");
        }
    }

    static void musteriAra(Scanner scanner) {
        System.out.print("Aranacak müşteri adı: ");
        String arama = scanner.nextLine().toLowerCase();
        boolean bulundu = false;
        for (int i = 0; i < musteriSayisi; i++) {
            if (musteriAdlari[i].toLowerCase().contains(arama)) {
                System.out.println(i + " - " + musteriAdlari[i] + " (" + musteriEmailleri[i] + ")");
                bulundu = true;
            }
        }
        if (!bulundu) {
            System.out.println("Eşleşen müşteri bulunamadı.");
        }
    }

    static void dosyayaKaydet() {
        try (PrintWriter writer = new PrintWriter("veriler.txt")) {
            writer.println(filmSayisi);
            for (int i = 0; i < filmSayisi; i++) {
                writer.println(filmAdlari[i]);
                writer.println(filmSureleri[i]);
                writer.println(filmTurleri[i]);
            }

            writer.println(musteriSayisi);
            for (int i = 0; i < musteriSayisi; i++) {
                writer.println(musteriAdlari[i]);
                writer.println(musteriEmailleri[i]);
            }

            for (int i = 0; i < musteriSayisi; i++) {
                for (int j = 0; j < filmSayisi; j++) {
                    writer.print(biletler[i][j] ? "1" : "0");
                }
                writer.println();
            }

        } catch (IOException e) {
            System.out.println("Dosya yazma hatası: " + e.getMessage());
        }
    }

    static void dosyadanYukle() {
        try (BufferedReader reader = new BufferedReader(new FileReader("veriler.txt"))) {
            filmSayisi = Integer.parseInt(reader.readLine());
            for (int i = 0; i < filmSayisi; i++) {
                filmAdlari[i] = reader.readLine();
                filmSureleri[i] = Integer.parseInt(reader.readLine());
                filmTurleri[i] = reader.readLine();
            }

            musteriSayisi = Integer.parseInt(reader.readLine());
            for (int i = 0; i < musteriSayisi; i++) {
                musteriAdlari[i] = reader.readLine();
                musteriEmailleri[i] = reader.readLine();
            }

            for (int i = 0; i < musteriSayisi; i++) {
                String satir = reader.readLine();
                for (int j = 0; j < satir.length(); j++) {
                    biletler[i][j] = satir.charAt(j) == '1';
                }
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Veri dosyası bulunamadı veya hatalı. Yeni sistem başlatılıyor.");
        }
    }
}