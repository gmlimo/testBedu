import clases.Coffee
import clases.Data
import clases.Donut
import clases.User

var datos = Data()
val iva = 1.08f
var costoDonas = 0.0f
var costoCafe = 0.0f

fun main(){

    //val donas = Donut("Small", "Chocolate")
    //costoDonas = donas.subTotal(2)

   /* val cafe = Coffee("Small", "Capuchino")
    costoCafe = cafe.subTotal(2)

    var total = (costoDonas + costoCafe) * iva
    println("El total a pagar es: $total")
*/


    val user = User("William", 2, "1234", "wlimon@cituspower.com")

    //Receive user and password
    print("Escribe tu usuario: ")
    var usuario = readLine()!!
    print("Escribe tu contraseña: ")
    var contraseña = readLine()!!

    /*The sucessfull login prompt you with the catalog and gives you options to buy.
    **Fail to login give you incorrect message
     */
    if (user.login(usuario, contraseña)){
        println("Login exitoso")
        //println("Estos son nuestros productos: $productList")
        println(" (1) Café \n (2) Donas")
        print("Selecciona la opción correcta: 1 es para Café 2 es para Donas: ")
        var selección = readLine()!!.toInt()

        //Review the options and sets the correct key to get the proper value(price) of the map
       val selcadena = when (selección){
           1 -> "café"
           2 -> "donas"
           else -> "No existe producto"
        }
        if (selección > 2) {
            println(selcadena)
            return
        }
        println("El precio de $selcadena es: ${datos.productList.getValue(selcadena)}")
    } else println("Usuario y/o contraseña incorrectos")


/*  //Login function test
    println(login("teaby", "hola"))

    var precio = productList.getValue("coffee")
    println("El precio del producto es: $precio") */


}


