package database;

import java.util.List;
import java.util.Optional;

public interface PrettyDisplayable<A> {
    void displayIndexed(List<A> list);
}
