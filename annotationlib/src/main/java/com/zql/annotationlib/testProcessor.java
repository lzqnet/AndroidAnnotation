package com.zql.annotationlib;

import com.google.auto.service.AutoService;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.Element;
import javax.tools.Diagnostic;

@SupportedAnnotationTypes(value = {"com.zql.annotationlib.testAnnotation"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class testProcessor extends AbstractProcessor {
    private Messager logger;
    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        logger=processingEnvironment.getMessager();
        logWarning("java init");

    }

//    @Override
//    public SourceVersion getSupportedSourceVersion() {
//        logWarning("getSupportedSourceVersion");
//
//        return SourceVersion.RELEASE_8;
//    }
//
//    @Override
//    public Set<String> getSupportedAnnotationTypes() {
//        logWarning("getSupportedAnnotationTypes");
//
//        Set<String> numbers = new HashSet<String>();
//        numbers.add(testAnnotation.class.getCanonicalName());
//        return numbers;
//    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
// 遍历所有注解元素
        logWarning("java process");

        logWarning("java roundEnvironment="+roundEnvironment.toString());

        for (Element annotatedElement : roundEnvironment.getElementsAnnotatedWith(testAnnotation.class)) {
            analysisAnnotated(annotatedElement);
        }
        return false;
    }
    private void analysisAnnotated(Element classElement){

        testAnnotation annotation = classElement.getAnnotation(testAnnotation.class);
        int id = annotation.test();
        String name = annotation.testString();
        logWarning("id="+id+" name="+name);
    }

    private void logWarning(String msg) {
        logger.printMessage(Diagnostic.Kind.WARNING,"\n [lzqtest java] "+ msg+"\n");
//        logger.printMessage(Diagnostic.Kind.WARNING,"-----------------------\n");

    }


}
