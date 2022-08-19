package LangtonsAnt;

import Geometry.Pair;
import lombok.Getter;

import java.awt.*;

public class Ant
{
    @Getter
    private final Pair<Long,Long> position;
    @Getter
    private short rotation;

    public Ant(Pair position, short rotation)
    {
        this.position = position;
        this.rotation = rotation;
    }

    public void rotate(short direction)
    {
        rotation = (short)Math.floorMod(rotation + direction, 4);
    }

    public void moveForward()
    {
        switch(rotation)
        {
            case 0:
                position.setFirst(position.getFirst() + 1);
                break;
            case 1:
                position.setSecond(position.getSecond() + 1);
                break;
            case 2:
                position.setFirst(position.getFirst() - 1);
                break;
            case 3:
                position.setSecond(position.getSecond() - 1);
                break;
        }
    }

    public void paint(Graphics g, Dimension windowSize, Pair<Long, Long> xSize, Pair<Long,Long> ySize)
    {
        long xDim = xSize.getSecond() - ySize.getFirst();
        long yDim = ySize.getSecond() - ySize.getFirst();

        int antWindowX = (int)(windowSize.width*((position.getFirst() - xSize.getFirst())/(float)xDim));
        int antWindowY = (int)(windowSize.height*((position.getSecond() - ySize.getFirst())/(float)yDim));

        g.setColor(Color.red);
        g.fillOval(antWindowX, antWindowY, 10, 10);
    }
}
