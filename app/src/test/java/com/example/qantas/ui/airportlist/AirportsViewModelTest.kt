package com.example.qantas.ui.airportlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.qantas.data.ApiClient
import com.example.qantas.domain.AirportListRepository
import com.example.qantas.domain.AirportListUseCase
import com.example.qantas.model.*
import io.mockk.coEvery
import io.mockk.mockk
import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.observers.TestObserver
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.reset
import org.mockito.MockitoAnnotations

class AirportsViewModelTest{

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var mLifecycle: LifecycleOwner

        private var airportsViewModel: AirportsViewModel?= null
        private var client: ApiClient? = null
        private lateinit var airport: Airport
        private var airportListUseCase: AirportListUseCase? = null
        private var airportListRepository: AirportListRepository? = null

    @Mock
    var airportsListLiveDataObserver: Observer<List<Airport>>? = null


    @Before
    fun setup(){
        RxAndroidPlugins.setMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        MockitoAnnotations.openMocks(this)
        mLifecycle = mock(LifecycleOwner::class.java)
        client = mockk()
        airportListRepository = mockk()
        airportListUseCase = AirportListUseCase(airportListRepository!!)
        airportsViewModel = AirportsViewModel()
        airportsViewModel?.airportListUseCase = airportListUseCase!!
        airport = Airport("code","Annaba", City("Annaba","Annaba","Annaba"), Country("Annaba","Annaba"),
            true,true,true,
            Location(98,98.89,"east",123.32,43.23,"north",45.34),true,
            Region("Annaba","Annaba"),
            true, State("Annaba","Annaba")
        )
        airportsListLiveDataObserver?.let {
            airportsViewModel?.airportsListLiveData?.observeForever(
                it
            )
        }
    }

    @Test
    fun testGetAirports(){
        val airports = listOf(airport)
        val obs = Single.just(airports)
        val observer:TestObserver<List<Airport>> = TestObserver.create()
        obs.subscribe(observer)
        observer.assertNoErrors()
            .onNext(airports)
        coEvery { airportListUseCase?.executeGetAirPortList() } returns obs
        airportsViewModel?.getAirports()
        Mockito.verify<Observer<List<Airport>>>(airportsListLiveDataObserver).onChanged(ArgumentMatchers.any())

    }
    

    @Test
    fun testGetAirportsError(){
        val airports = listOf(airport)
        val obs: Single<List<Airport>> = Single.create { e: SingleEmitter<List<Airport>> ->        e.tryOnError(Throwable("mock_error"))
        }
        val observer:TestObserver<List<Airport>> = TestObserver.create()

        observer.onNext(airports)
        observer.onError(Throwable("mock_error"))
        observer.onComplete()
        obs.subscribe(observer)

        coEvery { airportListRepository?.getAirports() } returns obs
        airportsViewModel?.getAirports()
        Assert.assertEquals("mock_error",airportsViewModel?.errorLiveData?.value)
    }

    @After
    fun tearDown(){
        reset(airportsListLiveDataObserver)
    }
}