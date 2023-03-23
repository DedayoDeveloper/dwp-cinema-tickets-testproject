import org.junit.Test;
import uk.gov.dwp.uc.pairtest.TicketService;
import uk.gov.dwp.uc.pairtest.TicketServiceImpl;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

import static uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest.Type.*;


public class TicketServiceTests {

    @Test
    public void purchaseTickets() {
        TicketService ticketService = new TicketServiceImpl();
        Long accountId = Long.valueOf(100);
        TicketTypeRequest t = new TicketTypeRequest(ADULT,2);
        TicketTypeRequest t2 = new TicketTypeRequest(CHILD,2);
        TicketTypeRequest t3 = new TicketTypeRequest(INFANT,1);
        ticketService.purchaseTickets(accountId,t,t2,t3);
    }


}
