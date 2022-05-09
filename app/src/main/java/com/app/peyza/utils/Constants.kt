package com.app.peyza.utils

import android.app.Activity
import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowInsets
import android.widget.Toast
import androidx.annotation.NonNull

object Constants {
    const val BASE_URL = "https://data.covid19india.org/"
    const val UNAUTHORIZED = "unauthorized User"
    val REQ_POST_PRODUCT_PROFILE = 15042
    const val AUTOCOMPLETE_REQUEST_CODE = 1690
    const val LOCATION_REQUEST = 1688
    const val DEVICE_TYPE = "android"
    const val DEVICE_TOKEN = "fcmToken"
    const val BASE_IMAGE_URL = "https://s3.us-east-2.amazonaws.com/auctions-new/uploads/images/"
    const val TIME_DELAYS = 1000L
    const val LOCAL = "locale"
    const val CONTACT_INFO = "contact_info"
    const val GUEST_USER = "guest_user"
    const val REQ_CODE_CAMERA_IMAGE = 101
    const val REQ_CODE_GALLERY_IMAGE = 102
    const val USER_TYPE_MERCHANT = "seller"
    const val PAGE_LIMIT = 10
    const val RECENT_SEARCH_LIST = "recent_search_list"
    const val REQ_CODE_UPDATE_PRODUCT = 103
    const val OTHER_USER_PROFILE_CODE = 104
    const val REQ_CODE_SUB_CATEGORIES = 105
    const val DELAY_MS: Long = 1000//delay in milliseconds before task is to be executed
    const val PERIOD_MS: Long = 3000
    const val FILTERS_APPLIED = 106

