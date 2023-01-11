package com.example.perf16digitcodegenerator.refactor.strategy

import com.example.perf16digitcodegenerator.refactor.CodeGenerationStrategy
import java.util.*

class UUIDStrategy : CodeGenerationStrategy {
    override fun generate(size: Long): Set<String> =
        (1..size)
            .map { UUID.randomUUID().toString().uppercase().replace("-", "") }
            .toSet()
}
