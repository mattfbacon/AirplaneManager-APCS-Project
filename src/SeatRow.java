import java.util.Arrays;
import java.util.stream.Stream;

public class SeatRow {
	public SeatState[] row;

	public SeatRow(int rowLength) {
		row = new SeatState[rowLength];
		Arrays.fill(row, SeatState.EMPTY);
	}

	public Stream<SeatState> availableSeats() {
		return Arrays.stream(row).filter(seat -> seat == SeatState.EMPTY);
	}
}
