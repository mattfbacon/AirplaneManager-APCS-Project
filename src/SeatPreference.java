import java.util.EnumSet;

public class SeatPreference {
	protected final SeatType type;
	public final EnumSet<EconomySeatPos> economyPreference;
	public final EnumSet<FirstClassSeatPos> firstClassPreference;

	protected<E extends Enum<E> & SeatPos> SeatPreference(SeatType type, EnumSet<E> pos) {
		this.type = type;
		switch(type) {
			case ECONOMY:
				this.economyPreference = (EnumSet<EconomySeatPos>)pos;
				this.firstClassPreference = null;
				break;
			case FIRST_CLASS:
				this.firstClassPreference = (EnumSet<FirstClassSeatPos>)pos;
				this.economyPreference = null;
				break;
			default:
				this.firstClassPreference = null;
				this.economyPreference = null;
				break;
		}
	}

	public static SeatPreference fromFirstClassPref(EnumSet<FirstClassSeatPos> pos) {
		return new SeatPreference(SeatType.FIRST_CLASS, pos);
	}

	public static SeatPreference fromEconomyPref(EnumSet<EconomySeatPos> pos) {
		return new SeatPreference(SeatType.ECONOMY, pos);
	}

	public SeatType getType() {
		return type;
	}
}
