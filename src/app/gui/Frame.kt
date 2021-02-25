package app.gui

import lib.sRAD.gui.component.Resource.WSFS
import lib.sRAD.gui.component.VentanaEmergente
import lib.sRAD.gui.sComponent.*
import lib.sRAD.logic.Extension.*
import javax.swing.JOptionPane
import kotlin.random.Random

object Frame: SFrame() {
    private val pInformacion = SPanel(SPanel.INTERNO, 100, 60, 760, 1824)

    init {
        val scroll = SScrollPane(100, 60, 800, 620, pInformacion)
        add(scroll)

        val btRecrear = SButton(932, 60, 150, 32, "RE-CREAR")
        btRecrear.addActionListener {
            App.simular(App.RECREAR)
            actualizar()
        }
        add(btRecrear)

        val btRiesgo = SButton(932, 92, 150, 32, "RIESGO")
        btRiesgo.addActionListener {
            var min: Double? = null
            var max: Double? = null
            var acumulado = 0.0
            var cantidad = 0

            for(i in 0 until 10000) {
                App.simular(App.RECREAR)
                val ingreso = App.getIngresosFinales()
                if(min == null || ingreso < min) {
                    min = ingreso
                }
                if(max == null || ingreso > max) {
                    max = ingreso
                }
                acumulado += ingreso
                cantidad++
            }
            val esperanza = acumulado/cantidad
            mostrarRiesgo(min!!, max!!, esperanza)
        }
        add(btRiesgo)

        val btAjuste = SButton(932, 124, 150, 32, "AJUSTAR")
        btAjuste.addActionListener {
            realizarAjuste()
            actualizar()
        }
        add(btAjuste)

        val btEstocastico = SButton(932, 156, 150, 32, "ESTOCASTICO")
        btEstocastico.addActionListener {
            App.simular(App.ESTOCASTICO)
        }
        add(btEstocastico)

        val btOptimizar = SButton(932, 188, 150, 32, "OPTIMIZAR")
        btOptimizar.addActionListener { App.optimizar() }
        add(btOptimizar)

        setMainBar("Simulación de Monte Carlo")
        setProperties(ESTANDAR)
    }

    private fun mostrarRiesgo(min: Double, max: Double, media: Double) {
        val ventanaEmergente = VentanaEmergente(this, 532, 270)

        val lMensaje = SLabel(32, 32, 436, 28, "Después de replicar 10000 veces la simulación, tenemos:")
        ventanaEmergente.add(lMensaje)

        val lMin = SLabel(64, 64, 436, 28, "Ingreso después de impuestos mínimo: ${toCOP(min)}")
        ventanaEmergente.add(lMin)

        val lMax = SLabel(64, 96, 436, 28, "Ingreso después de impuestos máximo: ${toCOP(max)}")
        ventanaEmergente.add(lMax)

        val lMed = SLabel(64, 128, 436, 28, "Ingreso después de impuestos esperado: ${toCOP(media)}")
        ventanaEmergente.add(lMed)

        val btClose = SButton(200, 185, 100, 32, "CERRAR")
        btClose.addActionListener { ventanaEmergente.cerrar() }
        ventanaEmergente.add(btClose)

        ventanaEmergente.lanzar()
    }

