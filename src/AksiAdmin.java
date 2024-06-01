public class AksiAdmin extends Aksi {
    @Override
    public void tampilanAksi() {
        System.out.println("Aksi Admin:");
        System.out.println("1. Tambah Film");
        System.out.println("2. Lihat List Film");
        System.out.println("3. Logout");
        System.out.println("4. Tutup Aplikasi");
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

    public void tambahFilm() {
        Main.scanner.nextLine();
        System.out.print("Nama Film: ");
        String name = Main.scanner.nextLine();
        System.out.print("Deskripsi Film: ");
        String description = Main.scanner.nextLine();
        System.out.print("Harga Tiket: ");
        double price = Main.scanner.nextDouble();
        System.out.print("Stok Tiket: ");
        int stock = Main.scanner.nextInt();

        Film.addFilm(name, description, price, stock);
    }
}