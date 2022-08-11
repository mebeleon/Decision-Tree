package sol;

import org.junit.Before;
import org.junit.Test;
import src.DecisionTreeCSVParser;
import src.Row;

import java.util.ArrayList;
import java.util.List;

public class Testing {
    List<Row>  dataObjects2;//dataObjects1,
    List<String> attributeList2;//attributeList1,
    Dataset testerMain; //tester,

    @Before
    public void setupData() {
        //this.dataObjects1 = DecisionTreeCSVParser.parse("C:\\cs200\\projects\\decision-tree-maryamia-mebeleon\\data\\mushrooms\\training.csv");
        //this.attributeList1 = new ArrayList<>(dataObjects1.get(0).getAttributes());
        this.dataObjects2 = DecisionTreeCSVParser.parse("C:\\cs200\\projects\\decision-tree-maryamia-mebeleon\\data\\fvtest.csv");
        this.attributeList2 = new ArrayList<>(dataObjects2.get(0).getAttributes());
        //this.tester = new Dataset(attributeList1, dataObjects1);
        this.testerMain = new Dataset(attributeList2, dataObjects2);
    }

    @Test
    public void testAttributes() {
       // System.out.println(tester.getAttributeList());
        System.out.println(testerMain.getAttributeList());
    }

    @Test
    public void testGetObjects() {
        //System.out.println(tester.getDataObjects());
        System.out.println(testerMain.getDataObjects());
    }

    @Test
    public void testSize() {
        //System.out.println(tester.size());
        System.out.println(testerMain.size());
    }

  //  @Test
    //public void testSameValue() {
       // System.out.println(testerMain.sameValues("green"));
   // }

   // @Test
   // public void testPossValues() {
        //System.out.println(testerMain.possValues("calories", new ArrayList<>()));
   // }

    //@Test
  //  public void testPossValue() {
     //   System.out.println(testerMain.possValue("calories"));
   // }

}
