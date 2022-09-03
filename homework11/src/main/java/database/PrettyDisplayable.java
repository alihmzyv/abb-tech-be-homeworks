package database;

import java.util.List;

public interface PrettyDisplayable<A> {
    void displayIndexed(List<A> list);
}
