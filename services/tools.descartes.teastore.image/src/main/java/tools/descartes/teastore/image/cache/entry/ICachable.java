package tools.descartes.teastore.image.cache.entry;

public interface ICachable<D extends ICachable<D>>  {
public  long getByteSize() ;

public  long getId() ;

}