    private fun realizarAjuste() { //coordenadas
        val ventanaEmergente = VentanaEmergente(this, 500, 580)

        val cantidadProductos = SLabel(150, 32, 300, 28, "Cantidad de productos.")
        ventanaEmergente.add(cantidadProductos)

        val tfCantidadProducto = STextField(32, 32, 100, 32, App.getCantidadProductos().toString())
        ventanaEmergente.add(tfCantidadProducto)

        val precioProducto = SLabel(150, 72, 300, 28, "Precio de venta de producto.")
        ventanaEmergente.add(precioProducto)

        val tfPrecioProducto = STextField(32, 72, 100, 32, App.precioProducto.toString())
        ventanaEmergente.add(tfPrecioProducto)

        val costoOp1 = SLabel(150, 112, 300, 28, "Costo de operación 1. ($/min)")
        ventanaEmergente.add(costoOp1)

        val tfCostoOp1 = STextField(32, 112, 100, 32, App.costoOp1.toString())
        ventanaEmergente.add(tfCostoOp1)

        val costoOp2 = SLabel(150, 152, 300, 28, "Costo de operación 2. ($/min)")
        ventanaEmergente.add(costoOp2)

        val tfCostoOp2 = STextField(32, 152, 100, 32, App.costoOp2.toString())
        ventanaEmergente.add(tfCostoOp2)

        val costoM1 = SLabel(150, 192, 300, 28, "Costo de muestra 1. ($/u)")
        ventanaEmergente.add(costoM1)

        val tfCostoM1 = STextField(32, 192, 100, 32, App.costoM1.toString())
        ventanaEmergente.add(tfCostoM1)

        val costoM2 = SLabel(150, 232, 300, 28, "Costo de muestra 2. ($/min)")
        ventanaEmergente.add(costoM2)

        val tfCostoM2 = STextField(32, 232, 100, 32, App.costoM2.toString())
        ventanaEmergente.add(tfCostoM2)

        val costoT = SLabel(150, 272, 300, 28, "Costo de taller. ($/min)")
        ventanaEmergente.add(costoT)

        val tfCostoT = STextField(32, 272, 100, 32, App.costoTaller.toString())
        ventanaEmergente.add(tfCostoT)

        val costoFijo = SLabel(150, 312, 300, 28, "Costo fijo.")
        ventanaEmergente.add(costoFijo)

        val tfCostoFijo = STextField(32, 312, 100, 32, App.costoFijo.toString())
        ventanaEmergente.add(tfCostoFijo)

        val inflacion = SLabel(150, 352, 300, 28, "Inflación.")
        ventanaEmergente.add(inflacion)

        val tfInflacion = STextField(32, 352, 100, 32, App.inflacion.toString())
        ventanaEmergente.add(tfInflacion)

        val eficienciaM1 = SLabel(150, 392, 300, 28, "Eficiencia de muestra 1.")
        ventanaEmergente.add(eficienciaM1)

        val tfEficienciaM1 = STextField(32, 392, 100, 32, App.eficienciaM1.toString())
        ventanaEmergente.add(tfEficienciaM1)

        val eficienciaM2 = SLabel(150, 432, 300, 28, "Eficiencia de muestra 2.")
        ventanaEmergente.add(eficienciaM2)

        val tfEficienciaM2 = STextField(32, 432, 100, 32, App.eficienciaM2.toString())
        ventanaEmergente.add(tfEficienciaM2)

        val porcentajeImpuesto = SLabel(150, 472, 300, 28, "Porcentaje de impuestos.")
        ventanaEmergente.add(porcentajeImpuesto)

        val tfPorcentajeImpuesto = STextField(32, 472, 100, 32, App.porcentajeImpuesto.toString())
        ventanaEmergente.add(tfPorcentajeImpuesto)

        val btClose = SButton(212, 524, 100, 32, "CERRAR")
        btClose.addActionListener { ventanaEmergente.cerrar() }
        ventanaEmergente.add(btClose)

        val btConfirm = SButton(70, 524, 100, 32, "ACEPTAR")
        btConfirm.addActionListener {
            if( isInt(tfCantidadProducto.text) && isDouble(tfCostoOp1.text) && isDouble(tfCostoOp2.text) && isDouble(tfCostoM1.text)
                && isDouble(tfCostoM2.text) && isDouble(tfCostoT.text) && isDouble(tfInflacion.text) && isDouble(tfCostoFijo.text)
                && isDouble(tfPorcentajeImpuesto.text) && isDouble(tfEficienciaM1.text) && isDouble(tfEficienciaM2.text)
                && isDouble(tfPrecioProducto.text)) {
                App.simular( tfCantidadProducto.text.toInt(), tfPrecioProducto.text.toDouble(), tfEficienciaM1.text.toDouble(), tfEficienciaM2.text.toDouble(), tfCostoOp1.text.toDouble(),
                    tfCostoOp2.text.toDouble(), tfCostoM1.text.toDouble(), tfCostoM2.text.toDouble(), tfCostoT.text.toDouble(), tfCostoFijo.text.toDouble(),
                    tfPorcentajeImpuesto.text.toDouble(), tfInflacion.text.toDouble(), false )
            }
            else {
                JOptionPane.showMessageDialog(null, "Por favor ingrese valores válidos", "Error", 0)
            }
            ventanaEmergente.cerrar()
        }
        ventanaEmergente.add(btConfirm)

        val btDefault = SButton(354, 524, 100, 32, "DEFAULT")
        btDefault.addActionListener {
            tfCantidadProducto.text = "1000"
            tfPrecioProducto.text = "2800.0"
            tfCostoOp1.text = "78.0"
            tfCostoOp2.text = "82.0"
            tfCostoM1.text = "7.0"
            tfCostoM2.text = "7.0"
            tfCostoT.text = "53.0"
            tfCostoFijo.text = "400000.0"
            tfInflacion.text = "0.0"
            tfPorcentajeImpuesto.text = "0.1"
            tfEficienciaM1.text = "0.5"
            tfEficienciaM2.text = "0.5"
        }
        ventanaEmergente.add(btDefault)

        ventanaEmergente.lanzar()
    }

