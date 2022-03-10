package tools.descartes.teastore.image.storage.rules;

import java.util.function.Predicate;
import tools.descartes.teastore.image.cache.entry.ICachable;
public class StoreAll<T extends ICachable<T>>  implements Predicate<T> {
@Override
public  boolean test(T t) {
return true;
}

}
