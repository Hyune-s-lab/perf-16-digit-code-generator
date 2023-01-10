package com.example.perf16digitcodegenerator.refactor

class CodeGenerator(private val codeGenerationStrategy: CodeGenerationStrategy) {
    fun generate(count: Long): Set<String> = codeGenerationStrategy.generate(count)
}
