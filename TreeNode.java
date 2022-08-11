package sol;

import src.Row;

import java.util.List;

public class TreeNode implements ITreeNode
{

    private String attribute;
    private List<Pointer> pointerList;
    private String defaultVal;

    public TreeNode(String attribute, List<Pointer> pointerList, String defaultVal) {
        this.attribute = attribute;
        this.pointerList = pointerList;
        this.defaultVal = defaultVal;
    }

    /**
     * Looks up the decision for a datum in the decision tree.
     *
     * @param datum the datum to lookup a decision for
     * @return the decision of the row
     */

    @Override
    public String getDecision(Row datum) {
        String rel = this.attribute;
        String val = datum.getAttributeValue(rel);
        for (Pointer p : this.pointerList) {
            if (p.getAttribute().equals(val)) {
            while (!pointerList.isEmpty()) {
                return p.getNext().getDecision(datum); }
            }
        }
        return this.attribute;
    }
}
