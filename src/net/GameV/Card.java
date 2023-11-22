package net.GameV;

public class Card {
	private final Rank rank;
	private final Suit suit;
	private final String texture;

	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
		this.texture = genTexture(rank, suit);
	}

	public Rank getRank() {
		return rank;
	}

	public Suit getSuit() {
		return suit;
	}

	public String getTexture() {
		return texture;
	}

	@Override
	public String toString() {
		return rank.getSymbol() + suit.getSymbol();
	}

	private static String genTexture(Rank rank, Suit suit) {
		StringBuilder builder = new StringBuilder();
		builder.append("╔═════╗").append("\n");
		builder.append("║" + String.format("%-5s", rank.getSymbol()) + "║").append("\n");
		builder.append("║  " + suit.getSymbol() + "  ║").append("\n");
		builder.append("║" + String.format("%5s", rank.getSymbol()) + "║").append("\n");
		builder.append("╚═════╝");
		return builder.toString();
	}

	public static enum Rank {
		ACE("A", 11),
		JACK("J", 10),
		QUEEN("Q", 10),
		KING("K", 10),
		N2("2", 2),
		N3("3", 3),
		N4("4", 4),
		N5("5", 5),
		N6("6", 6),
		N7("7", 7),
		N8("8", 8),
		N9("9", 9),
		N10("10", 10);

		private final String symbol;
		private final int points;

		private Rank(String symbol, int points) {
			this.symbol = symbol;
			this.points = points;
		}

		public String getSymbol() {
			return symbol;
		}

		public int getPoints() {
			return points;
		}

	}

	public static enum Suit {
		DIAMOND("♦"),
		SPADES("♠"),
		CLUBS("♣"),
		HEARTS("♥");

		private final String symbol;

		private Suit(String symbol) {
			this.symbol = symbol;
		}

		public String getSymbol() {
			return symbol;
		}
	}

}