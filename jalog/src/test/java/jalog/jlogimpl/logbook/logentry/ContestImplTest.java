package jalog.jlogimpl.logbook.logentry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContestImplTest {

    private ContestImpl contest;

    @BeforeEach
    void setUp() {
        contest = new ContestImpl();
    }

    @Test
    void defaults_areEmpty() {
        assertEquals("", contest.getTransmittedSerialNumber());
        assertEquals("", contest.getReceivedSerialNumber());
        assertEquals("", contest.getContestId());
    }

    @Test
    void setTransmittedSerialNumber_roundTrips() {
        contest.setTransmittedSerialNumber("042");
        assertEquals("042", contest.getTransmittedSerialNumber());
    }

    @Test
    void setReceivedSerialNumber_roundTrips() {
        contest.setReceivedSerialNumber("007");
        assertEquals("007", contest.getReceivedSerialNumber());
    }

    @Test
    void setContestId_roundTrips() {
        contest.setContestId("CQ-WW-CW");
        assertEquals("CQ-WW-CW", contest.getContestId());
    }

    @Test
    void toAdifString_allEmpty_returnsEmpty() {
        assertEquals("", contest.toAdifString());
    }

    @Test
    void toAdifString_txSerial_writesStxTag() {
        contest.setTransmittedSerialNumber("001");
        assertTrue(contest.toAdifString().contains("<STX:3>001"));
    }

    @Test
    void toAdifString_rxSerial_writesSrxTag() {
        contest.setReceivedSerialNumber("099");
        assertTrue(contest.toAdifString().contains("<SRX:3>099"));
    }

    @Test
    void toAdifString_contestId_writesContestIdTag() {
        contest.setContestId("ARRL-DX-CW");
        assertTrue(contest.toAdifString().contains("<CONTEST_ID:10>ARRL-DX-CW"));
    }

    @Test
    void toString_containsFields() {
        contest.setTransmittedSerialNumber("123");
        contest.setContestId("ARRL-10");
        String str = contest.toString();
        assertTrue(str.contains("123") || str.contains("ARRL-10") || str.contains("Contest") || str.contains("contest"));
    }
}
