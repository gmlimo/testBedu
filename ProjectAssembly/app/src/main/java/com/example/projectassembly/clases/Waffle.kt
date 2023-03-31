import com.example.projectassembly.clases.Data
import com.example.projectassembly.clases.Product

val data = Data()
class Waffle(override val flavor: String): Product("Waffle") {

    protected var sabor = ""
    override var quantity = 0
    override var price = data.productList.getValue("waffle")
    //override var size = tamaño

    init {
        this.sabor = flavor
    }

    //Función para calcular el precio de la cantidad de waffle en base a su tamaño
    override fun subTotal(cantidad: Int, tamaño: String): Float {
        this.quantity = cantidad
        var total = super.subTotal(cantidad, tamaño)
        println("Tu pedido son: $cantidad waffles $tamaño con un subtotal de: $total ")
        return total
    }

}