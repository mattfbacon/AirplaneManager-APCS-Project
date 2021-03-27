import java.util.ArrayList;

class EconomyRow extends SeatRow {
	public EconomyRow() {
		super(6);
	}
}

class FirstClassRow extends SeatRow {
	public FirstClassRow() {
		super(4);
	}
}

public class Airplane {
	private EconomyRow[] first_class = new EconomyRow[5];
	private FirstClassRow[] economy_class = new FirstClassRow[15];

	public Airplane() {
		// empty?
	}

	public SeatRow[] getFirstClass() {
		return first_class;
	}

	public SeatRow[] getEconomyClass() {
		return economy_class;
	}

	public String findSeats(int amountOfPeople, String type, ArrayList<String> preferences) {
		if (type == "economy") {
			if (amountOfPeople > 3) {
				return "Too many people!";
			}

			for (int x = 0; x < 15; x++) {
				SeatRow cur = economy_class[x];
				// check left side
				int amountAvailable = 0;
				for (int y = 0; y < 3; y++) {
					if (cur.row[y] == SeatState.EMPTY) {
						amountAvailable += 1;
					}
				}
				if (amountAvailable >= amountOfPeople) {
					// we got enough seats
					if (checkPreferencesEconomy("left", preferences, cur)) {
						// all preferences fullfilled, lets give them the seats
						ArrayList<Integer> seats = getSeatsEconomy("left", preferences, cur, amountOfPeople);
						String answer = "Economy class seats\n";
						for (int seat : seats) {
							answer += "Seat at row " + (x + 1) + " and collumn " + (seat + 1) + "\n";
							cur.fillSeat(x);
						}
						return answer;
					}
				}
				// check right side
				amountAvailable = 0;
				for (int y = 3; y < 6; y++) {
					if (cur.row[y] == SeatState.EMPTY) {
						amountAvailable += 1;
					}
				}
				if (amountAvailable >= amountOfPeople) {
					// we got enough seats
					if (checkPreferencesEconomy("right", preferences, cur)) {
						// all preferences fullfilled, lets give them the seats
						ArrayList<Integer> seats = getSeatsEconomy("right", preferences, cur, amountOfPeople);
						String answer = "Economy class seats\n";
						for (int seat : seats) {
							answer += "Seat at row " + (x + 1) + " and collumn " + (seat + 1) + "\n";
							cur.fillSeat(x);
						}
						return answer;
					}
				}
			}
			return "Your preferences cannot be fulfilled";
		} else {
			if (amountOfPeople > 4) {
				return "Too many people!";
			}
			for (int x = 0; x < 5; x++) {
				SeatRow cur = first_class[x];
				// check left side
				int amountAvailable = 0;
				for (int y = 0; y < 2; y++) {
					if (cur.row[y] == SeatState.EMPTY) {
						amountAvailable += 1;
					}
				}
				if (amountAvailable >= amountOfPeople) {
					// we got enough seats
					if (checkPreferencesFirst("left", preferences, cur)) {
						// all preferences fullfilled, lets give them the seats
						ArrayList<Integer> seats = getSeatsFirst("left", preferences, cur, amountOfPeople);
						String answer = "First class seats\n";
						for (int seat : seats) {
							answer += "Seat at row " + (x + 1) + " and collumn " + (seat + 1) + "\n";
							cur.fillSeat(x);
						}
						return answer;
					}
				}
				// check right side
				amountAvailable = 0;
				for (int y = 2; y < 4; y++) {
					if (cur.row[y] == SeatState.EMPTY) {
						amountAvailable += 1;
					}
				}
				if (amountAvailable >= amountOfPeople) {
					// we got enough seats
					if (checkPreferencesFirst("right", preferences, cur)) {
						// all preferences fullfilled, lets give them the seats
						ArrayList<Integer> seats = getSeatsFirst("right", preferences, cur, amountOfPeople);
						String answer = "First class seats\n";
						for (int seat : seats) {
							answer += "Seat at row " + (x + 1) + " and collumn " + (seat + 1) + "\n";
							cur.fillSeat(x);
						}
						return answer;
					}
				}
			}
			return "Your preferences cannot be fulfilled. Either reduce number of people or change your preferences.";
		}
	}

