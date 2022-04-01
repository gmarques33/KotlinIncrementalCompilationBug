@file:OptIn(ExperimentalAnvilApi::class)

package com.gmarques.anvilplugin

import com.google.common.truth.Truth.assertThat
import com.squareup.anvil.annotations.ExperimentalAnvilApi
import com.squareup.anvil.compiler.internal.testing.compileAnvil
import com.tschuchort.compiletesting.KotlinCompilation.ExitCode.OK
import org.junit.Test

class MyCodeGeneratorTest {

    @Test
    fun `It will fail with StackOverflowError`() {

        val result = compileAnvil(
            """
            abstract class OuterClass {
              interface InnerInterface
            }

            class ClassA : OuterClass {
              class ClassB : InnerInterface
            }
        """.trimIndent(),
        )

        assertThat(result.exitCode).isEqualTo(OK)
    }
}
