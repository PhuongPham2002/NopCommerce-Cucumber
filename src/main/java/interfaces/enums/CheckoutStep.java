package interfaces.enums;

public enum CheckoutStep {
    SHIPPING_METHOD("shipping-method","shipping-method","Shipping method"),
    SHIPPING_ADDRESS("shipping","new-address","Shipping address"),
    BILLING("billing","new-address","Billing address"),
    PAYMENT("payment-method","payment-method","Payment method"),
    PAYMENT_INFO("payment-info","payment-info","Payment information");


    String idPrefix;
    String classPrefix;
    String displayName;



    CheckoutStep(String idPrefix,String classPrefix,String displayName ){
        this.idPrefix=idPrefix;
        this.classPrefix=classPrefix;
        this.displayName=displayName;
    }

    public String getLocator(String locatorTemplate){
        return String.format(locatorTemplate,idPrefix,classPrefix);

    }

    public static CheckoutStep fromDisplayName(String name) {
        for (CheckoutStep step : CheckoutStep.values()) {
            if (step.displayName.equalsIgnoreCase(name.trim())) {
                return step;
            }
        }
        throw new IllegalArgumentException("Invalid checkout step: " + name);
    }

}