	public boolean checkPreferencesEconomy(String side, ArrayList<String> preferences, SeatRow cur) {
		if (side == "left") {
			for (String preference : preferences) {
				if (preference == "aisle") {
					if (cur.row[2] == SeatState.FULL) {
						return false;
					}
				} else if (preference == "center") {
					if (cur.row[1] == SeatState.FULL) {
						return false;
					}
				} else if (preference == "window") {
					if (cur.row[0] == SeatState.FULL) {
						return false;
					}
				}
			}
		} else {
			for (String preference : preferences) {
				if (preference == "aisle") {
					if (cur.row[3] == SeatState.FULL) {
						return false;
					}
				} else if (preference == "center") {
					if (cur.row[4] == SeatState.FULL) {
						return false;
					}
				} else if (preference == "window") {
					if (cur.row[5] == SeatState.FULL) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public ArrayList<Integer> getSeatsEconomy(String side, ArrayList<String> preferences, SeatRow cur,
			int amountOfPeople) {
		ArrayList<Integer> seats = new ArrayList<Integer>();

		if (side == "left") {
			for (String preference : preferences) {
				if (preference == "aisle") {
					seats.add(2);
					amountOfPeople -= 1;
				} else if (preference == "center") {
					seats.add(1);
					amountOfPeople -= 1;
				} else if (preference == "window") {
					seats.add(0);
					amountOfPeople -= 1;
				}
			}
			// fill in the non preference seats

			for (int x = 0; x < 3; x++) {
				if (amountOfPeople == 0) {
					return seats;
				}
				if (!seats.contains(x) && cur.row[x] == SeatState.EMPTY) {
					seats.add(x);
					amountOfPeople -= 1;
				}
			}
		} else {
			for (String preference : preferences) {
				if (preference == "aisle") {
					seats.add(3);
					amountOfPeople -= 1;
				} else if (preference == "center") {
					seats.add(4);
					amountOfPeople -= 1;
				} else if (preference == "window") {
					seats.add(5);
					amountOfPeople -= 1;
				}
			}
			// fill in the non preference seats

			for (int x = 3; x < 6; x++) {
				if (amountOfPeople == 0) {
					return seats;
				}
				if (!seats.contains(x) && cur.row[x] == SeatState.EMPTY) {
					seats.add(x);
					amountOfPeople -= 1;
				}
			}
		}
		return seats;
	}

	public boolean checkPreferencesFirst(String side, ArrayList<String> preferences, SeatRow cur) {
		if (side == "left") {
			for (String preference : preferences) {
				if (preference == "aisle") {
					if (cur.row[1] == SeatState.FULL) {
						return false;
					}
				} else if (preference == "window") {
					if (cur.row[0] == SeatState.FULL) {
						return false;
					}
				}
			}
		} else {
			for (String preference : preferences) {
				if (preference == "aisle") {
					if (cur.row[2] == SeatState.FULL) {
						return false;
					}
				} else if (preference == "window") {
					if (cur.row[3] == SeatState.FULL) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public ArrayList<Integer> getSeatsFirst(String side, ArrayList<String> preferences, SeatRow cur, int amountOfPeople) {
		ArrayList<Integer> seats = new ArrayList<Integer>();

		if (side == "left") {
			for (String preference : preferences) {
				if (preference == "aisle") {
					seats.add(1);
					amountOfPeople -= 1;
				} else if (preference == "window") {
					seats.add(0);
					amountOfPeople -= 1;
				}
			}
			// fill in the non preference seats

			for (int x = 0; x < 2; x++) {
				if (amountOfPeople == 0) {
					return seats;
				}
				if (!seats.contains(x) && cur.row[x] == SeatState.EMPTY) {
					seats.add(x);
					amountOfPeople -= 1;
				}
			}
		} else {
			for (String preference : preferences) {
				if (preference == "aisle") {
					seats.add(2);
					amountOfPeople -= 1;
				} else if (preference == "window") {
					seats.add(3);
					amountOfPeople -= 1;
				}
			}
			// fill in the non preference seats

			for (int x = 2; x < 4; x++) {
				if (amountOfPeople == 0) {
					return seats;
				}
				if (!seats.contains(x) && cur.row[x] == SeatState.EMPTY) {
					seats.add(x);
					amountOfPeople -= 1;
				}
			}
		}
		return seats;
	}
}
