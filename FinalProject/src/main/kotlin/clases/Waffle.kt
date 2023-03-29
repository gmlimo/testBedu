package clases

val data = Data()
class Waffle (var tamaño: Size, override val flavor: String): Product("Waffle"){

    protected var sabor = ""
    override var quantity = 0
    override var price = data.productList.getValue("Waffle")
    override var size = tamaño

    init {
        this.sabor = flavor
    }

    //Función para calcular el precio de la cantidad de donas en base a su tamaño
    override fun subTotal(cantidad: Int): Float {
        this.quantity = cantidad
        var total = super.subTotal(cantidad)
        println("Tu pedido son: $cantidad Waffles $tamaño con un subtotal de: $total ")
        return total
    }

}