import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import uk.gov.dwp.uc.pairtest.ResponseObject;
import uk.gov.dwp.uc.pairtest.TicketService;
import uk.gov.dwp.uc.pairtest.TicketServiceImpl;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest.Type.*;


public class TicketServiceTests {



    @Test
    public void purchaseTickets() {
        TicketService ticketService = new TicketServiceImpl();
        Long accountId = Long.valueOf(100892093);
        TicketTypeRequest t = new TicketTypeRequest(ADULT,2);
        TicketTypeRequest t2 = new TicketTypeRequest(CHILD,3);
        TicketTypeRequest t3 = new TicketTypeRequest(INFANT,1);
        ResponseObject expectedObject = new ResponseObject(5,70);
        ResponseObject actualObject = ticketService.purchaseTickets(accountId,t,t2,t3);
        assertEquals(expectedObject.toString(), actualObject.toString());
    }


}
