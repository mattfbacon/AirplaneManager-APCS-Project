import java.util.EnumSet;

public class AirplaneSeatingSystem {
	public static void main(String args[]) {
		final SeatPreference preferences = SeatPreference.fromEconomyPref(EnumSet.of(EconomySeatPos.WINDOW, EconomySeatPos.AISLE));

		final AirplaneMenu menu = new AirplaneMenu();

		SeatSearchResult result;
		
		result = menu.plane.findSeats(2, preferences);
		result.printResult();
		if (result.wasSuccessful()) { menu.reserveSeats((SuccessfulSeatSearch)result); }

		result = menu.plane.findSeats(2, preferences);
		result.printResult();

		menu.printPlane();
	}
}