    fun actualizar() { //coordenadas
        //vacía el panel de información
        pInformacion.removeAll()

        //carga la información
        val lVD = SLabel(32, 32, 600, 32, "Parámetros:", WSFS)
        val lCP = SLabel(64, 64, 600, 28, "Cantidad de productos: ${App.getCantidadProductos()}")
        val lPP = SLabel(64, 96, 600, 28, "Precio de venta de producto conforme: ${App.getPrecioProducto()}")
        val lCCO1 = SLabel(64, 128, 600, 28, "Costo de operación 1: ${App.getCostoOp1()} por minuto")
        val lCCO2 = SLabel(64, 160, 600, 28, "Costo de operación 2: ${App.getCostoOp2()} por minuto")
        val lCCM1 = SLabel(64, 192, 600, 28, "Costo de muestra 1: ${App.getCostoM1()} por unidad")
        val lCCM2 = SLabel(64, 224, 600, 28, "Costo de muestra 2: ${App.getCostoM2()} por minuto")
        val lCTA = SLabel(64, 256, 600, 28, "Costo de taller: ${App.getCostoT()} por minuto")
        val lCF = SLabel(64, 288, 600, 28, "Costos fijos: ${App.getCostosFijo()}")
        val lEM1 = SLabel(64, 320, 600, 28, "Eficiencia de muestra 1: ${App.getEficienciaM1()}")
        val lEM2 = SLabel(64, 352, 600, 28, "Eficiencia de muestra 2: ${App.getEficienciaM2()}")
        val lIG = SLabel(64, 384, 600, 28, "Inflación general: ${App.getInflacion()}")
        val lIP = SLabel(64, 416, 600, 28, "Porcentaje en impuestos por producto vendido: ${App.getPorcentajeImpuesto()}")

        val lDS = SLabel(32, 480, 600, 32, "Datos:", WSFS)
        val lCS = SLabel(64, 512, 600, 28, "Clientes satisfechos: ${App.getSatisfechos()} de ${App.getClientes()}")
        val lCRE = SLabel(64, 544, 600, 28, "Cantidad de reclamos: ${App.getReclamos()}")
        val lCPP = SLabel(64, 576, 600, 28, "Costo promedio por producto: ${App.getCostoPromedio()}")
        val lT11 = SLabel(64, 608, 600, 28, "Tiempo promedio por producto: ${App.getTiempoPromedio()} minutos")

        val lC = SLabel(32, 672, 600, 32, "Costos:", WSFS)
        val lCPBN = SLabel(64, 704, 600, 28, "Costos por pérdida del buen nombre: ${App.getCostosPBN()}")
        val lCR = SLabel(64, 736, 600, 28, "Costos por reclamos: ${App.getCostosReclamo()}")
        val lCI = SLabel(64, 768, 600, 28, "Costos de impuestos: ${App.getCostosImpuesto()}")
        val lCO1 = SLabel(64, 800, 600, 28, "Costos de operación 1: ${App.getCostosOp1()}")
        val lCM1 = SLabel(64, 832, 600, 28, "Costos de muestra 1: ${App.getCostosMuestra1()}")
        val lCO2 = SLabel(64, 864, 600, 28, "Costos de operación 2: ${App.getCostosOp2()}")
        val lCM2 = SLabel(64, 896, 600, 28, "Costos de muestra 2: ${App.getCostosM2()}")
        val lCT = SLabel(64, 928, 600, 28, "Costos de taller: ${App.getCostosTaller()}")
        val lCV = SLabel(64, 960, 600, 28, "Costos variables: ${App.getCostosVariable()}")

        val lEF = SLabel(32, 1024, 600, 32, "Estados finales de productos:", WSFS)
        val lE1 = SLabel(64, 1056, 600, 28, "Productos desechos: ${App.getCantidadProductos("D")}")
        val lE2 = SLabel(64, 1088, 600, 28, "Productos conformes: ${App.getCantidadProductos("PC")}")
        val lE3 = SLabel(64, 1120, 600, 28, "Productos que requieren re-proceso: ${
            App.getCantidadProductos("R2") + App.getCantidadProductos("R1")}")
        val lE4 = SLabel(64, 1152, 600, 28, "Productos que requieren reparación: ${App.getCantidadProductos("RP")}")
        val lE5 = SLabel(64, 1184, 600, 28, "Productos de re-clasificación: ${App.getCantidadProductos("RC")}")
        val lE6 = SLabel(64, 1216, 600, 28, "Productos de pre-venta: ${App.getCantidadProductos("PV")}")

        val lT = SLabel(32, 1280, 600, 32, "Tiempos:", WSFS)
        val lT1 = SLabel(64, 1312, 600, 28, "Tiempo total requerido en operación 1: ${App.getTiemposOp1()} minutos")
        val lT2 = SLabel(64, 1344, 600, 28, "Tiempo total requerido en operación 2: ${App.getTiemposOp2()} minutos")
        val lT3 = SLabel(64, 1376, 600, 28, "Tiempo total requerido en muestra 1: ${App.getTiemposM1()} minutos")
        val lT4 = SLabel(64, 1408, 600, 28, "Tiempo total requerido en muestra 2: ${App.getTiemposM2()} minutos")
        val lT5 = SLabel(64, 1440, 600, 28, "Tiempo total requerido en taller: ${App.getTiemposT()} minutos")
        val lT6 = SLabel(64, 1472, 600, 28, "Tiempo promedio por producto en operación 1: ${App.getTiempoOp1()} minutos")
        val lT7 = SLabel(64, 1504, 600, 28, "Tiempo promedio por producto en operación 2: ${App.getTiempoOp2()} minutos")
        val lT8 = SLabel(64, 1536, 600, 28, "Tiempo promedio por producto en muestra 1: ${App.getTiempoM1()} minutos")
        val lT9 = SLabel(64, 1568, 600, 28, "Tiempo promedio por producto en muestra 2: ${App.getTiempoM2()} minutos")
        val lT10 = SLabel(64, 1600, 600, 28, "Tiempo promedio por producto en el taller: ${App.getTiempoT()} minutos")

        val lAR = SLabel(32, 1664, 600, 32, "Análisis de riesgo:", WSFS)
        val lCN = SLabel(64, 1696, 600, 28, "Capital necesario: ${App.getCapitalNecesario()}")
        val lI = SLabel(64, 1728, 600, 28, "Ingresos antes de impuestos: ${App.getPreIngresos()}")
        val lID = SLabel(64, 1760, 600, 28, "Ingresos después de impuestos: ${App.getPosIngresos()}")

        //los muestra
        pInformacion.add(lVD)
        pInformacion.add(lCP)
        pInformacion.add(lPP)
        pInformacion.add(lCCO1)
        pInformacion.add(lCCO2)
        pInformacion.add(lCCM1)
        pInformacion.add(lCCM2)
        pInformacion.add(lCTA)
        pInformacion.add(lEM1)
        pInformacion.add(lEM2)
        pInformacion.add(lIG)
        pInformacion.add(lIP)

        pInformacion.add(lDS)
        pInformacion.add(lCS)
        pInformacion.add(lCRE)

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
        pInformacion.add(lCPP)

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
        pInformacion.add(lT11)

        pInformacion.add(lCN)
        pInformacion.add(lAR)
        pInformacion.add(lI)
        pInformacion.add(lID)

        //repinta
        repaint()
    }
}

