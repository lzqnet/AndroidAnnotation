package com.zql.annotationkotlinlib

import com.google.auto.service.AutoService
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

@SupportedAnnotationTypes(value = ["com.zql.annotationkotlinlib.KotlinAnnotation"])
@AutoService(Processor::class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedOptions()
class kotlinProcessor: AbstractProcessor() {
    private var logger: Messager? = null

    override fun init(p0: ProcessingEnvironment?) {
        super.init(p0)
        logger = p0?.getMessager()
        logWarning("kotlinProcessor init")

    }

//    override fun getSupportedAnnotationTypes(): MutableSet<String> {
//        return mutableSetOf(KotlinAnnotation::class.java.canonicalName)
//
//    }

    override fun process(p0: MutableSet<out TypeElement>?, p1: RoundEnvironment?): Boolean {
        logWarning("kotlin process")
        logWarning("kotlin roundEnvironment=" + p1.toString())

        for (annotatedElement in p1?.getElementsAnnotatedWith(KotlinAnnotation::class.java)!!) {
            analysisAnnotated(annotatedElement)
        }
        return true
    }
    private fun analysisAnnotated(classElement: Element) {
        val annotation: KotlinAnnotation =
            classElement.getAnnotation(KotlinAnnotation::class.java)
        val id: Int = annotation.test
        val name: String = annotation.testString
        logWarning("id=$id name=$name")
    }
    private fun logWarning(msg: String) {
        logger!!.printMessage(Diagnostic.Kind.WARNING, "\n[lzqtest][kotlin] $msg \n")
//        logger!!.printMessage(Diagnostic.Kind.WARNING, " \n  ")

    }

}