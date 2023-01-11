package com.example.perf16digitcodegenerator.refactor

class CodeGenerator(private val codeGenerationStrategy: CodeGenerationStrategy) {
    fun generate(size: Long): Set<String> = codeGenerationStrategy.generate(size)
}
