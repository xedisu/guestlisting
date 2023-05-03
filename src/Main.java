import java.util.List;
import java.util.Scanner;

public class Main {
    private static void showCommands() {
        System.out.println("help         - Afiseaza aceasta lista de comenzi");
        System.out.println("add          - Adauga o noua persoana (inscriere)");
        System.out.println("check        - Verifica daca o persoana este inscrisa la eveniment");
        System.out.println("remove       - Sterge o persoana existenta din lista");
        System.out.println("update       - Actualizeaza detaliile unei persoane");
        System.out.println("guests       - Lista de persoane care participa la eveniment");
        System.out.println("waitlist     - Persoanele din lista de asteptare");
        System.out.println("available    - Numarul de locuri libere");
        System.out.println("guests_no    - Numarul de persoane care participa la eveniment");
        System.out.println("waitlist_no  - Numarul de persoane din lista de asteptare");
        System.out.println("subscribe_no - Numarul total de persoane inscrise");
        System.out.println("search       - Cauta toti invitatii conform sirului de caractere introdus");
        System.out.println("save         - Salveaza lista cu invitati");
        System.out.println("restore      - Completeaza lista cu informatii salvate anterior");
        System.out.println("reset        - Sterge informatiile salvate despre invitati");
        System.out.println("quit         - Inchide aplicatia");
    }

    private static void addNewGuest(Scanner sc, GuestsList list) {
        String lastName = sc.nextLine();
        String firstName = sc.nextLine();
        String email = sc.nextLine();
        String phoneNumber = sc.nextLine();

        Guest guest = new Guest(lastName, firstName, email, phoneNumber);

        if (guest.getLastName() != null && guest.getFirstName() != null && guest.getEmail() != null && guest.getPhoneNumber() != null) {
            list.add(guest);
        }
    }

    private static void checkGuest(Scanner sc, GuestsList list) {
        System.out.println(findGuest(sc, list));
    }

    private static void removeGuest(Scanner sc, GuestsList list) {
        int opt = sc.nextInt();
        sc.nextLine();

        switch (opt) {
            case 1:
                String lastName = sc.nextLine();
                String firstName = sc.nextLine();
                list.remove(firstName, lastName);
                break;
            case 2:
                String matchEmail = sc.nextLine();
                list.remove(opt, matchEmail);
                break;
            case 3:
                String matchPhonenumber = sc.nextLine();
                list.remove(opt, matchPhonenumber);
                break;
        }
    }

    private static void updateGuest(Scanner sc, GuestsList list) {
        Guest foundGuest = findGuest(sc, list);
        if (foundGuest == null){
            return;
        }

        int modOpts = sc.nextInt();
        sc.nextLine();
        String modify = sc.nextLine();
        list.updateGuest(foundGuest, modOpts, modify);
    }

    private static Guest findGuest(Scanner sc, GuestsList list) {
        int opt = sc.nextInt();
        Guest foundGuest = null;
        sc.nextLine();

        switch (opt) {
            case 1:
                String lastName = sc.nextLine();
                String firstName = sc.nextLine();
                foundGuest = list.search(firstName, lastName);
                break;
            case 2:
                String matchEmail = sc.nextLine();
                foundGuest = list.search(opt, matchEmail);
                break;
            case 3:
                String matchPhonenumber = sc.nextLine();
                foundGuest = list.search(opt, matchPhonenumber);
                break;
        }
        if (foundGuest ==null){
            System.out.println("Guest not found");
        }

        return foundGuest;
    }

    private static void searchList(Scanner sc, GuestsList list) {
        String partialSearchString = sc.nextLine();
        List<Guest> partialSearchList = list.partialSearch(partialSearchString);
        if ( partialSearchList.size()==0){
            System.out.println("Nothing found");
            return;
        }
        for ( Guest guest : partialSearchList ){
            System.out.println(guest);
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        scanner.nextLine();

        GuestsList list = new GuestsList(size);

        boolean running = true;
        while (running) {
            String command = scanner.nextLine();

            switch (command) {
                case "help":
                    showCommands();
                    break;
                case "add":
                    addNewGuest(scanner, list);
                    break;
                case "check":
                    checkGuest(scanner, list);
                    break;
                case "remove":
                    removeGuest(scanner, list);
                    break;
                case "update":
                    updateGuest(scanner, list);
                    break;
                case "guests":
                    list.showGuestsList();
                    break;
                case "waitlist":
                    list.showWaitingList();
                    break;
                case "available":
                    System.out.println("Numarul de locuri ramase: " + list.numberOfAvailableSpots());
                    break;
                case "guests_no":
                    System.out.println("Numarul de participanti: " + list.numberOfGuests());
                    break;
                case "waitlist_no":
                    System.out.println("Dimensiunea listei de asteptare: " + list.numberOfPeopleWaiting());
                    break;
                case "subscribe_no":
                    System.out.println("Numarul total de persoane: " + list.numberOfPeopleTotal());
                    break;
                case "search":
                    searchList(scanner, list);
                    break;
                case "quit":
                    System.out.println("Aplicatia se inchide...");
                    scanner.close();
                    running = false;
                    break;
                default:
                    System.out.println("Comanda introdusa nu este valida.");
                    System.out.println("Incercati inca o data.");

            }
        }
    }
}