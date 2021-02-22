package app.gui

import lib.sRAD.gui.component.Resource.WSFS
import lib.sRAD.gui.sComponent.SFrame
import lib.sRAD.gui.sComponent.SLabel
import lib.sRAD.gui.sComponent.SPanel
import lib.sRAD.gui.sComponent.SScrollPane
import lib.sRAD.logic.Extension.toCOP
import lib.sRAD.logic.Extension.toPTJ
import kotlin.random.Random

object Frame: SFrame() {
    private val pInformacion = SPanel(SPanel.INTERNO, 100, 60, 760, 1664)

    init {
        val scroll = SScrollPane(100, 60, 800, 620, pInformacion)
        add(scroll)
        setMainBar("Simulación de Monte Carlo")
        setProperties(ESTANDAR)
    }

    fun actualizar() {
        //vacía el panel de información
        pInformacion.removeAll()

        //carga la información
        val lVD = SLabel(32, 32, 600, 32, "Parámetros:", WSFS)
        val lCP = SLabel(64, 64, 600, 28, "Cantidad de productos: ${App.getCantidadProductos()}")
        val lCCO1 = SLabel(64, 96, 600, 28, "Costo de operación 1: ${App.getCostoOp1()} por minuto")
        val lCCO2 = SLabel(64, 128, 600, 28, "Costo de operación 2: ${App.getCostoOp2()} por minuto")
        val lCCM1 = SLabel(64, 160, 600, 28, "Costo de muestra 1: ${App.getCostoM1()} por unidad")
        val lCCM2 = SLabel(64, 192, 600, 28, "Costo de muestra 2: ${App.getCostoM2()} por minuto")
        val lEM1 = SLabel(64, 224, 600, 28, "Eficiencia de muestra 1: ${App.getEficienciaM1()}")
        val lEM2 = SLabel(64, 256, 600, 28, "Eficiencia de muestra 2: ${App.getEficienciaM2()}")
        val lIG = SLabel(64, 288, 600, 28, "Inflación general: ${App.getInflacion()}")
        val lIP = SLabel(64, 320, 600, 28, "Porcentaje en impuestos por producto vendido: ${App.getPorcentajeImpuesto()}")

        val lDS = SLabel(32, 384, 600, 32, "Datos:", WSFS)
        val lCS = SLabel(64, 416, 600, 28, "Clientes satisfechos: ${App.getSatisfechos()} de ${App.getClientes()}")

        val lC = SLabel(32, 480, 600, 32, "Costos:", WSFS)
        val lCPBN = SLabel(64, 512, 600, 28, "Costos por pérdida del buen nombre: ${App.getCostosPBN()}")
        val lCR = SLabel(64, 544, 600, 28, "Costos por reclamos: ${App.getCostosReclamo()}")
        val lCI = SLabel(64, 576, 600, 28, "Costos de impuestos: ${App.getCostosImpuesto()}")
        val lCF = SLabel(64, 608, 600, 28, "Costos fijos: ${App.getCostosFijo()}")
        val lCO1 = SLabel(64, 640, 600, 28, "Costos de operación 1: ${App.getCostosOp1()}")
        val lCM1 = SLabel(64, 672, 600, 28, "Costos de muestra 1: ${App.getCostosMuestra1()}")
        val lCO2 = SLabel(64, 704, 600, 28, "Costos de operación 2: ${App.getCostosOp2()}")
        val lCM2 = SLabel(64, 736, 600, 28, "Costos de muestra 2: ${App.getCostosM2()}")
        val lCT = SLabel(64, 768, 600, 28, "Costos de taller: ${App.getCostosTaller()}")
        val lCV = SLabel(64, 800, 600, 28, "Costos variables: ${App.getCostosVariable()}")

        val lEF = SLabel(32, 864, 600, 32, "Estados finales de productos:", WSFS)
        val lE1 = SLabel(64, 896, 600, 28, "Productos desechos: ${App.getCantidadProductos("D")}")
        val lE2 = SLabel(64, 928, 600, 28, "Productos conformes: ${App.getCantidadProductos("PC")}")
        val lE3 = SLabel(64, 960, 600, 28, "Productos que requieren re-proceso 1: ${App.getCantidadProductos("R1")}")
        val lE4 = SLabel(64, 992, 600, 28, "Productos que requieren re-proceso 2: ${App.getCantidadProductos("R2")}")
        val lE5 = SLabel(64, 1024, 600, 28, "Productos que requieren reparación: ${App.getCantidadProductos("RP")}")
        val lE6 = SLabel(64, 1056, 600, 28, "Productos de re-clasificación: ${App.getCantidadProductos("RC")}")

        val lT = SLabel(32, 1120, 600, 32, "Tiempos:", WSFS)
        val lT1 = SLabel(64, 1152, 600, 28, "Tiempo total requerido en operación 1: ${App.getTiemposOp1()} minutos")
        val lT2 = SLabel(64, 1184, 600, 28, "Tiempo total requerido en operación 2: ${App.getTiemposOp2()} minutos")
        val lT3 = SLabel(64, 1216, 600, 28, "Tiempo total requerido en muestra 1: ${App.getTiemposM1()} minutos")
        val lT4 = SLabel(64, 1248, 600, 28, "Tiempo total requerido en muestra 2: ${App.getTiemposM2()} minutos")
        val lT5 = SLabel(64, 1280, 600, 28, "Tiempo total requerido en taller: ${App.getTiemposT()} minutos")
        val lT6 = SLabel(64, 1312, 600, 28, "Tiempo promedio por producto en operación 1: ${App.getTiempoOp1()} minutos")
        val lT7 = SLabel(64, 1344, 600, 28, "Tiempo promedio por producto en operación 2: ${App.getTiempoOp2()} minutos")
        val lT8 = SLabel(64, 1376, 600, 28, "Tiempo promedio por producto en muestra 1: ${App.getTiempoM1()} minutos")
        val lT9 = SLabel(64, 1408, 600, 28, "Tiempo promedio por producto en muestra 2: ${App.getTiempoM2()} minutos")
        val lT10 = SLabel(64, 1440, 600, 28, "Tiempo promedio por producto en el taller: ${App.getTiempoT()} minutos")

        val lAR = SLabel(32, 1504, 600, 32, "Análisis de riesgo:", WSFS)
        val lCN = SLabel(64, 1536, 600, 28, "Capital necesario: ${App.getCapitalNecesario()}")
        val lI = SLabel(64, 1568, 600, 28, "Ingresos antes de impuestos: ${App.getPreIngresos()}")
        val lID = SLabel(64, 1600, 600, 28, "Ingresos después de impuestos: ${App.getPosIngresos()}")

        //los muestra
        pInformacion.add(lVD)
        pInformacion.add(lCP)
        pInformacion.add(lCCO1)
        pInformacion.add(lCCO2)
        pInformacion.add(lCCM1)
        pInformacion.add(lCCM2)
        pInformacion.add(lEM1)
        pInformacion.add(lEM2)
        pInformacion.add(lIG)
        pInformacion.add(lIP)

        pInformacion.add(lDS)
        pInformacion.add(lCS)

        pInformacion.add(lC)
        pInformacion.add(lCPBN)
        pInformacion.add(lCR)
        pInformacion.add(lCI)
        pInformacion.add(lCF)
        pInformacion.add(lCO1)
        pInformacion.add(lCM1)
        pInformacion.add(lCO2)
        pInformacion.add(lCM2)
        pInformacion.add(lCT)
        pInformacion.add(lCV)

        pInformacion.add(lEF)
        pInformacion.add(lE1)
        pInformacion.add(lE2)
        pInformacion.add(lE3)
        pInformacion.add(lE4)
        pInformacion.add(lE5)
        pInformacion.add(lE6)

        pInformacion.add(lT)
        pInformacion.add(lT1)
        pInformacion.add(lT2)
        pInformacion.add(lT3)
        pInformacion.add(lT4)
        pInformacion.add(lT5)
        pInformacion.add(lT6)
        pInformacion.add(lT7)
        pInformacion.add(lT8)
        pInformacion.add(lT9)
        pInformacion.add(lT10)

        pInformacion.add(lCN)
        pInformacion.add(lAR)
        pInformacion.add(lI)
        pInformacion.add(lID)

        //repinta
        repaint()
    }
}

