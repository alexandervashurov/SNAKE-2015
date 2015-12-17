class DataOfSquare {

    final SquarePanel square;
    final Colors state;

    public DataOfSquare(Colors col) {

        square = new SquarePanel(col.getColor());
        state = col;
    }

    public Colors getState() {
        return state;
    }

    public void lightMeUp(Colors col) {
        square.changeColor(col.getColor());
    }
}
