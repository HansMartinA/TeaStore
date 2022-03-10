package tools.descartes.teastore.recommender.algorithm.impl;

import java.util.ArrayList;
import java.util.List;
import tools.descartes.teastore.recommender.algorithm.AbstractRecommender;
public class DummyRecommender extends AbstractRecommender {
@Override
protected  List<Long> execute(Long userid, List<Long> currentItems) {
List<Long> recommended = new  ArrayList<Long>();
recommended.add( - 1L);
return recommended;
}

}
