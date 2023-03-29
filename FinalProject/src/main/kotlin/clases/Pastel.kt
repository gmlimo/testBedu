package clases

var data = Data()

    class Pastel(var tamaño: Size, override val flavor: String): Product("Pastel") {

        protected var sabor = ""
        override var quantity = 0
        override var price = data.productList.getValue("Pastel")
        override var size = tamaño

        init {
            this.sabor = flavor
        }

        //Función para calcular el precio de la cantidad de pasteles en base a su tamaño
        override fun subTotal(cantidad: Int): Float {
            this.quantity = cantidad
            var total = super.subTotal(cantidad)
            println("Tu pedido son: $cantidad Pasteles $tamaño con un subtotal de: $total ")
            return total
        }
    }
}