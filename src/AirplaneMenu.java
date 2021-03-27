import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class AirplaneMenu {
	protected final Iterable<Character> letters = () -> IntStream.rangeClosed('a', 'z').mapToObj(var -> (char) var).iterator();

	public SeatPreference preference;
	public int numberOfPeople = 0;
	public Airplane plane = new Airplane();
	public String type = "economy";

	public void getPreferences(Scanner input) {
		// aisle center window (max size 3 for input 1-3), numberOfPeople is the maximum
		// number of preferences, make sure all the preferences are different
		// asks for input using in
		System.out.println(
			"What is your seating preference - enter them separarted by space - you cannot have duplicate preferenes and no more then the number of people you are seating");
		System.out.println("Type \"window\", \"aisle\", and \"middle\" if you are flying economy");
		/*
		 * Ben note to self: test for input past this point, "input.contains"?
		 */
	}

	public void getPeople(Scanner input) {
		System.out.println("How people are traveling together? 1-2 for first class or 1-3 for economy");
		while (!input.hasNextInt()) {
			System.out.println("Please enter an integer!");
		}
		numberOfPeople = input.nextInt();
	}

	public void getType(Scanner input) {
		System.out.println("Economy or first class? Enter \"economy\" or first");
		while (true) {
			String givenType = input.next();
			if (givenType != "economy" && givenType != "first") {
				System.out.println("Please enter a valid string, economy or first");
			} else {
				type = givenType;
				break;
			}
		}
	}

	private String seatSideToString(SeatRow side, String seatDelimiter, boolean reversed) {
		List<String> seats = Arrays.stream(side.row).map((SeatState seat) -> seat.repr).collect(Collectors.toList());
		if (reversed) Collections.reverse(seats);
		return seats.stream().collect(Collectors.joining(seatDelimiter));
	}

	private String seatRowToString(SeatRow[] row, String sidesDelimiter, String seatDelimiter) {
		return seatSideToString(row[0], seatDelimiter, false) + sidesDelimiter + seatSideToString(row[1], seatDelimiter, true); // reverse the right side so that aisle comes first
	}

	public void printPlane() {
		final Stream<String> firstClassSeats = Arrays.stream(plane.getFirstClass()).map((SeatRow[] row) -> seatRowToString(row, "||", " "));
		final Stream<String> economySeats = Arrays.stream(plane.getEconomyClass()).map((SeatRow[] row) -> seatRowToString(row, "||", ""));
		
		final int fuselagePadding = 76;
		final Stream<String> planeHeader = Stream.of(
			"    __",
			"   /  \\" ,
			"  /    \\",
			" |      |",
			"| First  |",
			"|1 2  3 4|"
		).map((String row) -> " ".repeat(fuselagePadding) + row);
		final Stream<String> firstClassSection = firstClassSeats.map((String row) -> " ".repeat(fuselagePadding) + "|" + row + "|");
		final Stream<String> economyHeader = Stream.of(
			"|  Econ  |",
			"|123  456|"
		).map((String row) -> " ".repeat(fuselagePadding) + row);
		final Iterator<Character> letterIterator = letters.iterator();
		final int wingPadding = 8;
		final String[] wingSection = {
			"                                                                    ",
			"                                                               __---",
			"                                                 ___------------    ",
			"                                    ___------------                 ",
			"                        _____------                                 ",
			"               ______---                                            ",
			"       _______-                                                     ",
			"      /                                                             ",
			"   __=                                                              ",
			"  /                 =======================================         ",
			" |            ==========                                   ====     ",
			"/       =======                                                 ====",
			"/======                                                             ",
			"                                                                    ",
			"                                                                    "
		};
		final Iterator<String> leftWing = Arrays.stream(wingSection).map((String row) -> " ".repeat(wingPadding) + row.substring(0, row.length() - 1)).iterator();
		final Iterator<String> rightWing = Arrays.stream(wingSection).map((String row) -> new StringBuilder(row).reverse().toString()).iterator();
		final Stream<String> economySection = economySeats.map((String row) ->
			leftWing.next()
			+ letterIterator.next()
			+ "|" + row + "|"
			+ rightWing.next());
		final int tailPadding = 54;
		final Stream<String> tailSection = Stream.of(
			"                      |        |",
			"                      |---  ---|",
			"               ____---|  |  |  |---____",
			"    _____------       |---  ---|       ------_____",
			"   |                  |        |                  |",
			"  |                   |        |                   |",
			" /    ________________|        |________________    \\",
			"|________|            \\________/           |________|"
		).map((String row) -> " ".repeat(tailPadding) + row);

		Stream.of(planeHeader, firstClassSection, economyHeader, economySection, tailSection)
			.flatMap(i -> i).forEach(System.out::println);
	}

	public void reserveSeats(SuccessfulSeatSearch seats) {
		switch(seats.type) {
			case ECONOMY:
				
				break;
			case FIRST_CLASS:
				break;
			default:
				return; // never
		}
	}
}
