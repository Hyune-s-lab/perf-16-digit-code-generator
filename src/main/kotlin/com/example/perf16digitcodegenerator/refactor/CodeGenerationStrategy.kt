package com.example.perf16digitcodegenerator.refactor

interface CodeGenerationStrategy {
    fun generate(size: Long): Set<String>
}
