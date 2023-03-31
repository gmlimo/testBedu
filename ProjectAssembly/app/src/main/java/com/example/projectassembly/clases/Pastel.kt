import com.example.projectassembly.clases.Data
import com.example.projectassembly.clases.Product

class Pastel(override val flavor: String): Product("pastel") {

    protected var sabor = ""
    override var quantity = 0
    override var price = data.productList.getValue("pastel")
    //override var size = tamaño

    init {
        this.sabor = flavor
    }

    //Función para calcular el precio de la cantidad de pastel en base a su tamaño
    override fun subTotal(cantidad: Int, tamaño: String): Float {
        this.quantity = cantidad
        var total = super.subTotal(cantidad, tamaño)
        println("Tu pedido son: $cantidad pastel $tamaño con un subtotal de: $total ")
        return total
    }

}