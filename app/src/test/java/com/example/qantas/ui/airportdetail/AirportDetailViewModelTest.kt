package com.example.qantas.ui.airportdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.qantas.domain.AirportListUseCase
import com.example.qantas.model.Airport
import com.example.qantas.model.City
import com.example.qantas.model.Country
import com.example.qantas.model.Location
import com.example.qantas.model.Region
import com.example.qantas.model.State
import io.mockk.coEvery
import io.mockk.mockk
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class AirportDetailViewModelTest {
  @get:Rule
  val rule = InstantTaskExecutorRule()

  private lateinit var airport: Airport
  private lateinit var mLifecycle: LifecycleOwner
  private var airportDetailViewModel: AirportDetailViewModel?= null

  @Mock
  var airportNameLiveDataObserver: Observer<String>? = null

  @Mock
  var countryNameLiveDataObserver: Observer<String>? = null

  @Mock
  var timeZoneLiveDataObserver: Observer<String>? = null

  @Mock
  var addressLiveDataObserver: Observer<String>? = null

  @Before
  fun setup(){
    RxAndroidPlugins.setMainThreadSchedulerHandler { Schedulers.trampoline() }
    RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    MockitoAnnotations.openMocks(this)
    mLifecycle = Mockito.mock(LifecycleOwner::class.java)
    airportDetailViewModel = AirportDetailViewModel()
    airport = Airport("code","Annaba", City("Annaba","Annaba","Annaba"), Country("Annaba","Annaba"),
      true,true,true,
      Location(98,98.89,"east",123.32,43.23,"north",45.34),true,
      Region("Annaba","Annaba"),
      true, State("Annaba","Annaba")
    )
    airportNameLiveDataObserver?.let {
      airportDetailViewModel?.airportNameLiveData?.observeForever(
        it
      )
    }
    countryNameLiveDataObserver?.let {
      airportDetailViewModel?.countryNameLiveData?.observeForever(
        it
      )
    }
    timeZoneLiveDataObserver?.let {
      airportDetailViewModel?.timeZoneLiveData?.observeForever(
        it
      )
    }
    addressLiveDataObserver?.let {
      airportDetailViewModel?.addressLiveData?.observeForever(
        it
      )
    }

  }

  @Test
  fun prepareDetailScreenDataTest(){
    airportDetailViewModel?.prepareDetailScreenData(airport)
    Mockito.verify<Observer<String>>(airportNameLiveDataObserver).onChanged(ArgumentMatchers.any())
    Mockito.verify<Observer<String>>(countryNameLiveDataObserver).onChanged(ArgumentMatchers.any())
    Mockito.verify<Observer<String>>(timeZoneLiveDataObserver).onChanged(ArgumentMatchers.any())
    Mockito.verify<Observer<String>>(addressLiveDataObserver).onChanged(ArgumentMatchers.any())
  }

  @After
  fun tearDown(){
    Mockito.reset(airportNameLiveDataObserver)
    Mockito.reset(countryNameLiveDataObserver)
    Mockito.reset(timeZoneLiveDataObserver)
    Mockito.reset(addressLiveDataObserver)
  }
}