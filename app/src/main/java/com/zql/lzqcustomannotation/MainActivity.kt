package com.zql.lzqcustomannotation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zql.annotationkotlinlib.KotlinAnnotation
import com.zql.annotationlib.testAnnotation

@testAnnotation(test=6,testString = "kotlin mainactivity1")
@KotlinAnnotation(test = 1, testString = "kotlin mainactivity1")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}