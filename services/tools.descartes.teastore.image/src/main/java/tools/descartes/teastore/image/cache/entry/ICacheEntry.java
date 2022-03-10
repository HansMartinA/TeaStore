package tools.descartes.teastore.image.cache.entry;

public interface ICacheEntry<T extends ICachable<T>>  extends ICachable<T> {
public  void wasUsed() ;

public  T getData() ;

}
