package tools.descartes.teastore.image.cache;

import java.util.LinkedList;
import java.util.function.Predicate;
import tools.descartes.teastore.image.cache.entry.ICachable;
import tools.descartes.teastore.image.cache.entry.SimpleEntry;
import tools.descartes.teastore.image.storage.IDataStorage;
public abstract class AbstractQueueCache<T extends ICachable<T>>  extends AbstractCache<LinkedList<SimpleEntry<T>>, T, SimpleEntry<T>> {
public  AbstractQueueCache(IDataStorage<T> cachedStorage, long maxCacheSize, Predicate<T> cachingRule){
super(new  LinkedList<>(), cachedStorage, maxCacheSize, cachingRule);
}
@Override
public  SimpleEntry<T> createEntry(T data) {
return new  SimpleEntry<T>(data);
}

@Override
protected abstract  void removeEntryByCachingStrategy() ;

}
