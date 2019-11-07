package com.example.helloandroid

import com.example.helloandroid.persistence.ConstraintViolationException
import com.example.helloandroid.persistence.Verzicht
import com.example.helloandroid.persistence.VerzichtDao
import org.assertj.core.api.Assertions.*
import org.junit.Test

import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.runners.MockitoJUnitRunner
import java.time.LocalDateTime

@RunWith(MockitoJUnitRunner::class)
class VerzichtServiceTest {

    @InjectMocks
    private lateinit var testCandidate: VerzichtService

    @Mock
    private lateinit var verzichtDao: VerzichtDao

    @Test
    fun testAddNewVerzicht_VerzichtDoesNotExist_CreatesVerzicht() {
        val newVerzicht = Verzicht(VERZICHT_NAME,0, LocalDateTime.now())

        testCandidate.addNewVerzicht(newVerzicht)

        verify(verzichtDao).insertVerzicht(newVerzicht)
    }

    @Test
    fun testAddNewVerzicht_VerzichtDoesExist_ThrowsException() {
        `when`(verzichtDao.findByName(VERZICHT_NAME))//
            .thenReturn(Verzicht(VERZICHT_NAME,0, LocalDateTime.now().minusDays(1)))

        assertThatThrownBy { testCandidate.addNewVerzicht(Verzicht(VERZICHT_NAME,0, LocalDateTime.now())) }//
            .isInstanceOf(ConstraintViolationException::class.java)

    }

    companion object {
        private const val VERZICHT_NAME = "Chocolate"
    }
}