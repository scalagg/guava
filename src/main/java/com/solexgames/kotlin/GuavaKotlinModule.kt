package com.solexgames.kotlin

import com.google.common.base.Predicate
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
        map["something"] = "something"

        val predicate = Predicate<String>{ true }
        println(predicate.apply("hi"))

        return map
    }

    override fun getCommands(): MutableList<BaseCommand>
    {
        val list = mutableListOf<BaseCommand>()
        list.add(TutorialCommand())

        return list
    }
}
