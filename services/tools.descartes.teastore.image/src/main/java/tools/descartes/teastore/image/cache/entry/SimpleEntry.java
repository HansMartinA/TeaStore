package tools.descartes.teastore.image.cache.entry;

public class SimpleEntry<D extends ICachable<D>>  extends AbstractEntry<D> {
public  SimpleEntry(D data){
super(data);
}
@Override
public  void wasUsed() {
}

}