//tiempo en muestras, en operaciones, total y promedios. costo por pbn
object App {
    //parametros
    private var costoOp1 = 78.0 // $/min
    private var costoOp2 = 82.0 // $/min
    private var costoM1 = 7.0 // $/unidad
    private var costoM2 = 78.0 // $/min
    private var costoTaller =53.0 // $/min
    private var cantidadProductos = 0

    private var inflacion = 0.0
    private var porcentajeImpuesto = 0.0
    private var eficienciaM1 = 0.50
    private var eficienciaM2 = 0.50

    //contadores
    private var costoMuestra1 = 0.0
    private var costoReclamo = 0.0
    private var costoFijo = 0.0
    private var costoPBN = 0.0
    private var costoImpuestos = 0.0

    private var tiemposMuestra1 = 0.0
    private var ingreso = 0.0
    private var clientes = 0
    private var satisfechos = 0
    //productos
    private var productos = mutableListOf<Producto>()

    //importar el porcentaje de impuesto
    fun simular (cantidadProductos: Int, eficienciaM1: Double, eficienciaM2: Double) {
        //almacena valores
        this.eficienciaM1 = eficienciaM1
        this.eficienciaM2 = eficienciaM2
        this.cantidadProductos = cantidadProductos
        //inicializa contadores
        tiemposMuestra1 = 0.0
        costoMuestra1 = 0.0
        costoReclamo = 0.0
        costoPBN = 0.0
        ingreso = 0.0
        clientes = 0
        satisfechos = 0
        productos.clear()
        //inicia simulación
        for (i in 0 until cantidadProductos) {
            //crea el producto
            productos.add(Producto())
            //lo inserta en el modelo
            operacion1(productos.last())
        }
        //grafica
        Frame.actualizar()
    }

