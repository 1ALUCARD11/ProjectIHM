import javax.swing.border.Border;
import java.awt.*;

class RoundBorder implements Border {
    private final int top;
    private final int left;
    private final int bottom;
    private final int right;

    public RoundBorder(int top, int left, int bottom, int right) {
        this.top = top;
        this.left = left;
        this.bottom = bottom;
        this.right = right;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x, y, width - 1, height - 1, 15, 15);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(top, left, bottom, right);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }
}