package tools.descartes.teastore.image.cache;

import java.util.Comparator;
import java.util.TreeSet;
import java.util.function.Predicate;
import tools.descartes.teastore.image.cache.entry.ICachable;
import tools.descartes.teastore.image.cache.entry.ICacheEntry;
import tools.descartes.teastore.image.storage.IDataStorage;
public abstract class AbstractTreeCache<T extends ICachable<T>, F extends ICacheEntry<T>>  extends AbstractCache<TreeSet<F>, T, F> {
public  AbstractTreeCache(IDataStorage<T> cachedStorage, long maxCacheSize, Predicate<T> cachingRule, Comparator<F> ordering){
super(new  TreeSet<>(ordering), cachedStorage, maxCacheSize, cachingRule);
}
@Override
protected abstract  F createEntry(T data) ;

@Override
protected  void removeEntryByCachingStrategy() {
dataRemovedFromCache(getEntries().pollFirst().getByteSize());
}

@Override
protected  void reorderAndTag(F data) {
getEntries().remove(data);
data.wasUsed();
getEntries().add(data);
}

}
