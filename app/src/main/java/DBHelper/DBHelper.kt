package DBHelper


import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import model.persondata


class DBHelper (context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VER) {
    companion object {
        private val DATABASE_VER = 1
        private val DATABASE_NAME = "newdata.db"

        //table
        private val TABLE_NAME = "Personlist"
                private val COL_ID = "Id"
        private val COL_NAME = "Name"
        private val COL_UDHAR = "Udhar"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY: String = "CREATE TABLE " + TABLE_NAME + "(" + COL_ID + " INTEGER PRIMARY KEY ," + COL_NAME + " TEXT," + COL_UDHAR + " INTEGER" + ")"
        db!!.execSQL(CREATE_TABLE_QUERY)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db!!)

    }

    //var exceed=0

    val allPerson: List<persondata>

        get() :List<persondata> {
            val firstPersons = ArrayList<persondata>()
            val selectQuery = " SELECT * FROM $TABLE_NAME"
            val db = this.writableDatabase
            val cursor = db.rawQuery(selectQuery, null)
            if (cursor.moveToFirst()) {
                do {
                    val person = persondata()
                    person.id = cursor.getInt(cursor.getColumnIndex(COL_ID))
                    person.name = cursor.getString(cursor.getColumnIndex(COL_NAME))
                    person.udhar = cursor.getInt(cursor.getColumnIndex(COL_UDHAR))

                    firstPersons.add(person)
                } while (cursor.moveToNext())
            }
            db.close()
            return firstPersons
        }


    fun addPerson(person: persondata) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_ID, person.id)
        values.put(COL_NAME, person.name)
        values.put(COL_UDHAR, person.udhar)
        //exceed=exceed+ person.udhar
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun updatePerson(person: persondata): Int {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_ID, person.id)
        values.put(COL_NAME, person.name)
        values.put(COL_UDHAR, person.udhar)

        return db.update(TABLE_NAME, values, "$COL_ID=?", arrayOf(person.id.toString()))

    }


    fun deletePerson(person: persondata) {
        val db = this.writableDatabase


        db.delete(TABLE_NAME, "$COL_ID=?", arrayOf(person.id.toString()))
        db.close()


    }
}
