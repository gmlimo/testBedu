package clases

data class Data(
    //Map of the Products Available
    var productList: MutableMap<String, Double> = mutableMapOf(
        "café" to 42.50,
        "donas" to 12.40
    ),

    //Map of pre-registered user name and password
    var loginData: MutableMap<String, String> = mutableMapOf(
        "1234" to "gmlimon",
        "hola" to "teaby",
        "5678" to "mbravo"
    )
)