object App {
    //ajustes pre-establecidos
    const val DEFAULT = 0
    const val RECREAR = 1
    const val ESTOCASTICO = 2

    //parametros
    private var cantidadProductos = 0
    var precioProducto = 2800.0
    var costoOp1 = 78.0 // $/min
    var costoOp2 = 82.0 // $/min
    var costoM1 = 7.0 // $/unidad
    var costoM2 = 7.0 // $/min
    var costoTaller = 53.0 // $/min
    var costoFijo = 0.0

    var inflacion = 0.0
    var porcentajeImpuesto = 0.0
    var eficienciaM1 = 0.50
    var eficienciaM2 = 0.50

    //contadores
    private var costoMuestra1 = 0.0
    private var costoReclamo = 0.0
    private var costoPBN = 0.0
    private var costoImpuestos = 0.0

    private var tiemposMuestra1 = 0.0
    private var ingreso = 0.0
    private var clientes = 0
    private var satisfechos = 0

    //productos
    private var productos = mutableListOf<Producto>()

    //importar el porcentaje de impuesto
    fun simular (
        cantidadProductos: Int = 1000, precioProducto: Double = 2800.0, eficienciaM1: Double = 0.50, eficienciaM2: Double = 0.50, costoOp1: Double = 78.0,
        costoOp2: Double = 82.2, costoM1: Double = 7.0, costoM2: Double = 7.0, costoTaller: Double = 53.0, costoFijo: Double = 400000.0,
        porcentajeImpuesto: Double = 0.1, inflacion: Double = 0.0, recrear: Boolean = true
            ) {
        //almacena valores
        this.cantidadProductos = cantidadProductos
        this.precioProducto = precioProducto
        this.eficienciaM1 = eficienciaM1
        this.eficienciaM2 = eficienciaM2
        this.costoOp1 = costoOp1 + inflacion*costoOp1
        this.costoOp2 = costoOp2 + inflacion*costoOp2
        this.costoM1 = costoM1 + inflacion*costoM1
        this.costoM2 = costoM2 + inflacion*costoM2
        this.costoFijo = costoFijo  + inflacion*costoFijo
        this.costoTaller = costoTaller + inflacion*costoTaller
        this.porcentajeImpuesto = porcentajeImpuesto
        if(!recrear)
            this.inflacion = inflacion

        //inicializa contadores y elimina los productos
        costoMuestra1 = 0.0
        costoReclamo = 0.0
        costoPBN = 0.0
        costoImpuestos = 0.0
        tiemposMuestra1 = 0.0
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
        if(!recrear)
            Frame.actualizar()
    }

