package clases

//Uno de los producto a manejar son las donas, esta clase define las donas
enum class Flavor {CARAMELO, CANELA, COCO, MOCA, VAINILLA, AZUCAR}
val data = Data()
class Donut(var tamaño: Size, val sabor: Flavor): Product("Donut") {

    override var quantity = 0
    override var price = data.productList.getValue("donas")
    override var size = tamaño



//Función para calcular el precio de la cantidad de donas en base a su tamaño
    override fun subTotal(cantidad: Int): Float {
        this.quantity = cantidad
        var total = super.subTotal(cantidad)
        println("Tu pedido son: $cantidad donas $tamaño sabor $sabor, con un subtotal de: $total ")
        return total
    }

}