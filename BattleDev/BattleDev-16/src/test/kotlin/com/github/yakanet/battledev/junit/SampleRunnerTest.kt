package com.github.yakanet.battledev.junit

import com.github.yakanet.battledev.junit.executor.KotlinExecutor
import com.github.yakanet.battledev.junit.sample.InputOutputSample
import com.github.yakanet.battledev.junit.sample.loadSamples
import fr.vco.codecontest.battledev16.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import java.util.stream.Stream


const val ANSI_RESET = "\u001B[0m"
const val ANSI_BLUE = "\u001B[34m"

class SampleRunnerTest {

    /**
     * Configure the downloaded zip file location
     */
    private val zipSamplePath = "download\\sample-aDYybko0cXc4bm9GendLNkIwSkJYUT09Ojqgl8NyhOxIq9MdJq32k8IZ.zip"

    /**
     * File being tested with the Executor wrapper.
     * You can comment any executor you are not using.
     */
    private val executors = listOf(
          //  KotlinExecutor(Exo1::main),
          //  KotlinExecutor(Exo2::main),
         //   KotlinExecutor(Exo3::main),
            KotlinExecutor(Exo4::main),
//            KotlinExecutor(Exo5::main),
//            KotlinExecutor(Exo6::main),
    )

    /**
     * Display output
     */
    private val showOutput = true

    @TestFactory
    fun sampleCases(): Stream<DynamicTest> {
        return loadSamples(zipSamplePath).map { sample: InputOutputSample ->
            executors.stream().map { executor ->
                val executorName = executor.name
                DynamicTest.dynamicTest("[$executorName] Test " + sample.index) {
                    println("${ANSI_BLUE}[$executorName] Running Test ${sample.index}${ANSI_RESET}")

                    val output = executor.execute(sample.input)
                    if (showOutput) {
                        println(output)
                    }
                    Assertions.assertLinesMatch(sample.output.lines(), output.lines())
                    println("")
                }
            }
        }.flatMap { it }
    }
}
