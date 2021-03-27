import java.util.HashSet;
import java.util.Set;

public class Airplane {

	private SeatRow[][] firstClass = new SeatRow[5][2];
	private SeatRow[][] economy = new SeatRow[15][2];

	public Airplane() {
		for (int row = 0; row < firstClass.length; row++) {
			for (int side = 0; side < firstClass[row].length; side++) {
				firstClass[row][side] = new SeatRow(2);
			}
		}
		for (int row = 0; row < economy.length; row++) {
			for (int side = 0; side < economy[row].length; side++) {
				economy[row][side] = new SeatRow(3);
			}
		}
	}

	public SeatState getEconomySeatState(int row, SeatSide side, EconomySeatPos pos) {
		return economy[row][side.index].row[pos.getPosition()];
	}

	public SeatState getFirstClassSeatState(int row, SeatSide side, FirstClassSeatPos pos) {
		return firstClass[row][side.index].row[pos.getPosition()];
	}

	public SeatRow[][] getFirstClass() {
		return firstClass;
	}

	public SeatRow[][] getEconomyClass() {
		return economy;
	}

	private SeatSearchResult findSeats(SeatRow[][] seatingArea, int amountOfPeople, Set<Integer> preferences, SeatType type) {
		if (amountOfPeople > seatingArea[0][0].row.length) return FailedSeatSearch.TOO_MANY_PEOPLE;
		for (int rowNum = 0; rowNum < seatingArea.length; rowNum++) {
			final SeatRow[] row = seatingArea[rowNum];
			for (SeatSide sideName : SeatSide.values()) {
				final SeatRow side = row[sideName.index];
				if (side.availableSeats().count() < amountOfPeople) { continue; } // not enough free seats in this row
				if (!preferences.stream().allMatch(pref -> side.row[pref] == SeatState.EMPTY)) { continue; } // preferences don't match in this row
				return new SuccessfulSeatSearch(rowNum, sideName, preferences, type);
			}
		}
		return FailedSeatSearch.NO_SUCH_SEATS;
	}

	public<E extends Enum<E> & SeatPos> SeatSearchResult findSeats(int amountOfPeople, SeatPreference preferences) {
		Set<Integer> preferredPositions = new HashSet<>();
		switch (preferences.getType()) {
			case ECONOMY:
				preferences.economyPreference.stream().forEach((SeatPos pos) -> preferredPositions.add(pos.getPosition()));
				return findSeats(economy, amountOfPeople, preferredPositions, SeatType.ECONOMY);
			case FIRST_CLASS:
				preferences.firstClassPreference.stream().forEach((SeatPos pos) -> preferredPositions.add(pos.getPosition()));
				return findSeats(firstClass, amountOfPeople, preferredPositions, SeatType.FIRST_CLASS);
			default:
				return null; // should never happen
		}
	}
}
