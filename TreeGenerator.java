
package sol;

import src.ITreeGenerator;
import src.Row;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * A class that implements the ITreeGenerator interface
 * used to generate a tree
 */

public class TreeGenerator implements ITreeGenerator<Dataset> { //implements ITreeGenerator<Dataset> {
    // TODO: Implement methods declared in ITreeGenerator interface!

        private ITreeNode head;

    /**
     * Recursive helper that generates a tree as a collection of nodes and leaves
     * @param trainingData The overall dataset to be used in tree creation
     * @param targetAttribute The attribute whose final outcome is being predicted (a value of this will
     *                        be in all leaves)
     * @return a root node that all other nodes and leaves are connected to
     */
        public ITreeNode subtree(Dataset trainingData, String targetAttribute) {
            if (trainingData.allSameValue(targetAttribute, new ArrayList<>()) | trainingData.getAttributeList().size() == 0) {
                return new Leaf(targetAttribute, trainingData.leafValue(targetAttribute));
            }
            // define the node's attribute
            String attribute = trainingData.pickAttribute(targetAttribute);

            // define the list of pointers
            List<Pointer> pointerList = new ArrayList<Pointer>();

            // list of possible values to use in building pointer list:
            List<String> possibleValues = trainingData.possValues(targetAttribute);

            //list of datasets to be used when making pointers
            List<Dataset> subdata = trainingData.partition(attribute);

            //compute the default value of the node:
            // = most common value of the targetAttribute in the subdata being used

            //if all rows have same value or no unused attributes left to split on, make a leaf

                {
                    // for each filtered dataset:
                    for (Dataset d : subdata) {
                        // for each possible value of a given attribute
//                        for (String val : d.possValues(attribute)) {
                            Pointer p = new Pointer(d.getFirstVal(attribute),
                                    this.subtree(d, targetAttribute));
                            //trainingData.pickAttribute(targetAttribute)));
                            pointerList.add(p);
                     //   }
                    }
            }
            return new TreeNode(attribute, pointerList, trainingData.leafValue(targetAttribute));
        }

    /**
     * Generates a tree and stores it in the head field
     * @param trainingData    the dataset to train on
     * @param targetAttribute the attribute to predict
     */
    @Override
    public void generateTree(Dataset trainingData, String targetAttribute) {
        trainingData.getAttributeList().remove(targetAttribute);
        this.head = this.subtree(trainingData, targetAttribute);
    }

    /**
     * Looks up the decision for a datum in the decision tree.
     * @param datum the datum to look up a decision for
     * @return the decision of the row
     */
    @Override
    public String getDecision(Row datum) {
        return this.head.getDecision(datum);
    }

}
