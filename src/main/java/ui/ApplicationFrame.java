package ui;

import Geometry.Grid;
import Geometry.Pair;
import LangtonsAnt.LangtonsAnt;
import LangtonsAnt.Ant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;

public class ApplicationFrame extends JFrame implements MouseWheelListener, MouseListener
{
    private LangtonsAnt langtonsAnt;
    private JPanel contentPane;

    Timer timer = new Timer();

    private final Thread antUpdateThread;

    public ApplicationFrame(Dimension size)
    {
        int squareWidth = size.width/200;
        int numSquaresForWidth = size.width/squareWidth;
        Pair<Long,Long> xVals = new Pair<Long,Long>(-(long)numSquaresForWidth/2, (long)numSquaresForWidth/2);
        int numSquaresForHeight = size.height/squareWidth;
        Pair<Long,Long> yVals = new Pair<Long,Long>(-(long)numSquaresForHeight/2, (long)numSquaresForHeight/2);

        langtonsAnt = new LangtonsAnt(new Grid(xVals, yVals), new Ant(new Pair<Long,Long>(0L, 0L), (short)0));

        this.antUpdateThread = new Thread(() -> this.updateAnt());

        setContentPane(contentPane);
        setSize(size);

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                onClose();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onClose();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        addMouseWheelListener(this);
        addMouseListener(this);

        antUpdateThread.start();
    }

    public void updateAnt()
    {
        while (true)
        {
            try
            {
                Thread.sleep(500);
            } catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
            langtonsAnt.update();
            this.repaint();
        }
    }

    @Override
    public void paint(Graphics g)
    {
        langtonsAnt.paint(g, getSize());
    }

    private void onClose()
    {
        dispose();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e)
    {

    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

    }

    @Override
    public void mousePressed(MouseEvent e)
    {

    }

    @Override
    public void mouseReleased(MouseEvent e)
    {

    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }
}
