package com.example.projectassembly.clases

class CartItem(
    private var imageResourceId: Int,
    private var product: String,
    private var price: Float,
) {

    private var buttonResourceId: Int = 0

    //Setters
    fun setImage(ID: Int) {
        this.imageResourceId = ID
    }
    fun setProduct(product: String) {
        this.product = product
    }
    fun setPrice(price: Float){
        this.price = price
    }

    //Getters
    fun getImage() = this.imageResourceId
    fun getProduct() = this.product
    fun getPrice() = this.price.toString()
}