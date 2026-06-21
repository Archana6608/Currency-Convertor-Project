package com.core.exchange.model;
public final class ConversionResult {
    private final String baseCurrency;
    private final String targetCurrency;
    private final double originalAmount;
    private final double convertedAmount;
    private final double exchangeRate;
    public ConversionResult(String baseCurrency, String targetCurrency, double originalAmount, double convertedAmount, double exchangeRate) {
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
        this.originalAmount = originalAmount;
        this.convertedAmount = convertedAmount;
        this.exchangeRate = exchangeRate;
    }
    public String getBaseCurrency() { return baseCurrency; }
    public String getTargetCurrency() { return targetCurrency; }
    public double getOriginalAmount() { return originalAmount; }
    public double getConvertedAmount() { return convertedAmount; }
    public double getExchangeRate() { return exchangeRate; }
}