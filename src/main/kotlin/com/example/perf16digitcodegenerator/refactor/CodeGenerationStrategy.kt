package com.example.perf16digitcodegenerator.refactor

interface CodeGenerationStrategy {
    fun generate(count: Long): Set<String>
}
