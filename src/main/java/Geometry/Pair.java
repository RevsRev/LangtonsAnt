package Geometry;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
public class Pair<T,V>
{
    @Getter @Setter
    private T first;
    @Getter @Setter
    private V second;

    public Pair(T first, V second)
    {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(first, second);
    }
}