    fun operacion1 (producto: Producto) {
        //establece estado
        if (producto.estado == "R1") {
            producto.estado = "PC"
        }
        else if(Random.nextDouble() < 0.84) {
            producto.estado = "PC"
        }
        else {
            if (Random.nextDouble() < 0.18125) {
                producto.estado = "D"
            }
            else {
                producto.estado = "R1"
            }
        }
        //calcula tiempos
        producto.tiempoOp1 += 4.3 + Random.nextDouble()*(7.1-4.3)
        //envia producto a muestra 1
        muestra1 (producto)
    }

    fun muestra1 (producto: Producto) {
        if (Random.nextDouble() < 1 - eficienciaM1) {
            operacion2(producto)
        }
        else {
            if (producto.estado == "C") {
                operacion2(producto)
            }
            else if (producto.estado == "R") {
                operacion1(producto)
            }
            tiemposMuestra1 += 2.5 + Random.nextDouble()*(3.2-2.5)
            costoMuestra1 += costoM1
        }
    }

    fun operacion2(producto: Producto) {
        if (producto.estado == "R2") {
            producto.estado = "PC"
        }
        else if (Random.nextDouble() < 0.927) {
            producto.estado = "PC"
        }
        else {
            val pseudoAleatorio = Random.nextDouble()
            if (pseudoAleatorio < 9.0/73.0) {
                producto.estado = "D"
            }
            else if (pseudoAleatorio < 45.0/73.0) {
                producto.estado = "R2"
            }
            else if (pseudoAleatorio < 62.0/73.0) {
                producto.estado = "RP"
            }
            else {
                producto.estado = "RC"
            }
        }
        //calcula tiempos
        producto.tiempoOp2 += 9.1 + Random.nextDouble()*(11.4-9.1)
        //envia los productos a la muestra 2
        muestra2 (producto)
    }

    fun muestra2 (producto: Producto) {
        if (Random.nextDouble() < 1 - eficienciaM2) {
            producto.precio = 2800.0
            cliente(producto)
        }
        else {
            if (producto.estado == "D") {
                producto.precio = 9.0 //pre-venta
                cliente(producto)
            }
            else if (producto.estado == "RC") {
                producto.precio = 1800.0
                cliente(producto)
            }
            else if (producto.estado == "RP") {
                //taller - calcula tiempos
                producto.tiempoT += 5.2 + Random.nextDouble()*(7.3-5.2)
                producto.precio = 2800.0
                cliente(producto)
            }
            else if (producto.estado == "PC") {
                producto.precio = 2800.0
                cliente(producto)
            }
            else {
                operacion2(producto)
            }
        }
        //calcula tiempos
        producto.tiempoM2 += 3.7 + Random.nextDouble()*(9.9-3.7)
    }

    fun cliente (producto: Producto) {
        //tambien afectar ingreso, e impuestos
        if (producto.estado == "D" && producto.precio > 9.0) {
            if(Random.nextDouble() < 103.0/138.0) {
                //reclama
                costoReclamo += 60.0
            }
            else {
                //no vuelve
                costoPBN += 170.0
            }
        }
        else {
            satisfechos ++
        }
        clientes ++
    }

    fun getCostoOp1(): String {
        return toCOP(costoOp1)
    }

    fun getCostoOp2(): String {
        return toCOP(costoOp2)
    }

