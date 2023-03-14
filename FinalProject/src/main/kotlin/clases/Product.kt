package clases

//Clase abstracta que se para definir el producto en su aspecto general
abstract class Product(open val name: String) {

    //Todos los productos tienen un sabor, tamaño, precio y se especifíca una cantidad
    abstract val flavor: String
    open protected var size: String = ""
    open protected var price: Float = 0.0f
    open protected var quantity: Int = 0

    //Función que calcula la cantidad a pagar de un producto génerico en base a la cantidad y tamaño
    open fun subTotal(cantidad: Int): Float {
        this.quantity = cantidad
        var total: Float = 0.0f
        if (size == "Small") {
            total = price * cantidad
        }
        else {
            total = (1.2 * cantidad * price).toFloat()
        }
       // println("El total es: $total")
        return total
    }
}