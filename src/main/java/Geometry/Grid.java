package Geometry;

import lombok.Getter;

import java.awt.*;
import java.util.HashSet;

public class Grid
{
    private final HashSet<Pair<Long,Long>> blackSquares = new HashSet<Pair<Long,Long>>();
    @Getter
    private final Pair<Long, Long> xSize;
    @Getter
    private final Pair<Long, Long> ySize;

    public Grid(Pair<Long,Long> xSize, Pair<Long,Long> ySize)
    {
        this.xSize = xSize;
        this.ySize = ySize;
    }

    public void paint(Graphics g, Dimension windowSize)
    {
        paintSquares(g, windowSize);
        paintGrid(g, windowSize);
    }

    public boolean queryBlackSquare(Pair<Long,Long> square)
    {
        return blackSquares.contains(square);
    }
    public void flipSquareColor(Pair<Long,Long> square)
    {
        Pair<Long,Long> squareCopy = new Pair<Long,Long>(square.getFirst().longValue(), square.getSecond().longValue());
        if (blackSquares.contains(squareCopy))
        {
            blackSquares.remove(squareCopy);
        }
        else {
            blackSquares.add(squareCopy);
        }
    }

    private void paintGrid(Graphics g, Dimension windowSize)
    {
        g.setColor(Color.black);

        long xDim = xSize.getSecond() - ySize.getFirst();
        long yDim = ySize.getSecond() - ySize.getFirst();

        int width = windowSize.width;
        int height = windowSize.height;

        for (long x=0; x<=xDim; x++)
        {
            int lineX = (int)((x*width)/xDim);
            g.drawLine(lineX, 0, lineX, height);
        }

        for (long y=0; y<=yDim; y++)
        {
            int lineY = (int)((y*height)/yDim);
            g.drawLine(0, lineY, width, lineY);
        }
    }
    private void paintSquares(Graphics g, Dimension windowSize)
    {
        long xDim = xSize.getSecond() - ySize.getFirst();
        long yDim = ySize.getSecond() - ySize.getFirst();

        int width = windowSize.width;
        int height = windowSize.height;

        for (long x=0; x<xDim; x++)
        {
            for (long y=0; y<yDim; y++)
            {
                if (blackSquares.contains(new Pair<Long, Long>(x + xSize.getFirst(),y+ySize.getFirst()))) {
                    g.setColor(Color.black);
                }
                else {
                    g.setColor(Color.white);
                }

                int xBottom = (int)((x*width)/xDim);
                int xTop = (int)(((x+1)*width)/xDim);
                int yBottom = (int)((y*height)/yDim);
                int yTop = (int)(((y+1)*height)/yDim);
                g.fillRect(xBottom, yBottom,xTop-xBottom, yTop-yBottom);
            }
        }
    }
}
