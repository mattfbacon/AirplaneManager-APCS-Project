import java.util.ArrayList;

public class SeatRow {
	public SeatState[] row;

	private void fillWith(SeatState value) {
		for (int i = 0; i < row.length; i++) {
			row[i] = value;
		}
	}

	public SeatRow(int size) {
		row = new SeatState[size];
		this.fillWith(SeatState.EMPTY);
	}

	public ArrayList<Integer> getAvailableSeats() {
		ArrayList<Integer> ret = new ArrayList<>();
		for (int i = 0; i < row.length; i++) {
			if (row[i] == SeatState.EMPTY) ret.add(i);
		}
		return ret;
	}

	/**
	 * {@return the number of seats available}
	 */
	public int getNumAvailableSeats() {
		return this.getAvailableSeats().size();
	}

	public void fillSeat(int location) {
		row[location] = SeatState.FULL;
	}
}
