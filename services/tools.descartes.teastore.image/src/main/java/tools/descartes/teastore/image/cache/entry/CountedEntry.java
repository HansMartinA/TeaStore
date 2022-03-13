package tools.descartes.teastore.image.cache.entry;

import java.util.concurrent.atomic.AtomicInteger;
public class CountedEntry<D extends ICachable<D>>  extends AbstractEntry<D> {
private AtomicInteger useCount = new  AtomicInteger();

public  CountedEntry(D data){
super(data);
}
public  int getUseCount() {
return useCount.get();
}

@Override
public  void wasUsed() {
useCount.incrementAndGet();
}

}
