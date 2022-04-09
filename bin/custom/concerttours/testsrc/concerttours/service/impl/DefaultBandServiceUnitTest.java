package concerttours.service.impl;

import concerttours.daos.BandDAO;
import concerttours.model.BandModel;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DefaultBandServiceUnitTest {
    private DefaultBandService bandService;
    private BandDAO bandDAO;
    private BandModel bandModel;

    private static final String BAND_CODE = "Ch00X";
    private static final String BAND_NAME = "Singers All";
    private static final String BAND_HISTORY = "Medieval choir formed in 2001, based in Munich famous for authentic monastic chants";

    @Before
    public void setUp() {
        bandService = new DefaultBandService();
        bandDAO = mock(BandDAO.class);
        bandService.setBandDAO(bandDAO);

        bandModel = new BandModel();
        bandModel.setCode(BAND_CODE);
        bandModel.setName(BAND_NAME);
        bandModel.setAlbumSales(1000L);
        bandModel.setHistory(BAND_HISTORY);
    }

    @Test
    public void testGetAllBands() {
        final List<BandModel> bandModels = Arrays.asList(bandModel);
        when(bandDAO.findBands()).thenReturn(bandModels);

        final List<BandModel> result = bandService.getBands();
        assertEquals("We should find one", 1, result.size());
        assertEquals("And should equals what the mock returned", bandModel, result.get(0));
    }

    @Test
    public void testGetBand() {
        when(bandDAO.findBandsByCode(BAND_CODE)).thenReturn(Collections.singletonList(bandModel));
        final BandModel result = bandService.getBandForCode(BAND_CODE);
        assertEquals("Band should equals() what the mock returned", bandModel, result);
    }
}
