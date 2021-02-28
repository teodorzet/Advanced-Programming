package Bonus;

import java.util.Objects;

public abstract class Source{
    protected int capacity;

    public abstract int getCapacity();
    public abstract void setCapacity(int capacity);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Source source = (Source) o;
        return capacity == source.capacity;
    }
    @Override
    public int hashCode() {
        return Objects.hash(capacity);
    }
}
