package com.example.perf16digitcodegenerator.refactor.strategy

import com.example.perf16digitcodegenerator.refactor.CodeGenerationStrategy
import java.util.concurrent.ThreadLocalRandom

class LegacyWithThreadLocalRandomStrategy : CodeGenerationStrategy {
    override fun generate(size: Long): Set<String> {
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
        } while (!isGeneratedCountEnough(codes, size))

        return codes
    }

    private fun getRandomIndex() = ThreadLocalRandom.current().nextInt(0, numUpperChars.size)

    private fun isGeneratedCountEnough(codes: Set<String>, count: Long) = codes.size >= count

    companion object {
        private const val codeDigits = 16
        private val numUpperChars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()
    }
}
