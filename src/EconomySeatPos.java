public enum EconomySeatPos implements SeatPos {
	AISLE(2),
	CENTER(1),
	WINDOW(0);

	private final int position;
	
	public int getPosition() {
		return position;
	}

	private EconomySeatPos(int position) {
		this.position = position;
	}
}
