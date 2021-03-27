public enum FailedSeatSearch implements SeatSearchResult {
	TOO_MANY_PEOPLE("Too many people"),
	NO_SUCH_SEATS("No such seats");

	public final String humanReadable;

	public boolean wasSuccessful() { return false; };
	public String toString() {
		return humanReadable;
	}

	public void printResult() {
		System.out.println("Seat search failed!");
		System.out.println(this.toString());
	}

	private FailedSeatSearch(String humanReadable) {
		this.humanReadable = humanReadable;
	}
}