    fun getCostoM1(): String {
        return toCOP(costoM1)
    }

    fun getCostoM2(): String {
        return toCOP(costoM2)
    }

    fun getEficienciaM1(): String {
        return toPTJ(eficienciaM1)
    }

    fun getEficienciaM2(): String {
        return toPTJ(eficienciaM2)
    }

    fun getInflacion(): String {
        return toPTJ(inflacion)
    }

    fun getPorcentajeImpuesto(): String {
        return toPTJ(porcentajeImpuesto)
    }

    fun getSatisfechos(): Int {
        return satisfechos
    }

    fun getClientes(): Int {
        return clientes
    }

    fun getCantidadProductos(estado: String): Int {
        var cantidad = 0
        for (i in productos) {
            if(i.estado == estado) {
                cantidad ++
            }
        }
        return cantidad
    }

    fun getCantidadProductos(): Int {
        return productos.size
    }

    fun getCostosPBN(): String {
        return toCOP(costoPBN)
    }

    fun getCostosReclamo(): String {
        return toCOP(costoReclamo)
    }

    fun getCostosImpuesto(): String {
        return toCOP(costoImpuestos)
    }

    fun getCostosFijo(): String {
        return toCOP(costoFijo)
    }

    fun getCostosMuestra1(): String {
        return toCOP(costoM1)
    }

    fun getCostosOp1 (): String {
        return toCOP(getCOp1())
    }

    private fun getCOp1 (): Double {
        var costo = 0.0
        for (i in productos) {
            costo += i.tiempoOp1 * costoOp1
        }
        return costo
    }

    fun getCostosOp2 (): String {
        return toCOP(getCOp2())
    }

    private fun getCOp2(): Double {
        var costo = 0.0
        for (i in productos) {
            costo += i.tiempoOp2 * costoOp2
        }
        return costo
    }

    fun getCostosM2 (): String {
        return toCOP(getCM2())
    }

    private fun getCM2(): Double {
        var costo = 0.0
        for (i in productos) {
            costo += i.tiempoM2 * costoM2
        }
        return costo
    }

    fun getCostosTaller(): String {
        return toCOP(getCT())
    }

    private fun getCT(): Double {
        var costo = 0.0
        for (i in productos) {
            costo += i.tiempoT * costoTaller
        }
        return costo
    }

    fun getCostosVariable(): String {
        return toCOP(getCV())
    }

    private fun getCV(): Double {
        return costoPBN + costoReclamo + costoImpuestos + getCOp1() + costoM1 + getCOp2() + getCM2() + getCT()
    }

    fun getTiemposOp1(): String {
        var tiempo = 0.0
        for (i in productos) {
            tiempo += i.tiempoOp1
        }
        return "%.2f".format(tiempo)
    }

    fun getTiemposOp2(): String {
        var tiempo = 0.0
        for (i in productos) {
            tiempo += i.tiempoOp2
        }
        return "%.2f".format(tiempo)
    }

    fun getTiemposM1(): String {
        return "%.2f".format(tiemposMuestra1)
    }

    fun getTiemposM2(): String {
        var tiempo = 0.0
        for (i in productos) {
            tiempo += i.tiempoM2
        }
        return "%.2f".format(tiempo)
    }

    fun getTiemposT(): String {
        var tiempo = 0.0
        for (i in productos) {
            tiempo += i.tiempoT
        }
        return "%.2f".format(tiempo)
    }

    fun getTiempoOp1(): String {
        return ""
    }

    fun getTiempoOp2(): String {
        return ""
    }

    fun getTiempoM1(): String {
        return ""
    }

    fun getTiempoM2(): String {
        return ""
    }

    fun getTiempoT(): String {
        return ""
    }

    fun getCapitalNecesario(): String {
        return toCOP(getCV() + costoFijo)
    }

    fun getPreIngresos(): String {
        return toCOP(getPI())
    }

    private fun getPI(): Double {
        return ingreso - getCV() - costoFijo
    }

    fun getPosIngresos(): String {
        return toCOP(getPI() - costoImpuestos)
    }
}

class Producto {
    /**
     * PC = Producto Conforme.
     * D = Desecho.
     * R1 = Re-proceso en operacion 1.
     * R2 = Re-proceso en operacion 2.
     * RP = Reparacion.
     * RC = Re-clasificacion.
     */
    var estado = ""
    var tiempoOp1 = 0.0
    var tiempoOp2 = 0.0
    var tiempoM2 = 0.0
    var tiempoT = 0.0
    var precio = 0.0
}