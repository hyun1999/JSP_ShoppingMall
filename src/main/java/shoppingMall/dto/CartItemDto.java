package shoppingMall.dto;


public class CartItemDto {
    private int itemId;
    private String productId;
    private int quantity;
    private int amount;

    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }

    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }
}
