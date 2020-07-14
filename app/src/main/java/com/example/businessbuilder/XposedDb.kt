package com.example.businessbuilder

import android.os.AsyncTask
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction


    object Yeet: Table(){
        val name = varchar("name", 50)
        val age = integer("age")
        val lastname = varchar("lastname", 50)
        val id = varchar("id",10)

        override val primaryKey = PrimaryKey(id, name = "PK_User_ID")
    }

fun insert() {


    Thread {Database.connect(
        "jdbc:mysql://host67.registrar-servers.com:3306/albreeztours_klavio",
        driver = "com.mysql.cj.jdbc.Driver",
        user = "albreezetours_klavio",
        password = "YBaim6RhJQKa"
    )
        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(Yeet)

            Yeet.insert {
                it[name] = "asd"
                it[lastname] = "sok"
                it[age] = 50
            }
        }}.start()
}