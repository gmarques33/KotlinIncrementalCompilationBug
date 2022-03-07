package com.gmarques.anvilplugin

import com.google.auto.service.AutoService
import com.squareup.anvil.annotations.ExperimentalAnvilApi
import com.squareup.anvil.compiler.api.AnvilContext
import com.squareup.anvil.compiler.api.CodeGenerator
import com.squareup.anvil.compiler.api.GeneratedFile
import com.squareup.anvil.compiler.api.createGeneratedFile
import com.squareup.anvil.compiler.internal.classesAndInnerClass
import com.squareup.anvil.compiler.internal.getKtClassOrObjectOrNull
import com.squareup.anvil.compiler.internal.requireFqName
import com.squareup.anvil.compiler.internal.requireTypeReference
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
            .classesAndInnerClass(module)
            .filter { it.name == "MyClass" }
            .map { myClass ->
                val typeReference =
                    myClass.primaryConstructor!!.valueParameters.first()
                        .requireTypeReference(module)

                val fqName =
                    typeReference.requireFqName(module)

                val ktClass =
                    module.getKtClassOrObjectOrNull(fqName)

                if (ktClass == null) {
                    throw Exception("Should not be null!")
                }

                createGeneratedFile(
                    codeGenDir = codeGenDir,
                    packageName = "com.gmarques.incremental",
                    fileName = "MyGeneratedClass.kt",
                    content = """
                        package com.gmarques.incremental
                        
                        class MyGeneratedClass { }
                    """.trimIndent()
                )
            }.toList()

    }
}