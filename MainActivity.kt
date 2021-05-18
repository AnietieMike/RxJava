package com.decagon.anietie.rxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    // In cases where multiple threads are used or data is received from multiple sources,
    // it is best to dispose using CompositeDisposable
    private val disposables = CompositeDisposable()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Flowable handles BackPressure
        val flowable = Flowable.just("Android", "Java", "Node", ".NET", "iOS", "Python")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { value -> println(value) }

        // Observable
        val observable = Observable.just("Blue", "Red", "Green", "White", "Yellow")
            .subscribe { v -> println("Received color: $v") }

        Log.d("FLOWABLE", "onCreate: $flowable")
        Log.d("OBSERVABLE", "onCreate: $observable")

        // Add to the disposables
        disposables.addAll(flowable, observable)
    }
}