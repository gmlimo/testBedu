package clases

//El otro producto es el café y se define en esta clase
enum class JARABES { CARAMELO, VAINILLA, MOCA, }
enum class CAFES { LATTE, CAPUCCINO, AMERICANO }
class Coffee(var tamaño: Size, var tipodecafe: CAFES,  var jarabe : JARABES): Product("Coffee") {

    //protected var sabor = ""
    open public override var quantity = 0
    override var price = data.productList.getValue("café")
    override var size = tamaño

    //init {
      //  this.sabor = flavor
    //}

    //Esta función calcula el precio total del café basado en la cantidad y el tamaño
    override fun subTotal(cantidad: Int): Float {
        this.quantity = cantidad
        var total = super.subTotal(cantidad)
        println("Tu pedido son: $cantidad tazas de café $tipodecafe $tamaño con un subtotal de: $total ")
        return total
    }

}