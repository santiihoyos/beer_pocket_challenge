package com.santiihoyos.beers.data.datasource

import com.santiihoyos.beers.data.datasource.impl.RestBeersDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.io.File
import java.util.*

@RunWith(JUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class RestBeerDataSourceImplTest {

    /**
     * DISCLAIMER: api calls wont test because it's should be tested by backend team.
     */

    @Test
    fun `get test retrofit instance returns ok`() {
        runTest {
            val instance = RestBeersDataSource.getInstance("https://api.punkapi.com/v2/")
            val resquestOk = instance.getBeers( 1, 3)
            assert(resquestOk.count() == 3)
        }
    }

    //Test for calls..

    //Maybe extract interceptors to test...
}