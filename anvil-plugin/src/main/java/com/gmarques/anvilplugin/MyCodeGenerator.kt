package com.gmarques.anvilplugin

import com.google.auto.service.AutoService
import com.squareup.anvil.annotations.ExperimentalAnvilApi
import com.squareup.anvil.compiler.api.AnvilContext
import com.squareup.anvil.compiler.api.CodeGenerator
import com.squareup.anvil.compiler.api.GeneratedFile
import com.squareup.anvil.compiler.api.createGeneratedFile
import com.squareup.anvil.compiler.internal.reference.classAndInnerClassReferences
import org.jetbrains.kotlin.descriptors.ModuleDescriptor
import org.jetbrains.kotlin.psi.KtFile
import java.io.File

@ExperimentalAnvilApi
@AutoService(CodeGenerator::class)
class MyCodeGenerator : CodeGenerator {
    override fun isApplicable(context: AnvilContext): Boolean = true

    override fun generateCode(
        codeGenDir: File,
        module: ModuleDescriptor,
        projectFiles: Collection<KtFile>,
    ): Collection<GeneratedFile> {

        return projectFiles
            .classAndInnerClassReferences(module)
            .filter { it.shortName == "ClassB" }
            .mapNotNull { myClass ->

                val directSuperClassReferences = myClass.directSuperClassReferences()

                null
            }.toList()

    }
}