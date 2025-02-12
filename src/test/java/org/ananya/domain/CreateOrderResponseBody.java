package org.ananya.domain;

public class CreateOrderResponseBody {
    private boolean created;
    private String orderId;


    public boolean getCreated() {
        return created;
    }

    public void setCreated(boolean created) {
        this.created = created;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
