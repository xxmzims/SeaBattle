package myProject;

enum CellState {
    WATER("\uD83C\uDF0A"),
    SHIP("⛵"),
    MISS("\uD83C\uDF2B"),
    DESTROYED("\uD83D\uDCA5"),
    HIT("❌");

    private final String representation;

    CellState(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }
}