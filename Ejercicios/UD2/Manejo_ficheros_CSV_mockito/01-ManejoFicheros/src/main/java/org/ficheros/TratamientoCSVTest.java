package org.ficheros;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TratamientoCSVTest {

    @Mock
    private Funko funko1;
    @Mock
    private Funko funko2;
    @Mock
    private Funko funko3;
    @Mock
    private Funko funko4;
    @Spy
    private ArrayList<Funko> funkos;
    @InjectMocks
    private FunkoList list;

    @BeforeEach
    public void setup(){
        funkos.add(funko1);
        funkos.add(funko2);
        funkos.add(funko3);
        funkos.add(funko4);
    }

    @Test
    public void mostExpensiveFunkoTest(){
        double val1 = 1000.0, val2 = 2000.0, val3 = 100.0, val4 = 100.0;
        when(funko1.getPrice()).thenReturn(val1);
        when(funko2.getPrice()).thenReturn(val2);
        when(funko3.getPrice()).thenReturn(val3);
        when(funko4.getPrice()).thenReturn(val4);

        assertEquals(val2, list.mostExpensiveFunko().getPrice());

        verify(funko1, atLeastOnce()).getPrice();
        verify(funko2, atLeastOnce()).getPrice();
        verify(funko3, atLeastOnce()).getPrice();
        verify(funko4, atLeastOnce()).getPrice();
    }

    @Test
    public void medianPriceTest(){
        double val1 = 1000.0, val2 = 2000.0, val3 = 100.0, val4 = 100.0;
        when(funko1.getPrice()).thenReturn(val1);
        when(funko2.getPrice()).thenReturn(val2);
        when(funko3.getPrice()).thenReturn(val3);
        when(funko4.getPrice()).thenReturn(val4);

        double sum = val1+val2+val3+val4;

        assertEquals((sum/ funkos.size()), list.medianPrice());

        verify(funko1, atLeastOnce()).getPrice();
        verify(funko2, atLeastOnce()).getPrice();
        verify(funko3, atLeastOnce()).getPrice();
        verify(funko4, atLeastOnce()).getPrice();
    }

    @Test
    public void funkosByModelTest(){
        when(funko1.getModel()).thenReturn("MARVEL");
        when(funko2.getModel()).thenReturn("MARVEL");
        when(funko3.getModel()).thenReturn("MARVEL");
        when(funko4.getModel()).thenReturn("MARVEL");

        assertEquals(4, list.funkosByModel().get("MARVEL").size());

        verify(funko1, atLeastOnce()).getModel();
        verify(funko2, atLeastOnce()).getModel();
        verify(funko3, atLeastOnce()).getModel();
        verify(funko4, atLeastOnce()).getModel();
    }

    @Test
    public void funkosReleasedIn2023Test(){
        when(funko1.getReleaseDate()).thenReturn(LocalDate.of(2023, 1, 1));
        when(funko2.getReleaseDate()).thenReturn(LocalDate.of(2023, 1, 1));
        when(funko3.getReleaseDate()).thenReturn(LocalDate.of(2023, 1, 1));
        when(funko4.getReleaseDate()).thenReturn(LocalDate.of(2023, 1, 1));

        assertEquals(4, list.funkosReleasedIn2023().size());

        verify(funko1, atLeastOnce()).getReleaseDate();
        verify(funko2, atLeastOnce()).getReleaseDate();
        verify(funko3, atLeastOnce()).getReleaseDate();
        verify(funko4, atLeastOnce()).getReleaseDate();
    }
}