    fun getWidth(@NonNull activity: Activity): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics = activity.windowManager.currentWindowMetrics
            val insets = windowMetrics.windowInsets
                .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
            windowMetrics.bounds.width() - insets.left - insets.right
        } else {
            val displayMetrics = DisplayMetrics()
            activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
            displayMetrics.widthPixels
        }
    }


    fun getHeight(@NonNull activity: Activity): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics = activity.windowManager.currentWindowMetrics
            val insets = windowMetrics.windowInsets
                .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
            windowMetrics.bounds.height() - insets.top - insets.bottom
        } else {
            val displayMetrics = DisplayMetrics()
            activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
            displayMetrics.heightPixels
        }
    }

    object Config {
        var PAYMENT_BRANDS: MutableSet<String> = LinkedHashSet()

        init {
            PAYMENT_BRANDS?.add("VISA")
            PAYMENT_BRANDS?.add("MASTER")
            //PAYMENT_BRANDS?.add("PAYPAL")
            //PAYMENT_BRANDS?.add("GOOGLEPAY")
        }
    }

    object ConfigPayment {
        var PAYMENT_BRANDS: MutableSet<String> = LinkedHashSet()

        init {
            PAYMENT_BRANDS?.add("VISA")
            PAYMENT_BRANDS?.add("MASTER")
            PAYMENT_BRANDS?.add("STC_PAY")
            //PAYMENT_BRANDS?.add("PAYPAL")
            //PAYMENT_BRANDS?.add("GOOGLEPAY")
        }
    }

    fun Context.showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    fun Context.showToast(resId: Int) {
        Toast.makeText(this, getString(resId), Toast.LENGTH_SHORT).show()
    }


    //HyperPay valid payment options
    /*"valid options are: AFFIRM, AFTERPAY, AFTERPAY_PACIFIC, AIRPLUS, ALIA, ALIADEBIT, ALIPAY, ALLPAGO_INVOICE, AURA, AMAZONPAY, AMEX, APOSTAR, APPLEPAY, ARGENCARD, ASTROPAY_STREAMLINE_CASH, ASTROPAY_STREAMLINE_OT, ASYACARD, AXESS, BALOTO, BANCOLOMBIA, BANCONTACT_LINK, BANCONTACT_QR, BBVA_CONTINENTAL, BCCARD, BCMC, BCP, BELK_GIFT_CARD, BELK_PRIVATE_LABEL, BEVALIDA, BITCOIN, BOLETO, BONUS, BOTON_PSE, BRADESCO, CABAL, CABALDEBIT, CAJA_AREQUIPA, CAJA_CUSCO, CAJA_HUANCAYO, CAJA_ICA, CAJA_PIURA, CAJA_TACNA, CAJA_TRUJILLO, CARDFINANS, CARNET, CARTEBANCAIRE, CARTEBLEUE, CASHLINKMALTA, CASH_ON_DELIVERY, CASHPRESSO, CASHU, CENCOSUD, CHINAUNIONPAY, CMR_FALABELLA, CORDIAL, CORDOBESA, CREDISENSA, CREDIT_CLICK, DANKORT, DAOPAY, DATACODE, DELTA, DIMONEX, DINACARD, DINERS, DIRECTDEBIT_AT, DIRECTDEBIT_DE, DIRECTDEBIT_SEPA, DIRECTDEBIT_SEPA_MIX_AT, DIRECTDEBIT_SEPA_MIX_DE, DIRECTDEBIT_US, DISCOVER, EFECTY, ELO, ENTERCASH, ENTERPAY, EPS, EURO6000, FACILYPAY_3X, FACILYPAY_4X, FACILYPAY_3XSANSFRAIS, FACILYPAY_4XSANSFRAIS, FOURB, GANA, GIROPAY, GOOGLEPAY, HIPERCARD, IDEAL, IK_PRIVATE_LABEL, IK_PRIVATE_LABEL_VA, IKANOOI_FI, IKANOOI_NO, IKANOOI_SE, INICIS, INTERAC, INTERAC_ONLINE, INTERBANK, INVOICE, IPARA, IUPAY, JCB, JIFITI, KLARNA_INSTALLMENTS, KLARNA_INVOICE, KLARNA_CHECKOUT, KLARNA_PAYMENTS_BILLPAY, KLARNA_PAYMENTS_PAYLATER, KLARNA_PAYMENTS_PAYNOW, KLARNA_PAYMENTS_SLICEIT, LASER, LYFPAY, MADA, MAESTRO, MAGNA, MASTER, MASTERDEBIT, MASTERPASS, MAXIMUM, MBWAY, MERCADOLIVRE, MONEYBOOKERS, MONEYSAFE, MULTICAJA, NATIVA, NARANJA, NEQUI, NETELLER, ONECARD, ONEY_CARD, ONEY_BANK_CARD, ONEY_PRIVATE_CARD, ONEY_GIFTCARD, OXXO, PAGOSNET, PAGO_EFECTIVO, PAGO_FACIL, PASTEANDPAY_V, PAYBOX, PAYDIREKT, PAY_FAWRY, PAYNET, PAYOLUTION_ELV, PAYOLUTION_INS, PAYOLUTION_INVOICE, PAYPAL, PAYPAL_CONTINUE, PAYLIB, SHETAB, PAYSAFECARD, PAYTRAIL, PF_KARTE_DIRECT, PIX, POLI, POSTEPAY, PREPAYMENT, PRESTO, PRZELEWY, PUNTO_RED, QIWI, RAPI_PAGO, RATENKAUF, RED_SERVI, RUPAY, SADAD, SCOTIABANK, SENCILLITO, SERVIPAG, SERVIRED, SIBS_MULTIBANCO, SISTEMACLAVE, SOFINCOSANSFRAIS, SOFINCO, SOFORTUEBERWEISUNG, SPEI, STC_PAY, SU_RED, SU_SUERTE, TABBY, TAMARA, TARJETASHOPPING, TENPAY, TRUSTLY, TRUSTPAY_VA, UKASH, UNIONPAY, UNIONPAY_GENERIC, UNIONPAY_SMS, VISA, VISADEBIT, VISAELECTRON, VPAY, WECHAT_PAY, WESTERN_UNION, YANDEX, YANDEX_CARD, YANDEX_CHECKOUT, TCARD, TCARDDEBIT, _2C2P, SEPA, PRIVATE_LABEL"*/
}