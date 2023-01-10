package com.example.perf16digitcodegenerator.refactor.strategy

import com.example.perf16digitcodegenerator.refactor.CodeGenerationStrategy
import kotlin.random.Random

class LegacyWithKotlinRandomStrategy : CodeGenerationStrategy {
    override fun generate(count: Long): Set<String> {
        val codes = mutableSetOf<String>()
        val code = StringBuilder()
        do {
            repeat((1..codeDigits).count()) {
                getRandomIndex().apply {
                    code.append(numUpperChars[this])
                }
            }
            codes.add(code.toString())
            code.clear()
        } while (!isGeneratedCountEnough(codes, count))

        return codes
    }

    private fun getRandomIndex() = Random.nextInt(0, numUpperChars.size)

    private fun isGeneratedCountEnough(codes: Set<String>, count: Long) = codes.size >= count

    companion object {
        private val numUpperChars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()
        private const val codeDigits = 16
    }
}
