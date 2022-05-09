package com.app.peyza.views.models

data class CovidData(
    val cases_time_series: ArrayList<CasesData>? = null,
    val statewise: ArrayList<StatesData>? = null,
    val tested: ArrayList<TestedData>? = null

)

data class CasesData(
    val id: Int? = 0,
    val dailyconfirmed: String? = null,
    val dailydeceased: String? = null,
    val dailyrecovered: String? = null,
    val date: String? = null,
    var isFav: Int? = 0,
    val dateymd: String? = null,
    val totalconfirmed: String? = null,
    val totaldeceased: String? = null,
    val totalrecovered: String? = null
)

data class StatesData(
    val active: String? = null,
    val confirmed: String? = null,
    val deaths: String? = null,
    val deltaconfirmed: String? = null,
    val deltadeaths: String? = null,
    val deltarecovered: String? = null,
    val lastupdatedtime: String? = null,
    val migratedother: String? = null,
    val recovered: String? = null,
    val state: String? = null,
    var isFav: Int? = 0,
    val id: Int? = 0,
    val statecode: String? = null,
    val statenotes: String? = null
)
//

data class TestedData(
    val id: Int? = 0,
    val dailyrtpcrsamplescollectedicmrapplication: String? = null,
    val firstdoseadministered: String? = null,
    val frontlineworkersvaccinated1stdose: String? = null,
    val frontlineworkersvaccinated2nddose: String? = null,
    val healthcareworkersvaccinated1stdose: String? = null,
    val healthcareworkersvaccinated2nddose: String? = null,
    val over45years1stdose: String? = null,
    var isFav: Int? = 0,
    val over45years2nddose: String? = null,
    val over60years1stdose: String? = null,
    val over60years2nddose: String? = null,
    val positivecasesfromsamplesreported: String? = null,
    val registrationabove45years: String? = null,
    val registrationflwhcw: String? = null,
    val registrationonline: String? = null,
    val registrationonspot: String? = null,
    val samplereportedtoday: String? = null,
    val seconddoseadministered: String? = null,
    val source: String? = null,
    val source2: String? = null,
    val source3: String? = null,
    val source4: String? = null,
    val testedasof: String? = null,
    val testsconductedbyprivatelabs: String? = null,
    val totaldosesadministered: String? = null,
    val totaldosesavailablewithstates: String? = null,
    val totaldosesavailablewithstatesprivatehospitals: String? = null,
    val totaldosesinpipeline: String? = null,
    val totaldosesprovidedtostatesuts: String? = null,
    val totalindividualsregistered: String? = null,
    val totalindividualstested: String? = null,
    val totalpositivecases: String? = null,
    val totalrtpcrsamplescollectedicmrapplication: String? = null,
    val totalsamplestested: String? = null,
    val totalsessionsconducted: String? = null,
    val totalvaccineconsumptionincludingwastage: String? = null,
    val updatetimestamp: String? = null,
    val years1stdose: String? = null,
    val years2nddose: String? = null
)
