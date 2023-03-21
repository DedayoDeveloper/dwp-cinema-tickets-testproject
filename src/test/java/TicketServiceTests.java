import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import uk.gov.dwp.uc.pairtest.TicketService;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest.Type.ADULT;

@RunWith(MockitoJUnitRunner.class)
public class TicketServiceTests {

    @Mock
    TicketService ticketService;


    @Test
    public void getTicketPrice() {
//        when(ticketService.(ADULT)).thenReturn("ADULT");
//        assertEquals("ADULT", ticketService.getTicketType(ADULT));
    }
}
