package com.app.peyza.views.localDatabase

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.app.peyza.views.models.CasesData
import com.app.peyza.views.models.StatesData
import com.app.peyza.views.models.TestedData


class DBHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        // here we have defined variables for our database

        // below is variable for database name
        private val DATABASE_NAME = "COVID_19_DATA"

        // below is the variable for database version
        private val DATABASE_VERSION = 1

        // below is the variable for table name
        val CASES_TABLE_NAME = "cases_table"

        // below is the variable for id column
        val ID_COL = "id"
        val isFav = "isFav"
        // below is the variable for name column

        val dailyconfirmed = "dailyconfirmed"
        val dailyrecovered = "dailyrecovered"
        val totalconfirmed = "totalconfirmed"
        val totalrecovered = "totalrecovered"
        val dailydeceased = "dailydeceased"
        val date = "date"


        val STATES_TABLE_NAME = "states_table"

        val active = "active"
        val confirmed = "confirmed"
        val deaths = "deaths"
        val recovered = "recovered"
        val state = "state"
        val statecode = "statecode"
        val lastupdatedtime = "lastupdatedtime"


        val TESTED_TABLE_NAME = "tested_table"

        val source = "source"
        val testedasof = "testedasof"
        val totalindividualstested = "totalindividualstested"
        val totalpositivecases = "totalpositivecases"
        val updatetimestamp = "updatetimestamp"

    }

    // below is the method for creating a database by a sqlite query
    override fun onCreate(db: SQLiteDatabase) {
        // below is a sqlite query, where column names
        // along with their data types is given
        val query = ("CREATE TABLE " + CASES_TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, " +
                dailyconfirmed + " TEXT," +
                dailyrecovered + " TEXT," +
                totalconfirmed + " TEXT," +
                totalrecovered + " TEXT," +
                dailydeceased + " TEXT," +
                isFav + " INTEGER," +
                date + " TEXT" + ")")

        val query2 = ("CREATE TABLE " + STATES_TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, " +
                active + " TEXT," +
                confirmed + " TEXT," +
                deaths + " TEXT," +
                recovered + " TEXT," +
                state + " TEXT," +
                statecode + " TEXT," +
                isFav + " INTEGER," +
                lastupdatedtime + " TEXT" + ")")

        val quer3 = ("CREATE TABLE " + TESTED_TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, " +
                source + " TEXT," +
                testedasof + " TEXT," +
                totalindividualstested + " TEXT," +
                totalpositivecases + " TEXT," +
                isFav + " INTEGER," +
                updatetimestamp + " TEXT" + ")")

        // we are calling sqlite
        // method for executing our query
        db.execSQL(query)
        db.execSQL(query2)
        db.execSQL(quer3)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // this method is to check if table already exists
        db.execSQL("DROP TABLE IF EXISTS $CASES_TABLE_NAME")
        db.execSQL("DROP TABLE IF EXISTS $STATES_TABLE_NAME")
        db.execSQL("DROP TABLE IF EXISTS $TESTED_TABLE_NAME")
        onCreate(db)
    }

    // This method is for adding data in our database
    fun addCases(data: ArrayList<CasesData>) {
        val db = this.writableDatabase

        data.forEach {
            val values = ContentValues()
            values.put(dailyconfirmed, it.dailyconfirmed)
            values.put(dailyrecovered, it.dailyrecovered)
            values.put(totalconfirmed, it.totalconfirmed)
            values.put(totalrecovered, it.totalrecovered)
            values.put(dailydeceased, it.dailydeceased)
            values.put(date, it.date)
            values.put(isFav, it.isFav)
            // all values are inserted into database
            db.insert(CASES_TABLE_NAME, null, values)
        }
        db.close()
    }

    // This method is for adding data in our database
    fun addStates(data: ArrayList<StatesData>) {
        val db = this.writableDatabase

        data.forEach {
            val values = ContentValues()
            values.put(active, it.active)
            values.put(confirmed, it.confirmed)
            values.put(deaths, it.deaths)
            values.put(recovered, it.recovered)
            values.put(state, it.state)
            values.put(statecode, it.statecode)
            values.put(isFav, it.isFav)
            values.put(lastupdatedtime, it.lastupdatedtime)
            // all values are inserted into database
            db.insert(STATES_TABLE_NAME, null, values)
        }
        db.close()
    }

    // This method is for adding data in our database
    fun addTested(data: ArrayList<TestedData>) {
        val db = this.writableDatabase

        data.forEach {
            val values = ContentValues()
            values.put(source, it.source)
            values.put(testedasof, it.testedasof)
            values.put(totalindividualstested, it.totalindividualstested)
            values.put(totalpositivecases, it.totalpositivecases)
            values.put(updatetimestamp, it.updatetimestamp)
            values.put(isFav, it.isFav)
            // all values are inserted into database
            db.insert(TESTED_TABLE_NAME, null, values)
        }
        db.close()
    }

    fun getCasesData(): ArrayList<CasesData> {
        val empList: ArrayList<CasesData> = ArrayList()
        val selectQuery = "SELECT  * FROM $CASES_TABLE_NAME"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        if (cursor.moveToFirst()) {
            do {
                val emp = CasesData(
                    id = cursor.getInt(cursor.getColumnIndex("id")),
                    isFav = cursor.getInt(cursor.getColumnIndex("isFav")),
                    dailyconfirmed = cursor.getString(cursor.getColumnIndex("dailyconfirmed")),
                    dailyrecovered = cursor.getString(cursor.getColumnIndex("dailyrecovered")),
                    totalconfirmed = cursor.getString(cursor.getColumnIndex("totalconfirmed")),
                    totalrecovered = cursor.getString(cursor.getColumnIndex("totalrecovered")),
                    dailydeceased = cursor.getString(cursor.getColumnIndex("dailydeceased")),
                    date = cursor.getString(cursor.getColumnIndex("date"))
                )
                empList.add(emp)
            } while (cursor.moveToNext())
        }
        return empList
    }

    fun getStatesData(): ArrayList<StatesData> {
        val empList: ArrayList<StatesData> = ArrayList()
        val selectQuery = "SELECT  * FROM $STATES_TABLE_NAME"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        if (cursor.moveToFirst()) {
            do {

                val emp = StatesData(
                    id = cursor.getInt(cursor.getColumnIndex("id")),
                    active = cursor.getString(cursor.getColumnIndex("active")),
                    isFav = cursor.getInt(cursor.getColumnIndex("isFav")),
                    confirmed = cursor.getString(cursor.getColumnIndex("confirmed")),
                    deaths = cursor.getString(cursor.getColumnIndex("deaths")),
                    recovered = cursor.getString(cursor.getColumnIndex("recovered")),
                    state = cursor.getString(cursor.getColumnIndex("state")),
                    statecode = cursor.getString(cursor.getColumnIndex("statecode")),
                    lastupdatedtime = cursor.getString(cursor.getColumnIndex("lastupdatedtime"))
                )
                empList.add(emp)
            } while (cursor.moveToNext())
        }
        return empList
    }

    fun getTestedData(): ArrayList<TestedData> {
        val empList: ArrayList<TestedData> = ArrayList()
        val selectQuery = "SELECT  * FROM $TESTED_TABLE_NAME"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        if (cursor.moveToFirst()) {
            do {
                val emp = TestedData(
                    id = cursor.getInt(cursor.getColumnIndex("id")),
                    source = cursor.getString(cursor.getColumnIndex("source")),
                    isFav = cursor.getInt(cursor.getColumnIndex("isFav")),
                    testedasof = cursor.getString(cursor.getColumnIndex("testedasof")),
                    totalindividualstested = cursor.getString(cursor.getColumnIndex("totalindividualstested")),
                    totalpositivecases = cursor.getString(cursor.getColumnIndex("totalpositivecases")),
                    updatetimestamp = cursor.getString(cursor.getColumnIndex("updatetimestamp"))
                )
                empList.add(emp)
            } while (cursor.moveToNext())
        }
        return empList
    }

    fun updateTable(id: Int?, value: Int?, tableName: String): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(isFav, value)
        // Updating Row
        val success = db.update(tableName, contentValues, "id=$id", null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }

}