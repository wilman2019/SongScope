package Java.TextFields;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.AbstractBorder;

public class SearchField extends JTextField {

    public boolean isShowAndHide() {
        return showAndHide;
    }

    public void setShowAndHide(boolean showAndHide) {
        this.showAndHide = showAndHide;
        repaint();
    }

    public String getLabelText() {
        return labelText;
    }

    public void setLabelText(String labelText) {
        this.labelText = labelText;
    }

    public Color getLineColor() {
        return lineColor;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    private boolean mouseOver = false;
    private String labelText = "Search";
    private Color lineColor = new Color(21, 170, 180);
    private final Image filter;
    private final Image filter_checked;
    private boolean filterMenuOpen = false;
    private boolean showAndHide;

    public SearchField(JPanel advancedSearchMenu) {
        setOpaque(false);
        setBorder(new RoundedBorder(30));
        setSelectionColor(new Color(76, 204, 255));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                mouseOver = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent me) {
                mouseOver = false;
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent me) {
                if (showAndHide) {
                    int x = getWidth() - 30;
                    if (new Rectangle(x, 0, 30, 30).contains(me.getPoint())) {
                        filterMenuOpen = !filterMenuOpen;
                        if (filterMenuOpen) {
                            // TODO
                            advancedSearchMenu.setVisible(true);

                        } else {
                            // TODO
                            advancedSearchMenu.setVisible(false);
                        }
                        repaint();
                    }
                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent me) {
                if (showAndHide) {
                    int x = getWidth() - 30;
                    if (new Rectangle(x, 0, 30, 30).contains(me.getPoint())) {
                        setCursor(new Cursor(Cursor.HAND_CURSOR));
                    } else {
                        setCursor(new Cursor(Cursor.TEXT_CURSOR));
                    }
                }
            }
        });
        filter_checked = new ImageIcon("src/img/filter_checked.png").getImage();
        filter = new ImageIcon("src/img/filter_unchecked.png").getImage();

    }



    @Override
    public void paint(Graphics grphcs) {
        super.paint(grphcs);
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        if (mouseOver) {
            g2.setColor(lineColor);
        } else {
            g2.setColor(new Color(150, 150, 150));
        }
        createHintText(g2);
        if (showAndHide) {
            createShowHide(g2);
        }
        g2.dispose();
    }

    private void createShowHide(Graphics2D g2) {
        int x = getWidth() - 30 + 5;
        int y = (getHeight() - 20) / 2;
        g2.drawImage(filterMenuOpen ? filter_checked : filter, x, y, null);
    }

    private void createHintText(Graphics2D g2) {
        if (getText().isEmpty()) {
            Insets in = getInsets();
            g2.setColor(new Color(150, 150, 150));
            FontMetrics ft = g2.getFontMetrics();
            Rectangle2D r2 = ft.getStringBounds(labelText, g2);
            double height = getHeight() - in.top - in.bottom;
            double textY = (height - r2.getHeight()) / 2;
            g2.drawString(labelText, in.left, (int) (in.top + textY + ft.getAscent()));
        }
    }




    public class RoundedBorder extends AbstractBorder {
        private int radius;
    
        RoundedBorder(int radius) {
            this.radius = radius;
        }
    
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.drawRoundRect(x, y, width-1, height-1, radius, radius);
        }
    
        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(5, 15, 5, 30);
        }
    
        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.left = insets.right = insets.top = insets.bottom = this.radius;
            return insets;
        }
    }
}
