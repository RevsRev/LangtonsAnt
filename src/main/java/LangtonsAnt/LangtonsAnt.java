package LangtonsAnt;

import Geometry.Grid;
import Geometry.Pair;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public class LangtonsAnt
{
    @Getter
    private final Grid grid;
    @Getter
    private final Ant ant;

    Pair<Long, Long> antPos = new Pair<Long, Long>(0L, 0L);

    public LangtonsAnt(Grid grid, Ant ant)
    {
        this.ant = ant;
        this.grid = grid;
    }

    public void update()
    {
        Pair<Long, Long> antPosition = ant.getPosition();
        if (grid.queryBlackSquare(antPosition)) {
            ant.rotate((short) 1);
        }
        else {
            ant.rotate((short) -1);
        }
        grid.flipSquareColor(antPosition);
        ant.moveForward();
    }

    public void paint(Graphics g, Dimension windowSize)
    {
        grid.paint(g, windowSize);
        ant.paint(g, windowSize, grid.getXSize(), grid.getYSize());
    }
}
