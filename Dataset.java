package sol;

import src.IDataset;
import src.Row;

import java.util.*;

/**
 * A class that implements the IDataset interface,
 * representing a training data set.
 */
public class Dataset implements IDataset {
    // TODO: Implement methods declared in IDataset interface!
    private List<String> attributes;
    private List<Row> rowObjects;
    private List<String> usedAttributes;

    /**
     * Constructor to make a dataset object
     * @param attributes a list of attributes a row has values for
     * @param rowObjects a list of objects as rows, with a value for each attribute
     */
    public Dataset(List<String> attributes, List<Row> rowObjects) {
        this.attributes = attributes;
        this.rowObjects = rowObjects;
        this.usedAttributes = new ArrayList<>();

    }


    /**
     * @return the row of a one row dataset
     */
    public Row getRow() {
        return this.rowObjects.get(0);
    }


    /**
     * Gets list of attributes in the dataset
     *
     * @return a list of strings
     */
    @Override
    public List<String> getAttributeList() {
        Set<String> atts = this.rowObjects.get(0).getAttributes();
        ArrayList<String> attVals = new ArrayList<>();
        attVals.addAll(atts);
        return attVals;
    }

    /**
     * Gets list of data objects (row) in the dataset
     *
     * @return a list of Rows
     */
    @Override
    public List<Row> getDataObjects() {
        return this.rowObjects;
    }

    /**
     * finds the size of the dataset (number of rows)
     *
     * @return the number of rows in the dataset
     */
    @Override
    public int size() {
        MutableList<Row> rows = new MutableList<Row>();
        for (Row r : this.rowObjects) {
            rows.addLast(r);
        }
        return rows.size();
    }

    /**
     * Filters a dataset for all the rows that contain the same value for a certain attribute
     * @param value The attribute value being looked for
     * @return a dataset that only contains rows that have the given attribute value
     */

    public Dataset filter(String value) {
        List<Row> sameRows = new ArrayList<Row>();
        for (Row r : this.rowObjects) {
            if (r.sameAttribute(value)) {
                sameRows.add(r);
            }
        }
        return new Dataset(this.attributes, sameRows);
    }

   /* public List<String> possValues(String attribute) {
        List<String> values = new ArrayList<String>();
        for (Row r : this.rowObjects) {
            values.add(r.getAttributeValue(attribute));
        }
        Set<String> vals = new HashSet<>(values);
        values.clear();
        values.addAll(vals);
        return values;
    } */

    /**
     * Return a list of the unique possible values an attribute can have
     * @param attribute The attribute whose values are being looked for
     * @return A list of unique possible values
     */
    public List<String> possValues(String attribute) {
        List<String> values = new ArrayList<>();
        for (Row r : this.rowObjects) {
            if (!values.contains(r.getAttributeValue(attribute))) {
                values.add(r.getAttributeValue(attribute));
            }
        } return values;}

    /**
     * Selects a random attribute for a node to have and thus for tree to split on
     * @param target The final target attribute (which cannot be split on)
     * @return a String that represents an attribute that can be used to split on
     */
    public String pickAttribute(String target) {
        List<String> attributes = this.getAttributeList();
        attributes.remove(target);
        Random random = new Random();
        int randomNum = random.nextInt(attributes.size());
        String a = attributes.get(randomNum);
        if (!this.usedAttributes.contains(a)) {
            this.usedAttributes.add(a); }
        return a;
    }
    //else { return this.pickAttribute(target);}


    /**
     * Determines if row objects have the same value for a given
     * attribute
     * @return True if all rows in a dataset have the same value
     */

    public boolean allSameValue(String attribute, List<String> values) {
        for (Row r : this.rowObjects) {
            values.add(r.getAttributeValue(attribute));
        }
        for (String v : values) {
            if (!v.equals(values.get(0)))
                return false;
        }
        return true;
    }

    /**
     * Filters datasets based on whether rows all have the same value for a given attribute
     * @param nodeAttribute The attribute whose values are being compared
     * @return a list of datasets, each with rows of a possible value of the attribute
     */
    public List<Dataset> subDatasets(String nodeAttribute) {
        List<String> values = this.possValues(nodeAttribute);
        List<Dataset> datasetList = new ArrayList<>();
        for (String v : values) {
            Dataset d = this.filter(v);
            datasetList.add(d);
        }
        return datasetList;
    }

  /*  public String leafValue(String nodeAttribute) {
        List<Dataset> dList = this.subDatasets(nodeAttribute);
        String val = null;
        for (Dataset d : dList) {
            if (d.allSameValue(nodeAttribute)) {
                val = d.rowObjects.get(0).getAttributeValue(nodeAttribute);
            } else {
                return this.possValue(nodeAttribute);
            }
        }
        return val;
    } */

    /**
     * Finds the most common value of a dataset(ie only possible outcome) to be
     * used as the value of a leaf
     * @param attribute The attribute whose final decision is being looked for
     * @return A string representing the only possible outcome for the attribute
     */

    public String leafValue(String attribute) {
        List<Dataset> datasetList = this.subDatasets(attribute);
        int count = 0;
        String value = null;
        for (Dataset d : datasetList) {
            int l = d.size();
            if (l > count) {
                count = count + 1;
                value = d.rowObjects.get(0).getAttributeValue(attribute);
            }
        }
        return value;
    }

   /* public String mostCommonValue(String targetAttribute) {
        String val = null;
if (this.allSameValue(targetAttribute)) {
    val = this.rowObjects.get(0).getAttributeValue(targetAttribute);
    }
        return val;
    }

    /* public List<Dataset> partition(String attribute) {
        List<String> values = this.possValues(attribute);
        List<Row> rows = new ArrayList<>();
        List<Dataset> datasets = new ArrayList<>();
        for (String v : values) {
            for (Row r : this.rowObjects) {
                if (r.getAttributeValue(attribute).equals(v)) {
                    rows.add(r);
                }
            }
        }
        Dataset d = new Dataset(this.getAttributeList(), rows);
        datasets.add(d);
        return datasets;
    }

   /* public String defaultValue(String targetAttribute){
         */

}
