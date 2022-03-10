package tools.descartes.teastore.image.cache.rules;

import java.util.function.Predicate;
import tools.descartes.teastore.image.cache.entry.ICachable;
public class CacheAll<T extends ICachable<T>>  implements Predicate<T> {
@Override
public  boolean test(T t) {
return true;
}

}
