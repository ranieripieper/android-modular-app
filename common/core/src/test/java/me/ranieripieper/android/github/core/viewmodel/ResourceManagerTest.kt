package me.ranieripieper.android.github.core.viewmodel

import android.content.Context
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ResourceManagerTest {

    @Mock
    private lateinit var context: Context

    private lateinit var resourceManager: ResourceManager

    @Before
    fun setup() {
        resourceManager = ResourceManager(context)
    }

    @Test
    fun `test getString without arguments`() {
        val resourceId = 1
        val value = "value"
        Mockito.`when`(context.getString(resourceId)).thenReturn(value)

        val result = resourceManager.getString(resourceId)

        Assert.assertEquals(value, result)
    }

    @Test
    fun `test getString with arguments`() {

        val resourceId = 1
        val arg = "arg"
        val value = "value $arg"
        Mockito.`when`(context.getString(resourceId, arg)).thenReturn(value)

        val result = resourceManager.getString(resourceId, arg)

        Assert.assertEquals(value, result)
    }
}