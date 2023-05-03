import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class GuestsList {
    private final int guestsCapacity;
    private final ArrayList<Guest> guestList;
    private final ArrayList<Guest> waitList;


    public GuestsList(int guestsCapacity) {
        this.guestsCapacity = guestsCapacity;
        guestList = new ArrayList<>();
        waitList = new ArrayList<>();

    }

    private void updateGuestList() {
        if (guestList.size() < guestsCapacity && waitList.size() > 0) {
            guestList.add(waitList.get(0));
            System.out.println("Felicitari " +waitList.get(0).fullName() +"! Locul tau la eveniment este confirmat. Te asteptam!");
            waitList.remove(0);

        }
    }

    /**
     * Add a new, unique guest to the list.
     *
     * @param guest the guest to be added
     * @return '-1' if the guest is already present, '0' if he is a guest, or the
     * number on the waiting list
     */
    public int add(Guest guest) {
        if (guestList.contains(guest)) {
            return 0;
        }

        if (waitList.contains(guest)) {
            return -1;
        }

        if (guestList.size() < guestsCapacity) {
            guestList.add(guest);
            System.out.println("[" + guest.fullName() + "] Felicitari! Locul tau la eveniment este confirmat. Te asteptam!");
            return 0;
        } else {
            waitList.add(guest);
            int index = waitList.indexOf(guest) + 1;
            System.out.println("[" + guest.fullName() + "] Te-ai inscris cu succes in lista de asteptare si ai primit numarul de ordine "+index+". Te vom notifica daca un loc devine disponibil.");
            return index;
        }
    }

    /**
     * Check if someone is already registered (either as a guest, or on the waiting
     * list).
     *
     * @param g the guest we are searching for
     * @return true if present, false if not
     */
    private boolean isOnTheListAlready(Guest g) {
        for (Guest guest : guestList) {
            if (guest.hashCode() == g.hashCode()) {
                if (guest.equals(g)) {
                    return true;
                }
            }
        }

        for (Guest guest : waitList) {
            if (guest.hashCode() == g.hashCode()) {
                if (guest.equals(g)) {
                    return true;
                }
            }
        }

        return false;
    }

    public Guest findGuestFromLists(Guest g) {
        for (Guest guest : guestList) {
            if (guest.hashCode() == g.hashCode()) {
                if (guest.equals(g)) {
                    return guest;
                }
            }
        }

        for (Guest guest : waitList) {
            if (guest.hashCode() == g.hashCode()) {
                if (guest.equals(g)) {
                    return guest;
                }
            }
        }

        return null;
    }

    public void updateGuest(Guest guest, int opts, String match) {
        if (match == null || match.equals("")) {
            System.out.println("Incorrect input of the update string");
            return;
        }

        switch (opts) {
            case 1:
                guest.setLastName(match);
                break;
            case 2:
                guest.setFirstName(match);
                break;
            case 3:
                guest.setEmail(match);
                break;
            case 4:
                guest.setPhoneNumber(match);
                break;
        }
    }

    /**
     * Search for a guest based on first and last name. Return the first result.
     *
     * @param firstName first name of the guest
     * @param lastName  last name of the guest
     * @return the guest if found, null if not
     */
    public Guest search(String firstName, String lastName) {
        for (Guest guest : guestList) {
            if (guest.getFirstName().equals(firstName) && guest.getLastName().equals(lastName)) {
                return guest;
            }
        }

        for (Guest guest : waitList) {
            if (guest.getFirstName().equals(firstName) && guest.getLastName().equals(lastName)) {
                return guest;
            }
        }

        return null;
    }

    /**
     * Search for a guest based on email or phone number. Return the first result.
     *
     * @param opt   option to use for searching: 2 for email, 3 for phone number
     * @param match the match we are searching for
     * @return the guest if found, null if not
     */
    public Guest search(int opt, String match) {
        switch (opt) {

            case 2:
                for (Guest guest : guestList) {
                    if (guest.getEmail().equals(match)) {
                        return guest;
                    }
                }
                for (Guest guest : waitList) {
                    if (guest.getEmail().equals(match)) {
                        return guest;
                    }
                }
                break;
            case 3:
                for (Guest guest : guestList) {
                    if (guest.getPhoneNumber().equals(match)) {
                        return guest;
                    }
                }
                for (Guest guest : waitList) {
                    if (guest.getPhoneNumber().equals(match)) {
                        return guest;
                    }
                }
                break;
        }
        return null;
    }

    /**
     * Remove a guest based on first and last name. Remove the first result.
     *
     * @param firstName first name of the guest
     * @param lastName  last name of the guest
     * @return true if removed, false if not
     */
    public boolean remove(String firstName, String lastName) {
        Guest guestToBeRemoved = search(firstName, lastName);

        if (guestToBeRemoved == null) {
            return false;
        }

        if (guestList.contains(guestToBeRemoved)) {
            guestList.remove(guestToBeRemoved);
            updateGuestList();
        } else {
            waitList.remove(guestToBeRemoved);
        }

        return true;
    }

    /**
     * Remove a guest based on email or phone number. Remove the first result.
     *
     * @param opt   option to use for searching: 2 for email, 3 for phone number
     * @param match the match we are searching for
     * @return true if removed, false if not
     */
    public boolean remove(int opt, String match) {
        Guest guestToBeRemoved = search(opt, match);

        if (guestToBeRemoved != null) {
            remove(guestToBeRemoved.getFirstName(), guestToBeRemoved.getLastName());
            return true;
        }

        return false;
    }

    // Show the list of guests.
    public void showGuestsList() {
        int index = 1;
        for (Guest guest : guestList) {

            System.out.println(index+ ". "+guest);
            index++;
        }
    }

    // Show the people on the waiting list.
    public void showWaitingList() {
        if (waitList.size() == 0) {
            System.out.println("Lista de asteptare este goala...");
            return;
        }
        int index = 1;
        for (Guest guest : waitList) {
            System.out.println(index+ ". "+guest);
            index++;
        }
    }

    /**
     * Show how many free spots are left.
     *
     * @return the number of spots left for guests
     */
    public int numberOfAvailableSpots() {
        return guestsCapacity - guestList.size();
    }

    /**
     * Show how many guests there are.
     *
     * @return the number of guests
     */
    public int numberOfGuests() {
        return guestList.size();
    }

    /**
     * Show how many people are on the waiting list.
     *
     * @return number of people on the waiting list
     */
    public int numberOfPeopleWaiting() {
        return waitList.size();
    }

    /**
     * Show how many people there are in total, including guests.
     *
     * @return how many people there are in total
     */
    public int numberOfPeopleTotal() {
        return numberOfGuests() + numberOfPeopleWaiting();
    }

    /**
     * Find all people based on a partial value search.
     *
     * @param match the match we are looking for
     * @return a list of people matching the criteria
     */
    public List<Guest> partialSearch(String match) {
        ArrayList<Guest> partialSearchList = new ArrayList<>();
        match = match.toLowerCase();
        for (Guest guest : guestList) {
            if (guest.getFirstName().toLowerCase().contains(match)) {
                partialSearchList.add(guest);
                continue;
            }
            if (guest.getLastName().toLowerCase().contains(match)) {
                partialSearchList.add(guest);
                continue;
            }
            if (guest.getEmail().toLowerCase().contains(match)) {
                partialSearchList.add(guest);
                continue;
            }
            if (guest.getPhoneNumber().toLowerCase().contains(match)) {
                partialSearchList.add(guest);
            }
        }
        for (Guest guest : waitList) {
            if (guest.getFirstName().toLowerCase().contains(match)) {
                partialSearchList.add(guest);
                continue;
            }
            if (guest.getLastName().toLowerCase().contains(match)) {
                partialSearchList.add(guest);
                continue;
            }
            if (guest.getEmail().toLowerCase().contains(match)) {
                partialSearchList.add(guest);
                continue;
            }
            if (guest.getPhoneNumber().toLowerCase().contains(match)) {
                partialSearchList.add(guest);
            }
        }
        return partialSearchList;
    }

    @Override
    public String toString() {
        return "Guestlist: " + guestList + "\n" +
                "WaitList: " + waitList;
    }
}
