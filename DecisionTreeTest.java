package sol;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;
import org.junit.Before;
import src.DecisionTreeCSVParser;
import src.Row;

import java.util.ArrayList;
import java.util.List;

public class DecisionTreeTest {

    // Constructor for DecisionTreeTest tester class - don't need to add anything in here!
    public DecisionTreeTest() {

    }

    @Before
    public void setupData() {

    }

    @Test
    public void testExample() {
        assertEquals(6, 1 + 2 + 3);
    }

    // TODO: Add your tests here!
    @Test
    public void tryDataset() {
        List<Row> dataObjects = DecisionTreeCSVParser.parse("C:\\cs200\\projects\\decision-tree-maryamia-mebeleon\\data\\fvtest.csv");
        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
        Dataset training = new Dataset(attributeList, dataObjects);

        // builds a TreeGenerator object and generates a tree for "foodType"
        TreeGenerator generator = new TreeGenerator();
        generator.generateTree(training, "foodType");

        // makes a new (partial) Row representing the tangerine from the example
        Row tangerine = new Row();
        tangerine.setAttributeValue("color", "orange");
        tangerine.setAttributeValue("highProtein", "false");
        tangerine.setAttributeValue("calories", "high");
        tangerine.setAttributeValue("foodType", "fruit");

        // uses the decision tree generated on line 6 to make a decision
        String decision = generator.getDecision(tangerine);

//the following prints "fruit"
        System.out.println(decision);
    }
}
