package com.taufik.model;

/**
 * @author taufik.budiyanto
 * @date 05/01/2021
 * com.taufik.model
 */
public class TaxSchema {
    private Double taxRate;
    private Double min;
    private Double max;

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }
}
