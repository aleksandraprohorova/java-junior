package com.acme.edu;

import com.acme.edu.common.Message;
import com.acme.edu.common.Printer;
import com.acme.edu.messages.IntMessage;
import com.acme.edu.messages.StringMessage;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class LoggerControllerTest {
    @Test
    public void shouldFlushWhenLogDifferentTypeMessage() {

        Message stringMessage = mock(StringMessage.class);
        Message intMessage = mock(IntMessage.class);

        when(stringMessage.isSameType(intMessage)).thenReturn(false);
        when(intMessage.isSameType(stringMessage)).thenReturn(false);

        when(stringMessage.getDecoratedString()).thenReturn("string: defaultexample");
        when(intMessage.getDecoratedString()).thenReturn("primitive: 12");

        Printer printer = mock(Printer.class);

        final LoggerController controllerSut = new LoggerController(printer);

        controllerSut.log(stringMessage);
        controllerSut.log(intMessage);
        controllerSut.flush();

        InOrder inOrder = Mockito.inOrder(printer);
        inOrder.verify(printer).print(stringMessage);
        inOrder.verify(printer).print(intMessage);
    }
    @Test
    public void shouldAccumulateWhenLogSameTypeMessage() {

        StringMessage stringMessage = mock(StringMessage.class);
        StringMessage sameTypeMessage = mock(StringMessage.class);
        Printer printer = mock(Printer.class);

        when(stringMessage.isSameType(sameTypeMessage)).thenReturn(true);
        when(stringMessage.accumulate(sameTypeMessage)).thenReturn(null);

        final LoggerController controllerSut = new LoggerController(printer);

        controllerSut.log(stringMessage);
        controllerSut.log(sameTypeMessage);
        controllerSut.flush();

        verify(printer, times(1)).print(stringMessage);
        verifyNoMoreInteractions(printer);
    }

    @Test
    public void shouldNotAccumulateWhenLogDifferentMessages() {

        StringMessage stringMessage = mock(StringMessage.class);
        StringMessage anotherStringMessage = mock(StringMessage.class);

        Printer printer = mock(Printer.class);

        when(stringMessage.isSameType(anotherStringMessage)).thenReturn(true);
        when(stringMessage.accumulate(anotherStringMessage))
                .thenReturn(anotherStringMessage);

        final LoggerController controllerSut = new LoggerController(printer);

        controllerSut.log(stringMessage);
        controllerSut.log(anotherStringMessage);
        controllerSut.flush();

        verify(printer, times( 1)).print(stringMessage);
        verify(printer, times( 1)).print(anotherStringMessage);
    }
}
