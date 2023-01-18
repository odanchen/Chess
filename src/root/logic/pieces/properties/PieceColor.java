package root.logic.pieces.properties;

public enum PieceColor {
    WHITE("w"), BLACK("b");

    private final String colorSign;

    PieceColor(String colorSign) {
        this.colorSign = colorSign;
    }

    public String getColorSign() {
        return this.colorSign;
    }
}
