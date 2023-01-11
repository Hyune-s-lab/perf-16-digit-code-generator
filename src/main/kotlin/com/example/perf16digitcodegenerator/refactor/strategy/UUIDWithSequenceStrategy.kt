package com.example.perf16digitcodegenerator.refactor.strategy

import com.example.perf16digitcodegenerator.refactor.CodeGenerationStrategy
import java.util.*

class UUIDWithSequenceStrategy : CodeGenerationStrategy {
    override fun generate(size: Long): Set<String> =
        (1..size).asSequence()
            .map { UUID.randomUUID().toString().uppercase().replace("-", "") }
            .toSet()
}
