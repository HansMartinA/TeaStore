package tools.descartes.teastore.persistence.repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class DatabaseManagementEntity {
@Id
@GeneratedValue
private long id;

private boolean finishedGenerating;

 DatabaseManagementEntity(){
finishedGenerating = false;
}
 long getId() {
return id;
}

public  boolean isFinishedGenerating() {
return finishedGenerating;
}

public  void setFinishedGenerating(boolean finishedGenerating) {
this.finishedGenerating = finishedGenerating;
}

}
