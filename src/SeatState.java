public enum SeatState {
	FULL("x"),
	EMPTY("o");

	public final String repr;

	private SeatState(String repr) {
		this.repr = repr;
	}
}
