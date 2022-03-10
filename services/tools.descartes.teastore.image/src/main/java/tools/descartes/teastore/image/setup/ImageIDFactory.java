package tools.descartes.teastore.image.setup;

import java.util.concurrent.atomic.AtomicLong;
public enum ImageIDFactory {
ID ,
;

private AtomicLong nextID = new  AtomicLong(1);

private  ImageIDFactory(){
}
public  long getNextImageID() {
return nextID.getAndIncrement();
}

public  void startAtID(long nextID) {
this.nextID.set(nextID);
}

}
