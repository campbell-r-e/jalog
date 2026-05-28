package jalog.jlogimpl.remotecallsign.callookreader;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CallookXmlConstantsTest {

    @Test
    void root_toString() {
        assertEquals("callook", CallookXmlConstants.Root.toString());
    }

    @Test
    void status_toString() {
        assertEquals("status", CallookXmlConstants.Status.toString());
    }

    @Test
    void callsign_toString() {
        assertEquals("callsign", CallookXmlConstants.Callsign.toString());
    }

    @Test
    void name_toString() {
        assertEquals("name", CallookXmlConstants.Name.toString());
    }

    @Test
    void address_toString() {
        assertEquals("address", CallookXmlConstants.Address.toString());
    }

    @Test
    void addressLine1_toString() {
        assertEquals("line1", CallookXmlConstants.AddressLine1.toString());
    }

    @Test
    void addressLine2_toString() {
        assertEquals("line2", CallookXmlConstants.AddressLine2.toString());
    }

    @Test
    void latitude_toString() {
        assertEquals("latitude", CallookXmlConstants.Latitude.toString());
    }

    @Test
    void longitude_toString() {
        assertEquals("longitude", CallookXmlConstants.Longitude.toString());
    }

    @Test
    void grantDate_toString() {
        assertEquals("grantdate", CallookXmlConstants.GrantDate.toString());
    }

    @Test
    void expiryDate_toString() {
        assertEquals("expirydate", CallookXmlConstants.ExpiryDate.toString());
    }

    @Test
    void frn_toString() {
        assertEquals("frn", CallookXmlConstants.Frn.toString());
    }

    @Test
    void callsignStatus_toString() {
        assertEquals("status", CallookXmlConstants.CallsignStatus.toString());
    }

    @Test
    void gridSquare_toString() {
        assertEquals("gridsquare", CallookXmlConstants.GridSquare.toString());
    }

    @Test
    void currentCallsignValue_toString() {
        assertEquals("current", CallookXmlConstants.CurrentCallsignValue.toString());
    }

    @Test
    void previousCallsignValue_toString() {
        assertEquals("previous", CallookXmlConstants.PreviousCallsignValue.toString());
    }

    @Test
    void lastActionDate_toString() {
        assertEquals("lastactiondate", CallookXmlConstants.LastActionDate.toString());
    }

    @Test
    void attn_toString() {
        assertEquals("attn", CallookXmlConstants.Attn.toString());
    }

    @Test
    void trustee_toString() {
        assertEquals("trustee", CallookXmlConstants.Trustee.toString());
    }

    @Test
    void callook_toString() {
        assertEquals("callook", CallookXmlConstants.Callook.toString());
    }
}
