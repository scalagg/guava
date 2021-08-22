package com.solexgames.kotlin

import com.google.common.base.Predicate
import com.solexgames.datastore.commons.connection.impl.redis.NoAuthRedisConnection
import com.solexgames.datastore.commons.storage.impl.RedisStorageBuilder
import com.solexgames.guava.module.GuavaModule
import com.solexgames.guava.module.tutorial.command.TutorialCommand
import com.solexgames.lib.acf.BaseCommand

class GuavaKotlinModule: GuavaModule
{

    companion object Horse
    {

        val somethingElse = ""

        @JvmStatic
        fun main(args: Array<String>) {
            val predicate = Predicate<String>{
                it?.contains("hi") ?: true
            }
            println(predicate.apply("hi"))

            val mutableMap = mutableMapOf<String, String>()
            mutableMap["something"] = "something"
            mutableMap["also"] = "else"

            mutableMap.forEach {
                println("${it.key} + ${it.value}")
            }
        }
    }

    fun doSomething(): MutableMap<String, String>
    {
        val map = mutableMapOf<String, String>()
        map["something"] = "val"

        val predicate = Predicate<String>{ true }
        println(predicate.apply("hi ily <3"))

        val conn = NoAuthRedisConnection(
            "127.0.0.1", 6379
        );
        val builder = RedisStorageBuilder<Test>()

        builder.setConnection(conn)
        builder.setSection("datastore_test")
        builder.setType(Test::class.java)

        val layer = builder.build()

        layer.runCommand {
            val somethingFromSomething = it.hget("datastore_test", "call911")
            println("normal command $somethingFromSomething")

            map["something command"] = somethingFromSomething
        }

        layer.fetchEntryByKey("call911").thenAccept {
            println("fetched using method $it")

            map["something method"] = it.toString()
        }

        return map
    }

    override fun getCommands(): MutableList<BaseCommand>
    {
        val list = mutableListOf<BaseCommand>()
        list.add(TutorialCommand())

        return list
    }
}
