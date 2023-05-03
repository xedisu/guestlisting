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
        System.out.println("Adding a new Guest, please give all the necessary details.");
        System.out.println("Lastname:");
        String lastName = sc.nextLine();
        System.out.println("Firstname:");
        String firstName = sc.nextLine();
        System.out.println("Email:");
        String email = sc.nextLine();
        System.out.println("Phone Number");
        String phoneNumber = sc.nextLine();
        Guest guest = new Guest(lastName, firstName, email, phoneNumber);


        if (guest.getLastName() != null && guest.getFirstName() != null && guest.getEmail() != null && guest.getPhoneNumber() != null) {
            list.add(guest);
        }
    }

    private static void checkGuest(Scanner sc, GuestsList list) {
       // System.out.println("Checking a guest, please select the search criteria from the following:");
        System.out.println(findGuest(sc, list));
    }

    private static void removeGuest(Scanner sc, GuestsList list) {
        System.out.println("Removing a guest, please select the option criteria from the following:\n" +
                "\t 1.LastName and FirstName\n" +
                "\t 2.Email\n" +
                "\t 3.PhoneNumber");
        int opt = sc.nextInt();
        sc.nextLine();

        switch (opt) {
            case 1:
                System.out.println("Give the LastName and FirstName, separated by a whitespace");
                String match = sc.nextLine();
                String lastName = match.substring(0, match.indexOf(" "));
                String firstName = match.substring(match.indexOf(" ") + 1);
                System.out.println( list.remove(firstName, lastName));
                break;
            case 2:
                System.out.println("Give the email");
                String matchEmail = sc.nextLine();
                System.out.println( list.remove(opt, matchEmail));
                break;
            case 3:
                System.out.println("Give the phonenumber");
                String matchPhonenumber = sc.nextLine();
                System.out.println( list.remove(opt, matchPhonenumber));
                break;
        }
    }

    private static void updateGuest(Scanner sc, GuestsList list) {
        System.out.println("Updating a guest, please select the option criteria from the following:");

       Guest foundGuest = findGuest(sc, list);
        if (foundGuest == null){
            return;
        }

        System.out.println("What do you want modified? 1-lastname | 2-firstname | 3-email | 4-phonenumber");
        int modOpts = sc.nextInt();
        sc.nextLine();
        String modify = sc.nextLine();
        list.updateGuest(foundGuest, modOpts, modify);
    }

    private static Guest findGuest(Scanner sc, GuestsList list) {
        System.out.println("\t 1.LastName and FirstName\n" +
                "\t 2.Email\n" +
                "\t 3.PhoneNumber");
        int opt = sc.nextInt();
        Guest foundGuest = null;
        sc.nextLine();

        switch (opt) {
            case 1:
                System.out.println("Give the LastName and FirstName, separated by a whitespace");
                String match = sc.nextLine();
                String lastName = match.substring(0, match.indexOf(" "));
                String firstName = match.substring(match.indexOf(" ") + 1);
                foundGuest = list.search(firstName, lastName);
                break;
            case 2:
                System.out.println("Give the email");
                String matchEmail = sc.nextLine();
                foundGuest = list.search(opt, matchEmail);
                break;
            case 3:
                System.out.println("Give the phonenumber");
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
        System.out.println("Attempting to partial search the lists, give the string criteria");
        String partialSearchString = sc.nextLine();
        System.out.println(list.partialSearch(partialSearchString));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        scanner.nextLine();

        GuestsList list = new GuestsList(size);

        Guest g1 = new Guest("Dobrescu", "MihaiCatalin", "mihai.c.dobrescu@gmail.com", "07283467520");
        Guest g5 = new Guest("Dobresque", "MihaiCatalin", "mihai.c.dobresque@gmail.com", "07283234520");
        Guest g2 = new Guest("Tanasoiu", "Bogdanof", "cafedon@gmail.com", "072333330");
        Guest g3 = new Guest("Enescu", "Suzana", "pucii@gmail.com", "07456345665");
        Guest g4 = new Guest("Corcoveanu", "Constantin", "morcoveanu@gmail.com", "0745234234");
        Guest g6 = new Guest("Constantina", "CristianaEmanuella", "kusky@gmail.com", "08512354123");
        list.add(g1);
        list.add(g2);
        list.add(g3);
        list.add(g4);
        list.add(g5);
        list.add(g6);

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