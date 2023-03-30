package clases

//Uno de los producto a manejar son las donas, esta clase define las donas

var data = Data()
class Donut(var tamaño: Size, override val flavor: String): Product("Donut") {

    protected var sabor = ""
    override var quantity = 0
    override var price = data.productList.getValue("donas")
    override var size = tamaño

    init {
        this.sabor = flavor
    }

//Función para calcular el precio de la cantidad de donas en base a su tamaño
    override fun subTotal(cantidad: Int): Float {
        this.quantity = cantidad
        var total = super.subTotal(cantidad)
        println("Tu pedido son: $cantidad donas $tamaño con un subtotal de: $total ")
        return total
    }

}