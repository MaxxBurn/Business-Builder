package com.example.businessbuilder

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.*


class XposedDb(){

    val db = Database.connect("jdcb:mysql://albreezetours.com:2083/albreezetours_klavio", driver = "org.h2.Driver",
        user = "albreezetours", password = "PmRAPLBraeHG")



    object Users: IntIdTable(){
        val sequelId: Column<Int> = integer("sequel_id").uniqueIndex()
        val name: Column<String> = varchar("name",255)
        val lastname: Column<String> = varchar("lastname",255)
        val age: Column<Int> = integer("age")


        override val primaryKey = PrimaryKey(id, name="PK_Users_ID")
    }

    val id = Users.insertAndGetId{
        it[name] = "Max"
        it[lastname] = "Burn"
        it[age] = 20
        it[sequelId] = 1
    }

}