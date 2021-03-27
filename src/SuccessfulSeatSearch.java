import java.util.Set;

public class SuccessfulSeatSearch implements SeatSearchResult {
	public final int rowNum;
	public final SeatSide side;
	public final Set<Integer> preferences;
	public final SeatType type;

	public SuccessfulSeatSearch(int rowNum, SeatSide side, Set<Integer> preferences, SeatType type) {
		this.rowNum = rowNum;
		this.side = side;
		this.preferences = preferences;
		this.type = type;
	}

	public String toString() {
		return String.format("Row %d on the %s", rowNum, side.name().toLowerCase());
	}

	public void printResult() {
		System.out.println("Seat search succeeded!");
		System.out.println(this.toString());
	}

	public boolean wasSuccessful() { return true; }
}
