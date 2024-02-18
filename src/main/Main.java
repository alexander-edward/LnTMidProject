package main;

import java.util.*;

public class Main {
	ArrayList <String> typeKodeKaryawan = new ArrayList<>();
	ArrayList <String> typeNamaKaryawan = new ArrayList<>();
	ArrayList <String> typeJenisKelaminKaryawan = new ArrayList<>();
	ArrayList <String> typeJabatanKaryawan = new ArrayList<>();
	ArrayList <Integer> typeGajiKaryawan = new ArrayList<>();
	ArrayList <String> typeListManager = new ArrayList<>();
	ArrayList <String> typeListSupervisor = new ArrayList<>();
	ArrayList <String> typeListAdmin = new ArrayList<>();
	Scanner scan = new Scanner(System.in);
	String namaKaryawan, jenisKelaminKaryawan, jabatanKaryawan, returnToMenu;
	int menuInput, gajiManager, gajiSupervisor, gajiAdmin, jumlahSupervisor, jumlahAdmin;
	double bonusManager, bonusSupervisor, bonusAdmin;
	
	void showMenu() {
		System.out.println("List Menu");
		System.out.println("1. Insert data karyawan");
		System.out.println("2. View data karyawan");
		System.out.println("3. Update data karyawan");
		System.out.println("4. Delete data karyawan");
		System.out.print(">> ");
		try {
			menuInput = scan.nextInt();
		} catch (Exception E) {
			System.out.println("Input must be a number");
			menuInput = 0;
		} finally {
			scan.nextLine();
		}
	}
	
	void insertDataKaryawan() {
		
		//Kode Karyawan
		Random rand = new Random();
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String kode;
		do {
			//2 Random huruf
			int index1 = rand.nextInt(alphabet.length());
			int index2 = rand.nextInt(alphabet.length());
			char kode1 = alphabet.charAt(index1);
			char kode2 = alphabet.charAt(index2);
			
			//4 Random angka
			int angka1 = rand.nextInt(10);
			int angka2 = rand.nextInt(10);
			int angka3 = rand.nextInt(10);
			int angka4 = rand.nextInt(10);
			
			kode = "" + kode1 + kode2 + "-" + angka1 + angka2 + angka3 + angka4;
		}while(typeKodeKaryawan.contains(kode));
		
		//Nama Karyawan
		do {
			System.out.println("Input nama karyawan [>= 3]: ");
			namaKaryawan = scan.nextLine();
		}while(!(namaKaryawan.length() >= 3));
		
		//Jenis Kelamin Karyawan
		do {
			System.out.print("Input jenis kelamin [Laki-Laki | Perempuan] (Case Sensitive): ");
			jenisKelaminKaryawan = scan.nextLine();
		}while(!(jenisKelaminKaryawan.equals("Laki-Laki") ||  jenisKelaminKaryawan.equals("Perempuan")));
		
		//Jabatan dan Gaji Karyawan
		do {
			System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case Sensitive): ");
			jabatanKaryawan = scan.nextLine();
			if (jabatanKaryawan.equals("Manager")) {
				typeListManager.add(kode);
			}
			else if (jabatanKaryawan.equals("Supervisor")) {
				typeListSupervisor.add(kode);
			}
			else {
				typeListAdmin.add(kode);
			}
		}while(!(jabatanKaryawan.equals("Manager") || jabatanKaryawan.equals("Supervisor") || jabatanKaryawan.equals("Admin")));
		
		System.out.println("Berhasil menambahkan karyawan dengan id " + kode);
		
