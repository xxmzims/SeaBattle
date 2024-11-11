package myProject;

class Cell {
    private CellState state;

    public Cell(CellState state) {
        this.state = state;
    }

    public String getRepresentation() {
        return state.getRepresentation();
    }

    public void setState(CellState state) {
        this.state = state;
    }
}