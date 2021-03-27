public enum FirstClassSeatPos implements SeatPos {
	AISLE(1),
	WINDOW(0);

	private final int position;

	public int getPosition() {
		return position;
	}

	private FirstClassSeatPos(int position) {
		this.position = position;
	}
}
