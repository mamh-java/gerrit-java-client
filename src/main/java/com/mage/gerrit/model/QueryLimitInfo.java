package com.mage.gerrit.model;

/**
 * The QueryLimitInfo entity contains information about the Query Limit of a user.
 */
public class QueryLimitInfo extends BaseModel {
    /**
     * Lower limit.
     */
    private int min;

    /**
     * Upper limit.
     */
    private int max;


    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
