package edu.upbc.beducoffee.files

//Uno de los producto a manejar son las donas, esta clase define las donas

class Donut(override val flavor: String): Product("Donut") {



    //Función para calcular el precio de la cantidad de donas en base a su tamaño
    override fun subTotal(cantidad: Int, tamaño: String, price: Float): Float {
        var total = super.subTotal(cantidad, tamaño, price)
        return total
    }

}