		gajiKaryawan();
		typeKodeKaryawan.add(kode);
		typeNamaKaryawan.add(namaKaryawan);
		typeJenisKelaminKaryawan.add(jenisKelaminKaryawan);
		typeJabatanKaryawan.add(jabatanKaryawan);
	}
	
	void gajiKaryawan() {
		if (jabatanKaryawan.equals("Manager")) {
			gajiManager = 8_000_000;
			typeGajiKaryawan.add(gajiManager);
			if (typeListManager.size() % 3 == 1 && typeListManager.size() > 1) {
				keteranganBonusManager();
				perhitunganBonusManager();
			}
		}
		if (jabatanKaryawan.equals("Supervisor")) {
			gajiSupervisor = 6_000_000;
			typeGajiKaryawan.add(gajiSupervisor);
			if (typeListSupervisor.size() % 3 == 1 && typeListSupervisor.size() > 1) {
				keteranganBonusSupervisor();
				perhitunganBonusSupervisor();
			}
		}
		if (jabatanKaryawan.equals("Admin")) {
			gajiAdmin = 4_000_000;		
			typeGajiKaryawan.add(gajiAdmin);
			if (typeListAdmin.size() % 3 == 1 && typeListAdmin.size() > 1) {
				keteranganBonusAdmin();
				perhitunganBonusAdmin();
			}
		}	
	}
	
	void perhitunganBonusManager() {
		for (int i = 0; i < typeKodeKaryawan.size(); i++) {
			if (typeJabatanKaryawan.get(i).equals("Manager")) {
				int bonusManager = (int) (0.1 * typeGajiKaryawan.get(i));
				typeGajiKaryawan.set(i, typeGajiKaryawan.get(i) + bonusManager);
			}
		}
	}
	
	void perhitunganBonusSupervisor() {
		for (int i = 0; i < typeKodeKaryawan.size(); i++) {
			if (typeJabatanKaryawan.get(i).equals("Supervisor")) {
				int bonusSupervisor = (int) (0.075 * typeGajiKaryawan.get(i));
				typeGajiKaryawan.set(i, typeGajiKaryawan.get(i) + bonusSupervisor);
			}
		}
	}
	
	void perhitunganBonusAdmin() {
		for (int i = 0; i < typeKodeKaryawan.size(); i++) {
			if (typeJabatanKaryawan.get(i).equals("Admin")) {
				int bonusAdmin = (int) (0.05 * typeGajiKaryawan.get(i));
				typeGajiKaryawan.set(i, typeGajiKaryawan.get(i) + bonusAdmin);
			}
		}
	}
	
	void keteranganBonusManager() {
		if (typeListManager.size() % 3 == 1){
			for (int j = 0; j < typeListManager.size()-1; j++) {
				System.out.println("Bonus sebesar 10% telah diberikan kepada manager dengan nama: " + typeListManager.get(j));
			}
		}
	}
	
	void keteranganBonusSupervisor() {
		if (typeListSupervisor.size() % 3 == 1) {
			for (int j = 0; j < typeListSupervisor.size()-1; j++) {
				System.out.println("Bonus sebesar 7,5% telah diberikan kepada supervisor dengan nama: " + typeListSupervisor.get(j));
			}
		}
	}
	
	void keteranganBonusAdmin() {	
		if (typeListAdmin.size() % 3 == 1) {
			for (int j = 0; j < typeListSupervisor.size()-1; j++) {
				System.out.println("Bonus sebesar 5% telah diberikan kepada admin dengan nama: " + typeListAdmin.get(j));
			}
		}
	}
	
	void ascendingSorting() {
		int len = typeNamaKaryawan.size();
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len - 1; j++) {
				if (typeNamaKaryawan.get(j+1).compareTo(typeNamaKaryawan.get(j)) < 0) {
					String tempKodeKaryawan = typeKodeKaryawan.get(j);
					typeKodeKaryawan.set(j, typeKodeKaryawan.get(j+1));
					typeKodeKaryawan.set(j+1, tempKodeKaryawan);
					String tempNamaKaryawan = typeNamaKaryawan.get(j);
					typeNamaKaryawan.set(j, typeNamaKaryawan.get(j+1));
					typeNamaKaryawan.set(j+1, tempNamaKaryawan);
					String tempJenisKelaminKaryawan = typeJenisKelaminKaryawan.get(j);
					typeJenisKelaminKaryawan.set(j, typeJenisKelaminKaryawan.get(j+1));
					typeJenisKelaminKaryawan.set(j+1, tempJenisKelaminKaryawan);
					String tempJabatanKaryawan = typeJabatanKaryawan.get(j);
					typeJabatanKaryawan.set(j, typeJabatanKaryawan.get(j+1));
					typeJabatanKaryawan.set(j+1, tempJabatanKaryawan);
					Integer tempGajiKaryawan = typeGajiKaryawan.get(j);
					typeGajiKaryawan.set(j, typeGajiKaryawan.get(j+1));
					typeGajiKaryawan.set(j+1, tempGajiKaryawan);
					
				}
			}
		}
	}
	
	void viewDataKaryawan() {
		if(typeKodeKaryawan.isEmpty()) {
			System.out.println("There is no data");
		}
		else {
			ascendingSorting();
			System.out.println("|No| Kode Karyawan | Nama Karyawan     | Jenis Kelamin |    Jabatan    | Gaji Karyawan ");
			for (int i = 0; i < typeKodeKaryawan.size(); i++) {
				System.out.println(" "+ (i + 1) +"    "+ typeKodeKaryawan.get(i) +"         "+ typeNamaKaryawan.get(i) + "               " + typeJenisKelaminKaryawan.get(i) + "         " + typeJabatanKaryawan.get(i) + "        " + typeGajiKaryawan.get(i));
			}
		}
	}
	
	void updateDataKaryawan() {
		String newNamaKaryawan;
		String newJenisKelaminKaryawan;
		String newJabatanKaryawan;
		int index;
		
		if (typeKodeKaryawan.isEmpty()) {
			System.out.println("There is no data");
		}
		else {
			viewDataKaryawan();
			//Input nomor karyawan
			do {
				System.out.println("Input No: ");
				try {
					index = scan.nextInt();
				}catch (Exception E) {
					System.out.println("Input must be a number");
					index = 0;
				}finally {
					scan.nextLine();
				}
			}while(index > typeKodeKaryawan.size() || index <= 0);
			
			//Update nama karyawan
			do {
				System.out.println("Input nama karyawan [>= 3]: ");
				newNamaKaryawan = scan.nextLine();
				if (!(newNamaKaryawan.equals("0"))) {
					typeNamaKaryawan.set(index-1, newNamaKaryawan);
				}
			} while (!(newNamaKaryawan.length() >= 3));
			
			//Update jenis kelamin karyawan
			do {
				System.out.print("Input jenis kelamin [Laki-Laki | Perempuan] (Case Sensitive): ");
				newJenisKelaminKaryawan = scan.nextLine();
				if (!(newJenisKelaminKaryawan.equals("0"))) {
					typeJenisKelaminKaryawan.set(index-1, newJenisKelaminKaryawan);
				}
			}while(!(newJenisKelaminKaryawan.equals("Laki-Laki") ||  newJenisKelaminKaryawan.equals("Perempuan")));
			
			//Update Jabatan Karyawan
			do {
				System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case Sensitive): ");
				newJabatanKaryawan = scan.nextLine();
				if (!(newJabatanKaryawan.equals("0"))) {
					typeJabatanKaryawan.set(index - 1, newJabatanKaryawan);
					if (newJabatanKaryawan.equals("Manager")) {
						gajiManager = 8_000_000;
						typeGajiKaryawan.set(index - 1, gajiManager);
						
					} else if (newJabatanKaryawan.equals("Supervisor")) {
						gajiSupervisor = 6_000_000;
						typeGajiKaryawan.set(index - 1, gajiSupervisor);
						
					} else {
						gajiAdmin = 4_000_000;
						typeGajiKaryawan.set(index - 1, gajiAdmin);
					}
				}
			} while (!(newJabatanKaryawan.equals("Manager") || newJabatanKaryawan.equals("Supervisor") || newJabatanKaryawan.equals("Admin")));
			
			System.out.println("Berhasil mengupdate karyawan dengan id " + typeKodeKaryawan.get(index - 1));
			
			System.out.println("Enter to return");
			returnToMenu= scan.nextLine();
		}		
	}
	
	void deleteDataKaryawan() {
		int index = 0;
		if (typeKodeKaryawan.isEmpty()) {
			System.out.println("There is no data");
		}
		else {
			viewDataKaryawan();
			do {
				System.out.println("Input nomor urutan karyawan yang ingin dihapus: ");
				try {
					index = scan.nextInt();
				}catch (Exception E) {
					System.out.println("Input must be a number");
					index = 0;
				}finally {
					scan.nextLine();
				}
			}while(index > typeKodeKaryawan.size() || index <= 0);
			
			System.out.println("Karyawan dengan kode " + typeKodeKaryawan.get(index - 1) + " berhasil dihapus");
			typeKodeKaryawan.remove(index - 1);
			
			
			System.out.println("Enter to return");
			returnToMenu= scan.nextLine();
			
		}
	}
	
	public Main() {
		while(true) {
			showMenu();
			switch(menuInput) {
			case 1 :
				insertDataKaryawan();
				break;
			case 2 : 
				viewDataKaryawan();
				break;
			case 3 : 
				updateDataKaryawan();
				break;
			case 4 : 
				deleteDataKaryawan();
				break;
			default : 
				System.out.println("Input invalid");
			}
		}
	}

	public static void main(String[] args) {
		new Main();
	}

}
