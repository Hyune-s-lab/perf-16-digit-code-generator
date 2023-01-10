package com.example.perf16digitcodegenerator.refactor.strategy

import com.example.perf16digitcodegenerator.refactor.CodeGenerationStrategy
import java.util.*

class UUIDStrategy : CodeGenerationStrategy {
    override fun generate(count: Long): Set<String> =
        (1..count)
            .map { UUID.randomUUID().toString().uppercase().replace("-", "") }
            .toSet()
}
