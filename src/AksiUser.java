import java.util.Map;

public class AksiUser extends Aksi {
    @Override
    public void tampilanAksi() {
        System.out.println("Aksi User:");
        System.out.println("1. Pesan Film");
        System.out.println("2. Lihat Saldo");
        System.out.println("3. Lihat List Film");
        System.out.println("4. Lihat Pesanan");
        System.out.println("5. Logout");
        System.out.println("6. Tutup Aplikasi");
    }

    @Override
    public void keluar() {
        Akun.logout();
        System.out.println("Anda telah logout.");
    }

    @Override
    public void tutupAplikasi() {
        System.out.println("Aplikasi ditutup.");
        System.exit(0);
    }

    @Override
    public void lihatListFilm() {
        for (Film film : Film.getFilms().values()) {
            System.out.println("Film " + film.getName() + " - " + film.getDescription() + " - Harga: " + film.getPrice() + " - Stok: " + film.getStock());
        }
    }

    public void lihatSaldo() {
        System.out.println("Saldo anda: " + Akun.getCurrentUser().getSaldo());
    }

    public void pesanFilm() {
        Main.scanner.nextLine();
        System.out.print("Nama Film yang ingin dipesan: ");
        String name = Main.scanner.nextLine();
        Film film = Film.getFilms().get(name);

        if (film == null) {
            System.out.println("Film yang dicari tidak ditemukan.");
            return;
        }

        System.out.print("Jumlah tiket yang ingin dipesan: ");
        int quantity = Main.scanner.nextInt();

        if (quantity > film.getStock()) {
            System.out.println("Stok tiket tidak mencukupi.");
            return;
        }

        double totalPrice = quantity * film.getPrice();
        if (totalPrice > Akun.getCurrentUser().getSaldo()) {
            System.out.println("Saldo tidak mencukupi, saldo yang dimiliki " + Akun.getCurrentUser().getSaldo());
            return;
        }

        film.setStock(film.getStock() - quantity);
        Akun.getCurrentUser().setSaldo(Akun.getCurrentUser().getSaldo() - totalPrice);
        Akun.getCurrentUser().addPesanan(film, quantity);

        System.out.println("Tiket berhasil dipesan.");
    }

    public void lihatPesanan() {
        Map<String, Pesanan> pesanan = Akun.getCurrentUser().getPesanan();

        if (pesanan.isEmpty()) {
            System.out.println("Kamu belum pernah melakukan pemesanan.");
            return;
        }

        pesanan.values().forEach(p -> 
            System.out.println("Film: " + p.getFilm().getName() + " - Jumlah: " + p.getKuantitas() + " - Total Harga: " + (p.getKuantitas() * p.getFilm().getPrice())));
    }
}