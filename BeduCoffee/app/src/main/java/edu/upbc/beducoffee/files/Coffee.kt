package edu.upbc.beducoffee.files

//El otro producto es el café y se define en esta clase
class Coffee(override val flavor: String): Product("Coffee") {



    //Esta función calcula el precio total del café basado en la cantidad y el tamaño
    override fun subTotal(cantidad: Int, tamaño: String, price: Float): Float {
        var total = super.subTotal(cantidad, tamaño, price)
        println("Tu pedido son: $cantidad tazas de café $tamaño con un subtotal de: $total ")
        return total
    }

}