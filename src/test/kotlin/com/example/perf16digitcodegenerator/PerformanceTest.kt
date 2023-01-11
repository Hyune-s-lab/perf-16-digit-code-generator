package com.example.perf16digitcodegenerator

import com.example.perf16digitcodegenerator.refactor.CodeGenerator
import com.example.perf16digitcodegenerator.refactor.strategy.LegacyWithJavaRandomStrategy
import com.example.perf16digitcodegenerator.refactor.strategy.LegacyWithKotlinRandomStrategy
import com.example.perf16digitcodegenerator.refactor.strategy.LegacyWithThreadLocalRandomStrategy
import com.example.perf16digitcodegenerator.refactor.strategy.UUIDStrategy
import com.example.perf16digitcodegenerator.refactor.strategy.UUIDWithSequenceStrategy
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldBeUpperCase
import io.kotest.matchers.string.shouldNotContain
import java.util.*
import kotlin.system.measureTimeMillis

class PerformanceTest : FunSpec({
    val testSizes = listOf(10_000L, 1_000_000L, 5_000_000L, 10_000_000L)
    val legacyWithThreadLocalRandomGenerator = CodeGenerator(LegacyWithThreadLocalRandomStrategy())
    val legacyWithJavaRandomGenerator = CodeGenerator(LegacyWithJavaRandomStrategy())
    val legacyWithKotlinRandomGenerator = CodeGenerator(LegacyWithKotlinRandomStrategy())
    val uuidGenerator = CodeGenerator(UUIDStrategy())
    val uuidWithSequence = CodeGenerator(UUIDWithSequenceStrategy())

    test("performanceTest") {
        testSizes.forEach {
            println("### $it loop")
            println("### legacyWithThreadLocalRandom  = ${measureTimeMillis(legacyWithThreadLocalRandomGenerator, it)} ms")
            println("### legacyWithJavaRandom         = ${measureTimeMillis(legacyWithJavaRandomGenerator, it)} ms")
            println("### legacyWithKotlinRandom       = ${measureTimeMillis(legacyWithKotlinRandomGenerator, it)} ms")
            println("### uuid                         = ${measureTimeMillis(uuidGenerator, it)} ms")
            println("### uuidWithSequence             = ${measureTimeMillis(uuidWithSequence, it)} ms")
            println("### justUUID                     = ${measureTimeMillis2(it)} ms")
            println("### justUUIDWithSequence         = ${measureTimeMillis3(it)} ms")
            println()
        }
    }
})

fun measureTimeMillis(generator: CodeGenerator, size: Long): Long {
    var codes: Set<String>
    val elapsed = measureTimeMillis {
        codes = generator.generate(size)
    }

    codes.size shouldBe size
    codes.first() shouldNotContain "-"
    codes.first().shouldBeUpperCase()

//    println(codes)
    return elapsed
}

fun measureTimeMillis2(size: Long): Long {
    var codes: List<UUID>
    val elapsed = measureTimeMillis {
        codes = (1..size)
            .map { UUID.randomUUID() }
    }

    codes.size shouldBe size

//    println(codes)
    return elapsed
}

fun measureTimeMillis3(size: Long): Long {
    var codes: List<UUID>
    val elapsed = measureTimeMillis {
        codes = (1..size).asSequence()
            .map { UUID.randomUUID() }
            .toList()
    }

    codes.size shouldBe size

//    println(codes)
    return elapsed
}
