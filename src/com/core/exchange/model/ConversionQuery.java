package com.core.exchange.model;
public class ConversionQuery {
    private final String baseCurrency;
    private final String targetCurrency;
    private final double amount;
    public ConversionQuery(String baseCurrency, String targetCurrency, double amount) {
        this.baseCurrency = baseCurrency.toUpperCase().trim();
        this.targetCurrency = targetCurrency.toUpperCase().trim();
        this.amount = amount;
    }
    public String getBaseCurrency() { return baseCurrency; }
    public String getTargetCurrency() { return targetCurrency; }
    public double getAmount() { return amount; }
}