class DataOfSquare {

    final SquarePanel square;

    public DataOfSquare(Colors col) {

        square = new SquarePanel(col.getColor());
    }

    public void lightMeUp(Colors col) {
        square.changeColor(col.getColor());
    }
}