    fun simular(type: Int) {
        if(type == DEFAULT) {
            simular(recrear = false)
        }
        else if(type == RECREAR) {
            simular(cantidadProductos, precioProducto, eficienciaM1, eficienciaM2, costoOp1, costoOp2, costoM1, costoM2, costoTaller, costoFijo, porcentajeImpuesto,
                0.0, true)
        }

        else if(type == ESTOCASTICO) {
            simular(cantidadProductos, precioProducto, eficienciaM1, eficienciaM2, costoOp1, costoOp2, costoM1, costoM2, costoTaller, costoFijo, porcentajeImpuesto,
                inflacion, false)
        }
    }

    private fun operacion1 (producto: Producto) {
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

    private fun muestra1 (producto: Producto) {
        if (Random.nextDouble() < 1 - eficienciaM1) {
            //no muestrea
            operacion2(producto)
        }
        else {
            //muestrea
            if (producto.estado == "PC") {
                operacion2(producto)
            }
            else if (producto.estado == "R1") {
                operacion1(producto)
            }
            //costo por muestreo
            producto.tiempoM1 = 2.5 + Random.nextDouble()*(3.2-2.5)
            tiemposMuestra1 += producto.tiempoM1
            costoMuestra1 += costoM1
        }
    }

    private fun operacion2(producto: Producto) {
        //establece estado
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

    private fun muestra2 (producto: Producto) {
        if (Random.nextDouble() < 1 - eficienciaM2) {
            //no muestrea
            producto.precio = precioProducto
            cliente(producto)
        }
        else {
            //muestrea
            if (producto.estado == "D") {
                producto.estado = "PV"
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
                producto.estado = "PC"
                producto.precio = precioProducto
                cliente(producto)
            }
            else if (producto.estado == "PC") {
                producto.precio = precioProducto
                cliente(producto)
            }
            else {
                operacion2(producto)
            }
            //calcula tiempos
            producto.tiempoM2 += 3.7 + Random.nextDouble()*(9.9-3.7)
        }
    }

    private fun cliente (producto: Producto) {
        //el cliente paga por el producto
        ingreso += producto.precio
        //el impuesto de la venta se debe sumar
        costoImpuestos += porcentajeImpuesto * producto.precio

        //y revisa decide si está conforme con lo que recibió .tambien afectar impuestos
        if (producto.estado == "D" && producto.precio > 9.0) {
            clienteInconforme()
        }
        else if (producto.estado == "R1" || producto.estado == "R2" || producto.estado == "RP") {
            clienteInconforme()
        }
        else if (producto.estado == "RC" && producto.precio > 1800) {
            clienteInconforme()
        }
        else {
            satisfechos ++
        }
        clientes ++
    }

    private fun clienteInconforme() {
        if(Random.nextDouble() < 103.0/138.0) {
            //reclama
            costoReclamo += 60.0
        }
        else {
            //no vuelve
            costoPBN += 170.0
        }
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
        return toCOP(costoMuestra1)
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
        var tiempo = 0.0
        var cantidad = 0
        for (i in productos) {
            if(i.tiempoOp1 > 0) {
                tiempo += i.tiempoOp1
                cantidad ++
            }
        }
        return if (cantidad != 0)
            "%.2f".format(tiempo/cantidad)
        else 
            "NO APLICA"
    }

    fun getTiempoOp2(): String {
        var tiempo = 0.0
        var cantidad = 0
        for (i in productos) {
            if(i.tiempoOp2 > 0) {
                tiempo += i.tiempoOp2
                cantidad ++
            }
        }
        return if (cantidad != 0)
            "%.2f".format(tiempo/cantidad)
        else 
            "NO APLICA"
    }

    fun getTiempoM1(): String {
        var tiempo = 0.0
        var cantidad = 0
        for (i in productos) {
            if(i.tiempoM1 > 0) {
                tiempo += i.tiempoM1
                cantidad ++
            }
        }
        return if (cantidad != 0)
            "%.2f".format(tiempo/cantidad)
        else
            "NO APLICA"
    }

    fun getTiempoM2(): String {
        var tiempo = 0.0
        var cantidad = 0
        for (i in productos) {
            if(i.tiempoM2 > 0) {
                tiempo += i.tiempoM2
                cantidad ++
            }
        }
        return if (cantidad != 0)
            "%.2f".format(tiempo/cantidad)
        else 
            "NO APLICA"
    }

    fun getTiempoT(): String {
        var tiempo = 0.0
        var cantidad = 0
        for (i in productos) {
            if(i.tiempoT > 0) {
                tiempo += i.tiempoT
                cantidad ++
            }
        }
        return if (cantidad != 0)
            "%.2f".format(tiempo/cantidad)
        else 
            "NO APLICA"
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
        return toCOP(getIngresosFinales())
    }

    fun getIngresosFinales(): Double {
        return getPI() - costoImpuestos
    }

    fun getReclamos(): String {
        return (costoReclamo/60.0).toInt().toString()
    }

    fun getCostoPromedio(): String {
        var acumulado = 0.0
        for (i in productos) {
            acumulado += i.tiempoOp1*costoOp1 + i.tiempoOp2*costoOp2 + if(i.tiempoM1>0.0) costoM1 else 0.0 + i.tiempoM2*costoM2 + i.tiempoT*costoTaller
        }
        return toCOP(acumulado/cantidadProductos)
    }

    fun getTiempoPromedio(): String {
        var acumulado = 0.0
        for (i in productos) {
            acumulado += i.tiempoOp1 + i.tiempoOp2 + i.tiempoM1 + i.tiempoM2 + i.tiempoT
        }
        return "%.2f".format(acumulado/ cantidadProductos)
    }

    fun getPrecioProducto(): String {
        return toCOP(precioProducto)
    }

    fun getCostoT(): String {
        return toCOP(costoTaller)
    }

    fun optimizar() {
        var eficienciaM1Optimo = 0.0
        var ingresoEsperadoMáximo = 0.0
        var contador = 0.0

        //eficienciaM1
        while (contador <= 1.0) {
            eficienciaM1 = contador
            val esperanza = ingresoDespuesDeImpuestosEsperado()
            if (esperanza > ingresoEsperadoMáximo) {
                ingresoEsperadoMáximo = esperanza
                eficienciaM1Optimo = contador
            }
            contador += 0.01
        }
        eficienciaM1 = eficienciaM1Optimo

        //eficienciaM2
        var eficienciaM2Optimo = 0.0
        contador = 0.0
        while (contador <= 1.0) {
            eficienciaM2 = contador
            val esperanza = ingresoDespuesDeImpuestosEsperado()
            if (esperanza > ingresoEsperadoMáximo) {
                ingresoEsperadoMáximo = esperanza
                eficienciaM2Optimo = contador
            }
            contador += 0.01
        }
        eficienciaM2 = eficienciaM2Optimo

        //optimizar cantidadProductos
        var cantidadProductosOptimo = 0
        for (i in 0 .. 600) {
            cantidadProductos = i*5
            val esperanza = ingresoDespuesDeImpuestosEsperado()
            if (esperanza > ingresoEsperadoMáximo) {
                ingresoEsperadoMáximo = esperanza
                cantidadProductosOptimo = i*5
            }
        }
        cantidadProductos = cantidadProductosOptimo

        simular(RECREAR)

        JOptionPane.showMessageDialog(null, "EficienciaM1Optimo: $eficienciaM1Optimo\n" +
                "EficienciaM2Optimo: $eficienciaM2Optimo\n" +
                "CantidadProductosOptimo: $cantidadProductosOptimo")
    }

    fun ingresoDespuesDeImpuestosEsperado(): Double {
        var min: Double? = null
        var max: Double? = null
        var acumulado = 0.0
        var cantidad = 0

        for(i in 0 until 1000) {
            simular(RECREAR)
            val ingreso = getIngresosFinales()
            if(min == null || ingreso < min) {
                min = ingreso
            }
            if(max == null || ingreso > max) {
                max = ingreso
            }
            acumulado += ingreso
            cantidad++
        }
        return acumulado/cantidad
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
     * PV = Pre-venta
     */
    var estado = ""
    var tiempoOp1 = 0.0
    var tiempoOp2 = 0.0
    var tiempoM1 = 0.0
    var tiempoM2 = 0.0
    var tiempoT = 0.0
    var precio = 0.0
}