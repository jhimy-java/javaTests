package com.jhimydev.javatests.payments;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class PaymentProcessorTest {

    PaymentGateway paymentGateway;
    PaymentProcessor paymentProcessor;

    @Before
    public void setUp(){
         paymentGateway = Mockito.mock(PaymentGateway.class);
         paymentProcessor = new PaymentProcessor(paymentGateway);
    }


    @Test
    public void payment_is_correct() {
        //preparación del escenario

        Mockito.when(paymentGateway.requestPayment(Mockito.any()))
                .thenReturn(new PaymentResponse(PaymentResponse.PaymentStatus.OK));

        //la llamada al método que queremos probar
        boolean result = paymentProcessor.makePayment(1000);
        //la comprobación de que el resultado es el esperado
        assertTrue(result);
    }

    @Test
    public void payment_is_wrong() {

        Mockito.when(paymentGateway.requestPayment(Mockito.any()))
                .thenReturn(new PaymentResponse(PaymentResponse.PaymentStatus.ERROR));


        boolean result = paymentProcessor.makePayment(1000);

        assertFalse(result);
    }
}