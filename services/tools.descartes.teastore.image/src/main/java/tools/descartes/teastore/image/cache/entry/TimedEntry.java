package tools.descartes.teastore.image.cache.entry;

import java.util.concurrent.atomic.AtomicLong;
public class TimedEntry<D extends ICachable<D>>  extends AbstractEntry<D> {
private AtomicLong time = new  AtomicLong();

public  TimedEntry(D data){
super(data);
wasUsed();
}
public  long getTime() {
return time.get();
}

@Override
public  void wasUsed() {
time.set(System.nanoTime());
}

}
