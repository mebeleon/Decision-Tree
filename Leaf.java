package sol;

import src.Row;

public class Leaf implements ITreeNode {
    private String attribute;
    private String finalDecision;

    public Leaf(String targetAttribute, String finalDecision) {
        this.attribute = targetAttribute;
        this.finalDecision = finalDecision;
    }

    /**
     * Recursively traverses decision tree to return tree's decision for a row.
     *
     * @param forDatum the datum to lookup a decision for
     * @return the decision tree's decision
     */
    public String getDecision(Row forDatum) {
        return this.finalDecision;
    }

}